package com.immfly.backend.ms.flight.application.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
class ErrorResponse {

  private String errorCode;
  private String errorDescription;
  private String erroType;

}
