package com.example.waiterapp.dataclasses;

public class FoodCategoryData {
    private String categoryName;

    public FoodCategoryData(){}

    public FoodCategoryData(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
