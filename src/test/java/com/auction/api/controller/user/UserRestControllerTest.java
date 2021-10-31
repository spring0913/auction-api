package com.auction.api.controller.user;

import com.auction.api.controller.JoinRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    private String name;

    private String email;

    private String password;

    @BeforeEach
    void setUp() {
        name = "bmsung";
        email = "test@gmail.com";
        password = "1234";
    }

    @Test
    @Order(1)
    void 회원가입_성공() throws Exception {
        JoinRequest joinRequest = new JoinRequest(name, email, password);

        ResultActions result = mockMvc.perform(
            post("/api/user/join")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(joinRequest))
        );
        result.andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success", is(true)))
            .andExpect(jsonPath("$.response.name", is(name)))
            .andExpect(jsonPath("$.response.email", is(email)))
            .andExpect(jsonPath("$.response.createAt").exists())
            .andExpect(jsonPath("$.error").isEmpty());
    }

    @Test
    @Order(2)
    void 회원가입_실패_중복된_이메일() throws Exception {
        JoinRequest joinRequest = new JoinRequest(name, email, password);

        ResultActions result = mockMvc.perform(
            post("/api/user/join")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(joinRequest))
        );
        result.andDo(print())
            .andExpect(status().is5xxServerError())
            .andExpect(jsonPath("$.success", is(false)))
            .andExpect(jsonPath("$.response").isEmpty())
            .andExpect(jsonPath("$.error.message").exists())
            .andExpect(jsonPath("$.error.status", is(HttpStatus.INTERNAL_SERVER_ERROR.value())));
    }
}