package com.example.waiterapp.dataclasses;

public class RestaurantData {
    private String restaurantName;
    private String restaurantEmail;
    private String restaurantPassword;

    public RestaurantData(){}

    public RestaurantData(String restaurantName, String restaurantEmail, String restaurantPassword) {
        this.restaurantName = restaurantName;
        this.restaurantEmail = restaurantEmail;
        this.restaurantPassword = restaurantPassword;
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
}
