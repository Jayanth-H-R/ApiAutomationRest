package com.restassured.testcases;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.restassured.endpoints.PetEndPoints;
import com.restassured.payloads.Pet;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class PetTestCase {

    Pet pets=new Pet();
    Faker fakerData;
    PetTestCase petTc;
    int petId;

    public String setPets() throws JsonProcessingException {
        fakerData= new Faker();
        //pets=new Pet();
        JSONObject jsonBody = new JSONObject();
       //Map<String, Object> jsonBody=new HashMap<>();
        pets.setId(fakerData.hashCode());
        petId = pets.getId();
        jsonBody.put("id",pets.getId());
        jsonBody.put("name","doggie");
        JSONObject category=new JSONObject();
        category.put("id",1);
        category.put("name","German Shephaerd");
        jsonBody.put("category",category);
        String[] photoUrls={"String1"};
        jsonBody.put("photoUrls",photoUrls);

        // Create and add the tags list of objects
       JSONObject tag = new JSONObject();
        tag.put("id", 2);
        tag.put("name", "Doberman");
        List<Map<String, Object>> tags = Arrays.asList(tag);
        jsonBody.put("tags", tags);
        jsonBody.put("status", "available");
        // Convert the HashMap to a JSON string using Jackson
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(jsonBody);
        System.out.println("pet id is ---> "+pets.getId());

        return json;
    }

    @Test
    public void createPet() throws IOException {
        //petTc = new PetTestCase();
        //pets=new Pet();
        String payload = new String(Files.readAllBytes(Paths.get("./src/test/resources/petpayload.json")));
        Response resp = PetEndPoints.createPet(payload);
        resp.then().statusCode(200).body("id",equalTo(1203))
                .log().all();
         Assert.assertEquals(resp.jsonPath().getInt("id"),pets.getId());
    }
}
