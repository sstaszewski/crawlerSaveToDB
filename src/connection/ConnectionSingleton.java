package connection;

import java.sql.*;

public class ConnectionSingleton {
    private static ConnectionSingleton instance;
    private ConnectionSingleton() {
    }
    public static ConnectionSingleton getInstance() {
        if (instance == null) {
            instance = new ConnectionSingleton();
        }
        return instance;
    }
    public static Connection getConnection() {
        String url = "jdbc:mysql://localhost:3306/samochody";
        String user = "java";
        String password = "java";
        Connection connection= null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}