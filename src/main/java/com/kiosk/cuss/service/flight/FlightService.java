package com.kiosk.cuss.service.flight;

import com.kiosk.cuss.dto.flight.FlightDto;
import com.kiosk.cuss.entity.Flight;
import com.kiosk.cuss.exception.FlightNotFoundException;
import com.kiosk.cuss.repository.FlightRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class FlightService {

    private final FlightRepository flightRepository;

    public Flight getFlightById(Long Id) {
        Optional<Flight> flight = flightRepository.findById(Id);
        if (flight.isEmpty())
            throw new FlightNotFoundException(String.format("flight id {} not found", Id));
        return flight.get();
    }

    @Transactional
    public void decreaseAvailableSeat(FlightDto flightDto) {
        Flight flight = getFlightById(flightDto.FlightId());
        flightRepository.decrementSeat(flight.getId(), flight.getVersion());
    }
}
