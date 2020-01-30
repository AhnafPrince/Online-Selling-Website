/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Product;

/**
 *
 * @author ahnaf
 */
public class ProductDAO {

    private final String jdbcURL = "jdbc:mysql://localhost:3306/techparts";
    private final String jdbcUsername = "root";
    private final String jdbcPassword = "";

    private static final String INSERT_PRODUCT_SQL = "INSERT INTO products" + "  (id, productname, type, price, info, image, "
            + "availablequantity, soldquantity) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

    private static final String SELECT_PRODUCT_BY_ID = "select productname, type, price, info, image, "
            + "availablequantity, soldquantity from products where id = ?";

    private static final String SELECT_ALL_PRODUCTS = "select * from products;";

    private static final String DELETE_PRODUCT_SQL = "delete from products where id = ?";

    private static final String UPDATE_PRODUCT_SQL = "update products set productname = ?, type = ?, price =? "
            + "info = ?, image = ?, availablequantity = ? , soldquantity = ?  where id = ?;";

    private static final String CHECK_AVAILABLE_ID = "select id from products where id = ?;";

    protected Connection getConnection(PrintWriter out) {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            out.print("Not!");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (connection == null) {
            out.println("Not Connected");
        }
        return connection;
    }

    public void insertProduct(Product product, PrintWriter out) throws SQLException {
        System.out.println(INSERT_PRODUCT_SQL);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = getConnection(out); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCT_SQL)) {

            preparedStatement.setInt(1, product.getId());
            preparedStatement.setString(2, product.getProductName());
            preparedStatement.setString(3, product.getProductType());
            preparedStatement.setInt(4, product.getPrice());
            preparedStatement.setString(5, product.getProductInfo());
            preparedStatement.setString(6, product.getImageSource());
            preparedStatement.setInt(7, product.getAvailableQuantity());
            preparedStatement.setInt(8, product.getSoldQuantity());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public List< Product> selectAllProducts(PrintWriter out) throws SQLException {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List< Product> products = new ArrayList<>();
        // Step 1: Establishing a Connection
        try {
            Connection connection = getConnection(out);

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCTS);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt(1);
                String productName = rs.getString(2);
                String type = rs.getString(3);
                int price = rs.getInt(4);
                String info = rs.getString(5);
                String image = rs.getString(6);
                int availableQuantity = rs.getInt(7);
                int soldQuantity = rs.getInt(8);
                products.add(new Product(id, productName, type, price, info, image, availableQuantity, soldQuantity));
            }
        } catch (SQLException e) {
            out.println("Could not get product List information " + e.getMessage());
        }
        return products;
    }

    public String[] selectProductByID(int id, PrintWriter out) throws SQLException {

        String[] product = new String[8];
        // using try-with-resources to avoid closing resources (boiler plate code)

        // Step 1: Establishing a Connection
        try {
            Connection connection = getConnection(out);

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_ID);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {

                product[0] = rs.getString(1);
                product[1] = rs.getString(2);
                product[2] = Integer.toString(rs.getInt(3));
                product[3] = rs.getString(4);
                product[4] = rs.getString(5);
                product[5] = Integer.toString(rs.getInt(6));
                product[6] = Integer.toString(rs.getInt(7));

            }
            product[7] = Integer.toString(id);
        } catch (SQLException e) {
            out.println("Could not get product List information " + e.getMessage());
        }
        return product;
    }

    public boolean deleteProduct(int id, PrintWriter out) throws SQLException {
        boolean rowDeleted = false;
        try (Connection connection = getConnection(out); PreparedStatement statement = connection.prepareStatement(DELETE_PRODUCT_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            out.println("Could not delete product List information " + e.getMessage());
        }
        return rowDeleted;
    }

    public boolean checkAvailableId(int id, PrintWriter out) throws SQLException {

        try (Connection connection = getConnection(out); PreparedStatement statement = connection.prepareStatement(CHECK_AVAILABLE_ID);) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (!rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            out.println("Could not get id information " + e.getMessage());
        }
        return false;
    }

    public boolean updateProduct(Product product, PrintWriter out) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection(out); PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT_SQL);) {

            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setString(2, product.getProductType());
            preparedStatement.setInt(3, product.getPrice());
            preparedStatement.setString(4, product.getProductInfo());
            preparedStatement.setString(5, product.getImageSource());
            preparedStatement.setInt(6, product.getAvailableQuantity());
            preparedStatement.setInt(7, product.getSoldQuantity());
            preparedStatement.setInt(8, product.getId());

            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();

            rowUpdated = preparedStatement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    private void printSQLException(SQLException ex) {

        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

}
