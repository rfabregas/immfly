package com.immfly.backend.ms.flight.domain.port.input;

import com.immfly.backend.ms.flight.domain.entity.FlightInformation;
import com.immfly.backend.ms.flight.domain.port.output.FlightInformationRepository;
import com.immfly.backend.ms.flight.domain.shared.annotation.Interactor;
import lombok.RequiredArgsConstructor;

@Interactor
@RequiredArgsConstructor
public class FlightInformationService implements GetFlightInformationUseCase {

  private final FlightInformationRepository flightInformationRepository;

  @Override
  public FlightInformation getFlightInformation(String tailNumber, String flightNumber) {
    flightInformationRepository
        .save(FlightInformation.builder().name("Test").build());

    Object flightInfo = flightInformationRepository.findByTailNumber();

    return FlightInformation.builder().build();
  }
}
