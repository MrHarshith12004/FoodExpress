package com.foodapp.controller;

import java.io.IOException;

import com.foodapp.dao.UserDAO;
import com.foodapp.daoimpl.UserDAOImpl;
import com.foodapp.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        userDAO = new UserDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/jsp/register.jsp")
               .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");

        // Basic Validation
        if (fullName == null || fullName.trim().isEmpty()
                || email == null || email.trim().isEmpty()
                || password == null || password.trim().isEmpty()
                || phone == null || phone.trim().isEmpty()
                || address == null || address.trim().isEmpty()) {

            request.setAttribute("error", "All fields are required.");

            request.getRequestDispatcher("/jsp/register.jsp")
                   .forward(request, response);

            return;
        }

        // Check Email Exists
        User existingUser = userDAO.getUserByEmail(email);

        if (existingUser != null) {

            request.setAttribute("error",
                    "Email already registered.");

            request.getRequestDispatcher("/jsp/register.jsp")
                   .forward(request, response);

            return;
        }

        User user = new User();

        user.setFullName(fullName);
        user.setEmail(email);
        user.setPassword(password);
        user.setPhone(phone);
        user.setAddress(address);
        user.setRole("CUSTOMER");

        int result = userDAO.addUser(user);

        if (result > 0) {

            response.sendRedirect(
                    request.getContextPath()
                    + "/login?success=registered");

        } else {

            request.setAttribute("error",
                    "Registration Failed.");

            request.getRequestDispatcher("/jsp/register.jsp")
                   .forward(request, response);
        }
    }

}