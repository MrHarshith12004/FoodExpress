package com.foodapp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Collection;

import com.foodapp.dao.MenuDAO;
import com.foodapp.daoimpl.MenuDAOImpl;
import com.foodapp.model.Cart;
import com.foodapp.model.CartItem;
import com.foodapp.model.Menu;
import com.foodapp.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private MenuDAO menuDAO;

    @Override
    public void init() throws ServletException {
        menuDAO = new MenuDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(true);
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }

        String action = request.getParameter("action");
        if (action == null) {
            action = "view";
        }

        if ("get".equals(action)) {
            sendCartJsonResponse(response, cart);
            return;
        }

        // Default view: forward to cart.jsp page
        User loggedUser = (User) session.getAttribute("loggedUser");
        request.setAttribute("loggedUser", loggedUser);
        request.setAttribute("cart", cart);
        request.getRequestDispatcher("/jsp/cart.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(true);
        User loggedUser = (User) session.getAttribute("loggedUser");

        // AJAX requests check login
        String action = request.getParameter("action");
        if (loggedUser == null) {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.print("{\"success\":false,\"redirect\":\"" + request.getContextPath() + "/login\"}");
            out.flush();
            return;
        }

        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }

        try {
            if ("add".equals(action)) {
                int menuId = Integer.parseInt(request.getParameter("menuId"));
                Menu menu = menuDAO.getMenuById(menuId);
                if (menu != null) {
                    cart.addItem(menu);
                }
            } else if ("update".equals(action)) {
                int menuId = Integer.parseInt(request.getParameter("menuId"));
                int quantity = Integer.parseInt(request.getParameter("quantity"));
                cart.updateQuantity(menuId, quantity);
            } else if ("remove".equals(action)) {
                int menuId = Integer.parseInt(request.getParameter("menuId"));
                cart.removeItem(menuId);
            } else if ("clear".equals(action)) {
                cart.clearCart();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        sendCartJsonResponse(response, cart);
    }

    private void sendCartJsonResponse(HttpServletResponse response, Cart cart) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        int totalItems = 0;
        Collection<CartItem> items = cart.getItems();
        for (CartItem item : items) {
            totalItems += item.getQuantity();
        }

        StringBuilder json = new StringBuilder();
        json.append("{");
        json.append("\"success\":true,");
        json.append("\"totalItems\":").append(totalItems).append(",");
        json.append("\"grandTotal\":").append(cart.getGrandTotal()).append(",");
        json.append("\"items\":[");

        int i = 0;
        for (CartItem item : items) {
            Menu menu = item.getMenu();
            json.append("{");
            json.append("\"menuId\":").append(menu.getMenuId()).append(",");
            json.append("\"foodName\":\"").append(escapeJson(menu.getFoodName())).append("\",");
            json.append("\"price\":").append(menu.getPrice()).append(",");
            json.append("\"quantity\":").append(item.getQuantity()).append(",");
            json.append("\"subtotal\":").append(item.getSubtotal());
            json.append("}");
            if (++i < items.size()) {
                json.append(",");
            }
        }
        json.append("]");
        json.append("}");

        out.print(json.toString());
        out.flush();
    }

    private String escapeJson(String s) {
        if (s == null) return "";
        return s.replace("\\", "\\\\").replace("\"", "\\\"");
    }
}
