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
  private String name;

}
