package com.kiosk.cuss.service.baggage;

import com.kiosk.cuss.dto.baggage.SearchBaggageRequest;
import com.kiosk.cuss.entity.Baggage;

import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BaggageSearchQueryService {

    private final BaggageSpecificBuilderComponent baggageSpecificBuilderComponent;

    public Specification<Baggage> buildSearchQuery(SearchBaggageRequest request) {
        return baggageSpecificBuilderComponent.build(request);
    }
}
