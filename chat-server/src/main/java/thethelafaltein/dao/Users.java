package thethelafaltein.dao;

import java.sql.*;

import thethelafaltein.model.User;

public class Users {
    Connection connection;

    public Users(Connection connection){
        this.connection = connection;
    }

    public void insertUser(String username,String email,String password){
        String INSERT_QUERY = String.format("INSERT INTO users (username,email,password)"+
        "VALUES (1%s,2%s,3%s)", username,email,password);

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY);

         } catch (SQLException eSqlException){
            eSqlException.printStackTrace();
         }

    }

    public User getUserByUsernameAndPassowrd(String username,String password){
        String SELECT_QUERY = String.format("SELECT * FROM users WHERE username = 1%s and passowrd = 2%s", username,password);

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                return new User(resultSet.getLong("id"),resultSet.getString("username"),resultSet.getString("password"),resultSet.getString("email"),resultSet.getTimestamp("created_at").toLocalDateTime(),resultSet.getTimestamp("updated_at").toLocalDateTime());
            }
        } catch (SQLException eSqlException){
            eSqlException.printStackTrace();
            
        }

        return null;

    }
}
