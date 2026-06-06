package dev.abazure.bookingservice.service;

import dev.abazure.bookingservice.client.inventory.InventoryClient;
import dev.abazure.bookingservice.dto.BookingRequest;
import dev.abazure.bookingservice.dto.BookingResponse;
import dev.abazure.bookingservice.exception.ConflictException;
import dev.abazure.bookingservice.exception.ErrorCode;
import dev.abazure.bookingservice.exception.NotFoundException;
import dev.abazure.bookingservice.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookingService {
    private final CustomerRepository customerRepository;
    private final InventoryClient inventoryClient;

    public BookingResponse createBooking(BookingRequest request) {
        customerRepository.findById(request.userId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.CUSTOMER_NOT_FOUND));

        var event = inventoryClient.getEvent(request.eventId());
        if (event.capacity() < request.ticketCount()) {
            throw new ConflictException(ErrorCode.INSUFFICIENT_STOCK);
        }

        return BookingResponse.builder().build();

    }
}