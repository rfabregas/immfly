package com.immfly.backend.ms.flight.domain.port.input;

import com.immfly.backend.ms.flight.domain.entity.FlightInformation;
import java.util.List;

public interface GetFlightInformationUseCase {

  List<FlightInformation> findFlightInformationByTailNumberAndFlightNumber(String tailNumber, String flightNumber);
  void saveExternalFlightInformation(List<FlightInformation> flightInformationList);
}
