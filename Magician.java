package MagicAgent;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class Magician {
   
   // To get all Magican from the table
    public static ArrayList<String> getMagican()
    {
        ArrayList<String> Magican = new ArrayList<>();
        
        try
        {
           ResultSet resultSet = Connect.getConnection().prepareStatement("SELECT * FROM MagicanTable").executeQuery();
           
           while(resultSet.next())
           {
               Magican.add(resultSet.getString("MagicanName"));
           }         
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            System.exit(0);
        }
        
        return Magican;
    }
   
    //Method to get free Magican
    public static String getFreeMagician(String holiday)
    {
       String freeMagician = "";
       try
       {
          PreparedStatement statement = Connect.getConnection().prepareStatement(
                        "SELECT MagicanName FROM MagicanTable WHERE MagicanName NOT IN (SELECT Magician FROM BookingTable WHERE Holiday = ?)");
            statement.setString(1, holiday);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            
            freeMagician = resultSet.getString("MagicanName");

       }
       catch(SQLException e)
       {
           freeMagician=null;       
       }
               
       return freeMagician;
    
    }
    
 public static void addMagician(String magician)
 {
    String status;
    try
    {
       PreparedStatement statement = Connect.getConnection().prepareStatement("INSERT INTO MagicanTable (MagicanName) VALUES (?) ");
       statement.setString(1,magician);
       statement.executeUpdate();
       
       status = String.format("%s has been added to the MagicianTable\n", magician);
       MagicanFrame.setStatusText(status);
    }
    
   catch(SQLException e)
    {
        e.printStackTrace();
        System.exit(0);
    }
        
    
 }
    
    
   //This is the drop magician function that deletes from the booking and magician tables
 //on the magician
 public static void dropMagician(String magician)
 {
    String status;
    
    try
    {
       //deletes from magician table
       PreparedStatement drop = Connect.getConnection().prepareStatement("DELETE FROM MagicanTable WHERE (MagicanName)= ? ");
       drop.setString(1,magician);
       drop.executeUpdate();
       
       //deletes from bookingtable
       drop = Connect.getConnection().prepareStatement("DELETE FROM BookingTable WHERE (Magician)= ? ");
       drop.setString(1,magician);
       drop.executeUpdate();
       
       status=String.format("%s has been dropped \n",magician);
       MagicanFrame.setStatusText(status);
    }
    
    catch(SQLException e)
    {
        e.printStackTrace();
        System.exit(0);
    }
    
 }
    
}
