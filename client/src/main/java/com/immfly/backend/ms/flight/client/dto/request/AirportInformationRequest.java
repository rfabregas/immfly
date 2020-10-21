package com.immfly.backend.ms.flight.client.dto.request;

import lombok.Data;

@Data
public class AirportInformationRequest {
  private String code;
  private String city;
  private String alternateIdent;
  private String airportName;
}
