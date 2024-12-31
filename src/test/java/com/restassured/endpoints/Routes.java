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


}
