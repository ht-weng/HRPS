package HRPSapplication;

import HRPSapplication.GuestMasterList;
import HRPSapplication.HotelRooms;
import HRPSapplication.Reservation;
import HRPSapplication.RoomService;
import java.util.Scanner;

/**
 * Main application for user--system interaction
 */
public class App {
	/**
	 * Total Number of rooms
	 */
	private static final int TOTAL_RMS = HotelRooms.BY_PRESIDENT_ROOMS;
	
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);

		/**
		 * Array of guests
		 */
	  	GuestMasterList gml = new GuestMasterList();
	  	/**
	  	 * Array of rooms
	  	 */
	   	HotelRooms rooms = new HotelRooms();
	   	/**
	   	 * Array of reservations
	   	 */
	   	Reservation[] reservations = new Reservation[500];
	   	/**
	   	 * Array of room services
	   	 */
    	RoomService[] rmServices = new RoomService[50];
	      
    	int choice, choice2, choice3;
    	/**
    	 * Room ID
    	 */
    	int rID;
    	/**
    	 * Number of reservations
    	 */
    	int noOfReservations = 0;
    	/**
    	 * Number of room services
    	 */
    	int noOfServices = 0;
    	/**
    	 * Check if the info is recorded. If not, repeat the loop
    	 */
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
	    			System.out.println();
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
	    				if (gml.getCounter() > 0) {
		    				System.out.println("Please enter the search keyword: ");
		    				sc.nextLine();
		    				keyword = sc.nextLine();
		    				if (!gml.searchGuestByKeywords(gml, keyword).getIsEmpty()) {//check if the guest exists
		    					gml.searchGuestByKeywords(gml, keyword).updateGuest();
		    					isRecorded = true;
		    				} else {
		    					System.out.println("Guest does not exist!");
		    					System.out.println();
		    					isRecorded = false;
		    				}
	    				} else {
	    					System.out.println("None guest exists! Please create the guest details!");
	    					isRecorded = false;
	    				}
	    				break;
	    			case 3:
	    				if (gml.getCounter() > 0) {
		    				System.out.println("Please enter the search keyword: ");
		    				sc.nextLine();
		    				keyword = sc.nextLine();
		    				if (!gml.searchGuestByKeywords(gml, keyword).getIsEmpty()) {//check if the guest exists
		    					gml.searchGuestByKeywords(gml, keyword).printGuestDetail();;
		    					isRecorded = true;
		    				} else {
		    					System.out.println("Guest does not exist!");
		    					System.out.println();
		    					isRecorded = false;
		    				}
	    				} else {
	    					System.out.println("None guest exists! Please create the guest details!");
	    					isRecorded = false;
	    				}
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
	    			System.out.println();
	    			System.out.println("1: Create reservation");
	    			System.out.println("2: Update reservation");
	    			System.out.println("3: Remove reservation");
	    			System.out.println("4: Print all reservations");
	    			System.out.println("5: Exit");
	    			System.out.println("Please select the option: ");
	    			choice2 = sc.nextInt();
	    			switch(choice2) {
	    			case 1:
	    				noOfReservations++;
	    				reservations[noOfReservations-1] = new Reservation(rooms, gml, false, reservations, noOfReservations);
	    				isRecorded = true;
	    				break;
	    			case 2:
	    				int resID, index;
	    				if (noOfReservations > 0) {
		    				System.out.println("Enter the reservation ID: ");
		    				resID = sc.nextInt();
		    				index = Reservation.searchReservation(reservations, resID, noOfReservations);
		    				if (index > 0) {
		    					reservations[index].updateReservation(rooms, reservations, noOfReservations);
		    					isRecorded = true;
		    				} else {
		    					System.out.println("Reservation does not exist!");
		    					isRecorded = false;
		    				}
	    				} else {
	    					System.out.println("None reservation exists! Please create the reservation!");
	    					isRecorded = false;
	    				}
	    				break;
	    			case 3:
	    				if (noOfReservations > 0) {
		    				System.out.println("Enter the reservation ID: ");
		    				resID = sc.nextInt();
		    				index = Reservation.searchReservation(reservations, resID, noOfReservations);
		    				if (index > 0) {
		    					reservations[index].updateStatus("Cancelled");
		    					reservations[index].getRoom().setRoomStatus("vacant");
		    					isRecorded = true;
		    				} else {
		    					System.out.println("Reservation does not exist!");
		    					isRecorded = false;
		    				}
	    				} else {
	    					System.out.println("None reservation exists! Please create the reservation!");
	    					isRecorded = false;
	    				}
	    				break;
	    			case 4:
	    				if (noOfReservations > 0 ) {
	    					for (int i = 0; i < noOfReservations; i++) {
		    					reservations[i].reservationReceipt();
		    					isRecorded = true;
		    				}
	    				} else {
	    					System.out.println("None reservation exists! Please create the reservation!");
	    					isRecorded = false;
	    				}
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
	    		if (rID > 0 && rID <= TOTAL_RMS) {
	    			rooms.getRoomByRoomID(rID).updateRoomDetails();
	    		} else {
	    			System.out.println("Invalid room ID!");
	    		}
	    		break;
	    	case 4://Entering room service orders	
	    		System.out.println("Enter the reservation ID: ");
	    		int resID = sc.nextInt();
	    		int index = Reservation.searchReservation(reservations, resID, noOfReservations);
	    		if (index != -1) {
	    			if (noOfServices > 0) {
			    		System.out.println("The current room service list: ");
			    		for (int i = 0; i < noOfServices; i++) {
	    					System.out.println((i+1) + ": " + rmServices[i].getName());
	    					System.out.println(rmServices[i].getDescription());
	    					System.out.println("$" + rmServices[i].getPrice());
	    				}
			    		System.out.println("Enter the room service ID: ");
			    		int rmSvcID = sc.nextInt();
			    		reservations[index].enterRoomService(rmServices[rmSvcID-1]);
	    			} else {
	    				System.out.println("None room service available! Please create the room services!");
	    			}
	    		} else {
	    			System.out.println("Reservation does not exist!");
	    		}
	    		break;
	    	case 5://Create/Update/Remove room service menu items
	    		isRecorded = false;
	    		do {
	    			System.out.println();
	    			System.out.println("1: Create room service");
	    			System.out.println("2: Update room service");
	    			System.out.println("3: Remove room service");
	    			System.out.println("4: Exit");
	    			System.out.println("Please select the option: ");
	    			choice2 = sc.nextInt();
	    			switch(choice2) {
	    			case 1://create
	    				noOfServices++;
	    				System.out.println("Please enter the service name: ");
	    				sc.nextLine();
	    				String svcName = sc.nextLine();
	    				System.out.println("Please enter the service price: ");
	    				int svcPrice = sc.nextInt();
	    				System.out.println("Please enter the service description: ");
	    				sc.nextLine();
	    				String description = sc.nextLine();
	    				rmServices[noOfServices-1] = new RoomService(svcName, svcPrice, description);
	    				System.out.println("Room service recorded!");
	    				isRecorded = true;
	    				break;
	    			case 2://update
	    				if (noOfServices > 0) {
	    					System.out.println("The current room service list: ");
		    				for (int i = 0; i < noOfServices; i++) {
		    					System.out.println((i+1) + ": " + rmServices[i].getName());
		    					System.out.println(rmServices[i].getDescription());
		    					System.out.println("$" + rmServices[i].getPrice());
		    				}
		    				System.out.println("Enter the room service ID: ");
		    				int rmSvcID = sc.nextInt();
		    				if (rmSvcID > 0 && rmSvcID <= noOfServices) {
		    					rmServices[rmSvcID-1].updateService();
		    					isRecorded = true;
		    				} else {
		    					System.out.println("Room service does not exist!");
		    				}
	    				} else {
		    				System.out.println("None room service available! Please create the room services!");
		    			}
	    				break;
	    			case 3://remove
	    				if (noOfServices > 0) {
		    				System.out.println("The current room service list: ");
		    				for (int i = 0; i < noOfServices; i++) {
		    					System.out.println((i+1) + ": " + rmServices[i].getName());
		    					System.out.println(rmServices[i].getDescription());
		    					System.out.println("$" + rmServices[i].getPrice());
		    				}
		    				System.out.println("Enter the room service ID: ");
		    				int rmSvcID = sc.nextInt();
		    				if (rmSvcID > 0 && rmSvcID <= noOfServices) {
		    					RoomService.removeRoomService(rmServices, rmSvcID, noOfServices);
		    					System.out.println("Service removed!");
		    					noOfServices--;
		    					isRecorded = true;
		    				} else {
		    					System.out.println("Room service does not exist!");
		    				}
	    				} else {
		    				System.out.println("None room service available! Please create the room services!");
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
	    		System.out.println("Enter the room ID(1-" + TOTAL_RMS + "):");
	    		rID = sc.nextInt();
	    		if (rID > 0 && rID <= TOTAL_RMS) {
	    			rm = rooms.getRoomByRoomID(rID);
	    			if (rm.getRoomStatus().equals("vacant")) {
	    				System.out.println("This room is vacant");
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
	    		isRecorded = true;
	    		break;
	    	case 7://Room Check-in
	    		do {
	    			System.out.println("Select the check-in type: ");
	    			System.out.println("1.By Walk-in");
	    			System.out.println("2.By Reservation");
	    			System.out.println("3.Exit");
	    			choice2 = sc.nextInt();
	        	   
	    			switch(choice2) {
	    			case 1:
	    				noOfReservations++;
	    				reservations[noOfReservations-1] = new Reservation(rooms, gml, true, reservations, noOfReservations); 
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
	    					System.out.println("Reservation does not exist!");
	    					isRecorded = false;
	    				}
	    				break;
	    			case 3:
	    				System.out.println("Exiting...");
	    				isRecorded = true;
	    				break;
	    			default:
	    				System.out.println("Invalid option!");
	    				break;
	    			}
	    		}while(!isRecorded);
	    		break;
	    	case 8://Room Check-out and print bill invoice
	    		System.out.println("Please enter the room number(LL-RR): ");
	    		sc.nextLine();
	    		String rmNo = sc.nextLine();
	    		Reservation res = Reservation.searchReservationByRoomNo(reservations, rmNo, noOfReservations);
	    		if (!res.getIsEmpty()) {
	    			res.checkedOut();
	    			isRecorded = true;
	    		} else {
	    			//reservation does not exist
	    			isRecorded = false;
	    		}
	    		break;
	    	case 9://Print Room Status statistic report
	    		System.out.println("1.Print room type occupancy rate");
	    		System.out.println("2.Print room status");
	    		System.out.println("3.Exit");
	    		System.out.println("Please select the option: ");
	    		choice2 = sc.nextInt();
        	   
	    		switch(choice2) {
	    		case 1:
	    			System.out.println("1.Print single room occupancy rate");
	    			System.out.println("2.Print double room occupancy rate");
	    			System.out.println("3.Print deluxe room occupancy rate");
	    			System.out.println("4.Print president room occupancy rate");
	    			System.out.println("5.Print room occupancy rate summary");
	    			System.out.println("6.Exit");
		    		System.out.println("Please select the option: ");
		    		choice3 = sc.nextInt();
		    		switch(choice3) {
		    		case 1:
		    			rooms.printOccupiedSingleRooms();
		    			break;
		    		case 2:
		    			rooms.printOccupiedDoubleRooms();
		    			break;
		    		case 3:
		    			rooms.printOccupiedDeluxeRooms();
		    			break;
		    		case 4:
		    			rooms.printOccupiedPresidentRooms();
		    			break;
		    		case 5:
		    			rooms.printOccupiedRoomSummary();
		    			break;
		    		default:
		    			System.out.println("Exiting...");
		    			break;	
		    		}
		    		break;
	    		case 2:
	    			System.out.println("1.Print vacant rooms");
	    			System.out.println("2.Print occupied rooms");
	    			System.out.println("3.Print reserved rooms");
	    			System.out.println("4.Print rooms under maintenance");
	    			System.out.println("5.Print room status summary");
	    			System.out.println("6.Exit");
		    		System.out.println("Please select the option: ");
		    		choice3 = sc.nextInt();
		    		switch(choice3) {
		    		case 1:
		    			rooms.printVacantRooms();
		    			break;
		    		case 2:
		    			rooms.printOccupiedRooms();
		    			break;
		    		case 3:
		    			rooms.printReservedRooms();
		    			break;
		    		case 4:
		    			rooms.printRoomsUnderMaintenance();
		    			break;
		    		case 5:
		    			rooms.printRoomStatusSummary();
		    			break;
		    		default:
		    			System.out.println("Exiting...");
		    			break;	
		    		}
		    		break;
	    		default:
	    			System.out.println("Exiting...");
	    		}
	    		isRecorded = true;
	    		break;
	    	}
	    	//check if reservation is expired and update status according
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
}

