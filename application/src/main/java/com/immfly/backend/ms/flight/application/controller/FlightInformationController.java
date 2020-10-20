package com.immfly.backend.ms.flight.application.controller;

import com.immfly.backend.ms.flight.application.mapper.FlightInformationMapper;
import com.immfly.backend.ms.flight.client.dto.response.FlightInformationResponse;
import com.immfly.backend.ms.flight.client.rest.FlightInformationApi;
import com.immfly.backend.ms.flight.domain.port.input.GetFlightInformationUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FlightInformationController implements FlightInformationApi {

  private final GetFlightInformationUseCase getFlightInformationUseCase;
  private final FlightInformationMapper mapper;

  @Override
  public FlightInformationResponse getFlightInformation(String tailNumber, String flightNumber) {
    return mapper
        .toResponse(getFlightInformationUseCase.getFlightInformation(tailNumber, flightNumber));
  }
}
