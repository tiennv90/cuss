package com.kiosk.cuss.service.seat;

import com.kiosk.cuss.dto.checkin.CheckInDto;
import com.kiosk.cuss.entity.Passenger;


public interface SeatService {
    void updateSeat(CheckInDto checkInDto, Passenger passenger);
}
