package com.kiosk.cuss.repository;

import com.kiosk.cuss.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collection;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long> {

    @Query("Select p From Passenger p where p.updateTimestamp between :from and :to")
    public Collection<Passenger> findPassengersHaveStatusUpDateLast24h(@Param("from") LocalDateTime from, @Param("to") LocalDateTime to);

}
