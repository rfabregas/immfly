package com.immfly.backend.ms.flight.infrastructure.database.repository;

import com.immfly.backend.ms.flight.domain.entity.FlightInformation;
import com.immfly.backend.ms.flight.domain.port.output.ExternalFlightInformationRepository;
import com.immfly.backend.ms.flight.infrastructure.database.mapper.DBFlightInformationMapper;
import java.util.List;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ExternalInformationRepositoryRedisRepository implements
    ExternalFlightInformationRepository {

  private final DBFlightInformationMapper flightInformationMapper;
  private static final String EXTERNAL_FLIGHT_INFORMATION = "ExternalFlightInformation";
  private final RedisTemplate<String, Object> redisTemplate;
  private HashOperations<String, String, FlightInformation> hashOperations;


  @PostConstruct
  private void initHashOperations() {
    hashOperations = redisTemplate.opsForHash();
  }


  @Override
  public List<FlightInformation> retrieveFlightInformation() {
    return null;
  }

  @Override
  public void save(FlightInformation flightInformation) {

    hashOperations
        .put(EXTERNAL_FLIGHT_INFORMATION, flightInformation.getIdent(), flightInformation);
  }

}
