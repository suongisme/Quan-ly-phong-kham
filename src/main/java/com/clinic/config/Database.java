package com.clinic.config;

import com.clinic.utils.SystemUtils;

import java.sql.*;
import java.util.Objects;

public class Database {

    private static Connection connection;
    private static String username;
    private static String url;
    private static String password;
    private static String driverClassName;

    static {
        username = SystemUtils.getValueConfig("datasource.username");
        url = SystemUtils.getValueConfig("datasource.url");
        password = SystemUtils.getValueConfig("datasource.password");
        driverClassName = SystemUtils.getValueConfig("datasource.driver-class-name");
    }

    private static void openConnection() {
        try {
            Class.forName(driverClassName);
            connection = DriverManager.getConnection(url, username, password);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public static PreparedStatement save(String sql, Object...params) {
        try {
            return preparedStatement(sql, params);
        } catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static ResultSet query(String sql, Object... params) {
        try {
            return preparedStatement(sql, params).executeQuery();
        } catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private static PreparedStatement preparedStatement(String sql, Object...params) throws SQLException {
        if (Objects.isNull(connection) || connection.isClosed()) {
            openConnection();
        }
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        if (Objects.isNull(params)) return preparedStatement;

        for (int i = 0; i < params.length; i++) {
            preparedStatement.setObject(i + 1, params[i]);
        }
        return preparedStatement;
    }

    public static void closeConnection() {
        try {
            if (Objects.nonNull(connection) && !connection.isClosed()) {
                connection.close();
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}