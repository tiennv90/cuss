package com.kiosk.cuss.service.passenger;

import com.kiosk.cuss.entity.Passenger;
import com.kiosk.cuss.exception.PassengerNotFoundException;
import com.kiosk.cuss.repository.PassengerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PassengerService {

    private final PassengerRepository passengerRepository;

    public Passenger getPassengerById(Long id) {
        Optional<Passenger> p = passengerRepository.findById(id);
        if (p.isEmpty()) {
            throw new PassengerNotFoundException(String.format("Passenger Id {} not found", id));
        }
        return p.get();
    }
}
