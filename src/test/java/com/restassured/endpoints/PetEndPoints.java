package com.restassured.endpoints;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PetEndPoints extends Routes {

    public static Response createPet(String body,String endpoint) {
        Response response = given(setUp())
                .body(body)
                .when().post(endpoint);
        return response;

    }

    public static Response getPetId(int petId, String endPoint) {

        Response response = given(setUp())
                .pathParam("petId", petId)
                .when().get(endPoint);
        return response;

    }

    public static Response updatePetById(String body,String endPoint) {
        Response resp = given(setUp())
                .body(body)
                .when().put(endPoint);
        return resp;
    }

    public static Response deletePetById(int  id,String endPoint) {
        Response resp = given(setUp())
                .pathParam("petId", id)
                .when().delete(endPoint);
        return resp;
    }


}
