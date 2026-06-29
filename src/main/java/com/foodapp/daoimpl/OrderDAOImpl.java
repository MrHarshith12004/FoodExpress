package com.foodapp.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.foodapp.dao.OrderDAO;
import com.foodapp.model.Order;
import com.foodapp.util.DBConnection;

public class OrderDAOImpl implements OrderDAO {

    @Override
    public int placeOrder(Order order) {

        String sql = """
                INSERT INTO orders
                (
                user_id,
                restaurant_id,
                total_amount,
                delivery_address,
                payment_method,
                order_status
                )
                VALUES(?,?,?,?,?,?)
                """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, order.getUserId());
            ps.setInt(2, order.getRestaurantId());
            ps.setBigDecimal(3, order.getTotalAmount());
            ps.setString(4, order.getDeliveryAddress());
            ps.setString(5, order.getPaymentMethod());
            ps.setString(6, order.getOrderStatus());

            int rows = ps.executeUpdate();

            if (rows > 0) {

                ResultSet rs = ps.getGeneratedKeys();

                if (rs.next()) {
                    return rs.getInt(1);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public Order getOrderById(int orderId) {

        String sql = "SELECT * FROM orders WHERE order_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, orderId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Order order = new Order();

                order.setOrderId(rs.getInt("order_id"));
                order.setUserId(rs.getInt("user_id"));
                order.setRestaurantId(rs.getInt("restaurant_id"));
                order.setTotalAmount(rs.getBigDecimal("total_amount"));
                order.setDeliveryAddress(rs.getString("delivery_address"));
                order.setPaymentMethod(rs.getString("payment_method"));
                order.setOrderStatus(rs.getString("order_status"));
                order.setOrderDate(rs.getTimestamp("order_date"));

                return order;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Order> getOrdersByUser(int userId) {

        List<Order> orders = new ArrayList<>();

        String sql = """
                SELECT *
                FROM orders
                WHERE user_id=?
                ORDER BY order_date DESC
                """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Order order = new Order();

                order.setOrderId(rs.getInt("order_id"));
                order.setUserId(rs.getInt("user_id"));
                order.setRestaurantId(rs.getInt("restaurant_id"));
                order.setTotalAmount(rs.getBigDecimal("total_amount"));
                order.setDeliveryAddress(rs.getString("delivery_address"));
                order.setPaymentMethod(rs.getString("payment_method"));
                order.setOrderStatus(rs.getString("order_status"));
                order.setOrderDate(rs.getTimestamp("order_date"));

                orders.add(order);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return orders;
    }

    @Override
    public List<Order> getAllOrders() {

        List<Order> orders = new ArrayList<>();

        String sql = """
                SELECT *
                FROM orders
                ORDER BY order_date DESC
                """;

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement()) {

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                Order order = new Order();

                order.setOrderId(rs.getInt("order_id"));
                order.setUserId(rs.getInt("user_id"));
                order.setRestaurantId(rs.getInt("restaurant_id"));
                order.setTotalAmount(rs.getBigDecimal("total_amount"));
                order.setDeliveryAddress(rs.getString("delivery_address"));
                order.setPaymentMethod(rs.getString("payment_method"));
                order.setOrderStatus(rs.getString("order_status"));
                order.setOrderDate(rs.getTimestamp("order_date"));

                orders.add(order);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return orders;
    }

    @Override
    public int updateOrderStatus(int orderId, String status) {

        String sql = """
                UPDATE orders
                SET order_status=?
                WHERE order_id=?
                """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setInt(2, orderId);

            return ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public int cancelOrder(int orderId) {

        String sql = """
                UPDATE orders
                SET order_status='Cancelled'
                WHERE order_id=?
                """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, orderId);

            return ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

}