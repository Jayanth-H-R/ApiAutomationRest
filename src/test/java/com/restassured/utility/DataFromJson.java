package com.restassured.utility;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restassured.payloads.Pet;
import com.restassured.payloads.User;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class DataFromJson {

    List<Pet> petList;

    public static void main(String[] args) throws IOException {
        String path="./src/test/resources/petpayload.json";
        DataFromJson data=new DataFromJson();
        data.readDataJson(path);
    }

    public  Object readDataJson(String filePath) throws IOException {
        File file= new File(filePath);
        ObjectMapper objMap= new ObjectMapper();
//        petList=objMap.readValue(file,new TypeReference<List<Pet>>(){});
//        Object[] objValue=new Object[petList.size()];
//        for (int i=0;i< petList.size();i++){
//            System.out.println(petList.get(i));
//            objValue[i]=petList.get(i);
//        }

        // Deserialize JSON to Pet object
        Pet pet = objMap.readValue(file, Pet.class);

        // Print the Pet object to see if deserialization was successful
        System.out.println(pet);

        return pet;
    }

}
