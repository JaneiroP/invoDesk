package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLiteConexion {
    public static Connection connect() {
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:data/database3.db";
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
        } catch (Exception e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
        return conn;
    }

    public static void main(String[] args) {
        connect(); // call connection method
    }
}
