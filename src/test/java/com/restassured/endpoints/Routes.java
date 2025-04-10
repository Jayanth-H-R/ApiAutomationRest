package com.restassured.endpoints;
import com.restassured.utility.FileUtility;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.IOException;

import static io.restassured.RestAssured.given;

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
        RequestSpecification response = reqst
                .addHeader("Content-Type", "application/json")
                .setBaseUri(baseURI).build();
        return response;
    }

    /**
     * Sends a POST request with a JSON payload as a String.
     *
     * @param endpoint The endpoint URL.
     * @param payload  The request body as a JSON string.
     * @return Response object containing the API response.
     */

    public static Response postMethod(String endpoint, String payload){
        Response resp = given(setUp())
                .body(payload)
                .when().post(endpoint);
        return resp;
    }
    /**
     * Sends a POST request with a payload as an Object (can be a POJO or Map).
     *
     * @param endpoint The endpoint URL.
     * @param payload  The request body as an Object.
     * @return Response object containing the API response.
     */

    public static Response postMethod(String endpoint, Object payload){
        Response resp = given(setUp())
                .body(payload)
                .when().post(endpoint);
        return resp;
    }

    /**
     * Sends a POST request with path parameters and payload.
     *
     * @param endpoint   The endpoint URL containing a path variable.
     * @param payload    The request body as a JSON string.
     * @param path       The name of the path parameter.
     * @param pathvalue  The value to replace in the path.
     * @return Response object containing the API response.
     */

    public static Response postMethod(String endpoint, String payload,String path, Object pathvalue){
        Response resp = given(setUp())
                .body(payload)
                .pathParam(path,pathvalue)
                .when().post(endpoint);
        return resp;
    }


    /**
     * Sends a PUT request with a given payload.
     *
     * @param endpoint The endpoint URL.
     * @param payLoad  The request body as an Object.
     * @return Response object containing the API response.
     */
    public static Response putMethod(String endpoint, Object payLoad){
        Response resp = given(setUp())
                .body(payLoad)
                .when().put(endpoint);
        return resp;
    }
    /**
     * Sends a PUT request with a path parameter and payload.
     *
     * @param endpoint  The endpoint URL containing a path variable.
     * @param payLoad   The request body as an Object.
     * @param path      The name of the path parameter.
     * @param pathValue The value to replace in the path.
     * @return Response object containing the API response.
     */

    public static Response putMethod(String endpoint, Object payLoad,String path,Object pathValue){
        Response resp = given(setUp())
                .body(payLoad)
                .pathParam(path,pathValue)
                .when().put(endpoint);
        return resp;
    }

    /**
     * Sends a PATCH request with a JSON payload.
     *
     * @param endPoint The endpoint URL.
     * @param payLoad  The request body as a JSON string.
     * @return Response object containing the API response.
     */

    public static Response patchMethod(String endPoint, String payLoad){
        Response resp = given(setUp())
                .body(payLoad)
                .when().patch(endPoint);
        return resp;
    }

    /**
     * Sends a PATCH request with path parameter and payload.
     *
     * @param endpoint   The endpoint URL.
     * @param payLoad    The request body as a JSON string.
     * @param path       The name of the path parameter.
     * @param pathValue  The value to replace in the path.
     * @return Response object containing the API response.
     */

    public static Response patchMethod(String endpoint, String payLoad,String path,Object  pathValue){
        Response resp = given(setUp())
                .body(payLoad)
                .when().patch(endpoint);
        return resp;
    }

    /**
     * Sends a GET request without any parameters.
     *
     * @param endPoint The endpoint URL.
     * @return Response object containing the API response.
     */

    public static Response getMethod(String endPoint){
        Response resp = given(setUp())
                .when().get(endPoint);
        return resp;
    }

    /**
     * Sends a GET request with a path parameter.
     *
     * @param endPoint   The endpoint URL with path variable.
     * @param path       The name of the path parameter.
     * @param pathValue  The value to replace in the path.
     * @return Response object containing the API response.
     */

    public static Response getMethod(String endPoint,String path,Object pathValue){
        Response resp = given(setUp())
                .pathParam(path,pathValue)
                .when().get(endPoint);
        return resp;
    }
    /**
     * Sends a GET request with two query parameters.
     *
     * @param endPoint     The endpoint URL.
     * @param query1       First query parameter name.
     * @param query1Value  First query parameter value.
     * @param query2       Second query parameter name.
     * @param query2Value  Second query parameter value.
     * @return Response object containing the API response.
     */

    public static Response getMethod(String endPoint,String query1,Object query1Value,String query2,Object query2Value){
        Response resp = given(setUp())
                .queryParam(query1,query1Value)
                .queryParam(query2,query2Value)
                .when().get(endPoint);
        return resp;
    }
    /**
     * Sends a DELETE request with a path parameter.
     *
     * @param endPoint   The endpoint URL with path variable.
     * @param path       The name of the path parameter.
     * @param pathValue  The value to replace in the path.
     * @return Response object containing the API response.
     */


    public static Response deleteMethod(String endPoint,String path, Object pathValue){
        Response resp = given(setUp())
                .pathParam(path,pathValue)
                .when().delete(endPoint);
        return resp;
    }


}
