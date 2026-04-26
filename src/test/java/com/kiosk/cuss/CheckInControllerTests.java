package com.kiosk.cuss;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kiosk.cuss.controller.CheckInController;
import com.kiosk.cuss.dto.baggage.BaggageDto;
import com.kiosk.cuss.dto.checkin.CheckInDto;
import com.kiosk.cuss.dto.flight.FlightDto;
import com.kiosk.cuss.dto.response.CheckInResponseDto;
import com.kiosk.cuss.dto.seat.SeatDto;
import com.kiosk.cuss.entity.Baggage;
import com.kiosk.cuss.entity.Passenger;
import com.kiosk.cuss.service.baggage.BaggageService;
import com.kiosk.cuss.service.checkin.CheckInService;
import com.kiosk.cuss.service.passenger.PassengerService;
import com.kiosk.cuss.service.seat.SeatService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {CheckInController.class})
public class CheckInControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CheckInService checkInService;

    @Test
    public void userCheckInTest() throws Exception {
        SeatDto seat = SeatDto.builder().seatNumber("ACC1").build();
        FlightDto flight = FlightDto.builder().FlightId(42L).build();
        BaggageDto baggage1 = BaggageDto.builder().tagCode("ZK21400").weight(new BigDecimal("10.2")).build();
        BaggageDto baggage2 = BaggageDto.builder().tagCode("VK22100").weight(new BigDecimal("13.5")).build();
        Collection<BaggageDto> baggage = List.of(baggage1, baggage2);
        CheckInDto checkInRequest = CheckInDto.builder()
                .passengerId(1L).seatDto(seat)
                .flightDto(flight).pnr("LVX2K")
                .baggage(baggage).build();

        CheckInResponseDto response = CheckInResponseDto.builder()
                .status("CHECK_IN_COMPLETE").checkInDto(checkInRequest).build();

        when(checkInService.processCheckIn(checkInRequest)).thenReturn(response);

        mockMvc.perform(post("/api/v1/checkIn")
                .content(toJson(checkInRequest)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("CHECK_IN_COMPLETE"));
    }

    private static String toJson(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
