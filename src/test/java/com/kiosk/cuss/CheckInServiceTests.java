package com.kiosk.cuss;

import com.kiosk.cuss.dto.baggage.BaggageDto;
import com.kiosk.cuss.dto.checkin.CheckInDto;
import com.kiosk.cuss.dto.flight.FlightDto;
import com.kiosk.cuss.dto.seat.SeatDto;
import com.kiosk.cuss.entity.Passenger;
import com.kiosk.cuss.service.baggage.BaggageService;
import com.kiosk.cuss.service.checkin.CheckInService;
import com.kiosk.cuss.service.flight.FlightLockManager;
import com.kiosk.cuss.service.passenger.PassengerService;
import com.kiosk.cuss.service.seat.SeatService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CheckInServiceTests {

    @InjectMocks
    private CheckInService checkInService;

    @Mock
    private PassengerService passengerService;

    @Mock
    private SeatService seatService;

    @Mock
    private BaggageService baggageService;

    @Mock
    private ApplicationEventPublisher applicationEventPublisher;

    @Mock
    private FlightLockManager flightLockManager;

    @Test
    public void testCheckInService() {
        SeatDto seat = SeatDto.builder().seatNumber("ACC1").build();
        FlightDto flight = FlightDto.builder().FlightId(42L).build();
        BaggageDto baggage1 = BaggageDto.builder().tagCode("ZK21400").weight(new BigDecimal("10.2")).build();
        BaggageDto baggage2 = BaggageDto.builder().tagCode("VK22100").weight(new BigDecimal("13.5")).build();
        Collection<BaggageDto> baggage = List.of(baggage1, baggage2);
        CheckInDto checkInRequest = CheckInDto.builder()
                .passengerId(1L).seatDto(seat)
                .flightDto(flight).pnr("LVX2K")
                .baggage(baggage).build();

        Passenger p = Passenger.builder().id(1L).pnr("LVX2K").seatNumber("ACC1")
                .firstName("David").lastName("James").status("NOT_READY").build();

        when(passengerService.getPassengerById(1L)).thenReturn(p);
        when(flightLockManager.getLock(anyLong())).thenReturn(new ReentrantLock());

        checkInService.processCheckIn(checkInRequest);

        verify(flightLockManager, times(1)).getLock(42L);
        verify(seatService, times(1)).updateSeat(checkInRequest, p);
        verify(baggageService, times(1)).registerBaggage(baggage, p);
        verify(applicationEventPublisher, times(1)).publishEvent(any());
    }
}
