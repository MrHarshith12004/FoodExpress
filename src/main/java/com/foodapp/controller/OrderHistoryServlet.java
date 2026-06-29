package com.foodapp.controller;

import java.io.IOException;
import java.util.List;

import com.foodapp.dao.OrderDAO;
import com.foodapp.daoimpl.OrderDAOImpl;
import com.foodapp.model.Order;
import com.foodapp.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/orders")
public class OrderHistoryServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private OrderDAO orderDAO;

    @Override
    public void init() throws ServletException {
        orderDAO = new OrderDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loggedUser") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        User loggedUser = (User) session.getAttribute("loggedUser");
        List<Order> orderList = orderDAO.getOrdersByUser(loggedUser.getUserId());

        request.setAttribute("loggedUser", loggedUser);
        request.setAttribute("orders", orderList);

        // Pass success query params if they exist
        String success = request.getParameter("success");
        if (success != null) {
            request.setAttribute("successMessage", "Order Placed Successfully!");
            request.setAttribute("newOrderId", request.getParameter("orderId"));
        }

        request.getRequestDispatcher("/jsp/orders.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
