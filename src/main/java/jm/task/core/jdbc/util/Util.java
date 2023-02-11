package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    public static final String URL = "jdbc:mysql://localhost:3306/mydbtest";
    public static final String NAME = "root";
    public static final String PASSWORD = "root";
    // реализуйте настройку соеденения с БД
    public static Connection getConnection() {
        try  {
            Connection connection = DriverManager.getConnection(URL,NAME,PASSWORD);
            if(connection != null) {
                System.out.println("Connection is running");
               return connection;
            }
        } catch (SQLException e ) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e ) {
            e.printStackTrace();
        }
       return null;
    }
}
