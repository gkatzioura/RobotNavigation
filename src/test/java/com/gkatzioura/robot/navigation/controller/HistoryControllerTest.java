package com.gkatzioura.robot.navigation.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gkatzioura.robot.navigation.payload.NavigationRequest;
import com.gkatzioura.robot.navigation.service.NavigationService;
import org.json.JSONArray;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.net.URL;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class HistoryControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private NavigationService navigationService;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {

        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        URL uri = getClass().getClassLoader().getResource("request.json");
        NavigationRequest navigationRequest = objectMapper.readValue(uri,NavigationRequest.class);
        navigationService.navigate(navigationRequest);
    }

    @Test
    public void testInputHistory() throws Exception {

        MvcResult mvcResult = mockMvc.perform(get("/history/input"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        JSONArray jsonArray = new JSONArray(mvcResult.getResponse().getContentAsString());
        Assert.assertEquals(new Integer(1),new Integer(jsonArray.length()));
    }

    @Test
    public void testOutputHistory() throws Exception {

        MvcResult mvcResult = mockMvc.perform(get("/history/output"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        JSONArray jsonArray = new JSONArray(mvcResult.getResponse().getContentAsString());
        Assert.assertEquals(new Integer(1),new Integer(jsonArray.length()));
    }

}
