package com.joe.concurrent.part3;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * ConnectionDispenser
 * <p/>
 * Using ThreadLocal to ensure thread confinement
 *
 * @author Brian Goetz and Tim Peierls
 */
public class ConnectionDispenser {
    static String DB_URL = "jdbc:mysql://123.57.51.177:3307/mall";

    private ThreadLocal<Connection> connectionHolder = ThreadLocal.withInitial(() -> {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, "root", "root");
//            System.out.println(Thread.currentThread().getName() + ": " + "threadLocal init: " + connection);
            System.out.println(Thread.currentThread().getName() + ": " + "threadLocal init: " + connection.hashCode());

            return connection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    });

    public Connection getConnection() {
        return connectionHolder.get();
    }
}
