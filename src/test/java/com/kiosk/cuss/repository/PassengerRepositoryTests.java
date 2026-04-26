package com.kiosk.cuss.repository;

import com.kiosk.cuss.entity.Passenger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Collection;


@SpringBootTest
public class PassengerRepositoryTests {

    @Autowired
    private PassengerRepository passengerRepository;

    //@Test
    public void testSaveOnePassenger() {
        Passenger p = Passenger.builder().pnr("LVX2K").seatNumber("ACC1")
                .firstName("David").lastName("James").status("NOT_READY").build();
        passengerRepository.save(p);
    }

    @Test
    public void testFindPassengersHaveStatusUpdateLast24h() {
        LocalDateTime now = LocalDateTime.of(2025, 10,13, 23,0,0);

        Collection<Passenger> passengers =
                passengerRepository.findPassengersHaveStatusUpDateLast24h(now.minusDays(1), now);
        Assertions.assertEquals(1, passengers.size());
    }
}
