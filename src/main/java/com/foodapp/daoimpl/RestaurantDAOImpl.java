package com.foodapp.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.foodapp.dao.RestaurantDAO;
import com.foodapp.model.Restaurant;
import com.foodapp.util.DBConnection;

public class RestaurantDAOImpl implements RestaurantDAO {

    @Override
    public int addRestaurant(Restaurant restaurant) {

        String sql = """
                INSERT INTO restaurants
                (restaurant_name, description, address, phone,
                 image, rating, delivery_time, is_active)
                VALUES (?,?,?,?,?,?,?,?)
                """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, restaurant.getRestaurantName());
            ps.setString(2, restaurant.getDescription());
            ps.setString(3, restaurant.getAddress());
            ps.setString(4, restaurant.getPhone());
            ps.setString(5, restaurant.getImage());
            ps.setDouble(6, restaurant.getRating());
            ps.setInt(7, restaurant.getDeliveryTime());
            ps.setBoolean(8, restaurant.isActive());

            return ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public Restaurant getRestaurantById(int restaurantId) {

        String sql = "SELECT * FROM restaurants WHERE restaurant_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, restaurantId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Restaurant restaurant = new Restaurant();

                restaurant.setRestaurantId(rs.getInt("restaurant_id"));
                restaurant.setRestaurantName(rs.getString("restaurant_name"));
                restaurant.setDescription(rs.getString("description"));
                restaurant.setAddress(rs.getString("address"));
                restaurant.setPhone(rs.getString("phone"));
                restaurant.setImage(rs.getString("image"));
                restaurant.setRating(rs.getDouble("rating"));
                restaurant.setDeliveryTime(rs.getInt("delivery_time"));
                restaurant.setActive(rs.getBoolean("is_active"));

                return restaurant;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Restaurant> getAllRestaurants() {

        List<Restaurant> restaurantList = new ArrayList<>();

        String sql = """
                SELECT *
                FROM restaurants
                WHERE is_active = TRUE
                ORDER BY rating DESC
                """;

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement()) {

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                Restaurant restaurant = new Restaurant();

                restaurant.setRestaurantId(rs.getInt("restaurant_id"));
                restaurant.setRestaurantName(rs.getString("restaurant_name"));
                restaurant.setDescription(rs.getString("description"));
                restaurant.setAddress(rs.getString("address"));
                restaurant.setPhone(rs.getString("phone"));
                restaurant.setImage(rs.getString("image"));
                restaurant.setRating(rs.getDouble("rating"));
                restaurant.setDeliveryTime(rs.getInt("delivery_time"));
                restaurant.setActive(rs.getBoolean("is_active"));

                restaurantList.add(restaurant);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return restaurantList;
    }

    @Override
    public List<Restaurant> searchRestaurant(String keyword) {

        List<Restaurant> restaurantList = new ArrayList<>();

        String sql = """
                SELECT *
                FROM restaurants
                WHERE restaurant_name LIKE ?
                AND is_active = TRUE
                ORDER BY rating DESC
                """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + keyword + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Restaurant restaurant = new Restaurant();

                restaurant.setRestaurantId(rs.getInt("restaurant_id"));
                restaurant.setRestaurantName(rs.getString("restaurant_name"));
                restaurant.setDescription(rs.getString("description"));
                restaurant.setAddress(rs.getString("address"));
                restaurant.setPhone(rs.getString("phone"));
                restaurant.setImage(rs.getString("image"));
                restaurant.setRating(rs.getDouble("rating"));
                restaurant.setDeliveryTime(rs.getInt("delivery_time"));
                restaurant.setActive(rs.getBoolean("is_active"));

                restaurantList.add(restaurant);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return restaurantList;
    }

    @Override
    public int updateRestaurant(Restaurant restaurant) {

        String sql = """
                UPDATE restaurants
                SET restaurant_name=?,
                    description=?,
                    address=?,
                    phone=?,
                    image=?,
                    rating=?,
                    delivery_time=?,
                    is_active=?
                WHERE restaurant_id=?
                """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, restaurant.getRestaurantName());
            ps.setString(2, restaurant.getDescription());
            ps.setString(3, restaurant.getAddress());
            ps.setString(4, restaurant.getPhone());
            ps.setString(5, restaurant.getImage());
            ps.setDouble(6, restaurant.getRating());
            ps.setInt(7, restaurant.getDeliveryTime());
            ps.setBoolean(8, restaurant.isActive());
            ps.setInt(9, restaurant.getRestaurantId());

            return ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public int deleteRestaurant(int restaurantId) {

        String sql = "DELETE FROM restaurants WHERE restaurant_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, restaurantId);

            return ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }
}