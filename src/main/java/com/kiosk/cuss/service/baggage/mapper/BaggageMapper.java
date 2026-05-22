package com.kiosk.cuss.service.baggage.mapper;

import com.kiosk.cuss.dto.baggage.BaggageDto;
import com.kiosk.cuss.entity.Baggage;
import org.springframework.stereotype.Component;

@Component
public class BaggageMapper {

    public BaggageDto toDto(Baggage baggage) {
        return new BaggageDto(baggage.getId(),
                baggage.getPassenger().getId(),
                baggage.getWeight(),
                baggage.getTagCode());
    }
}
