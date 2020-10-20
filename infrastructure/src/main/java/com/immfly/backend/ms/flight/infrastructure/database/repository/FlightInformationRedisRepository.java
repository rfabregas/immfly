package com.immfly.backend.ms.flight.infrastructure.database.repository;

import com.immfly.backend.ms.flight.domain.entity.FlightInformation;
import com.immfly.backend.ms.flight.domain.port.output.FlightInformationRepository;
import com.immfly.backend.ms.flight.infrastructure.database.mapper.DBFlightInformationMapper;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class FlightInformationRedisRepository implements FlightInformationRepository {

  private final DBFlightInformationMapper flightInformationMapper;
  private static final String TABLE_NAME = "FlightInformation";
  private final RedisTemplate<String, Object> redisTemplate;
  private HashOperations<String, String, FlightInformation> hashOperations;


  @PostConstruct
  private void initHashOperations() {
    hashOperations = redisTemplate.opsForHash();
  }

  @Override
  public ResponseEntity<Object> findByTailNumber() {
    return new ResponseEntity<>(hashOperations.entries(TABLE_NAME), HttpStatus.OK);
  }

  @Override
  public void save(FlightInformation flightInformation) {

    hashOperations.put(TABLE_NAME, flightInformation.getIdent(), flightInformation);
  }
}
