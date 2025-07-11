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
   public static void updateData(String username, int userID) throws SQLException 
   {
       PreparedStatement preparedStatement=null;
       ResultSet resultSet = null;
       try{
            connection = ConnectionClassConn.connectionClass();
           String showDataQuery = "SELECT * FROM Student WHERE st_id =?";
           preparedStatement = connection.prepareStatement(showDataQuery);
           preparedStatement.setInt(1,userID);
         resultSet =  preparedStatement.executeQuery();
         if(resultSet.next())
         {
             String UpdateQuery = "UPDATE Student SET st_name = ? WHERE st_id =?";
             preparedStatement = connection.prepareStatement(UpdateQuery);
             preparedStatement.setString(1, username);
             preparedStatement.setInt(2, userID);
             int result = preparedStatement.executeUpdate();
             System.out.println((result>0) ? "Database Updated" : "Not Updated");    
         }
         else{
             System.out.println("Student doesnot exist in database");    
            }
         
       }catch (SQLException sqlException){
            System.out.println("Something went wrong while fetching student record.");
            sqlException.printStackTrace();
        }finally{
           connection.close();
        }
 
   }
   
   public static void deleteData(int userID) throws SQLException
   {
       PreparedStatement preparedStatement = null;
       try{
        connection = ConnectionClassConn.connectionClass();
        String deleteQuery ="DELETE FROM Student WHERE st_id = ?";
        preparedStatement = connection.prepareStatement(deleteQuery);
        preparedStatement.setInt(1, userID);
      int result = preparedStatement.executeUpdate();
           System.out.println((result>0)? "Deleted Successfully" : "Unsuccessfull");
       } catch ( SQLException sqlException)
       {
            System.out.println("Something went wrong while fetching student record.");
            sqlException.printStackTrace();
       }
       finally
       {
           connection.close();
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

    public static void main(String[] args) throws SQLException {
       insertIntoDatabase("1","aryan");
       insertIntoDatabase("2","sujata");
       insertIntoDatabase("4","pratham");
       insertIntoDatabase("5","jwala");
       retrieveData();
        System.out.println("now updating");
        updateData("bina",4);
        System.out.println("Now deleting");
        deleteData(5);
        retrieveData();
             
    }}

