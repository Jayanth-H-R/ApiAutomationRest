package com.restassured.endpoints;
import com.restassured.utility.FileUtility;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.io.IOException;

// This class holds all the REST API endpoint routes and setup configuration used in testing.
public class Routes {

    // Base URI for all API requests, read from a config file
    public static final String baseURI;


    static {
        try {
            //fetching base url from common property file
            baseURI = FileUtility.readData("baseurl");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    // ========== User Module Routes ==========

//    public static String createUserUrl = "/user";                      // Create a new user
//    public static String getUserUrl = "/user/{user}";                  // Get user details by username
//    public static String updateUserUrl = "/user/{user}";               // Update user by username
//    public static String deleteUserUrl = "/user/{user}";               // Delete user by username
//    public static String loginUserUrl = "/user/login";                 // Login user
//    public static String logoutUserUrl = "/user/logout";               // Logout user


//    // ========== Store Module Routes ==========
//    public static String placeOrderPet = "/store/order";               // Place an order for a pet
//    public static String getOrderStatus = "/store/inventory";          // Get inventory (order status)
//    public static String getOrderById = "/store/order/{id}";           // Get order details by ID
//    public static String deleteOrderById = "/store/order/{id}";        // Delete order by ID

//    // ========== Pet Module Routes ==========
//    public static String addnewPet = "/pet";                           // Add a new pet to the store
//    public static String getPetById = "/pet/{petId}";                  // Get pet details by ID
//    public static String updatePet = "/pet";                           // Update existing pet
//    public static String deletePetById = "/pet/{petId}";               // Delete pet by ID

    // Building a request specification with common settings (headers, base URI, etc.)
    public static RequestSpecification setUp(){

        RequestSpecBuilder reqst= new RequestSpecBuilder();
        RequestSpecification response = reqst.setAccept(ContentType.JSON)
                .addHeader("Content-Type", "application/json")
                .setBaseUri(baseURI).build();
        return response;
    }


}
