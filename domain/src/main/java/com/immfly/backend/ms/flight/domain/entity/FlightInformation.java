package com.immfly.backend.ms.flight.domain.entity;

import java.io.Serializable;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FlightInformation implements Serializable {

  @Builder.Default
  private String ident = UUID.randomUUID().toString();
  private String faFlightID;
  private String airline;
  private String airlineIata;
  private String flightNumber;
  private String tailNumber;
  private String type;
  private String codeShares;
  private boolean blocked;
  private boolean diverted;
  private boolean canceled;
  private AirportInformation origin;
  private AirportInformation destination;

}
