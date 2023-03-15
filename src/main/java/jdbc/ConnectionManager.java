package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static final String URl = "jdbc:postgresql://localhost:5432/skypro";
    private static final String USER = "postgres";
    private static final String PASSWORD = "15MoOndjkr";

    private ConnectionManager() {}

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URl, USER, PASSWORD);
    }
}