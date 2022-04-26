package com.esley.driver.controller;

import com.esley.driver.DriverServiceApplication;
import com.esley.driver.model.Driver;
import com.esley.driver.repository.DriverRepository;
import com.esley.driver.service.DriverService;
import com.fasterxml.jackson.databind.ObjectMapper;
import liquibase.exception.DatabaseException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = DriverServiceApplication.class)
@AutoConfigureMockMvc
@Transactional
class DriverControllerIT {

    @Autowired
    private MockMvc postman;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnAllDrivers() throws Exception {
        postman.perform(get("/driver"))
                //.andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void shouldGetSingleDriverByEmail() throws Exception {
        postman.perform(get("/driver/esleyowy@gmail.com"))
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("esleyowy@gmail.com"));
    }

    @Test
    void shouldAddDriver() throws Exception {
        Driver driverToAdd = new Driver(0, "stary@wp.pl", "Marcin", "Kowalski", 0, 20);


        postman.perform(post("/driver")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(driverToAdd)))
                //.andDo(print())
                .andExpect(status().isCreated())
                .andReturn()
                .getRequest()
                .getContentAsString();

        postman.perform(get("/driver/stary@wp.pl"))
                // .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("stary@wp.pl"));
    }
}