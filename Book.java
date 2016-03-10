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


/**
 *
 * @author nnamdiemetarom
 */
public class Book {
   
       
    public static void addBooking(String holiday, String customer, String magician, Timestamp timestamp)
    {
        String status;
        
        if (magician == null) 
        {
            WaitingList.addToWaitList(holiday, customer, timestamp);
        }
        else
        {
            try
            {
                PreparedStatement setBook = Connect.getConnection().prepareStatement("INSERT INTO BookingTable (Magician, Customer, Holiday, Timestamp) VALUES (?, ?, ?, ?)");
                setBook.setString(1, magician);
                setBook.setString(2, customer);
                setBook.setString(3, holiday);
                setBook.setTimestamp(4, timestamp);

                setBook.executeUpdate();    //update the database
                
                status = String.format("%s has been scheduled for %s with %s \n", magician, holiday, customer);
                MagicanFrame.setStatusText(status);                      //TRUE: the customer has been scheduled
            }
            catch (SQLException e)
            {
                e.printStackTrace();
                System.exit(0);
            }
            //if statement to check if the customer is already in the table
             //Customer.addCustomer(customer);// add customer to customer table
        }
       
    }
    
    //This method Queries the database based on the magican
    public static ArrayList<Booking> getBookingByMagician(String magician)
    {
       ArrayList<Booking> allBookings = new ArrayList<>();
       
       try
       {
          PreparedStatement statement = Connect.getConnection().prepareStatement("SELECT * FROM BookingTable WHERE Magician = ?");
          statement.setString(1, magician);
          
          ResultSet resultSet = statement.executeQuery();
       
         while(resultSet.next())
         {
            Booking next = new Booking(resultSet.getString("Holiday"),resultSet.getString("Customer"),resultSet.getString("Magician"), resultSet.getTimestamp("Timestamp"));
            allBookings.add(next);
         }
         
       }
       
       catch(SQLException e)
       {
         e.printStackTrace();
         System.exit(0);
       }
       
       return allBookings;
    }
    
   //This method Queries the database based on the Holiday
    public static ArrayList<Booking> getBookingByHoliday(String holiday)
    {
       ArrayList<Booking> allBookings = new ArrayList<>();
       
       try
       {
          PreparedStatement statement = Connect.getConnection().prepareStatement("SELECT * FROM BookingTable WHERE Holiday = ?");
          statement.setString(1, holiday);
          
          ResultSet resultSet = statement.executeQuery();
       
         while(resultSet.next())
         {
            Booking next = new Booking(resultSet.getString("Holiday"),resultSet.getString("Customer"),resultSet.getString("Magician"), resultSet.getTimestamp("Timestamp"));
            allBookings.add(next);
         }
         
       }
       
       catch(SQLException e)
       {
         e.printStackTrace();
         System.exit(0);
       }
       
       return allBookings;
    }
   
    
    //This is a method to get all the bookings from the bookings table to be used to display on a table 
    public static ArrayList<Booking> getallBookings()
    {
       ArrayList <Booking> allbooking = new ArrayList<>();
       
       try
       {
          ResultSet resultset = Connect.getConnection().prepareStatement("SELECT * FROM BookingTable").executeQuery();
          
          while(resultset.next())
          {
             Booking temp = new Booking(resultset.getString("Holiday"),resultset.getString("Customer"),resultset.getString("Magician"),resultset.getTimestamp("TimeStamp"));
             allbooking.add(temp);
          }
       }
          
      catch(SQLException e)
       {
         e.printStackTrace();
         System.exit(0);
       }
        return allbooking;
    }
    
   

}
