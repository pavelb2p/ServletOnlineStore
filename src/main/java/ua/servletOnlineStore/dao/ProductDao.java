package ua.servletOnlineStore.dao;

import ua.servletOnlineStore.model.entity.Cart;
import ua.servletOnlineStore.model.entity.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {

    private Connection connection;

    private String query;

    private PreparedStatement preparedStatement;

    private ResultSet resultSet;

    public ProductDao() {
    }

    public ProductDao(Connection connection) {
        this.connection = connection;
    }

    public List<Product> getAllProduct() {
        List<Product> products = new ArrayList<>();

        try {
            query = "select * from products";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Product row = new Product();
                row.setId(resultSet.getInt("id"));
                row.setName(resultSet.getString("name"));
                row.setCategory(resultSet.getString("category"));
                row.setPrice(resultSet.getDouble("price"));
                row.setImage(resultSet.getString("image"));

                products.add(row);

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return products;
    }

    public List<Cart> getCartProducts(ArrayList<Cart> cartList) {
        List<Cart> products = new ArrayList<Cart>();
        try {
            if (cartList.size() > 0) {
                for (Cart item : cartList) {
                    query = "select * from products where id=?";
                    preparedStatement = this.connection.prepareStatement(query);
                    preparedStatement.setInt(1, item.getId());
                    resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()) {
                        Cart row = new Cart();
                        row.setId(resultSet.getInt("id"));
                        row.setName(resultSet.getString("name"));
                        row.setCategory(resultSet.getString("category"));
                        row.setPrice(resultSet.getDouble("price") * item.getQuantity());
                        row.setQuantity(item.getQuantity());
                        products.add(row);
                    }
                }
            }
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
            throwables.printStackTrace();
        }
        return products;
    }

    public double getTotalCartPrice(ArrayList<Cart> cartList) {
        double sum = 0;

        try {
            if (cartList.size() > 0) {
                for (Cart item : cartList) {
                    query = "select price from products where id=?";
                    preparedStatement = this.connection.prepareStatement(query);
                    preparedStatement.setInt(1, item.getId());
                    resultSet = preparedStatement.executeQuery();

                    while (resultSet.next()) {
                        sum += resultSet.getDouble("price") * item.getQuantity();
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return sum;
    }
}
