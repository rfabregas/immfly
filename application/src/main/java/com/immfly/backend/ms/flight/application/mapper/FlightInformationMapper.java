package com.immfly.backend.ms.flight.application.mapper;

import com.immfly.backend.ms.flight.client.dto.response.FlightInformationResponse;
import com.immfly.backend.ms.flight.domain.entity.FlightInformation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FlightInformationMapper {

  FlightInformationResponse toResponse(FlightInformation entity);

  FlightInformation toEntity(FlightInformationResponse response);
}
