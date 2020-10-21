package com.immfly.backend.ms.flight.client.dto.request;

import lombok.Data;

@Data
public class FlightInformationRequest {

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
  private AirportInformationRequest origin;
  private AirportInformationRequest destination;

}
