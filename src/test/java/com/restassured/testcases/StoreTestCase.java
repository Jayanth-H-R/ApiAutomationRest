package com.restassured.testcases;

import com.github.javafaker.Faker;
import com.restassured.endpoints.StoreEndPoints;
import com.restassured.payloads.Store;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class StoreTestCase {
    Store store;
    List<Store> storeList;
    Faker fakerData;

    @BeforeClass
    public void setPayLoads() {
        store = new Store();
        fakerData = new Faker();
        store.setId(fakerData.idNumber().hashCode());
        store.setPetId(fakerData.number().hashCode());
        store.setQuantity(fakerData.random().hashCode());
        store.setComplete(true);
        store.setStatus("Available");
        store.setShipDate("2025-02-27T06:38:34.937Z");

         /*
         *
        user.setId(fakerData.idNumber().hashCode());
        user.setEmail(fakerData.internet().emailAddress());
        user.setFirstName(fakerData.name().firstName());
        user.setUsername(fakerData.name().username());
        user.setLastName(fakerData.name().lastName());
        user.setUserStatus(fakerData.hashCode());
        user.setPassword(fakerData.internet().password());
        user.setPhone(fakerData.idNumber().toString());
        * */

    }

    @Test()
    public void placeOrder() {
        Response resp = StoreEndPoints.placeOrder(store);
        resp.then().log().all();
        Assert.assertEquals(resp.statusCode(), 200);
        Assert.assertEquals(resp.jsonPath().getInt("id"), store.getId());
        store.setId(resp.jsonPath().getInt("id"));
    }

    @Test(dependsOnMethods = "placeOrder")
    public void getInventory() {

        Response resp = StoreEndPoints.getInventory();
        resp.then().log().all();
        Assert.assertEquals(resp.statusCode(), 200);


    }


    @Test(dependsOnMethods = "placeOrder")
    public void getOrderById() {
        Response resp = StoreEndPoints.getOrderById(store.getId());
        resp.then().log().all();
        Assert.assertEquals(resp.statusCode(), 200);
    }

    @Test(dependsOnMethods = "getOrderById")
    public void deleteOrderById() {
        Response resp = StoreEndPoints.getOrderById(store.getId());
        resp.then().log().all();
        Assert.assertEquals(resp.statusCode(), 200);
    }


}
