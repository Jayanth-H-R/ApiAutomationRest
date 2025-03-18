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
}
