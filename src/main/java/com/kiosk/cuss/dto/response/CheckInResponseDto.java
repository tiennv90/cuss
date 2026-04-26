package com.kiosk.cuss.dto.response;

import com.kiosk.cuss.dto.checkin.CheckInDto;
import lombok.Builder;

@Builder
public record CheckInResponseDto(String status, CheckInDto checkInDto) {
}
