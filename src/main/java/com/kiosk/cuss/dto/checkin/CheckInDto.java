package com.kiosk.cuss.dto.checkin;

import com.kiosk.cuss.dto.baggage.BaggageDto;
import com.kiosk.cuss.dto.flight.FlightDto;
import com.kiosk.cuss.dto.seat.SeatDto;
import lombok.Builder;

import java.util.Collection;

@Builder
public record CheckInDto(
        Long passengerId,
        String pnr,
        FlightDto flightDto,
        SeatDto seatDto,
        Collection<BaggageDto> baggage) {

}
