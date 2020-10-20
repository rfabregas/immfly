package com.immfly.backend.ms.flight.infrastructure.database.entity;

import java.io.Serializable;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("FlightInformation")
public class FlightInformationRedisEntity implements Serializable {

  private String id;

}
