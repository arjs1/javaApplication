import java.sql.Connection;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

import java.sql.ResultSet;

public class Main {
private static Connection connection = null;
private static Statement statement = null;

   public static void insertIntoDatabase(String userID, String userName ) 
   {
       PreparedStatement preparedStatement = null;
       try
     {
           connection = ConnectionClassConn.connectionClass();
           String insertData = "INSERT INTO Student (st_id,st_name) VALUES (?, ?)";
           preparedStatement = connection.prepareStatement(insertData);
           preparedStatement.setString(1,userID);
           preparedStatement.setString(2,userName);
        int rowAffected =   preparedStatement.executeUpdate();
        if(rowAffected>0)
        {
            System.out.println("Inserted Successfully");
       }
     }
        catch (SQLException sqlException){
            System.out.println("Something went wrong while creating student record.");
            System.out.println("Reason: " + sqlException.getMessage());
//          // sqlException.printStackTrace();
        }
     
        
   }   
   
   public static void retrieveData()
   {
     try
     {
           connection = ConnectionClassConn.connectionClass();
           statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Student");
             while (resultSet.next()) {
           
                String username = resultSet.getString("st_name");

                // Display values
                System.out.print("ID: " + resultSet.getInt("st_id"));
                System.out.print(", Username: " + username);
            }
             
             connection.close();
             statement.close();
     }
        catch (SQLException sqlException){
            System.out.println("Something went wrong while creating student record.");
            System.out.println("Reason: " + sqlException.getMessage());

        }
   }

    public static void main(String[] args) {
       insertIntoDatabase("7","aryan");
       retrieveData();
    }}

