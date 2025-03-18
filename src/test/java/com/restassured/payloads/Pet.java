package com.restassured.payloads;

import java.util.List;

public class Pet {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private int id;
    private String name;

    public Pet(){

    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public int getId(){
//        return id;
//    }
//
//    public void setId(int id){
//        this.id=id;
//    }

}
