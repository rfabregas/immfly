package com.immfly.backend.ms.flight.domain.port;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import com.immfly.backend.ms.flight.domain.entity.AirportInformation;
import com.immfly.backend.ms.flight.domain.entity.FlightInformation;
import com.immfly.backend.ms.flight.domain.port.input.FlightInformationService;
import com.immfly.backend.ms.flight.domain.port.output.ExternalFlightInformationRepository;
import com.immfly.backend.ms.flight.domain.port.output.FlightInformationRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class FlightInformationServiceTest {

  @Mock
  private FlightInformationRepository flightInformationRepository;
  @Mock
  private ExternalFlightInformationRepository externalFlightInformationRepository;

  private FlightInformationService flightInformationService;
  private List<FlightInformation> flightInformationList;


  @BeforeEach
  void init() {
    MockitoAnnotations.initMocks(this);
    flightInformationService = new FlightInformationService(flightInformationRepository,
        externalFlightInformationRepository);
    if (flightInformationList == null) {
      flightInformationList = new ArrayList<>();
      generateFlightInformationMock();
    }
  }

  @Test
  void retrieveFlightInformation_success() {
    when(externalFlightInformationRepository.retrieveExternalFlightInformation())
        .thenReturn(flightInformationList);
    when(flightInformationRepository.retrieveFlightInformation()).thenReturn(flightInformationList);

    List<FlightInformation> result = flightInformationService
        .findFlightInformationByTailNumberAndFlightNumber("EC-MYT", "653");

    assertNotNull(result);
    assertTrue(result.stream().findFirst().isPresent());
    assertTrue(result.stream().allMatch(
        res -> res.getTailNumber().equalsIgnoreCase("EC-MYT") && res.getFlightNumber()
            .equalsIgnoreCase("653")));

  }

  private List<FlightInformation> generateFlightInformationMock() {
    AirportInformation airport = AirportInformation.builder()
        .code("GCGM")
        .city("La Gomera")
        .alternateIdent("GMZ")
        .airportName("La Gomera")
        .build();

    FlightInformation mock1 = FlightInformation.builder()
        .ident("IBB653")
        .faFlightID("IBB653-1581399936-airline-0136")
        .airline("IBB")
        .airlineIata("NT")
        .flightNumber("653")
        .tailNumber("EC-MYT")
        .type("Form_Airline")
        .codeShares("IBE123")
        .blocked(false)
        .diverted(false)
        .canceled(false)
        .origin(airport)
        .destination(airport)
        .build();
    FlightInformation mock2 = FlightInformation.builder()
        .ident("IBB000")
        .faFlightID("IBB000-1581399936-airline-0136")
        .airline("IBB00")
        .airlineIata("NT00")
        .flightNumber("65300")
        .tailNumber("EC-MYT00")
        .type("Form_Airline00")
        .codeShares("IBE12300")
        .blocked(false)
        .diverted(false)
        .canceled(false)
        .origin(airport)
        .destination(airport)
        .build();

    flightInformationList.add(mock1);
    flightInformationList.add(mock2);

    return flightInformationList;
  }

}
