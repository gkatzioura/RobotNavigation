package com.gkatzioura.robot.navigation.controller;

import com.gkatzioura.robot.navigation.payload.NavigationRequest;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NavigationRequestValidator implements Validator {

    Pattern pattern = Pattern.compile("[NWES]*");

    @Override
    public boolean supports(Class<?> clazz) {
        return NavigationRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        NavigationRequest navigationRequest = (NavigationRequest) target;

        if(invalidPoint(navigationRequest.getCoords())) {
            errors.rejectValue("coords","Provide valid item coordinates");
            return;
        }

        if(navigationRequest.getRoomSize()==null||navigationRequest.getRoomSize().length!=2) {
            errors.rejectValue("roomSize","Provide valid room size");
            return;
        }

        if(invalidAccordingToRoomSize(navigationRequest.getCoords(),navigationRequest.getRoomSize())){
            errors.rejectValue("coords","Not inside room boundaries");
            return;
        }

        Matcher matcher = pattern.matcher(navigationRequest.getInstructions());

        if(!matcher.matches()) {
            errors.rejectValue("instructions","Please provide valid istructions");
        }

        if(navigationRequest.getPatches()==null) {
            errors.rejectValue("patches","please provide patches");
        }

        for(Integer[] patch : navigationRequest.getPatches()) {
            if(invalidPoint(patch)){
                errors.rejectValue("patches","patches should be valid coordinates");
                return;
            } else if(invalidAccordingToRoomSize(patch,navigationRequest.getRoomSize())){
                errors.rejectValue("patches","Not inside room boundaries");
            }
        }
    }

    private boolean invalidPoint(Integer[] point) {
        return point==null||point.length!=2;
    }

    private boolean invalidAccordingToRoomSize(Integer[] point,Integer[] roomSize) {

        return point[0]>=roomSize[0]|| point[1]>=roomSize[1]||point[0]<0|| point[1]<0;
    }
}
