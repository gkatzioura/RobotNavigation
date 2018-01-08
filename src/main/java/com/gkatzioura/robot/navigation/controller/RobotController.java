package com.gkatzioura.robot.navigation.controller;

import com.gkatzioura.robot.navigation.payload.NavigationRequest;
import com.gkatzioura.robot.navigation.payload.NavigationResponse;
import com.gkatzioura.robot.navigation.service.NavigationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("robot")
public class RobotController {

    @Autowired
    private NavigationService navigationService;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new NavigationRequestValidator());
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(RobotController.class);

    @PostMapping(path = "navigate")
    public NavigationResponse navigate(@Valid @RequestBody NavigationRequest navigationPayload) {

        NavigationResponse navigationResponse = navigationService.navigate(navigationPayload);
        return navigationResponse;
    }

}
