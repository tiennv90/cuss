package com.kiosk.cuss.service.checkin;

import com.kiosk.cuss.dto.checkin.CheckInDto;
import com.kiosk.cuss.dto.checkin.CheckInLogAppEventDto;
import com.kiosk.cuss.dto.checkin.ChekInLogDto;
import com.kiosk.cuss.dto.response.CheckInResponseDto;
import com.kiosk.cuss.entity.Passenger;
import com.kiosk.cuss.exception.PassengerPnrNotFoundException;
import com.kiosk.cuss.service.baggage.BaggageService;
import com.kiosk.cuss.service.passenger.PassengerService;
import com.kiosk.cuss.service.seat.SeatService;
import com.kiosk.cuss.util.RandomStringUtils;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CheckInService {

    private final PassengerService passengerService;
    private final SeatService seatService;
    private final BaggageService baggageService;
    private final ApplicationEventPublisher applicationEventPublisher;


    public CheckInResponseDto processCheckIn(CheckInDto checkInReq) {
        Passenger passenger = passengerService.getPassengerById(checkInReq.passengerId());
        validatePNR(checkInReq.pnr(), passenger.getPnr());
        seatService.updateSeat(checkInReq, passenger);
        baggageService.registerBaggage(checkInReq.baggage(), passenger);
        writeLogEvent(passenger);
        return new CheckInResponseDto("COMPLETED", checkInReq);
    }

    private void writeLogEvent(Passenger passenger) {
        applicationEventPublisher.publishEvent(new CheckInLogAppEventDto(this,
                ChekInLogDto.builder().passengerId(passenger.getId())
                        .kioskId(RandomStringUtils.getOneRandomString(10)).eventType("CHECK_IN_COMPLETE")
                        .build()));
    }

    private void validatePNR(String pnrRequest, String actualPnr) {
        if (!actualPnr.equalsIgnoreCase(pnrRequest))
            throw new PassengerPnrNotFoundException("Passenger PNR code not found");
    }
}
