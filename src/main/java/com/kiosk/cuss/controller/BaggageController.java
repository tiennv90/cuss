package com.kiosk.cuss.controller;

import com.kiosk.cuss.dto.baggage.BaggageDto;
import com.kiosk.cuss.dto.baggage.SearchBaggageRequest;
import com.kiosk.cuss.dto.baggage.UpdateBaggageStatusRequest;
import com.kiosk.cuss.service.baggage.BaggageSearchService;
import com.kiosk.cuss.service.baggage.UpdateBaggageService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/baggage")
@AllArgsConstructor
public class BaggageController {

    private final UpdateBaggageService updateBaggageService;
    private final BaggageSearchService baggageSearchService;

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

    @GetMapping("/search")
    public Page<BaggageDto> searchBaggage(SearchBaggageRequest searchBaggageRequest,
                                          @RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "5") int size) {
        Pageable pageRequest = PageRequest.of(page,size);
        return baggageSearchService.search(searchBaggageRequest, pageRequest);
    }
}
