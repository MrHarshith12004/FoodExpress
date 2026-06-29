package com.foodapp.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collection;

import com.foodapp.dao.OrderDAO;
import com.foodapp.dao.OrderItemDAO;
import com.foodapp.daoimpl.OrderDAOImpl;
import com.foodapp.daoimpl.OrderItemDAOImpl;
import com.foodapp.model.Cart;
import com.foodapp.model.CartItem;
import com.foodapp.model.Order;
import com.foodapp.model.OrderItem;
import com.foodapp.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private OrderDAO orderDAO;
    private OrderItemDAO orderItemDAO;

    @Override
    public void init() throws ServletException {
        orderDAO = new OrderDAOImpl();
        orderItemDAO = new OrderItemDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loggedUser") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null || cart.getItems().isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/home");
            return;
        }

        User loggedUser = (User) session.getAttribute("loggedUser");
        request.setAttribute("loggedUser", loggedUser);
        request.setAttribute("cart", cart);

        request.getRequestDispatcher("/jsp/checkout.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loggedUser") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        User loggedUser = (User) session.getAttribute("loggedUser");
        Cart cart = (Cart) session.getAttribute("cart");

        if (cart == null || cart.getItems().isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/home");
            return;
        }

        String deliveryAddress = request.getParameter("deliveryAddress");
        String paymentMethod = request.getParameter("paymentMethod");

        if (deliveryAddress == null || deliveryAddress.trim().isEmpty() ||
            paymentMethod == null || paymentMethod.trim().isEmpty()) {
            
            request.setAttribute("error", "Delivery Address and Payment Method are required.");
            request.setAttribute("loggedUser", loggedUser);
            request.setAttribute("cart", cart);
            request.getRequestDispatcher("/jsp/checkout.jsp").forward(request, response);
            return;
        }

        // Get restaurant ID from first item in the cart
        Collection<CartItem> cartItems = cart.getItems();
        int restaurantId = 0;
        if (!cartItems.isEmpty()) {
            CartItem firstItem = cartItems.iterator().next();
            restaurantId = firstItem.getMenu().getRestaurantId();
        }

        // 1. Create and save order
        Order order = new Order();
        order.setUserId(loggedUser.getUserId());
        order.setRestaurantId(restaurantId);
        order.setTotalAmount(cart.getGrandTotal());
        order.setDeliveryAddress(deliveryAddress);
        order.setPaymentMethod(paymentMethod);
        order.setOrderStatus("Pending");

        int orderId = orderDAO.placeOrder(order);

        if (orderId > 0) {
            // 2. Save each order item
            for (CartItem item : cartItems) {
                OrderItem orderItem = new OrderItem();
                orderItem.setOrderId(orderId);
                orderItem.setMenuId(item.getMenu().getMenuId());
                orderItem.setQuantity(item.getQuantity());
                orderItem.setPrice(item.getMenu().getPrice());
                orderItem.setSubtotal(item.getSubtotal());

                orderItemDAO.addOrderItem(orderItem);
            }

            // 3. Clear session cart
            cart.clearCart();

            // 4. Redirect to order history with success message
            response.sendRedirect(request.getContextPath() + "/orders?success=placed&orderId=" + orderId);
        } else {
            request.setAttribute("error", "Failed to place order. Please try again.");
            request.setAttribute("loggedUser", loggedUser);
            request.setAttribute("cart", cart);
            request.getRequestDispatcher("/jsp/checkout.jsp").forward(request, response);
        }
    }
}
