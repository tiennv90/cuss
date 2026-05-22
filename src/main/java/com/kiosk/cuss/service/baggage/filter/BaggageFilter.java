package com.kiosk.cuss.service.baggage.filter;

import com.kiosk.cuss.dto.baggage.SearchBaggageRequest;
import com.kiosk.cuss.entity.Baggage;
import org.springframework.data.jpa.domain.Specification;

public interface BaggageFilter<T extends Baggage, C extends SearchBaggageRequest> {
    Specification<T> apply(C request);
}
