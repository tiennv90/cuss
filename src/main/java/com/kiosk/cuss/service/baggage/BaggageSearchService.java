package com.kiosk.cuss.service.baggage;

import com.kiosk.cuss.dto.baggage.BaggageDto;
import com.kiosk.cuss.dto.baggage.SearchBaggageRequest;
import com.kiosk.cuss.entity.Baggage;
import com.kiosk.cuss.repository.BaggageRepository;
import com.kiosk.cuss.service.baggage.mapper.BaggageMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BaggageSearchService {

    private final BaggageSearchQueryService baggageSearchQueryService;
    private final BaggageRepository baggageRepository;
    private final BaggageMapper baggageMapper;

    public Page<BaggageDto> search(SearchBaggageRequest searchBaggageRequest, Pageable pageRequest) {
        var spec = baggageSearchQueryService.buildSearchQuery(searchBaggageRequest);
        return fetchResults(spec, pageRequest);

    }

    private Page<BaggageDto> fetchResults(Specification<Baggage> spec, Pageable pageRequest) {
         return baggageRepository.findAll(spec, pageRequest).map(baggageMapper::toDto);
    }
}
