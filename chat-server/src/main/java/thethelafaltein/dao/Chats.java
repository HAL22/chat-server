package thethelafaltein.dao;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import thethelafaltein.model.Chat;
import thethelafaltein.model.ChatStatus;

public class Chats {
    Connection connection;

    public Chats(Connection connection){
        this.connection = connection;
    }

    public void insertChat(long customerId, String message, ChatStatus chatStatus){
         String INSERT_QUERY = String.format("INSERT INTO chats (customer_id,message,status,created_at,updated_at)"+
         "VALUES (1%d,2%s,3%d,4%t,5%t)", customerId,message,chatStatus.getValue(),LocalDateTime.now(),LocalDateTime.now());

         try{
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY);

         } catch (SQLException eSqlException){
            eSqlException.printStackTrace();
         }
    }

    public Chat getChat(long chatId){
        String SELECT_QUERY = String.format("SELECT * FROM chats where id =  %s", chatId);

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                return  new Chat(resultSet.getLong("id"),resultSet.getString("message"),resultSet.getTimestamp("created_at").toLocalDateTime(),resultSet.getTimestamp("updated_at").toLocalDateTime(),ChatStatus.valueOf(resultSet.getInt("status")));
            }
         } catch (SQLException eSqlException){
            eSqlException.printStackTrace();
            
         }

         return null;
    }

    public List<Chat> getChatHistory(){
        String SELECT_CHAT_HISTORY = "SELECT * FROM chats WHERE status != 2";

        List<Chat>chats = new ArrayList<>();

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CHAT_HISTORY);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                chats.add( new Chat(resultSet.getLong("id"),resultSet.getString("message"),resultSet.getTimestamp("created_at").toLocalDateTime(),resultSet.getTimestamp("updated_at").toLocalDateTime(),ChatStatus.valueOf(resultSet.getInt("status"))));
            }
        } catch (SQLException eSqlException){
            eSqlException.printStackTrace();
            
        }

        return chats;
        
    }


    public void deleteChat(long chatId){
        String DELETE_CHAT_QUERY = String.format("UPDATE chats SET status = 2 WHERE id = %d and status != 2", chatId);

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CHAT_QUERY);

         } catch (SQLException eSqlException){
            eSqlException.printStackTrace();
         }
    }


}
