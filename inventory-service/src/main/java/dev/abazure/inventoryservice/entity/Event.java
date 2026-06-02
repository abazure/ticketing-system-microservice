package dev.abazure.inventoryservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long totalCapacity;
    private Long leftCapacity;
    private BigInteger ticketPrice;

    @ManyToOne
    @JoinColumn(name = "venue_id")
    private Venue venue;
}
