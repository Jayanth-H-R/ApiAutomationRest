package com.restassured.testcases;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.restassured.endpoints.UserEndPoints;
import com.restassured.payloads.User;
import com.restassured.utility.ExtentReports;
import com.restassured.utility.FileUtility;
import io.restassured.response.Response;

import org.testng.Assert;
import org.testng.annotations.*;


import java.io.File;

import java.io.IOException;
import java.util.List;

//@Listeners(ExtentReports.class)
public class UserTestCase {
    User user;
    Faker fakerData;
    List<User> userList;

    //@DataProvider
    public Object[] setDataFromJson() throws IOException {
        user = new User();
        ObjectMapper object = new ObjectMapper();
        File fileIn = new File(FileUtility.readData("userpayload"));
        userList = object.readValue(fileIn, new TypeReference<List<User>>() {
        });
        Object[] data = new Object[userList.size()];
        for (int i = 0; i < userList.size(); i++) {
            System.out.println(userList.get(i));
            data[i] = userList.get(i);

        }
        return data;
    }

    @BeforeTest
    public void setUpPayload() {
        user = new User();
        fakerData = new Faker();
        user.setId(fakerData.idNumber().hashCode());
        user.setEmail(fakerData.internet().emailAddress());
        user.setFirstName(fakerData.name().firstName());
        user.setLastName(fakerData.name().lastName());
        user.setUserStatus(fakerData.hashCode());
        user.setPassword(fakerData.internet().password());
        user.setPhone(fakerData.idNumber().toString());
        user.setUsername(fakerData.funnyName().toString());
    }

    @Test(priority = 1)
    public void createUser() {
        //System.out.println(user.getUsername() + " createUser");
        Response response = UserEndPoints.createUser(user,"/user");
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);

    }


    @Test(priority = 2)
    public void getUser() {
        System.out.println(user.getUsername() + " getUser");
        Response response = UserEndPoints.getUser(user.getUsername(),"/user/{user}");
        response.then().log().body();
        Assert.assertEquals(response.getStatusCode(), 200);
    }


    @Test(priority = 3)
    public void loginUser() {
        System.out.println(user.getUsername() + " loginUser");
        Response response = UserEndPoints.loginUser(user.getUsername(), user.getPassword(),"/user/login");
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
    }



    @Test(priority = 4)
    public void updateUser() {
        System.out.println(user.getUsername() + " updateUser");
        Response response = UserEndPoints.updateUser(user, user.getUsername(),"/user/{user}");
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);

    }

    @Test(priority = 5)
    public void deleteUser() {
        System.out.println(user.getUsername() + " deleteUser");
        Response response = UserEndPoints.deleteUser(user.getUsername(),"/user/{user}");
        response.then().statusCode(200).log().all();
    }

    @Test(priority = 6)
    public void logoutUser() {
        System.out.println(user.getUsername() + " logoutUser");
        Response response = UserEndPoints.logoutUser("/user/logout");
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
    }

}
