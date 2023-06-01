package com.example.waiterapp.dataclasses;

public class CartItems {
    private String pName;
    private String pPrice;
    private int pQuantity;


    public CartItems(){}

    public CartItems(String pName, String pPrice, int pQuantity) {
        this.pName = pName;
        this.pPrice = pPrice;
        this.pQuantity = pQuantity;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getpPrice() {
        return pPrice;
    }

    public void setpPrice(String pPrice) {
        this.pPrice = pPrice;
    }

    public int getpQuantity() {
        return pQuantity;
    }

    public void setpQuantity(int pQuantity) {
        this.pQuantity = pQuantity;
    }
}
