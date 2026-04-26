package com.kiosk.cuss.dto.baggage;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record BaggageDto(BigDecimal weight, String tagCode) {
}
