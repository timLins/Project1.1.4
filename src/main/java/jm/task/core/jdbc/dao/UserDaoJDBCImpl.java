package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Connection connection = Util.getConnection();
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String createTable = "create table if not exists users ( " +
                "user_id bigint not null primary key auto_increment," +
                "first_name varchar(50)," +
                "last_name varchar(50)," +
                "age integer );";
        try( PreparedStatement preparedStatement =  connection.prepareStatement(createTable)) {
            preparedStatement.execute();
            System.out.println("Table was created");
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }

    public void dropUsersTable() {
        String deleteTable = "drop table if exists users";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteTable)) {
            preparedStatement.execute();
            System.out.println("Table users was deleted");
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        String saveUser = "insert into users (first_name, last_name, age) values(?,?,?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(saveUser)) {
           preparedStatement.setString(1,name);
           preparedStatement.setString(2,lastName);
           preparedStatement.setByte(3,age);
            preparedStatement.executeUpdate();
            System.out.println("User с именем – "+name+ " добавлен в базу данных");
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }

    public void removeUserById(long id) {
        String removeUser = "delete from users where user_id ="+id+";";
        try (PreparedStatement preparedStatement = connection.prepareStatement(removeUser)){
            preparedStatement.execute();
            System.out.println("User with id = "+ id + "was deleted");

        } catch (SQLException e ){
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        String getAllUsers = "select * from users;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(getAllUsers)) {
            ResultSet result = preparedStatement.executeQuery();
           while (result.next()) {
               list.add
                       (new User(result.getString(2),result.getString(3),result.getByte(4)));
           }
            System.out.println(list);
        } catch (SQLException e ) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        return list;
    }

    public void cleanUsersTable() {
        String clearTable = "truncate users";
        try (PreparedStatement statement = connection.prepareStatement(clearTable)){
            statement.execute();
            System.out.println("table clear");
        } catch (SQLException e ) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }

    }
}
