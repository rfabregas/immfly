package com.immfly.backend.ms.flight.application.controller;

import static com.immfly.backend.ms.flight.application.roles.Roles.ROLE_ADMIN;

import com.immfly.backend.ms.flight.application.mapper.FlightInformationMapper;
import com.immfly.backend.ms.flight.client.dto.request.FlightInformationRequest;
import com.immfly.backend.ms.flight.client.dto.response.FlightInformationResponse;
import com.immfly.backend.ms.flight.client.rest.FlightInformationApi;
import com.immfly.backend.ms.flight.domain.port.input.GetFlightInformationUseCase;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FlightInformationController implements FlightInformationApi {

  private final GetFlightInformationUseCase getFlightInformationUseCase;
  private final FlightInformationMapper mapper;

  @Override
  @PreAuthorize("hasRole('"+ ROLE_ADMIN + "')")
  public List<FlightInformationResponse> getFlightInformation(String tailNumber,
      String flightNumber) {

    return mapper
        .toResponseList(getFlightInformationUseCase
            .findFlightInformationByTailNumberAndFlightNumber(tailNumber, flightNumber));
  }

  @Override
  public void saveExternalFlightInformation(
      List<FlightInformationRequest> flightInformationRequestList) {
    getFlightInformationUseCase
        .saveExternalFlightInformation(mapper.toEntityList(flightInformationRequestList));

  }
}
