package com.restassured.endpoints;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PetEndPoints {

    public static Response createPet(String body){

        Response response=  given().header("Content-Type","application/json").accept(ContentType.JSON).body(body)
                .when().post(Routes.addnewPet);
        return response;

    }
    public static Response getPetId(int petId){

        Response response=  given().header("Content-Type","application/json").accept(ContentType.JSON)
                .when().get(Routes.getPetById+petId);
        return response;

    }
    public static Response updatePetById(String body){
        Response resp =given().header("Content-Type","application/json").accept(ContentType.JSON)
                .body(body)
                .when().put(Routes.updatePet);
        return resp;
    }

    public static Response deletePetById(int id){
        Response resp = given().header("Content-Type", "application/json").accept(ContentType.JSON)
                .when().delete(Routes.deletePetById + id);
        return resp;
    }



}
