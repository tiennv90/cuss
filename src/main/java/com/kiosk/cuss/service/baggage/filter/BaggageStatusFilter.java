package com.kiosk.cuss.service.baggage.filter;

import com.kiosk.cuss.dto.baggage.SearchBaggageRequest;
import com.kiosk.cuss.entity.Baggage;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class BaggageStatusFilter implements BaggageFilter<Baggage, SearchBaggageRequest> {

    @Override
    public Specification<Baggage> apply(SearchBaggageRequest request) {
        return (root, query, criteriaBuilder) -> {
            if (request.tagCode() == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("status"), request.status());
        };
    }
}
