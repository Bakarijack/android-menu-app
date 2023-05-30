package com.example.waiterapp.dataclasses;

public class ProductData {
    private String productName;
    private String productPrice;
    private Integer productQuantity;
    private String productCategory;
    private String productDescription;
    private String productImageUri;

    public ProductData(String productName, String productPrice, Integer productQuantity, String productCategory, String productDescription, String productImageUri) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.productCategory = productCategory;
        this.productDescription = productDescription;
        this.productImageUri = productImageUri;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductImageUri() {
        return productImageUri;
    }

    public void setProductImageUri(String productImageUri) {
        this.productImageUri = productImageUri;
    }
}

