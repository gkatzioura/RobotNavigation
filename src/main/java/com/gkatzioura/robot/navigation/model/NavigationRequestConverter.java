package com.gkatzioura.robot.navigation.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gkatzioura.robot.navigation.payload.NavigationRequest;

import javax.persistence.AttributeConverter;
import java.io.IOException;

public class NavigationRequestConverter implements AttributeConverter<NavigationRequest,String > {

    private final static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(NavigationRequest request) {
        try {
            return objectMapper.writeValueAsString(request);
        } catch (JsonProcessingException ex) {
            throw new IllegalArgumentException("Could not convert model to json payload",ex);
        }
    }

    @Override
    public NavigationRequest convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, NavigationRequest.class);
        } catch (IOException ex) {
            throw new IllegalArgumentException("Could convert json to model",ex);
        }
    }

}
