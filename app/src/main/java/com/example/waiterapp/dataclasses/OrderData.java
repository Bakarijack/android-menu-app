package com.example.waiterapp.dataclasses;

public class OrderData {
    private int tableNumber;
    private String productName;
    private int productQuantity;
    private String totalPrice;
    private String natureOfPayment;
    private String orderDate;
    private String orderTime;
    private String natureOfOrder;

    public OrderData(){}

    public OrderData(int tableNumber, String productName, int productQuantity, String totalPrice, String natureOfPayment, String orderDate, String orderTime, String natureOfOrder) {
        this.tableNumber = tableNumber;
        this.productName = productName;
        this.productQuantity = productQuantity;
        this.totalPrice = totalPrice;
        this.natureOfPayment = natureOfPayment;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.natureOfOrder = natureOfOrder;
    }


    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getNatureOfPayment() {
        return natureOfPayment;
    }

    public void setNatureOfPayment(String natureOfPayment) {
        this.natureOfPayment = natureOfPayment;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getNatureOfOrder() {
        return natureOfOrder;
    }

    public void setNatureOfOrder(String natureOfOrder) {
        this.natureOfOrder = natureOfOrder;
    }
}
