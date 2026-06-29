package com.foodapp.model;

import java.math.BigDecimal;

public class Menu {

    private int menuId;
    private int restaurantId;
    private String foodName;
    private String description;
    private BigDecimal price;
    private String category;
    private boolean veg;
    private String image;
    private double rating;
    private boolean available;

    public Menu() {
    }

    public Menu(int menuId, int restaurantId, String foodName, String description,
                BigDecimal price, String category, boolean veg,
                String image, double rating, boolean available) {
        this.menuId = menuId;
        this.restaurantId = restaurantId;
        this.foodName = foodName;
        this.description = description;
        this.price = price;
        this.category = category;
        this.veg = veg;
        this.image = image;
        this.rating = rating;
        this.available = available;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isVeg() {
        return veg;
    }

    public void setVeg(boolean veg) {
        this.veg = veg;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}