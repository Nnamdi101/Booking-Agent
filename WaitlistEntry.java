/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MagicAgent;

import java.sql.Timestamp;

/**
 *
 * @author nnamdiemetarom
 */
public class WaitlistEntry {
   private final String holiday;
   private final String customer;
   private final Timestamp timestamp;
   
   public WaitlistEntry(String holiday, String customer, Timestamp timestamp)
   {
      this.holiday=holiday;
      this.customer=customer;
      this.timestamp=timestamp;
   }

 
   public String getHoliday() {
      return holiday;
   }

  
   public String getCustomer() {
      return customer;
   }

   public Timestamp getTimestamp() {
      return timestamp;
   }
      
   
}
