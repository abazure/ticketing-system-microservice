package dev.abazure.inventoryservice.service;

import dev.abazure.inventoryservice.dto.EventInventoryResponse;
import dev.abazure.inventoryservice.dto.VenueInventoryResponse;
import dev.abazure.inventoryservice.entity.Event;
import dev.abazure.inventoryservice.exception.ErrorCode;
import dev.abazure.inventoryservice.exception.NotFoundException;
import dev.abazure.inventoryservice.repository.EventRepository;
import dev.abazure.inventoryservice.repository.VenueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final EventRepository eventRepository;
    private final VenueRepository venueRepository;

    public List<EventInventoryResponse> getAllEvents() {

        return eventRepository.findAll()
                .stream()
                .map(event -> EventInventoryResponse.builder()
                        .id(event.getId())
                        .event(event.getName())
                        .ticketPrice(event.getTicketPrice())
                        .venue(event.getVenue())
                        .capacity(event.getTotalCapacity())
                        .build())
                .toList();
    }

    public VenueInventoryResponse getVenueInformation(Long id) {
        return venueRepository.findById(id)
                .map(venue -> VenueInventoryResponse.builder()
                        .venueId(venue.getId())
                        .venueName(venue.getName())
                        .totalCapacity(venue.getTotalCapacity())
                        .build())
                .orElseThrow(() -> new NotFoundException(ErrorCode.VENUE_NOT_FOUND));
    }

    public EventInventoryResponse getEventInventory(Long id) {
        return eventRepository.findById(id)
                .map(event -> EventInventoryResponse.builder()
                        .id(event.getId())
                        .event(event.getName())
                        .capacity(event.getTotalCapacity())
                        .venue(event.getVenue())
                        .build())
                .orElseThrow(() -> new NotFoundException(ErrorCode.EVENT_NOT_FOUND));
    }

    public void updateEventCapacity(final Long eventId, final Long ticketsBooked) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new NotFoundException(ErrorCode.EVENT_NOT_FOUND));
        event.setLeftCapacity(event.getLeftCapacity() - ticketsBooked);
        eventRepository.save(event);
    }
}

