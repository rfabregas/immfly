package com.immfly.backend.ms.flight.client.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FlightInformationResponse {

  private String ident;
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
  private AirportInformationResponse origin;
  private AirportInformationResponse destination;

}
