//package com.immfly.backend.ms.flight.infrastructure;
//
//import static org.hamcrest.Matchers.hasSize;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import com.immfly.backend.ms.flight.application.mapper.FlightInformationMapper;
//import com.immfly.backend.ms.flight.client.dto.response.FlightInformationResponse;
//import com.immfly.backend.ms.flight.domain.port.input.GetFlightInformationUseCase;
//import com.immfly.backend.ms.flight.infrastructure.boot.FlightInformationApplication;
//import java.util.ArrayList;
//import java.util.List;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.web.servlet.MockMvc;
//
//@AutoConfigureMockMvc
//@SpringBootTest(classes = FlightInformationApplication.class)
//public class FlightControllerTest {
//
//  @MockBean
//  private GetFlightInformationUseCase getFlightInformationUseCase;
//  @MockBean
//  private FlightInformationMapper mapper;
//  @Autowired
//  private MockMvc mockMvc;
//
//  private List<FlightInformationResponse> flightInformationResponses;
//
//  @BeforeEach
//  void init() {
//    MockitoAnnotations.initMocks(this);
//    if (flightInformationResponses == null) {
//      flightInformationResponses = new ArrayList<>();
//      generateFlightInformationMock();
//    }
//  }
//
//  @Test
//  void flightController_unauthorized() throws Exception {
//    mockMvc
//        .perform(get("/v1/flight-information/EC-MYT/653").contentType(MediaType.APPLICATION_JSON))
//        .andExpect(status().isUnauthorized());
//  }
//
//  @Test
//  @WithMockUser(username = "user", roles={"ADMIN"})
//  void flightController_emptyResult() throws Exception {
//    mockMvc
//        .perform(get("/v1/flight-information/EC-MYT/653").contentType(MediaType.APPLICATION_JSON))
//        .andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(0)));
//  }
//
//  @Test
//  @WithMockUser(username = "user", roles={"ADMIN"})
//  void flightController_success() throws Exception {
//    when(mapper
//        .toResponseList(getFlightInformationUseCase
//            .findFlightInformationByTailNumberAndFlightNumber(any(), any()))).thenReturn(flightInformationResponses);
//    mockMvc
//        .perform(get("/v1/flight-information/EC-MYT/653").contentType(MediaType.APPLICATION_JSON))
//        .andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2)));
//  }
//
//  private List<FlightInformationResponse> generateFlightInformationMock() {
//
//    flightInformationResponses.add(FlightInformationResponse.builder().build());
//    flightInformationResponses.add(FlightInformationResponse.builder().build());
//
//    return flightInformationResponses;
//  }
//}
