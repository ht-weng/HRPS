package HRPSapplication;

import HRPSapplication.GuestMasterList;
import HRPSapplication.HotelRooms;
import HRPSapplication.Reservation;
import java.util.Scanner;

public class App {
    public static void main(String args[])
    {
       /*// ===========To test functionality of Guest Class=================================
        Guest test= new Guest();
        Guest test2= new Guest();
        
        test.printGuestDetail();
        test2.printGuestDetail();
        
        test.updateGuest();//change to female
        test.printGuestDetail();
        
        test2.updateGuest();//change to thai nationality and thai conutry of birth
        test2.printGuestDetail();
       
        
       //===========To test functionality of GuestList class=========================
        GuestMasterList go = new GuestMasterList();
        go.newGuest(); //1st guest
        go.newGuest(); //2nd guest
       
       //-----------Print info of Guest in the Guest List class----------------------
        go.getMasterListElement(0).printGuestDetail(); //1st guest information
        go.getMasterListElement(1).printGuestDetail(); //2nd guest information  
       
        
        // ========= To test functionality of HotelRooms/Reservation Class====================
        
       // Room number is in this format AA-BB, where AA is the type of room and BB is the roomID.
       HotelRooms po= new HotelRooms();
       
       Reservation res1= new Reservation (po, go);
       Reservation res2= new Reservation (po, go);
       po.printRoomInformation();*/
       GuestMasterList gml = new GuestMasterList();
       HotelRooms rooms = new HotelRooms();
       Reservation[] reservations = new Reservation[200];
       
       int choice, choice2, noOfReservations = 0;
       Scanner sc = new Scanner(System.in);

       System.out.println();
       System.out.println("         |    |   -----    -----    ----  ");
       System.out.println("         |    |   |    |   |    |  |      ");
       System.out.println("         ------   -----    -----    ----  ");
       System.out.println("         |    |   |    |   |            | ");
       System.out.println("         |    |   |     |  |        ----  ");
       System.out.println("===================================================");
       System.out.println("Welcome to Hotel Reservation and Payment System!");

       do {
           System.out.println();
           System.out.println("---------------------------------------------------");
           System.out.println("|1:  Create/Update/Search guests detail           |");
           System.out.println("|2:  Create/Update/Remove/Print reservation       |");
           System.out.println("|3:  Create/Update rooms details                  |");
           System.out.println("|4:  Entering room service orders                 |");
           System.out.println("|5:  Create/Update/Remove room service menu items |");
           System.out.println("|6:  Check room availability                      |");
           System.out.println("|7:  Room Check-in                                |");
           System.out.println("|8:  Room Check-out and print bill invoice        |");
           System.out.println("|9:  Print Room Status statistic report           |");
           System.out.println("|10: Exit                                         |");
           System.out.println("---------------------------------------------------");
           System.out.println();
           System.out.println("Please select the option: ");
           choice = sc.nextInt();

           switch (choice) {
           case 1://Create/Update/Search guests detail
        	   System.out.println("1: Create guest details");
        	   System.out.println("2: Update guest details");
        	   System.out.println("3: Search guest details");
        	   System.out.println("4: Exit");
        	   System.out.println("Please select the option: ");
        	   choice2 = sc.nextInt();
        	   switch(choice2) {
        	   case 1:
        		   gml.newGuest();
        		   break;
        	   case 2:
        		   break;
        	   case 3:
        		   break;
        	   case 4:
        		   System.out.println("Exiting...");
        		   break;
        	   default:
        		   System.out.println("Invalid option!"); 
        		   break;
        	   }
        	   break; 
           case 2://Create/Update/Remove/Print reservation
        	   System.out.println("1: Create reservation");
        	   System.out.println("2: Update reservation");
        	   System.out.println("3: Remove reservation");
        	   System.out.println("4: Print reservation");
        	   System.out.println("5: Exit");
        	   System.out.println("Please select the option: ");
        	   choice2 = sc.nextInt();
        	   switch(choice2) {
        	   case 1:
        		   reservations[noOfReservations] = new Reservation(rooms, gml);
        		   break;
        	   case 2:
        		   int resID, index;
        		   System.out.println("Enter the reservation ID: ");
        		   resID = sc.nextInt();
        		   index = searchReservation(reservations, resID);
        		   //update the reservation
        		   break;
        	   case 3:
        		   System.out.println("Enter the reservation ID: ");
        		   resID = sc.nextInt();
        		   index = searchReservation(reservations, resID);
        		   //
        		   break;
        	   case 4:
        		   System.out.println("Enter the reservation ID: ");
        		   resID = sc.nextInt();
        		   index = searchReservation(reservations, resID);
        		   reservations[index].reservationReceipt();
        		   break;
        	   case 5:
        		   System.out.println("Exiting...");
        		   break;
        	   default:
        		   System.out.println("Invalid option!");
        		   break;
        	   }
        	   break;
           case 3://Create/Update rooms details
        	   break;
           case 4://Entering room service orders
        	   break;
           case 5://Create/Update/Remove room service menu items
        	   break;
           case 6://Check room availability
        	   break;
           case 7://Room Check-in
        	   
        	   break;
           case 8://Room Check-out and print bill invoice
        	   break;
           case 9://Print Room Status statistic report
        	   rooms.printRoomInformation();
        	   break;
           }
           if (reservations.length > 0) {
        	   for (int i = 0; i < reservations.length; i++) {
        		//   reservations[i].checkExpiry();
        	   }
           }

       } while (choice > 0 && choice < 10);
       System.out.println("===================================================");
       System.out.println("Exiting...");
       sc.close();  
    }
    
    //search reservation by reservation ID
    public static int searchReservation(Reservation[] res, int resID) {
    	int result = -1;
    	for (int i = 0; i < res.length; i++) {
    		if (res[i].getReservationID() == resID)
    			result = i;   		
    	}
    	return result;
    }
}

