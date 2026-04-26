package com.kiosk.cuss.service.baggage;

import com.kiosk.cuss.dto.baggage.BaggageDto;
import com.kiosk.cuss.entity.Baggage;
import com.kiosk.cuss.entity.Passenger;
import com.kiosk.cuss.repository.BaggageRepository;
import com.kiosk.cuss.util.RandomStringUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BaggageService {

    private final BaggageRepository baggageRepository;


    public void registerBaggage(Collection<BaggageDto> baggageDto, Passenger passenger) {
        List<Baggage> baggage = baggageDto.stream().map(dto -> Baggage.builder()
                .passenger(passenger)
                .weight(dto.weight()).tagCode(RandomStringUtils.getOneRandomString(20))
                .status("COMPLETED").build()).toList();
        baggageRepository.saveAll(baggage);
    }

}
