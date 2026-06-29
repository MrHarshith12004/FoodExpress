package com.foodapp.dao;

import java.util.List;
import com.foodapp.model.Restaurant;

public interface RestaurantDAO {

    int addRestaurant(Restaurant restaurant);

    Restaurant getRestaurantById(int restaurantId);

    List<Restaurant> getAllRestaurants();

    List<Restaurant> searchRestaurant(String keyword);

    int updateRestaurant(Restaurant restaurant);

    int deleteRestaurant(int restaurantId);
}