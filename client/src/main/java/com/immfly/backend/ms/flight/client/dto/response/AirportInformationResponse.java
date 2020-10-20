package com.immfly.backend.ms.flight.client.dto.response;

import lombok.Data;

@Data
public class AirportInformationResponse {
  private String code;
  private String city;
  private String alternateIdent;
  private String airportName;
}
