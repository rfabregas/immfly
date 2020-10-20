package com.immfly.backend.ms.flight.domain.port.output;

import com.immfly.backend.ms.flight.domain.entity.FlightInformation;
import com.immfly.backend.ms.flight.domain.shared.annotation.DomainRepository;
import java.util.List;

@DomainRepository
public interface ExternalFlightInformationRepository {

  List<FlightInformation> retrieveFlightInformation();

  void save(FlightInformation flightInformation);

}
