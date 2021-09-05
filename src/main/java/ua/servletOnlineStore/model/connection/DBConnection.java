package ua.servletOnlineStore.model.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

public class DBConnection {

    private static Connection connection = null;

    /**
     * Creating a database connection
     * @return
     * */
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        if (Objects.isNull(connection)) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_onlinestore", "root", "root");

            System.out.println("connection");
        }
        return connection;
    }
}
