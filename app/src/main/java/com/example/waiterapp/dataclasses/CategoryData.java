package com.example.waiterapp.dataclasses;

public class CategoryData {
    private String categoryImageUrl;
    private String categoryName;

    public CategoryData(){}

    public CategoryData(String categoryImageUrl, String categoryName) {
        this.categoryImageUrl = categoryImageUrl;
        this.categoryName = categoryName;
    }

    public CategoryData(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryImageUrl() {
        return categoryImageUrl;
    }

    public void setCategoryImageUrl(String categoryImageUrl) {
        this.categoryImageUrl = categoryImageUrl;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
