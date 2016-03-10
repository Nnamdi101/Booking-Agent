package MagicAgent;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Customer {
   
 //add a customer to customer table when they are booked or in waitlist
   public static void addCustomer(String customer)
   {
      try
        {
            PreparedStatement setCustomer = Connect.getConnection().prepareStatement("INSERT INTO CustomerTable (name) VALUES (?)");
            setCustomer.setString(1, customer);
            setCustomer.executeUpdate();
        } 
        catch (SQLException e)
        {
            e.printStackTrace();
            System.exit(0);
        }
   }
   
    //method to cancel customer
  public static String cancelCustomer(String name)
    {
        String status;
        String Holiday = null;
        
        try
        {
            //this deletes from the customer table
            PreparedStatement cancelCustomer = Connect.getConnection().prepareStatement("DELETE FROM CustomerTable WHERE (Name) = ?");
            cancelCustomer.setString(1, name);
            cancelCustomer.executeUpdate();
            
            //get the holiday the customer was booked for. If he was not booked
            //and was waiting in the waitlist, remove him from the waitlist
            try
            {
                PreparedStatement Theholiday = Connect.getConnection().prepareStatement("SELECT Holiday FROM BookingTable WHERE (customer) = ?");
                Theholiday.setString(1, name);
                ResultSet resultSet = Theholiday.executeQuery();
                resultSet.next();
                Holiday = resultSet.getString("Holiday");
            }
            catch (SQLException e)
            {
                Holiday = null;
            }
            
            //deletes from the bookings table
            cancelCustomer = Connect.getConnection().prepareStatement("DELETE FROM BookingTable WHERE (customer) = ?");
            cancelCustomer.setString(1, name);
            cancelCustomer.executeUpdate();
            
            //deletes from the waiting list
            cancelCustomer = Connect.getConnection().prepareStatement("DELETE FROM WaitListTable WHERE (customerName) = ?");
            cancelCustomer.setString(1, name);
            cancelCustomer.executeUpdate();
           
            status = String.format("%s's booking has been cancelled\n", name);
            MagicanFrame.setStatusText(status);
  
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            System.exit(0);
        }
        
        return Holiday;
    }
}




