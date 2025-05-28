import java.sql.*;

public class H2Conexion {
    public static void main(String[] args) {
        String url = "jdbc:h2:./data/mydb"; // will create ./data/mydb.mv.db

        try (Connection conn = DriverManager.getConnection(url, "sa", "")) {
            Statement stmt = conn.createStatement();
            stmt.execute("CREATE TABLE IF NOT EXISTS products (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255), price DECIMAL)");
            stmt.execute("INSERT INTO products (name, priceUnit) VALUES ('Notebook', 5.50)");

            ResultSet rs = stmt.executeQuery("SELECT * FROM products");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " - " + rs.getString("name") + " - $" + rs.getDouble("priceUnit"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}