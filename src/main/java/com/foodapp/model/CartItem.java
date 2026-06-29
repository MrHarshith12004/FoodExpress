package com.foodapp.model;

import java.math.BigDecimal;

public class CartItem {

    private Menu menu;
    private int quantity;

    public CartItem() {
    }

    public CartItem(Menu menu, int quantity) {
        this.menu = menu;
        this.quantity = quantity;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSubtotal() {
        return menu.getPrice().multiply(BigDecimal.valueOf(quantity));
    }
}