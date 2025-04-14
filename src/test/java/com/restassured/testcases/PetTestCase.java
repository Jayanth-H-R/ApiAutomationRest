package com.restassured.testcases;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.restassured.endpoints.Routes;
import com.restassured.payloads.Pet;
import com.restassured.utility.Reusables;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.*;

import static com.restassured.utility.Reusables.*;


public class PetTestCase extends Routes {

    Pet pets ;
    Faker fakerData;
    String json;
    public static int  petId;

@BeforeTest
    public void setPets() throws JsonProcessingException {
        fakerData = new Faker();
        pets=new Pet();
        JSONObject jsonBody = new JSONObject();
        //Map<String, Object> jsonBody=new HashMap<>();
        pets.setId(fakerData.number().hashCode());
        petId = pets.getId();
        jsonBody.put("id", pets.getId());
        jsonBody.put("name", "doggie");
        JSONObject category = new JSONObject();
        category.put("id", 1);
        category.put("name", "German Shephaerd");
        jsonBody.put("category", category);
        String[] photoUrls = {"String1"};
        jsonBody.put("photoUrls", photoUrls);

        // Create and add the tags list of objects
        JSONObject tag = new JSONObject();
        tag.put("id", 2);
        tag.put("name", "Doberman");
        List<Map<String, Object>> tags = Arrays.asList(tag);
        jsonBody.put("tags", tags);
        jsonBody.put("status", "available");

        // Convert the HashMap to a JSON string using Jackson
        ObjectMapper objectMapper = new ObjectMapper();
         json = objectMapper.writeValueAsString(jsonBody);
        System.out.println("pet id is ---> " + pets.getId());

       // return json;
    }

    @Test(priority = 1)
    public void createPet(ITestContext context) throws IOException {
       // String payload = new String(Files.readAllBytes(Paths.get("./src/test/resources/petpayload.json")));
        Response resp = postMethod("/pet",json);
        resp.then()
                .log().all();
        Assert.assertEquals(getStatusCode(resp),200);
        Assert.assertEquals(getAttributeIntValue(resp,"id"),pets.getId());
        context.setAttribute("petId",pets.getId());
    }




    @Test(priority = 2)
    public void updatePet(ITestContext context) throws IOException {
       // String filepath = FileUtility.readData("updatepet");
        //String payLoad = jsonFileConvert(filepath);
        Response resp = putMethod("/pet",json);
        resp.then().statusCode(200).log().all();
    }

    @Test(priority = 3)
    public void getPetById(ITestContext context) {
        System.out.println(petId + "------>");
        Object pet_Id= context.getAttribute("petId");
        Response resp = getMethod("/pet/{petId}","petId",petId);
        resp.then().statusCode(200).log().all();
    }

    @Test(priority = 4)
    public void deletePetId(ITestContext context) {
        Object  pet_Id = context.getAttribute("petId");
       System.out.println("petId_ in delete request "+pet_Id);
       System.out.println("petId_ in delete request from petClass"  + pets.getId());
        Response resp = deleteMethod("/pet/{petId}","petId",petId);
        resp.then().log().all();
        Assert.assertEquals(getStatusCode(resp),200);
    }

}
