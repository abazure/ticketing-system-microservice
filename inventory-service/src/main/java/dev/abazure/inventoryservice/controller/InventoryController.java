package dev.abazure.inventoryservice.controller;

import dev.abazure.inventoryservice.dto.EventInventoryResponse;
import dev.abazure.inventoryservice.dto.VenueInventoryResponse;
import dev.abazure.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/inventories")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("/events")
    public ResponseEntity<List<EventInventoryResponse>> getAllInventoryEvent() {
        return ResponseEntity.ok(inventoryService.getAllEvents());
    }

    @GetMapping("/venue/{venueId}")
    public ResponseEntity<VenueInventoryResponse> getInventoryByVenueId(@PathVariable Long venueId) {
        return ResponseEntity.ok(inventoryService.getVenueInformation(venueId));
    }

    @GetMapping("/events/{eventId}")
    public ResponseEntity<EventInventoryResponse> getInventoryForEvent(@PathVariable Long eventId) {
        return ResponseEntity.ok(inventoryService.getEventInventory(eventId));
    }

    @PutMapping("/events/{eventId}/capacity/{capacity}")
    public ResponseEntity<Void> updateEventCapacity(@PathVariable Long eventId,
                                                    @PathVariable Long capacity) {
        inventoryService.updateEventCapacity(eventId, capacity);
        return ResponseEntity.ok().build();
    }
}
