package com.kiosk.cuss.entity;

import com.kiosk.cuss.core.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(uniqueConstraints = {
       @UniqueConstraint(columnNames = {"flight_id", "seat_number"})
})
public class Passenger extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String pnr;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_id")
    private Flight flight;

    @Column(length = 5)
    private String seatNumber;

    @Column(length = 20)
    private String status;

    @OneToMany(mappedBy = "passenger")
    Set<Baggage> baggage = new HashSet<>();

}
