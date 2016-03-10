/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MagicAgent;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;


public class WaitingList {
   
   //adds a customer to the wait list for the holiday
    public static void addToWaitList(String holiday, String customer, Timestamp timestamp)
    {
        String status;
        
        try
        {
            PreparedStatement setList = Connect.getConnection().prepareStatement("INSERT INTO WaitListTable (HolidayName, CustomerName, Timestamp) VALUES (?, ?, ?)");
            setList.setString(1, holiday);
            setList.setString(2, customer);
            setList.setTimestamp(3, timestamp);

            setList.executeUpdate();

            status = String.format("%s has been added to the waitlist for %s \n", customer, holiday);
            MagicanFrame.setStatusText(status);     
        } 
        catch (SQLException e)
        {
            e.printStackTrace();
            System.exit(0);
        }
        
    }
    
    //method to get waitlist
    public static ArrayList<WaitlistEntry> getWaitList()
    {
       ArrayList<WaitlistEntry> waitList = new ArrayList<>();
       
       try
       {
          ResultSet resultSet = Connect.getConnection().prepareStatement("SELECT * FROM WaitListTable ORDER BY Timestamp").executeQuery();
          
          while(resultSet.next())
          {
             WaitlistEntry next = new WaitlistEntry(resultSet.getString("HolidayName"), resultSet.getString("CustomerName"), resultSet.getTimestamp("Timestamp"));
             waitList.add(next);
          }
       }
       
       catch(SQLException e)
       {
          e.printStackTrace();
          System.exit(0);
       }
       
       return waitList;
    }
    
    //method to get the entry on the waitlist based on the holiday
    //This will be used later in my GUI TO to assign the earlist customer in the waitlist
    //based on each holiday in the holiday combox
    public static WaitlistEntry getFirstOnWaitlist(String holiday)
    {
       WaitlistEntry earliest = null;
       try
       {
          
       PreparedStatement statement = Connect.getConnection().prepareStatement(
               "SELECT * FROM WaitListTable WHERE TimeStamp = (SELECT MIN(Timestamp) FROM WaitlistTable where HolidayName = ?)");
          statement.setString(1,holiday );
          ResultSet resultSet = statement.executeQuery();
          resultSet.next();
          
          earliest = new WaitlistEntry(resultSet.getString("HolidayName"),resultSet.getString("CustomerName"),resultSet.getTimestamp("TimeStamp"));
          
          //remove the customer from waitlist
          deleteFromWaitlist(resultSet.getString("CustomerName"));
       }
       
       catch(SQLException e)
       {
          earliest = null;
       }
       
       return earliest;
    }
    
    //function to delete customer from waitlist
    public static void deleteFromWaitlist(String customer)
    {
       String status;
       try
       {
          PreparedStatement statement = Connect.getConnection().prepareStatement("DELETE FROM WaitListTable WHERE (CustomerName) = ?");
          statement.setString(1,customer);
          statement.executeUpdate();
          
          status = String.format("%s was removed from waitlist\n", customer);
          MagicanFrame.setStatusText(status);
       }
       catch(SQLException e)
      {
         e.printStackTrace();
         System.exit(0);
      }
    }
    
}
