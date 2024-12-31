package com.restassured.payloads;

import java.util.List;

public class UserfromJson {

    private  List<User> lists;


    public  List<User> getLists() {

        return lists;
    }

    public  void setLists(List<User> lists) {
        this.lists = lists;
    }


    @Override
    public String toString() {
        return "UserfromJson{" +
                "lists=" + lists +
                '}';
    }
}
