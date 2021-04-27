package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DbConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/web_services";
    private static final String USER = "postgres";
    private static final String PASSWORD = "pass4";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, "hey", e);
        }
        return connection;
    }
}
