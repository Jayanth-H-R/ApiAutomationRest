package com.restassured.endpoints;
import com.restassured.utility.FileUtility;

import java.io.IOException;


public class Routes {

    public static final String baseURI;

    static {
        try {
            baseURI = FileUtility.readData("baseurl");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static  String createUserUrl = baseURI+"/user";
    public static String getUserUrl =baseURI+"/user/";
    public static  String updateUserUrl =baseURI+"/user/";
    public static  String deleteUserUrl =baseURI+"/user/";
    public static  String loginUserUrl =baseURI+"/user/login";
    public static String logoutUserUrl= baseURI+"/user/logout";
    public static String placeOrderPet= baseURI+"/store/order";
    public static String getOrderStatus= baseURI+"/store/inventory";
    public static String  getOrderById= baseURI+"/store/order/";
    public static String deleteOrderById= baseURI+"/store/order/";
    public static String addnewPet=baseURI+"/pet";
    public static String getPetById=baseURI+"/pet/";
    public static String updatePet=baseURI+"/pet";
    public static String deletePetById=baseURI+"/pet/";


}
