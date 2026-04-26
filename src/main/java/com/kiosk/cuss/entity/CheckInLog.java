package com.kiosk.cuss.entity;

import com.kiosk.cuss.core.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@Setter
public class CheckInLog extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "passenger_id")
    private Passenger passenger;

    private LocalDateTime timestamp;

    @Column(length = 20)
    private String kioskId;

    @Column(length = 50)
    private String eventType;

}
