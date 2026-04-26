package com.kiosk.cuss.dto.checkin;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;


@Getter
public class CheckInLogAppEventDto extends ApplicationEvent {

    private final ChekInLogDto checkInLogDto;

    public CheckInLogAppEventDto(Object source, ChekInLogDto checkInLogDto) {
        super(source);
        this.checkInLogDto = checkInLogDto;
    }
}
