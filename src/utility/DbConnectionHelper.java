package utility;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

/**
 * This is a helper class, it contains methods for database related operations
 *
 * @author Prateek Prasher
 * @author prateekprshr@gmail.com
 */
public class DbConnectionHelper {
    private static final String user = "root";
    private static final String password = "thisismysqlroot";
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/taskDb";

    /**
     * This methods establishes a connection to a database
     *
     * @return connection The databse connection
     */
    public static Connection getConnection() {
        Connection connection = null;

        try {
            Class.forName(driver).newInstance();
        } catch (Exception e) {
            System.out.println("Unhandled exception: " + e);
        }

        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("Database error: " + e);
        }

        return connection;
    }
}

