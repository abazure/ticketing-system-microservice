package dev.abazure.orderservice.service;

import dev.abazure.orderservice.client.inventory.InventoryClient;
import dev.abazure.orderservice.entity.Order;
import dev.abazure.orderservice.event.BookingEvent;
import dev.abazure.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;

    @KafkaListener(topics = "booking", groupId = "order-service")
    public void orderEvent(BookingEvent bookingEvent){
        log.info("Received order event: {}", bookingEvent);
        var order = Order.builder()
                .totalPrice(bookingEvent.totalPrice())
                .quantity(BigInteger.valueOf(bookingEvent.ticketCount()))
                .customerId(bookingEvent.userId())
                .eventId(bookingEvent.eventId())
                .build();
        orderRepository.save(order);
        inventoryClient.updateInventory(order.getEventId(), bookingEvent.ticketCount());
        log.info("Inventory Update Event");
    }
}