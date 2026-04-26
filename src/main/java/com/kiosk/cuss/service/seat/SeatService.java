package com.kiosk.cuss.service.seat;

import com.kiosk.cuss.dto.checkin.CheckInDto;
import com.kiosk.cuss.dto.flight.FlightDto;
import com.kiosk.cuss.dto.seat.SeatDto;
import com.kiosk.cuss.entity.Passenger;
import com.kiosk.cuss.service.flight.FlightService;
import com.kiosk.cuss.util.RandomStringUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SeatService {

    private final FlightService flightService;

    public void updateSeat(CheckInDto checkInDto, Passenger passenger) {
        SeatDto seatDto = checkInDto.seatDto();
        FlightDto flightDto = checkInDto.flightDto();
        if (seatDto != null) {
            passenger.setSeatNumber(seatDto.seatNumber());
        } else {
            passenger.setSeatNumber(RandomStringUtils.getOneRandomString(5));
        }
        flightService.decreaseAvailableSeat(flightDto);
    }
}
