package com.kiosk.cuss.entity;

import com.kiosk.cuss.core.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Baggage extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "baggage_seq")
    @SequenceGenerator(name = "baggage_seq",sequenceName = "baggage_seq", allocationSize = 5)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "passenger_id")
    private Passenger passenger;

    @Column(precision = 5, scale = 2)
    private BigDecimal weight;

    @Column(unique = true, length = 20)
    private String tagCode;

    @Column(length = 20)
    private String status;

}
