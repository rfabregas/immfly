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
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
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

    List<FlightInformation> retrievedFlightInformations = new ArrayList<>();
    Map<String, String> persistedExternalFlightInformations = hashOperations
        .entries(EXTERNAL_FLIGHT_INFORMATION);
    try {
      if (!persistedExternalFlightInformations.isEmpty()) {
        Map.Entry<String, String> persistedExternalFlightInformation = persistedExternalFlightInformations
            .entrySet().iterator()
            .next();
        String externalFlightInformation = persistedExternalFlightInformation.getValue();

        retrievedFlightInformations = convertStringToList(externalFlightInformation);
      } else {
        retrievedFlightInformations = generateFlightInformationMock();
      }

    } catch (IOException e) {
      log.error(e.getMessage());
    }
    return retrievedFlightInformations;
  }

  @Override
  public void save(List<FlightInformation> flightInformationList) {

    if (flightInformationList != null) {
      String jsonAsString;
      try {

        jsonAsString = convertObjectToString(flightInformationList);
        hashOperations.put(EXTERNAL_FLIGHT_INFORMATION, UUID.randomUUID().toString(), jsonAsString);

      } catch (JsonProcessingException e) {
        log.error(e.getMessage());
      }
    }
  }

  private String convertObjectToString(Object object) throws JsonProcessingException {
    return objectMapper.writeValueAsString(object);
  }

  private List<FlightInformation> convertStringToList(String json) throws JsonProcessingException {
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
