import dao.ProductDAO;
import models.Product;

import java.math.BigDecimal;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ProductDAO dao = new ProductDAO();

        // Ensure the products table exists
        dao.createTableIfNotExists();

//        // Optional: Insert a sample product
//        Product sample = new Product("Sample Product", 1002, "Demo description", new BigDecimal("99.99"), new BigDecimal("59.99"));
//        dao.createProduct(sample);

        // Print all products
        List<Product> products = dao.getAllProducts();
        for (Product p : products) {
            System.out.println("[" + p.getReferenceCode() + "] " + p.getProductName() + " - $" + p.getPriceUnit());
        }
    }
}
