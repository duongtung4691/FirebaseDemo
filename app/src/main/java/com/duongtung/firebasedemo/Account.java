package com.duongtung.firebasedemo;

/**
 * Created by MacBookPro on 11/29/16.
 */

public class Account {
    //name and address string
    private String name;
    private String address;

    public Account() {
      /*Blank default constructor essential for Firebase*/
    }

    //Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
