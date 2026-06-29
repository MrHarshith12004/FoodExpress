package com.foodapp.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.foodapp.dao.MenuDAO;
import com.foodapp.model.Menu;
import com.foodapp.util.DBConnection;

public class MenuDAOImpl implements MenuDAO {

    @Override
    public int addMenu(Menu menu) {

        String sql = """
                INSERT INTO menu
                (restaurant_id,
                food_name,
                description,
                price,
                category,
                is_veg,
                image,
                rating,
                is_available)
                VALUES(?,?,?,?,?,?,?,?,?)
                """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, menu.getRestaurantId());
            ps.setString(2, menu.getFoodName());
            ps.setString(3, menu.getDescription());
            ps.setBigDecimal(4, menu.getPrice());
            ps.setString(5, menu.getCategory());
            ps.setBoolean(6, menu.isVeg());
            ps.setString(7, menu.getImage());
            ps.setDouble(8, menu.getRating());
            ps.setBoolean(9, menu.isAvailable());

            return ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public Menu getMenuById(int menuId) {

        String sql = "SELECT * FROM menu WHERE menu_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, menuId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Menu menu = new Menu();

                menu.setMenuId(rs.getInt("menu_id"));
                menu.setRestaurantId(rs.getInt("restaurant_id"));
                menu.setFoodName(rs.getString("food_name"));
                menu.setDescription(rs.getString("description"));
                menu.setPrice(rs.getBigDecimal("price"));
                menu.setCategory(rs.getString("category"));
                menu.setVeg(rs.getBoolean("is_veg"));
                menu.setImage(rs.getString("image"));
                menu.setRating(rs.getDouble("rating"));
                menu.setAvailable(rs.getBoolean("is_available"));

                return menu;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Menu> getMenuByRestaurant(int restaurantId) {

        List<Menu> menuList = new ArrayList<>();

        String sql = """
                SELECT *
                FROM menu
                WHERE restaurant_id=?
                AND is_available=TRUE
                ORDER BY category, food_name
                """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, restaurantId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Menu menu = new Menu();

                menu.setMenuId(rs.getInt("menu_id"));
                menu.setRestaurantId(rs.getInt("restaurant_id"));
                menu.setFoodName(rs.getString("food_name"));
                menu.setDescription(rs.getString("description"));
                menu.setPrice(rs.getBigDecimal("price"));
                menu.setCategory(rs.getString("category"));
                menu.setVeg(rs.getBoolean("is_veg"));
                menu.setImage(rs.getString("image"));
                menu.setRating(rs.getDouble("rating"));
                menu.setAvailable(rs.getBoolean("is_available"));

                menuList.add(menu);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return menuList;
    }

    @Override
    public List<Menu> getAllMenu() {

        List<Menu> menuList = new ArrayList<>();

        String sql = """
                SELECT *
                FROM menu
                ORDER BY restaurant_id, category, food_name
                """;

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement()) {

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                Menu menu = new Menu();

                menu.setMenuId(rs.getInt("menu_id"));
                menu.setRestaurantId(rs.getInt("restaurant_id"));
                menu.setFoodName(rs.getString("food_name"));
                menu.setDescription(rs.getString("description"));
                menu.setPrice(rs.getBigDecimal("price"));
                menu.setCategory(rs.getString("category"));
                menu.setVeg(rs.getBoolean("is_veg"));
                menu.setImage(rs.getString("image"));
                menu.setRating(rs.getDouble("rating"));
                menu.setAvailable(rs.getBoolean("is_available"));

                menuList.add(menu);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return menuList;
    }

    @Override
    public int updateMenu(Menu menu) {

        String sql = """
                UPDATE menu
                SET
                restaurant_id=?,
                food_name=?,
                description=?,
                price=?,
                category=?,
                is_veg=?,
                image=?,
                rating=?,
                is_available=?
                WHERE menu_id=?
                """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, menu.getRestaurantId());
            ps.setString(2, menu.getFoodName());
            ps.setString(3, menu.getDescription());
            ps.setBigDecimal(4, menu.getPrice());
            ps.setString(5, menu.getCategory());
            ps.setBoolean(6, menu.isVeg());
            ps.setString(7, menu.getImage());
            ps.setDouble(8, menu.getRating());
            ps.setBoolean(9, menu.isAvailable());
            ps.setInt(10, menu.getMenuId());

            return ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public int deleteMenu(int menuId) {

        String sql = "DELETE FROM menu WHERE menu_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, menuId);

            return ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

}