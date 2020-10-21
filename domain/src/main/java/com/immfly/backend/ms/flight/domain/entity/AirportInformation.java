package com.immfly.backend.ms.flight.domain.entity;

import java.io.Serializable;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AirportInformation implements Serializable {

  private String code;
  private String city;
  private String alternateIdent;
  private String airportName;

}
