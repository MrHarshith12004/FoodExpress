package com.foodapp.model;

public class Restaurant {

    private int restaurantId;
    private String restaurantName;
    private String description;
    private String address;
    private String phone;
    private String image;
    private double rating;
    private int deliveryTime;
    private boolean active;

    public Restaurant() {
    }

    public Restaurant(int restaurantId, String restaurantName, String description,
                      String address, String phone, String image,
                      double rating, int deliveryTime, boolean active) {
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.description = description;
        this.address = address;
        this.phone = phone;
        this.image = image;
        this.rating = rating;
        this.deliveryTime = deliveryTime;
        this.active = active;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public int getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(int deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}