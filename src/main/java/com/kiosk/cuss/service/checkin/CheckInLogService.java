package com.kiosk.cuss.service.checkin;

import com.kiosk.cuss.dto.checkin.CheckInLogAppEventDto;
import com.kiosk.cuss.dto.checkin.ChekInLogDto;
import com.kiosk.cuss.entity.CheckInLog;
import com.kiosk.cuss.entity.Passenger;
import com.kiosk.cuss.repository.CheckInLogRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CheckInLogService {

    private final CheckInLogRepository checkInLogRepository;

    public void writeCheckInLog(CheckInLogAppEventDto checkInLogAppEventDto) {

        ChekInLogDto dto = checkInLogAppEventDto.getCheckInLogDto();
        Passenger passenger = Passenger.builder().id(dto.passengerId()).build();

        CheckInLog log = CheckInLog.builder().passenger(passenger)
                .kioskId(dto.kioskId())
                .eventType(dto.eventType()).build();
        checkInLogRepository.save(log);
    }
}
