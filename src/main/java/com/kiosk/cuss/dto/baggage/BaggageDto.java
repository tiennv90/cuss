package com.kiosk.cuss.dto.baggage;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record BaggageDto(Long id, Long passengerId, BigDecimal weight, String tagCode) {
}
