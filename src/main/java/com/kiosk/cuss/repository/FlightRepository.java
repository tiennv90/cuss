package com.kiosk.cuss.repository;

import com.kiosk.cuss.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    @Modifying
    @Query("""
            UPDATE Flight f
            SET f.availableSeats = f.availableSeats - 1,
                f.version = f.version + 1
            WHERE f.id = :id
                AND f.version = :version
                AND f.availableSeats > 0
            """)
    int decrementSeat(@Param("id") Long flightId, @Param("version") Long version);
}
