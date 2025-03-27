package com.simplebooks.tests;

import Utility.DataFromCsv;
import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import payloads.AuthBook;
import payloads.SubmitOrderBook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class SimpleBooksTest {
    public static String baseUrl = "https://simple-books-api.glitch.me";
    public static String token;
    AuthBook auth;
    Faker faker;
    List<String> bookIds;
    SubmitOrderBook submitOrderBook = new SubmitOrderBook();


    @Test(priority = 0)
    public void getAvailableBooks() {
        Response reqs = given().header("Content-Type", "application/json")
                .accept(ContentType.JSON).baseUri(baseUrl)
                .when().get("/books")
                .then().log().all().statusCode(200).extract().response();
         bookIds = reqs.jsonPath().getList("id");  // Assuming the response has a list of books with ids
        System.out.println("Available Book IDs: " + bookIds);
    }

    @Test(priority = 1)
    public void getToken() {
        faker = new Faker();
        auth = new AuthBook();
        auth.setClientName(faker.name().username());
        auth.setClientEmail(faker.name().firstName() + faker.number().numberBetween(1, 50) + "@gmail.com");
//    auth.setClientName(faker.name().username());
//    auth.setClientEmail("Test"+faker.number().numberBetween(1,50)+"@gmail.com");

        Response resp = given().log().all().header("Content-Type", "application/json").accept(ContentType.JSON)
                .body(auth).baseUri(baseUrl)
                .when().post("/api-clients")
                .then().statusCode(201).log().all().extract().response();
        token = resp.jsonPath().get("accessToken");
    }


    @Test(priority = 2)
    public void createAnOrder() {
        faker = new Faker();
        //SubmitOrderBook submitOrderBook= new SubmitOrderBook();
        submitOrderBook.setBookId(faker.number().numberBetween(1, bookIds.size()));
        submitOrderBook.setCustomerName(faker.name().firstName());
        Response reqs = given().header("Content-Type", "application/json").body(submitOrderBook)
                .accept(ContentType.JSON).baseUri(baseUrl).header("Authorization", "Bearer " + token)
                .when().post("/orders")
                .then().statusCode(201).log().all().extract().response();
        Assert.assertTrue(reqs.jsonPath().get("created"));
        submitOrderBook.setOrderID(reqs.jsonPath().getString("orderId"));
        System.out.println(reqs.getBody().asString());
    }

    @Test(priority = 3)
    public void getBookById() {

        Response reqs = given().header("Content-Type", "application/json")
                .accept(ContentType.JSON).baseUri(baseUrl).header("Authorization", "Bearer " + token)
                .when().get("/orders/" + submitOrderBook.getOrderID())
                .then().statusCode(200).log().all().extract().response();
    }

    @Test(priority = 4, dataProviderClass = DataFromCsv.class, dataProvider = "readCSV")
    public void updateBookById(String name) {
        faker = new Faker();
        HashMap body = new HashMap<>();
        body.put("customerName", name + faker.name().lastName());
        Response reqs = given().header("Content-Type", "application/json").header("Authorization", "Bearer " + token)
                .body(body)
                .accept(ContentType.JSON).baseUri(baseUrl)
                .when().patch("/orders/" + submitOrderBook.getOrderID())
                .then().statusCode(204).extract().response();
    }

    @Test(priority = 5)
    public void getStatus() {
        Response reqs = given().header("Content-Type", "application/json")
                .accept(ContentType.JSON).baseUri(baseUrl)
                .when().get("/status")
                .then().statusCode(200).extract().response();
    }

    @Test(priority = 6)
    public void deleteAvailableBooks() {
        Response reqs = given().header("Content-Type", "application/json").header("Authorization", "Bearer " + token)
                .accept(ContentType.JSON).baseUri(baseUrl)
                .when().delete("/orders/" + submitOrderBook.getOrderID())
                .then().statusCode(204).extract().response();
    }

}
