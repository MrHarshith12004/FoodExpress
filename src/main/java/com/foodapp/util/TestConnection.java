package com.foodapp.util;

import java.sql.Connection;

public class TestConnection {

    public static void main(String[] args) {

        try (Connection connection = DBConnection.getConnection()) {

            if (connection != null) {
                System.out.println("Database Connected Successfully!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}