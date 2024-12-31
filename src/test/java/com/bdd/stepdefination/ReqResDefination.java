package com.bdd.stepdefination;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;

import static io.restassured.RestAssured.*;

public class ReqResDefination {
    public Response response;
    String registerID;

    @Given("^set the header and payload$")
    public void setUpReq() {
        baseURI = "https://reqres.in/api/users";
    }

    @When("^select post method to create user$")
    public void createUser() {
        HashMap map = new HashMap<>();
        map.put("name", "morpheus");
        map.put("job", "leader");

        response = given().header("Content-Type", "application/json")
                .accept(ContentType.JSON)
                .body(map)
                .when().post(baseURI);
    }

    @Then("^the response status code should be$")
    public void validateUser() {
        response.then().statusCode(201).log().all();
        int createUserId = response.jsonPath().getInt("id");
        System.out.println(createUserId);

    }

    @Given("^set the header$")
    public void setUpGetReq() {
        baseURI = "https://reqres.in/api/users/2";
    }


    @When("^select get method to fetch user details$")
    public void getUser() {
        response = given().header("Content-Type", "application/json")
                .accept(ContentType.JSON)
                .when().get(baseURI);
    }

    @Then("^the response status code should be 200$")
    public void validateGetUser() {
        response.then().statusCode(200).log().all();
        System.out.println(response.getStatusCode());
    }

    @Given("^set the header and update load")
    public void setUpdateReq() {
        baseURI = "https://reqres.in/api/users/2";
    }

    @When("^select put method to update user (.*) and (.*)$")
    public void updateUser(String username, String job) {
        HashMap map = new HashMap<>();
        map.put("name", username);
        map.put("job", job);

        response = given().header("Content-Type", "application/json")
                .accept(ContentType.JSON)
                .body(map)
                .when().put(baseURI);
    }

    @Then("^validate the response status code should be 200$")
    public void validateUpdateUser() {
        response.then().statusCode(200).log().all();
        String name = response.jsonPath().getString("name");
        System.out.println(name);
    }

    @Given("^set the header for register")
    public void setRegisterReq() {
        baseURI = "https://reqres.in/api/Request/register";
    }

    @When("^select post method to register user (.*) and (.*)$")
    public void registerUser(String email, String pass) {
        HashMap map = new HashMap<>();
        map.put("email", email);
        map.put("password", pass);

        response = given().header("Content-Type", "application/json")
                .accept(ContentType.JSON)
                .body(map)
                .when().post(baseURI);


    }

    @Then("^validate the response status code and id$")
    public void validateRegisterUser() {
        response.then().statusCode(201).log().all();
        registerID = response.jsonPath().getString("id");
        System.out.println(registerID);
    }


}
