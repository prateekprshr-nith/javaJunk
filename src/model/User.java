package model;

import java.sql.*;
import java.util.ArrayList;

/**
 * User class, acts as a model for users database table
 *
 * @author Pratee Prasher
 * @author prateekprshr@gmail.com
 */
public class User {
    private String username;
    private String password;
    private static Connection connection = null;


    static {
        String user = "root";
        String password = "thisismysqlroot";
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/taskDb";

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
    }


    public User() {
        username = null;
        password = null;
    }


    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public void setUsername(String username) {
        this.username = username;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public String getUsername() {
        return username;
    }


    public String getPassword() {
        return password;
    }


    @Override
    public String toString() {
        return "Username: " + username + " Password: " + password;
    }


    public static ArrayList<User> all() {
        ArrayList<User> users = new ArrayList<User>();

        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM users";

            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                users.add(new User(results.getString("username"), results.getString("password")));
            }

            statement.close();
        } catch (SQLException e) {
            System.out.println("Database error: " + e);
        }

        return users;
    }


    public static User find(String username) {
        User user = null;

        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM users WHERE username = '" + username + "'";
            System.out.println(query);
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.first()) {
                user = new User(resultSet.getString("username"), resultSet.getString("password"));
            }

            System.out.println("Rows fetched:");

            statement.close();
        } catch (SQLException e) {
            System.out.println("Database error here: " + e);
        }

        return user;
    }


    public void save() {
        try {
            Statement statement = connection.createStatement();
            String query = "INSERT INTO users VALUE " +
                    "('" + username + "', '" + password + "')";

            int count = statement.executeUpdate(query);

            System.out.println("Rows updated:" + count);

            statement.close();
        } catch (SQLException e) {
            System.out.println("Database error here: " + e);
        }
    }


    public void delete() {
        try {
            Statement statement = connection.createStatement();
            String query = "DELETE FROM users WHERE username = '" + username + "'";

            int count = statement.executeUpdate(query);

            System.out.println("Rows deleted:" + count);

            statement.close();
        } catch (SQLException e) {
            System.out.println("Database error here: " + e);
        }
    }
}
