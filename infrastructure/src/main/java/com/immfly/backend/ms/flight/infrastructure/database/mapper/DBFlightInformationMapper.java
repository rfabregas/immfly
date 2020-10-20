package com.immfly.backend.ms.flight.infrastructure.database.mapper;

import com.immfly.backend.ms.flight.domain.entity.FlightInformation;
import com.immfly.backend.ms.flight.infrastructure.database.entity.FlightInformationRedisEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DBFlightInformationMapper {

  FlightInformation toDomainEntity(FlightInformationRedisEntity redisEntity);

  FlightInformationRedisEntity toRedisEntity(FlightInformation flightInformation);

}
