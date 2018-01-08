package com.gkatzioura.robot.navigation.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gkatzioura.robot.navigation.payload.NavigationResponse;

import javax.persistence.AttributeConverter;
import java.io.IOException;

public class NavigationResponseConverter implements AttributeConverter<NavigationResponse,String > {

    private final static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(NavigationResponse response) {

        try {
            return objectMapper.writeValueAsString(response);
        } catch (JsonProcessingException ex) {
            throw new IllegalArgumentException("Could not convert model to json payload",ex);
        }
    }

    @Override
    public NavigationResponse convertToEntityAttribute(String dbData) {

        try {
            return objectMapper.readValue(dbData, NavigationResponse.class);
        } catch (IOException ex) {
            throw new IllegalArgumentException("Could convert json to model",ex);
        }
    }
}
