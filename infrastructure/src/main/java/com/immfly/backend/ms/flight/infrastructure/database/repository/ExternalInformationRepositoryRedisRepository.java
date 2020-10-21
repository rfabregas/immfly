package com.immfly.backend.ms.flight.infrastructure.database.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.immfly.backend.ms.flight.domain.entity.FlightInformation;
import com.immfly.backend.ms.flight.domain.port.output.ExternalFlightInformationRepository;
import com.immfly.backend.ms.flight.domain.port.output.FlightInformationRepository;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ExternalInformationRepositoryRedisRepository implements
    ExternalFlightInformationRepository {

  private static final String EXTERNAL_FLIGHT_INFORMATION = "ExternalFlightInformation";
  private static final String RESOURCE_PATH_JSON = "classpath:json/FlightInformation.json";
  private final FlightInformationRepository flightInformationRepository;
  private final RedisTemplate<String, Object> redisTemplate;
  private final ObjectMapper objectMapper;
  private final ResourceLoader resourceLoader;

  private HashOperations<String, String, String> hashOperations;


  @PostConstruct
  private void initHashOperations() {
    hashOperations = redisTemplate.opsForHash();
  }


  @Override
  @Cacheable(value = "externalFlightInformation")
  public List<FlightInformation> retrieveExternalFlightInformation() {

    List<FlightInformation> retrievedFlightInformation = new ArrayList<>();
    Map<String, String> test = hashOperations.entries(EXTERNAL_FLIGHT_INFORMATION);
    try {
      if (!test.isEmpty()) {
        Map.Entry<String, String> entry = test.entrySet().iterator().next();
        String jsonAsString = entry.getValue();

        retrievedFlightInformation = convertStringtoList(jsonAsString);
      } else {
        retrievedFlightInformation = generateFlightInformationMock();
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
    redisTemplate.expire(EXTERNAL_FLIGHT_INFORMATION, 20, TimeUnit.SECONDS);
    return retrievedFlightInformation;


  }

  @Override
  public void save(List<FlightInformation> flightInformationList) {

    if (flightInformationList != null) {
      String jsonAsString;
      try {

        jsonAsString = convertObjectToString(flightInformationList);
        hashOperations.put(EXTERNAL_FLIGHT_INFORMATION, UUID.randomUUID().toString(), jsonAsString);

      } catch (JsonProcessingException e) {
        e.printStackTrace();
      }
    }
  }

  private String convertObjectToString(Object object) throws JsonProcessingException {
    return objectMapper.writeValueAsString(object);
  }

  private List<FlightInformation> convertStringtoList(String json) throws JsonProcessingException {
    return objectMapper.readValue(json, new TypeReference<List<FlightInformation>>() {
    });
  }

  private List<FlightInformation> generateFlightInformationMock() throws IOException {
    Resource flightInformationJson = resourceLoader.getResource(RESOURCE_PATH_JSON);
    return objectMapper
        .readValue(flightInformationJson.getFile(), new TypeReference<List<FlightInformation>>() {
        });
  }

}
