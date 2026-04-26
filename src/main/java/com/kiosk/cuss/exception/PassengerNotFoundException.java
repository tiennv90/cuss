package com.kiosk.cuss.exception;

import com.kiosk.cuss.service.passenger.PassengerService;

public class PassengerNotFoundException extends RuntimeException {
    public PassengerNotFoundException(String message) {
        super(String.format("Passenger Not Found: {}",message ));
    }
}
