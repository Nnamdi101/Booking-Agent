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
public class Booking {
   private final String customer;
   private final String magician;
   private final String holiday;
   private Timestamp timestamp;
  
   
   public Booking(String holiday, String customer, String magician, Timestamp timestamp)
   {
      this.customer=customer;
      this.magician=magician;
      this.holiday=holiday;
      this.timestamp=timestamp;
   }

   /**
    * @return the customer
    */
   public String getCustomer() {
      return customer;
   }

   /**
    * @return the magician
    */
   public String getMagician() {
      return magician;
   }

   /**
    * @return the holiday
    */
   public String getHoliday() {
      return holiday;
   }

   /**
    * @return the timestamp
    */
   public Timestamp getTimestamp() {
      return timestamp;
   }

   /**
    * @param timestamp the timestamp to set
    */
   public void setTimestamp(Timestamp timestamp) {
      this.timestamp = timestamp;
   }

   

   
}
