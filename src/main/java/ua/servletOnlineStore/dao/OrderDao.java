package ua.servletOnlineStore.dao;

import ua.servletOnlineStore.model.entity.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class OrderDao {

    private Connection connection;
    private String query;
    private PreparedStatement preparedStatement;

    public OrderDao(Connection connection) {
        this.connection = connection;
    }
    /**
     * The method saves the order to the database
     * @param order
     * @return
     */

    public boolean saveOrder(Order order) {

        try {
            query = "insert into orders(p_id,u_id,o_quantity,o_date) values(?,?,?,?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, order.getpId());
            preparedStatement.setInt(2, order.getUId());
            preparedStatement.setInt(3, order.getQuantity());
            preparedStatement.setString(4, order.getDate());
            int resultSet = preparedStatement.executeUpdate();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            System.out.print(e.getMessage());
            return false;
        }


    }
}



