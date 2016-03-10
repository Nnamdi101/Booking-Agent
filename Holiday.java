package MagicAgent;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class Holiday {
   
   // To get all Magican from the table
    public static ArrayList<String> getHoliday()
    {
        ArrayList<String> Holiday = new ArrayList<>();
        
        try
        {
           ResultSet resultSet = Connect.getConnection().prepareStatement("SELECT * FROM HolidayTable").executeQuery();
           
           while(resultSet.next())
           {
               Holiday.add(resultSet.getString("HolidayName"));
           }        
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            System.exit(0);
        }
        
        return Holiday;
    }
    
    //method to add holiday
    public static void addHoliday(String holiday)
    {
        String status;
        
        try
        {
            PreparedStatement setH = Connect.getConnection().prepareStatement("INSERT INTO HolidayTable (HolidayName) VALUES (?)");
            setH.setString(1, holiday);
            setH.executeUpdate();
            
        status = String.format("%s has been added to Holidays table\n", holiday);
        MagicanFrame.setStatusText(status);
        } 
        catch (SQLException e)
        {
            e.printStackTrace();
            System.exit(0);
        }
    }
   
}
