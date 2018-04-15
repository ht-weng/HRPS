package HRPSapplication;

import HRPSapplication.GuestMasterList;
import HRPSapplication.HotelRooms;
import HRPSapplication.Reservation;
import HRPSapplication.RoomService;
import java.util.Scanner;

public class App {
    public static void main(String args[]) {
       /*// ===========To test functionality of Guest Class=================================
        Guest test= new Guest();
        Guest test2= new Guest();
        
        test.printGuestDetail();
        test2.printGuestDetail();
        
        test.updateGuest();//change to female
        test.printGuestDetail();
        
        test2.updateGuest();//change to thai nationality and thai country of birth
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
    	Scanner sc = new Scanner(System.in);
       GuestMasterList gml = new GuestMasterList();
       HotelRooms rooms = new HotelRooms();
       Reservation[] reservations = new Reservation[500];
       RoomService[] rmServices = new RoomService[50];
       
       int choice, choice2, rID, noOfReservations = 0, noOfServices = 0;
       boolean isRecorded = false;
       Room rm;
       String keyword;

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
        	   do {
	        	   System.out.println("1: Create guest details");
	        	   System.out.println("2: Update guest details");
	        	   System.out.println("3: Search guest details");
	        	   System.out.println("4: Exit");
	        	   System.out.println("Please select the option: ");
	        	   choice2 = sc.nextInt();
	        	   switch(choice2) {
	        	   case 1:
	        		   gml.newGuest();
	        		   isRecorded = true;
	        		   break;
	        	   case 2:
	        		   System.out.println("Please enter the search keyword: ");
	        		   sc.nextLine();
	        		   keyword = sc.nextLine();
	        		   if (!gml.searchGuestByKeywords(gml, keyword).getIsEmpty()) {
	        			   gml.searchGuestByKeywords(gml, keyword).updateGuest();
	        			   isRecorded = true;
	        		   } else {
	        			   //target guest does not exist
	        			   isRecorded = false;
	        		   }
	        		   break;
	        	   case 3:
	        		   System.out.println("Please enter the search keyword: ");
	        		   sc.nextLine();
	        		   keyword = sc.nextLine();
	        		   if (!gml.searchGuestByKeywords(gml, keyword).getIsEmpty()) {
	        			   gml.searchGuestByKeywords(gml, keyword).printGuestDetail();;
	        			   isRecorded = true;
	        		   } else {
	        			   //target guest does not exist
	        			   isRecorded = false;
	        		   }
	        		   isRecorded = true;
	        		   break;
	        	   case 4:
	        		   isRecorded = true;
	        		   System.out.println("Exiting...");
	        		   break;
	        	   default:
	        		   System.out.println("Invalid option!"); 
	        		   break;
	        	   }
        	   }while(!isRecorded);
        	   break; 
           case 2://Create/Update/Remove/Print reservation
        	   isRecorded = false;
        	   do {
	        	   System.out.println("1: Create reservation");
	        	   System.out.println("2: Update reservation");
	        	   System.out.println("3: Remove reservation");
	        	   System.out.println("4: Print reservation");
	        	   System.out.println("5: Exit");
	        	   System.out.println("Please select the option: ");
	        	   choice2 = sc.nextInt();
	        	   switch(choice2) {
	        	   case 1:
	        		   noOfReservations++;
	        		   reservations[noOfReservations-1] = new Reservation(rooms, gml, false);
	        		   isRecorded = true;
	        		   break;
	        	   case 2:
	        		   int resID, index;
	        		   System.out.println("Enter the reservation ID: ");
	        		   resID = sc.nextInt();
	        		   index = Reservation.searchReservation(reservations, resID, noOfReservations);
	        		   reservations[index].updateReservation(rooms);
	        		   isRecorded = true;
	        		   break;
	        	   case 3:
	        		   System.out.println("Enter the reservation ID: ");
	        		   resID = sc.nextInt();
	        		   index = Reservation.searchReservation(reservations, resID, noOfReservations);
	        		   reservations[index].updateStatus("Cancelled");
	        		   reservations[index].getRoom().setRoomStatus("vacant");
	        		   isRecorded = true;
	        		   break;
	        	   case 4:
	        		   System.out.println("Enter the reservation ID: ");
	        		   resID = sc.nextInt();
	        		   index = Reservation.searchReservation(reservations, resID, noOfReservations);
	        		   reservations[index].reservationReceipt();
	        		   isRecorded = true;
	        		   break;
	        	   case 5:
	        		   System.out.println("Exiting...");
	        		   isRecorded = true;
	        		   break;
	        	   default:
	        		   System.out.println("Invalid option!");
	        		   break;
	        	   }
        	   }while(!isRecorded);
        	   break;
           case 3://Create/Update rooms details
        	   System.out.println("Enter the room ID:");
        	   rID = sc.nextInt();
        	   if (rID > 0 && rID <= 48) {
        		   getRoomByRoomID(rooms, rID).updateRoomDetails();
        	   } else {
        		   System.out.println("Invalid room ID!");
        	   }
        	   break;
           case 4://Entering room service orders
        	   System.out.println("Enter the reservation ID: ");
        	   int resID = sc.nextInt();
        	   int index = Reservation.searchReservation(reservations, resID, noOfReservations);
        	   System.out.println("The current room service list: ");
    		   for (int i = 0; i < noOfServices; i++) {
    			   System.out.println(noOfServices + ": " + rmServices[i].getName());
    		   }
    		   System.out.println("Enter the room service ID: ");
    		   int rmSvcID = sc.nextInt();
        	   reservations[index].enterRoomService(rmServices[rmSvcID-1]);;
        	   break;
           case 5://Create/Update/Remove room service menu items
        	   isRecorded = false;
        	   do {
	        	   System.out.println("1: Create room service");
	        	   System.out.println("2: Update room service");
	        	   System.out.println("3: Remove room service");
	        	   System.out.println("4: Exit");
	        	   System.out.println("Please select the option: ");
	        	   choice2 = sc.nextInt();
	        	   switch(choice2) {
	        	   case 1:
	        		   noOfServices++;
	        		   System.out.println("Please enter the service name: ");
	        		   sc.nextLine();
	        		   String svcName = sc.nextLine();
	        		   System.out.println("Please enter the service price: ");
	        		   int svcPrice = sc.nextInt();
	        		   rmServices[noOfServices-1] = new RoomService(svcName, svcPrice);
	        		   System.out.println("Room service recorded!");
	        		   isRecorded = true;
	        		   break;
	        	   case 2:
	        		   System.out.println("The current room service list: ");
	        		   for (int i = 0; i < noOfServices; i++) {
	        			   System.out.println(noOfServices + ": " + rmServices[i].getName());
	        		   }
	        		   System.out.println("Enter the room service ID: ");
	        		   rmSvcID = sc.nextInt();
	        		   if (rmSvcID > 0 && rmSvcID <= noOfServices) {
	        			   rmServices[rmSvcID-1].updateService();
	        			   isRecorded = true;
	        		   } else {
	        			   System.out.println("Invalid room service ID!");
	        		   }
	        		   break;
	        	   case 3:
	        		   System.out.println("The current room service list: ");
	        		   for (int i = 0; i < noOfServices; i++) {
	        			   System.out.println(noOfServices + ": " + rmServices[i].getName());
	        		   }
	        		   System.out.println("Enter the room service ID: ");
	        		   rmSvcID = sc.nextInt();
	        		   if (rmSvcID > 0 && rmSvcID <= noOfServices) {
	        			   RoomService.removeRoomService(rmServices, rmSvcID, noOfServices);
	        			   System.out.println("Service removed!");
	        			   isRecorded = true;
	        		   } else {
	        			   System.out.println("Invalid room service ID!");
	        		   }
	        		   break;
	        	   case 4:
	        		   System.out.println("Exiting...");
	        		   isRecorded = true;
	        		   break;
	        	   default:
	        		   System.out.println("Invalid option!");
	        		   break;
	        	   }
        	   }while(!isRecorded);
        	   break;
           case 6://Check room availability
        	   System.out.println("Enter the room ID:");
        	   rID = sc.nextInt();
        	   if (rID > 0 && rID <= 48) {
        		   rm = getRoomByRoomID(rooms, rID);
        		   if (rm.getRoomStatus().equals("vacant")) {
        			   System.out.println("This room is available");
        		   } else if (rm.getRoomStatus().equals("occupied")) {
        			   System.out.println("This room is occupied");
        		   } else if (rm.getRoomStatus().equals("reserved")) {
        			   System.out.println("This room is reserved");
        		   } else {
        			   System.out.println("This room is under maintenance");
        		   } 
        	   } else {
        		   System.out.println("Invalid room ID!");
        	   }
        	   break;
           case 7://Room Check-in
        	   isRecorded = false;
        	   do {
	        	   System.out.println("Select the check-in type: ");
	        	   System.out.println("1.By Walk-in");
	        	   System.out.println("2.By Reservation");
	        	   System.out.println("3.Exit");
	        	   choice = sc.nextInt();
	        	   
	        	   switch(choice) {
	        	   case 1:
	        		   noOfReservations++;
	        		   reservations[noOfReservations-1] = new Reservation(rooms, gml, true); 
	        		   System.out.println("Room checked-in successfully!");
	        		   isRecorded = true;
	        		   break;
	        	   case 2:
	        		   System.out.println("Please enter guest name: ");
	        		   sc.nextLine();
	        		   keyword = sc.nextLine();
	        		   Reservation res = reservations[0].searchReservationByName(reservations, keyword, noOfReservations);
	        		   if (!res.getIsEmpty()) {
	        			   res.checkedIn();
	        			   isRecorded = true;
	        		   } else {
	        			   isRecorded = false;
	        		   }
	        		   break;
	        	   case 3:
	        		   isRecorded = true;
	        		   System.out.println("Exiting...");
	        		   break;
	        	   default:
	        		   System.out.println("Invalid option!");
	        		   break;
	        	   }
        	   }while(!isRecorded);
        	   break;
           case 8://Room Check-out and print bill invoice
        	   System.out.println("Please enter the room number: ");
        	   sc.nextLine();
        	   String rmNo = sc.nextLine();
        	   Reservation res = Reservation.searchReservationByRoomNo(reservations, rmNo, noOfReservations);
        	   if (!res.getIsEmpty()) {
    			   res.checkedOut();
    			   isRecorded = true;
    		   } else {
    			   isRecorded = false;
    		   }
        	   break;
           case 9://Print Room Status statistic report
        	   rooms.printRoomInformation();
        	   break;
           }
           if (noOfReservations > 0) {
        	   for (int i = 0; i < noOfReservations; i++) {
        		   reservations[i].checkExpiry();
        	   }
           }
           
       } while (choice > 0 && choice < 10);
       System.out.println("===================================================");
       System.out.println("Exiting..."); 
       sc.close();
    }
    
  
    
    public static Room getRoomByRoomID(HotelRooms rooms, int rID) {
    	if (rID > 0 && rID <=20) {
    		return rooms.getSingleRoom(rID-1);
    	} else if (rID > 20 && rID <= 40) {
    		return rooms.getDoubleRoom(rID-21);
    	} else if (rID > 40 && rID <= 46) {
    		return rooms.getDeluxeRoom(rID-41);
    	} else {
    		return rooms.getPresidentRoom(rID-47);
    	}
    }
    
    
}

