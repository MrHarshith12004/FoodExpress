package com.foodapp.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.foodapp.dao.OrderItemDAO;
import com.foodapp.model.OrderItem;
import com.foodapp.util.DBConnection;

public class OrderItemDAOImpl implements OrderItemDAO {

    @Override
    public int addOrderItem(OrderItem item) {

        String sql = """
                INSERT INTO order_items
                (
                    order_id,
                    menu_id,
                    quantity,
                    price,
                    subtotal
                )
                VALUES(?,?,?,?,?)
                """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, item.getOrderId());
            ps.setInt(2, item.getMenuId());
            ps.setInt(3, item.getQuantity());
            ps.setBigDecimal(4, item.getPrice());
            ps.setBigDecimal(5, item.getSubtotal());

            return ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public List<OrderItem> getItemsByOrder(int orderId) {

        List<OrderItem> items = new ArrayList<>();

        String sql = """
                SELECT *
                FROM order_items
                WHERE order_id=?
                """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, orderId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                OrderItem item = new OrderItem();

                item.setOrderItemId(rs.getInt("order_item_id"));
                item.setOrderId(rs.getInt("order_id"));
                item.setMenuId(rs.getInt("menu_id"));
                item.setQuantity(rs.getInt("quantity"));
                item.setPrice(rs.getBigDecimal("price"));
                item.setSubtotal(rs.getBigDecimal("subtotal"));

                items.add(item);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return items;
    }

}