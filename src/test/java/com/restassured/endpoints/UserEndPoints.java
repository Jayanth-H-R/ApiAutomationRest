package com.restassured.endpoints;

import com.restassured.payloads.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class UserEndPoints extends Routes{

    public static Response createUser(User userCreatePayload,String endpoint) {
        Response response = given(setUp())
                .body(userCreatePayload)
                .when().post(endpoint);
        return response;
    }


    public static Response getUser(String userName, String endpoint) {
        Response response = given(setUp())
                .pathParam("user",userName).log().all()
                .when().get(endpoint);

        return response;
    }

    public static Response updateUser(User userCreatePayload, String userName, String endpoint) {
        Response response = given(setUp())
                .pathParam("user",userName)
                .body(userCreatePayload)
                .when().put(endpoint);
        return response;
    }

    public static Response deleteUser(String userName, String endpoint) {

        Response response = given(setUp()).log().all()
                .pathParam("user",userName)
                .when().delete(endpoint);
        return response;

    }

    public static  Response loginUser(String userName, String password,String endpoint){
        Response response = given(setUp())
                .queryParam("username", userName)
                .queryParam("password", password)
                .when().get(endpoint);
        return response;

    }

    public static  Response logoutUser(String endpoint){
        Response response = given(setUp())
                .when().get(endpoint);
        return response;
    }
}
