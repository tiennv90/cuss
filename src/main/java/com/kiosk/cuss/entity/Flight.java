package com.kiosk.cuss.entity;

import com.kiosk.cuss.core.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Flight extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    private String flightCode;

    private LocalDateTime departureTime;

    private Integer availableSeats;

    @OneToMany(mappedBy = "flight")
    Set<Passenger> passengers = new HashSet<>();
}
