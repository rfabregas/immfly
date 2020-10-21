package com.immfly.backend.ms.flight.domain.port.input;

import com.immfly.backend.ms.flight.domain.entity.FlightInformation;
import com.immfly.backend.ms.flight.domain.port.output.ExternalFlightInformationRepository;
import com.immfly.backend.ms.flight.domain.port.output.FlightInformationRepository;
import com.immfly.backend.ms.flight.domain.shared.annotation.Interactor;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;

@Interactor
@RequiredArgsConstructor
public class FlightInformationService implements GetFlightInformationUseCase {

  private final FlightInformationRepository flightInformationRepository;
  private final ExternalFlightInformationRepository externalFlightInformationRepository;

  @Override
  public List<FlightInformation> findFlightInformationByTailNumberAndFlightNumber(
      String tailNumber,
      String flightNumber) {

    List<FlightInformation> flightInformationList = externalFlightInformationRepository
        .retrieveExternalFlightInformation();

    List<FlightInformation> flights = flightInformationRepository.retrieveFlightInformation();

    if (!flights.equals(flightInformationList)) {
      flightInformationRepository.delete();
      flights = flightInformationRepository
          .save(flightInformationList);
    }

    return flights.stream()
        .filter(flight -> tailNumber.equalsIgnoreCase(flight.getTailNumber()) && flightNumber
            .equalsIgnoreCase(flight.getFlightNumber()))
        .collect(
            Collectors.toList());
  }

  @Override
  public void saveExternalFlightInformation(
      List<FlightInformation> flightInformationList) {

    externalFlightInformationRepository
        .save(flightInformationList);
  }
}
