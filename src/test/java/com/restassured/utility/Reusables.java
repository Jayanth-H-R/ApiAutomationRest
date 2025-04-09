package com.restassured.utility;

import io.restassured.response.Response;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Reusables {

    public static int getStatusCode(Response resp) {

        return resp.getStatusCode();
    }

    public static String getAttributeValue(Response resp, String attribute) {
        return resp.jsonPath().get(attribute);

    }
    public static List<Object> getAttributeValues(Response resp, String attribute) {
        List<Object> list = resp.jsonPath().getList(attribute);
        return list;

    }

    public static boolean getAttributeBooleanValue(Response resp, String attribute) {
        return resp.jsonPath().getBoolean(attribute);

    }
    public static int getAttributeIntValue(Response resp, String attribute){
        return resp.jsonPath().getInt(attribute);
    }

    public static String jsonFileConvert(String filePath) throws IOException {
        String payload = new String(Files.readAllBytes(Paths.get(filePath)));

        return payload;
    }

}
