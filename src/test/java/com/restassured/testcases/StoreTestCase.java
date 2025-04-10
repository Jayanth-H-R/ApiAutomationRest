package com.restassured.testcases;

import com.github.javafaker.Faker;
import com.restassured.endpoints.Routes;
import com.restassured.endpoints.StoreEndPoints;
import com.restassured.payloads.Store;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

import static com.restassured.utility.Reusables.*;

public class StoreTestCase extends Routes {
    Store store;
    List<Store> storeList;
    Faker fakerData;

    @BeforeTest
    public void setPayLoads() {
        store = new Store();
        fakerData = new Faker();
        store.setId(fakerData.number().numberBetween(120,5000));
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

    @Test(priority = 0)
    public void placeOrder() {
        Response resp = postMethod("/store/order",store);
        resp.then().log().all();
        Assert.assertEquals(getStatusCode(resp), 200);
        Assert.assertEquals(getAttributeIntValue(resp, "id"), store.getId());
        store.setId(getAttributeIntValue(resp, "id"));
    }

    @Test(priority = 1)
    public void getInventory() {

        Response resp = getMethod("/store/inventory");
        resp.then().log().all();
        Assert.assertEquals(resp.statusCode(), 200);

    }


    @Test(priority = 2,dependsOnMethods = "placeOrder")
    public void getOrderById() {
        System.out.println("store id fetched in getOrderById: "+store.getId());
        Response resp = getMethod("/store/order/{id}","id",store.getId());
        resp.then().statusCode(200).log().all();
        //Assert.assertEquals(resp.statusCode(), 200);
    }

    @Test(priority = 3)
    public void deleteOrderById() {
        System.out.println("store id fetched in deleteOrderById: "+store.getId());
        Response resp = deleteMethod("/store/order/{id}","id",store.getId());
        resp.then().log().all();
        Assert.assertEquals(resp.statusCode(), 200);
    }

}
