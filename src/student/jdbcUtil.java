package student;

import java.sql.*;
import java.util.ResourceBundle;

public class jdbcUtil {
    private jdbcUtil(){}

    private static ResourceBundle bundle = ResourceBundle.getBundle("jdbc");

    static {
        try {
            Class.forName(bundle.getString("jdbc.driver"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        String url = bundle.getString("jdbc.url");
        String user = bundle.getString("jdbc.username");
        String password = bundle.getString("jdbc.password");
        Connection connection = DriverManager.getConnection(url, user, password);
        return connection;
    }

    public static void closeResources(ResultSet resultSet, Statement statement, Connection connection) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

}
