package com.immfly.backend.ms.flight.domain.port.output;

import com.immfly.backend.ms.flight.domain.entity.FlightInformation;
import com.immfly.backend.ms.flight.domain.shared.annotation.DomainRepository;

@DomainRepository
public interface FlightInformationRepository {

  Object findByTailNumber();

  void save(FlightInformation flightInformation);

}
