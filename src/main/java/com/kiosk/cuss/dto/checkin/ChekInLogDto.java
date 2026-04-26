package com.kiosk.cuss.dto.checkin;

import lombok.Builder;

@Builder
public record ChekInLogDto(Long passengerId, String eventType, String kioskId) {
}
