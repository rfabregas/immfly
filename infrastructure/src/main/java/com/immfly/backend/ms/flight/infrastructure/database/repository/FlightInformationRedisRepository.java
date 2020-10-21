package com.immfly.backend.ms.flight.infrastructure.database.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.immfly.backend.ms.flight.domain.entity.FlightInformation;
import com.immfly.backend.ms.flight.domain.port.output.FlightInformationRepository;
import com.immfly.backend.ms.flight.infrastructure.database.mapper.DBFlightInformationMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class FlightInformationRedisRepository implements FlightInformationRepository {

  private final DBFlightInformationMapper flightInformationMapper;
  private static final String TABLE_NAME = "FlightInformation";
  private final RedisTemplate<String, String> redisTemplate;
  private final ObjectMapper objectMapper;
  private HashOperations<String, String, String> hashOperations;


  @PostConstruct
  private void initHashOperations() {
    hashOperations = redisTemplate.opsForHash();
  }

  @Override
  public List<FlightInformation> retrieveFlightInformation() {

    List<FlightInformation> flightInformationList = new ArrayList<>();
    Map<String, String> info = hashOperations.entries(TABLE_NAME);

    if (!info.isEmpty()) {
      Map.Entry<String, String> entry = info.entrySet().iterator().next();
      try {
        flightInformationList = convertStringtoList(entry.getValue());

      } catch (JsonProcessingException e) {
        log.error(e.getMessage());
      }
    }
    return flightInformationList;
  }

  @Override
  public List<FlightInformation> save(List<FlightInformation> flightInformationList) {
    try {
      String json = objectMapper.writeValueAsString(flightInformationList);
      hashOperations.put(TABLE_NAME, UUID.randomUUID().toString(), json);
    } catch (JsonProcessingException e) {
      log.error(e.getMessage());
    }
    return flightInformationList;
  }

  @Override
  public void delete() {
    initHashOperations();
  }

  private List<FlightInformation> convertStringtoList(String json) throws JsonProcessingException {
    return objectMapper.readValue(json, new TypeReference<List<FlightInformation>>() {
    });
  }
}
