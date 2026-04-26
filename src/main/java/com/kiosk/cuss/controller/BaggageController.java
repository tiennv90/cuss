package com.kiosk.cuss.controller;

import com.kiosk.cuss.dto.baggage.UpdateBaggageStatusRequest;
import com.kiosk.cuss.service.baggage.UpdateBaggageService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/baggage")
@AllArgsConstructor
public class BaggageController {

    private final UpdateBaggageService updateBaggageService;

    @PostMapping("/updateStatus/v1")
    public ResponseEntity<Void> updateBaggageStatus(@RequestBody UpdateBaggageStatusRequest request) {
        updateBaggageService.updateBaggageStatusV1(request.baggageIds(), request.status());
        return ResponseEntity.ok().build();
    }


    @PostMapping("/updateStatus/v2")
    public ResponseEntity<Void> updateBaggageStatusV2(@RequestBody UpdateBaggageStatusRequest request) {
        updateBaggageService.updateBaggageStatusV2(request.baggageIds(), request.status());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/updateStatus/v3")
    public ResponseEntity<Void> updateBaggageStatusV3(@RequestBody UpdateBaggageStatusRequest request) {
        updateBaggageService.updateBaggageStatusV3(request.baggageIds(), request.status());
        return ResponseEntity.ok().build();
    }
}
