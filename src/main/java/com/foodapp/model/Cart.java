package com.foodapp.model;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {

    private final Map<Integer, CartItem> items = new LinkedHashMap<>();

    public void addItem(Menu menu) {
        CartItem item = items.get(menu.getMenuId());

        if (item == null) {
            items.put(menu.getMenuId(), new CartItem(menu, 1));
        } else {
            item.setQuantity(item.getQuantity() + 1);
        }
    }

    public void removeItem(int menuId) {
        items.remove(menuId);
    }

    public void updateQuantity(int menuId, int quantity) {
        CartItem item = items.get(menuId);

        if (item != null) {
            item.setQuantity(quantity);
        }
    }

    public Collection<CartItem> getItems() {
        return items.values();
    }

    public BigDecimal getGrandTotal() {

        BigDecimal total = BigDecimal.ZERO;

        for (CartItem item : items.values()) {
            total = total.add(item.getSubtotal());
        }

        return total;
    }

    public void clearCart() {
        items.clear();
    }
}