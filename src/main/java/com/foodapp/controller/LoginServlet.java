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
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

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

        request.getRequestDispatcher("/jsp/login.jsp")
               .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Basic Validation
        if (email == null || email.trim().isEmpty()
                || password == null || password.trim().isEmpty()) {

            request.setAttribute("error", "Email and Password are required.");

            request.getRequestDispatcher("/jsp/login.jsp")
                   .forward(request, response);

            return;
        }

        User user = userDAO.login(email, password);

        if (user != null) {

            HttpSession session = request.getSession();

            session.setAttribute("loggedUser", user);

            session.setMaxInactiveInterval(30 * 60);

            response.sendRedirect(request.getContextPath() + "/home");

        } else {

            request.setAttribute("error",
                    "Invalid Email or Password");

            request.getRequestDispatcher("/jsp/login.jsp")
                   .forward(request, response);
        }
    }

}