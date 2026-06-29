<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    if (session != null && session.getAttribute("loggedUser") != null) {
        response.sendRedirect(request.getContextPath() + "/home");
    } else {
        response.sendRedirect(request.getContextPath() + "/login");
    }
%>