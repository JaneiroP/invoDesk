package dao;

import db.SQLiteConexion;
import models.Product;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    public void createTableIfNotExists() {
        String sql = """
        CREATE TABLE IF NOT EXISTS products (
            reference_code INTEGER PRIMARY KEY,
            product_name TEXT NOT NULL,
            description TEXT,
            price_unit DECIMAL(10, 2),
            cost DECIMAL(10, 2)
        )
    """;

        try (Connection conn = SQLiteConexion.connect();
             Statement stmt = conn.createStatement()) {

            stmt.execute(sql);
            System.out.println("Table 'products' ready.");
        } catch (SQLException e) {
            System.out.println("Table creation failed: " + e.getMessage());
        }
    }


    public void createProduct(Product product) {
        String sql = "INSERT INTO products (reference_code, product_name, description, price_unit, cost) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = SQLiteConexion.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, product.getReferenceCode());
            pstmt.setString(2, product.getProductName());
            pstmt.setString(3, product.getDescription());
            pstmt.setBigDecimal(4, product.getPriceUnit());
            pstmt.setBigDecimal(5, product.getCost());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Create failed: " + e.getMessage());
        }
    }

    public List<Product> getAllProducts() {
        String sql = "SELECT * FROM products";
        List<Product> productList = new ArrayList<>();

        try (Connection conn = SQLiteConexion.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Product p = new Product(
                        rs.getString("product_name"),
                        rs.getInt("reference_code"),
                        rs.getString("description"),
                        rs.getBigDecimal("price_unit"),
                        rs.getBigDecimal("cost")
                );
                productList.add(p);
            }

        } catch (SQLException e) {
            System.out.println("Read failed: " + e.getMessage());
        }

        return productList;
    }

    public void updateProduct(Product product) {
        String sql = "UPDATE products SET product_name = ?, description = ?, price_unit = ?, cost = ? WHERE reference_code = ?";
        try (Connection conn = SQLiteConexion.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, product.getProductName());
            pstmt.setString(2, product.getDescription());
            pstmt.setBigDecimal(3, product.getPriceUnit());
            pstmt.setBigDecimal(4, product.getCost());
            pstmt.setInt(5, product.getReferenceCode());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Update failed: " + e.getMessage());
        }
    }

    public void deleteProduct(int referenceCode) {
        String sql = "DELETE FROM products WHERE reference_code = ?";
        try (Connection conn = SQLiteConexion.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, referenceCode);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Delete failed: " + e.getMessage());
        }
    }
}
