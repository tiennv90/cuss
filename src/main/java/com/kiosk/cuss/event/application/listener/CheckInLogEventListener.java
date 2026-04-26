package com.kiosk.cuss.event.application.listener;

import com.kiosk.cuss.dto.checkin.CheckInLogAppEventDto;
import com.kiosk.cuss.service.checkin.CheckInLogService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class CheckInLogEventListener implements ApplicationListener<CheckInLogAppEventDto> {

    private final CheckInLogService checkInLogService;

    @Override
    @Async
    public void onApplicationEvent(CheckInLogAppEventDto event) {
        log.info("Write log event");
        checkInLogService.writeCheckInLog(event);
    }
}
