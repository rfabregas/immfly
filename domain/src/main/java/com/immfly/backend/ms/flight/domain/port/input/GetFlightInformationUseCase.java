package com.immfly.backend.ms.flight.domain.port.input;

import com.immfly.backend.ms.flight.domain.entity.FlightInformation;

public interface GetFlightInformationUseCase {

  FlightInformation getFlightInformation(String tailNumber, String flightNumber);
}
