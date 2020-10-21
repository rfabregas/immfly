package com.immfly.backend.ms.flight.application.mapper;

import com.immfly.backend.ms.flight.client.dto.request.FlightInformationRequest;
import com.immfly.backend.ms.flight.client.dto.response.FlightInformationResponse;
import com.immfly.backend.ms.flight.domain.entity.FlightInformation;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FlightInformationMapper {

  FlightInformationResponse toResponse(FlightInformation entity);

  FlightInformation toEntity(FlightInformationResponse response);

  List<FlightInformationResponse> toResponseList(List<FlightInformation> entities);

  List<FlightInformation> toEntityList(List<FlightInformationRequest> entities);
}
