package com.immfly.backend.ms.flight.client.rest;

import com.immfly.backend.ms.flight.client.dto.response.FlightInformationResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(
    value = "v1/flight-information",
    produces = "application/json",
    consumes = "application/json"
)
public interface FlightInformationApi {

  @GetMapping(value = "/{tail-number}/{flight-number}")
  FlightInformationResponse getFlightInformation(
      @PathVariable(value = "tail-number") String tailNumber,
      @PathVariable(value = "flight-number") String flightNumber);
}
