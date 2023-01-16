package com.example.demo;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;


public class AdminDataBaseHandler {

    Connection dbConnection;

    public Connection getDbConnection() throws SQLException, ClassNotFoundException {

        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://172.20.8.18:5432/kp0092_01";

        Properties authorization = new Properties();
        authorization.put("user", "st0092");
        authorization.put("password", "qwerty92");

        dbConnection = DriverManager.getConnection(url, authorization);

        return dbConnection;

    }

}
