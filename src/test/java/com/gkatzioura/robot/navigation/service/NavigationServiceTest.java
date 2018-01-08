package com.gkatzioura.robot.navigation.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gkatzioura.robot.navigation.payload.NavigationRequest;
import com.gkatzioura.robot.navigation.payload.NavigationResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URL;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NavigationServiceTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private NavigationService navigationService;

    @Test
    public void testNavigation() throws Exception {

        URL uri = getClass().getClassLoader().getResource("request.json");

        NavigationRequest navigationRequest = objectMapper.readValue(uri,NavigationRequest.class);
        NavigationResponse response = navigationService.navigate(navigationRequest);

        Assert.assertEquals(new Integer(1),response.getCoords()[0]);
        Assert.assertEquals(new Integer(3),response.getCoords()[1]);
        Assert.assertEquals(new Integer(1),response.getPatches());
    }

}
