package com.kiosk.cuss.controller;

import com.kiosk.cuss.dto.checkin.CheckInDto;
import com.kiosk.cuss.dto.response.CheckInResponseDto;
import com.kiosk.cuss.service.checkin.CheckInService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/checkIn")
public class CheckInController {

    private final CheckInService checkInService;

    @PostMapping
    public ResponseEntity<CheckInResponseDto> checkIn(@RequestBody CheckInDto checkInRequest) {
        return ResponseEntity.ok(checkInService.processCheckIn(checkInRequest));
    }
}
