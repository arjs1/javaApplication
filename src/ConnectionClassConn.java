
import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.SQLException;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author aryanjaiswal
 */
public class ConnectionClassConn {
   private static  Connection connection = null;
  
    public static Connection connectionClass()
    {
        
         try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            System.out.println("Connecting to database");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:8889/user", "root","root");
           
        } catch (SQLException | ClassNotFoundException excepObject) {
        
            excepObject.printStackTrace();
        } 
            return connection;
    }
    }


    
    
    
    
    

