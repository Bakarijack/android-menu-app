package com.example.waiterapp.dataclasses;

public class RestaurantData {
    private String restaurantName;
    private String restaurantEmail;
    private String restaurantPassword;
    private Integer restaurantTableNumber;

    public RestaurantData(){}

    public RestaurantData(String restaurantName, String restaurantEmail, String restaurantPassword, Integer restaurantTableNumber) {
        this.restaurantName = restaurantName;
        this.restaurantEmail = restaurantEmail;
        this.restaurantPassword = restaurantPassword;
        this.restaurantTableNumber = restaurantTableNumber;
    }

    public RestaurantData(String resName, String resEmail, String pass) {
        this.restaurantName = resName;
        this.restaurantEmail = resEmail;
        this.restaurantPassword = pass;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantEmail() {
        return restaurantEmail;
    }

    public void setRestaurantEmail(String restaurantEmail) {
        this.restaurantEmail = restaurantEmail;
    }

    public String getRestaurantPassword() {
        return restaurantPassword;
    }

    public void setRestaurantPassword(String restaurantPassword) {
        this.restaurantPassword = restaurantPassword;
    }

    public Integer getRestaurantTableNumber() {
        return restaurantTableNumber;
    }

    public void setRestaurantTableNumber(int restaurantTableNumber) {
        this.restaurantTableNumber = restaurantTableNumber;
    }
}
