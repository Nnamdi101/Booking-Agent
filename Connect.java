/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MagicAgent;

/**
 *
 * @author nnamdiemetarom
 */
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

//class to create a connection once to the database and to be used thtoughout the program
public class Connect
{
    private static final String DATABASE_URL = "jdbc:derby://localhost:1527/AgentDTB";         //database location
    private static final String user = "java";                                                    
    private static final String password = "java";
    private static Connection connection;

    public static Connection getConnection()
    { 
        try
        {  
            connection = DriverManager.getConnection(DATABASE_URL, user, password);                 //connect to the database
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            System.exit(0);
        }
        
        return connection;
    }
}