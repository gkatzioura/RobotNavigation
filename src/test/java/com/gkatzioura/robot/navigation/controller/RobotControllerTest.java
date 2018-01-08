package com.gkatzioura.robot.navigation.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.StreamUtils;
import org.springframework.web.context.WebApplicationContext;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RobotControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testNavigate() throws Exception {

        InputStream requestStream = getClass().getClassLoader().getResourceAsStream("request.json");
        String requestJson = StreamUtils.copyToString(requestStream, StandardCharsets.UTF_8);

        InputStream responseStream = getClass().getClassLoader().getResourceAsStream("response.json");
        String responseJson = StreamUtils.copyToString(responseStream,StandardCharsets.UTF_8);

        mockMvc.perform(post("/robot/navigate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
            .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json(responseJson));
    }

    @Test
    public void testInvalidNavigate() throws Exception {

        InputStream requestStream = getClass().getClassLoader().getResourceAsStream("invalid_request.json");
        String requestJson = StreamUtils.copyToString(requestStream, StandardCharsets.UTF_8);

        mockMvc.perform(post("/robot/navigate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testOutOfBoundsNavigate() throws Exception {

        InputStream requestStream = getClass().getClassLoader().getResourceAsStream("outofbounds_request.json");
        String requestJson = StreamUtils.copyToString(requestStream, StandardCharsets.UTF_8);

        mockMvc.perform(post("/robot/navigate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }


}
