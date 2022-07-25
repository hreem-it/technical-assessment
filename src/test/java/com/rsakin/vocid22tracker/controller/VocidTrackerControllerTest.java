package com.rsakin.vocid22tracker.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.rsakin.vocid22tracker.exception.handler.GenericExceptionHandler;
import com.rsakin.vocid22tracker.model.dto.PatientDTO;
import com.rsakin.vocid22tracker.model.entity.Patient;
import com.rsakin.vocid22tracker.service.VocidTrackerService;
import com.rsakin.vocid22tracker.service.impl.VocidTrackerServiceImplTest;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(MockitoExtension.class)
class VocidTrackerControllerTest {

    private MockMvc mvc;

    @Mock
    private VocidTrackerService vocidTrackerService;

    @InjectMocks
    private VocidTrackerController vocidTrackerController;

    @BeforeEach
    public void setup() {
        // We would need this line if we would not use the MockitoExtension
        // MockitoAnnotations.initMocks(this);
        // Here we can't use @AutoConfigureJsonTesters because there isn't a Spring context
        JacksonTester.initFields(this, new ObjectMapper());
        // MockMvc standalone approach
        mvc = MockMvcBuilders.standaloneSetup(vocidTrackerController)
                .setControllerAdvice(new GenericExceptionHandler())
                .build();
    }

    @Test
    void getAllPatients() throws Exception {
        // init test values / given
        List<Patient> expectedPatients = VocidTrackerServiceImplTest.getSamplePatients();

        // stub - when
        when(vocidTrackerService.getAllPatients()).thenReturn(expectedPatients);

        MockHttpServletResponse response = mvc.perform(get("/api/vocid22/patient/all")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        List<Patient> actualPatients =
                new ObjectMapper().readValue(response.getContentAsString(), new TypeReference<>() {
                });
        assertEquals(expectedPatients.size(), actualPatients.size());
    }

    @Test
    void getPatient() throws Exception {
        // init test values
        Patient expectedPatient = VocidTrackerServiceImplTest.getSamplePatients().get(0);

        // stub - given
        when(vocidTrackerService.getPatientById(any())).thenReturn(expectedPatient);

        MockHttpServletResponse response = mvc.perform(get("/api/vocid22/patient/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        Patient actPatient = new ObjectMapper().readValue(response.getContentAsString(), Patient.class);
        Assert.assertEquals(expectedPatient, actPatient);
    }

    @Test
    void savePatient() throws Exception {
        // init test values
        Patient additionalPatient = new Patient();
        additionalPatient.setSocialSecurityNumber("123123123123");
        additionalPatient.setName("New Patient");
        additionalPatient.setLastname("Last");
        // Need to set @NotBlank fields
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setSocialSecurityNumber("123123123123");
        patientDTO.setName("New Patient");
        patientDTO.setLastname("Last");

        // stub - given
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String expectedAirportJsonStr = ow.writeValueAsString(additionalPatient);
        when(vocidTrackerService.addPatient(patientDTO)).thenReturn(additionalPatient);

        MockHttpServletResponse response = mvc.perform(post("/api/vocid22/patient/create")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(expectedAirportJsonStr))
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }


    @Test
    void deletePatient() throws Exception {
        // stub - given
        Mockito.when(vocidTrackerService.deletePatient(any())).thenReturn(true);

        MockHttpServletResponse response = mvc.perform(delete("/api/vocid22/patient/1")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        String actualResponseStr = response.getContentAsString();
        Assert.assertEquals("true", actualResponseStr);
    }

}