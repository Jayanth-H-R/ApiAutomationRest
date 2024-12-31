package com.restassured.endpoints;

import com.restassured.payloads.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class UserEndPoints {

    public static Response createUser(User userCreatePayload) {
        Response response = given().header("Content-Type", "application/json")
                .accept(ContentType.JSON)
                .body(userCreatePayload)
                .when().post(Routes.createUserUrl);
        return response;
    }


    public static Response getUser(String userName) {
        Response response = given().header("Content-Type", "application/json")
                .accept(ContentType.JSON)
                .when().get(Routes.getUserUrl+userName);

        return response;
    }

    public static Response updateUser(User userCreatePayload, String userName) {
        Response response = given().header("Content-Type", "application/json")
                .accept(ContentType.JSON)
                .body(userCreatePayload)
                .when().put(Routes.updateUserUrl+userName);
        return response;
    }

    public static Response deleteUser(String userName) {

        Response response = given()
                .when().delete(Routes.deleteUserUrl+userName);
        return response;

    }

    public static  Response loginUser(String userName, String password){
        Response response = given().header("Content-Type", "application/json")
                .accept(ContentType.JSON)
                .queryParam("username", userName)
                .queryParam("password", password)
                .when().get(Routes.loginUserUrl);
        return response;

    }

    public static  Response logoutUser(){
        Response response = given().header("Content-Type", "application/json")
                .accept(ContentType.JSON)
                .when().get(Routes.logoutUserUrl);
        return response;
    }
}
