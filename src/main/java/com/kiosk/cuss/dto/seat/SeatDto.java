package com.kiosk.cuss.dto.seat;

import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record SeatDto(@Size(max = 5) String seatNumber) {
}
