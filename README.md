# Booking-Agent
This is a java program that acts as a books Magicians for particulars holidays. It utilizes SQL and a graphics user interface to enhance the user experience. 
# Specification
-The UI provides a book botton for booking a customer  and combo-box specifying the holiday. The program takes an available Magician from the magican table in the database and books the customer for the holiday.
-In the event all magicians are booked for a holiday, the customers are added to a waitlist.
-The programs allow the user to add holidays and magicans as they see fit. If a new magician is added, the program takes the earliest customer, based on timestamp, and books him with the newly added magician.
-The program also allows the user to drop a Magician, in which case the customer he was booked with is added to the waitlist.
