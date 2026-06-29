package com.foodapp.dao;

import java.util.List;
import com.foodapp.model.OrderItem;

public interface OrderItemDAO {

    int addOrderItem(OrderItem item);

    List<OrderItem> getItemsByOrder(int orderId);

}