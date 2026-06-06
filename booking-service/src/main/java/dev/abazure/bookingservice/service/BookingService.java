package dev.abazure.bookingservice.service;

import dev.abazure.bookingservice.client.inventory.InventoryClient;
import dev.abazure.bookingservice.dto.BookingRequest;
import dev.abazure.bookingservice.dto.BookingResponse;
import dev.abazure.bookingservice.event.BookingEvent;
import dev.abazure.bookingservice.exception.ConflictException;
import dev.abazure.bookingservice.exception.ErrorCode;
import dev.abazure.bookingservice.exception.NotFoundException;
import dev.abazure.bookingservice.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookingService {
    private final CustomerRepository customerRepository;
    private final InventoryClient inventoryClient;
    private final KafkaTemplate<String, BookingEvent> kafkaTemplate;

    public BookingResponse createBooking(BookingRequest request) {
        customerRepository.findById(request.userId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.CUSTOMER_NOT_FOUND));

        var event = inventoryClient.getEvent(request.eventId());
        if (event.capacity() < request.ticketCount()) {
            throw new ConflictException(ErrorCode.INSUFFICIENT_STOCK);
        }

        var bookingEvent = BookingEvent.builder()
                .userId(request.userId())
                .eventId(request.eventId())
                .ticketCount(request.ticketCount())
                .totalPrice(event.ticketPrice().multiply(BigInteger.valueOf(request.ticketCount())))
                .build();

        kafkaTemplate.send("booking", bookingEvent);
        log.info("Booking send to kafka : {}", bookingEvent);
        return BookingResponse.builder()
                .userId(bookingEvent.userId())
                .eventId(bookingEvent.eventId())
                .totalCount(bookingEvent.ticketCount())
                .totalPrice(bookingEvent.totalPrice())
                .build();
    }
}