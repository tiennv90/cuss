package com.kiosk.cuss.service.baggage;

import com.kiosk.cuss.dto.baggage.SearchBaggageRequest;
import com.kiosk.cuss.entity.Baggage;
import com.kiosk.cuss.service.baggage.filter.BaggageFilter;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class BaggageSpecificBuilderComponent {

    private final List<BaggageFilter<Baggage, SearchBaggageRequest>> filters;

    public Specification<Baggage> build(SearchBaggageRequest request) {
        Specification<Baggage> spec = Specification.unrestricted();
        for (BaggageFilter<Baggage, SearchBaggageRequest> filter : filters) {
            Specification<Baggage> s = filter.apply(request);
            if (s != null) {
                spec = spec.and(s);
            }
        }
        return spec;
    }
}
