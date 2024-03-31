package thethelafaltein.dao;

import java.io.EOFException;
import java.sql.*;

public class Config {
    static final String DB_URL = "jdbc:mysql://localhost";
    static final String USER = "guest";
    static final String PASS = "guest123";

    private static final String CREATE_CHATS_TABLE = "CREATE TABLE IF NOT EXISTS chats (" +
        "id BIGINT AUTO_INCREMENT PRIMARY KEY," +
        "customer_id BIGINT NOT NULL," +
        "message LARGETEXT NOT NULL"+
        "status INT NOT NULL" + 
        "created_at DATETIME NOT NULL"+
        "updated_at DATETIME NOT NULL)";

    private static final String CREATE_USERS_TABLE = "CREATE TABLE IF NOT EXISTS users (" +
        "id BIGINT AUTO_INCREMENT PRIMARY KEY,"  +
        "username VARCHAR(100) NOT NULL"+
        "email VARCHAR(100) NOT NULL" +
        "password VARCHAR(100) NOT NULL"+
        "created_at DATETIME NOT NULL"+
        "updated_at DATETIME NOT NULL"+
        "UNIQUE(username, password)"+
        ")";          

    public static void createTable(String sqlStatement, Connection connection, Statement s){
        try {
            s.executeUpdate(sqlStatement);

            System.out.println("Table created successfully.");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        try (
            // Establishing a connection to the MySQL database
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
            // Creating a statement object
            Statement statement = connection.createStatement();
        ){
            createTable(CREATE_CHATS_TABLE,connection,statement);

            createTable(CREATE_USERS_TABLE,connection,statement);

            Users users= new Users(connection);

            users.insertUser("thethela", "thethela@hotmail.com", "123");

            System.out.println("User hardcoded");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    
}
