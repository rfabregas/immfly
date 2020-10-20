package com.immfly.backend.ms.flight.infrastructure.boot;

import com.immfly.backend.ms.flight.domain.port.output.ExternalFlightInformationRepository;
import com.immfly.backend.ms.flight.domain.shared.annotation.DomainRepository;
import com.immfly.backend.ms.flight.domain.shared.annotation.Interactor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@ComponentScan(
    basePackages = {"com.immfly.backend.ms.flight.*"},
    basePackageClasses = {
        com.immfly.backend.ms.flight.domain.port.output.FlightInformationRepository.class,
        ExternalFlightInformationRepository.class
    },
    includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {
        DomainRepository.class, Interactor.class}))
public class FlightInformationApplication {

  public static void main(String[] args) {
    SpringApplication.run(FlightInformationApplication.class, args);
  }

}
