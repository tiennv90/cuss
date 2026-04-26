package com.kiosk.cuss.dto.baggage;

import java.util.List;

public record UpdateBaggageStatusRequest(List<Long> baggageIds, String status) {
}
