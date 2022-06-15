package com.xiaoyuan.usermanager.utils;

import java.sql.*;

public class JDBC {

    private final String url = "jdbc:mysql://127.0.0.1:3306/mshd?serverTimezone=GMT";
    private final String driverName = "com.mysql.cj.jdbc.Driver";
    private final String username = "root";
    private final String password = "woshimaomao";

    private static Connection conn = null;

    public JDBC() throws ClassNotFoundException, SQLException {
        Class.forName(driverName);
        conn = DriverManager.getConnection(url, username, password);
    }

    public ResultSet query(String sql) throws SQLException {
        Statement stmt = conn.createStatement();
        return stmt.executeQuery(sql);
    }

    public static Connection getConnection() {
        return conn;
    }

    public void closeConnection() throws SQLException {
        conn.close();
    }
}
