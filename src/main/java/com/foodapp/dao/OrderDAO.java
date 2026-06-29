package com.foodapp.dao;

import java.util.List;
import com.foodapp.model.Order;

public interface OrderDAO {

    int placeOrder(Order order);

    Order getOrderById(int orderId);

    List<Order> getOrdersByUser(int userId);

    List<Order> getAllOrders();

    int updateOrderStatus(int orderId, String status);

    int cancelOrder(int orderId);
}