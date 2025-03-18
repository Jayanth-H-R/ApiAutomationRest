package com.restassured.endpoints;

import com.restassured.payloads.Store;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class StoreEndPoints {

    public static Response placeOrder(Store store) {
        Response resp = given().header("Content-Type", "application/json")
                .accept(ContentType.JSON).body(store)
                .when().post(Routes.placeOrderPet);
        return resp;

    }

    public static Response getInventory(){
        Response resp = given().header("Content-Type", "application/json")
                .accept(ContentType.JSON)
                .when().get(Routes.getOrderStatus);
        return resp;
    }

    public static Response getOrderById(int id){
        Response resp=given().header("Content-Type","application/json")
                .accept(ContentType.JSON)
                .when().get(Routes.getOrderById+id);
        return resp;
    }

    public static Response deleteOrderById(int id){
        Response resp=given().header("Content-Type","application/json")
                .accept(ContentType.JSON)
                .when().delete(Routes.deleteOrderById+id);
        return resp;
    }

}
