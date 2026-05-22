package com.kiosk.cuss.dto.baggage;

public record SearchBaggageRequest(String tagCode, String status, Long passengerId) {
}
