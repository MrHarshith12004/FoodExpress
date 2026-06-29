package com.foodapp.controller;

import java.io.IOException;
import java.util.List;

import com.foodapp.dao.MenuDAO;
import com.foodapp.dao.RestaurantDAO;
import com.foodapp.daoimpl.MenuDAOImpl;
import com.foodapp.daoimpl.RestaurantDAOImpl;
import com.foodapp.model.Menu;
import com.foodapp.model.Restaurant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/restaurant")
public class RestaurantServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private RestaurantDAO restaurantDAO;
    private MenuDAO menuDAO;

    @Override
    public void init() throws ServletException {

        restaurantDAO = new RestaurantDAOImpl();
        menuDAO = new MenuDAOImpl();

    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
        response.setHeader("Pragma","no-cache");
        response.setDateHeader("Expires",0);

        HttpSession session = request.getSession(false);

        if(session==null || session.getAttribute("loggedUser")==null)
        {
            response.sendRedirect(request.getContextPath()+"/login");
            return;
        }

        String id=request.getParameter("restaurantId");

        if(id==null || id.isEmpty())
        {
            response.sendRedirect(request.getContextPath()+"/home");
            return;
        }

        int restaurantId=Integer.parseInt(id);

        Restaurant restaurant=
                restaurantDAO.getRestaurantById(restaurantId);

        if(restaurant==null)
        {
            response.sendRedirect(request.getContextPath()+"/home");
            return;
        }

        List<Menu> menuList=
                menuDAO.getMenuByRestaurant(restaurantId);

        request.setAttribute("restaurant",restaurant);

        request.setAttribute("menuList",menuList);

        request.getRequestDispatcher("/jsp/restaurant.jsp")
               .forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request,response);

    }

}