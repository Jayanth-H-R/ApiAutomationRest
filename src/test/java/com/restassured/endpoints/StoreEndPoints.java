package com.restassured.endpoints;

import com.restassured.payloads.Store;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class StoreEndPoints extends Routes{

    public static Response placeOrder(Store store,String endPoint) {
        Response resp = given(setUp()).body(store)
                .when().post(endPoint);
        return resp;

    }

    public static Response getInventory(String endpoint){
        Response resp = given(setUp())
                .when().get(endpoint);
        return resp;
    }

    public static Response getOrderById(int id, String endpoint){
        Response resp=given(setUp())
                .pathParam("id",id).log().all()
                .when().get(endpoint);
        return resp;
    }

    public static Response deleteOrderById(int id,String endpoint){
        Response resp=given(setUp())
                .pathParam("id",id)
                .log().all()
                .when().delete(endpoint);
        return resp;
    }

}
