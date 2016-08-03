package model;

import java.sql.*;
import java.util.ArrayList;

/**
 * Task class, acts as a model for tasks database table
 *
 * @author Pratee Prasher
 * @author prateekprshr@gmail.com
 */
public class Task {
    private int id;
    private String description;
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


    public Task() {
        id = 0;
        description = null;
    }


    public Task(String description) {
        this.description = description;
    }


    private Task(int id, String description) {
        this.id = id;
        this.description = description;
    }


    public void setId(int id) {
        this.id = id;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public int getId() {
        return id;
    }


    public String getDescription() {
        return description;
    }


    @Override
    public String toString() {
        return "Id: " + id + " Description: " + description;
    }


    public static ArrayList<Task> all() {
        ArrayList<Task> tasks = new ArrayList<Task>();

        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM tasks";

            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                tasks.add(new Task(results.getInt("id"), results.getString("description")));
            }

            statement.close();
        } catch (SQLException e) {
            System.out.println("Database error: " + e);
        }

        return tasks;
    }


    public static Task find(int id) {
        Task task = null;

        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM tasks WHERE id = " + id;

            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.first()) {
                task = new Task(resultSet.getInt("id"), resultSet.getString("description"));
            }

            System.out.println("Rows fetched:");

            statement.close();
        } catch (SQLException e) {
            System.out.println("Database error here: " + e);
        }

        return task;
    }


    public void save() {
        try {
            Statement statement = connection.createStatement();
            String query = "INSERT INTO tasks (description) VALUE " +
                    "('" + description + "')";

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
            String query = "DELETE FROM tasks WHERE id = " + id;

            int count = statement.executeUpdate(query);

            System.out.println("Rows deleted:" + count);

            statement.close();
        } catch (SQLException e) {
            System.out.println("Database error here: " + e);
        }
    }
}