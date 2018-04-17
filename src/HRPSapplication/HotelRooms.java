package HRPSapplication;

import HRPSapplication.Room;

public class HotelRooms {
	 
	private Room[] singleRooms;
	private Room[] doubleRooms;
	private Room[] deluxeRooms;
	private Room[] presidentRooms;

	public static final int MAX_SINGLE_ROOMS=20;
	public static final int MAX_DOUBLE_ROOMS=20;
	public static final int MAX_DELUXE_ROOMS=6;
	public static final int MAX_PRESIDENT_ROOMS=2;
	public static final int BY_DOUBLE_ROOMS = MAX_SINGLE_ROOMS + MAX_DOUBLE_ROOMS;
	public static final int BY_DELUXE_ROOMS = BY_DOUBLE_ROOMS + MAX_DELUXE_ROOMS;
	public static final int BY_PRESIDENT_ROOMS = BY_DELUXE_ROOMS + MAX_PRESIDENT_ROOMS;
	
	//Constructor
	public HotelRooms()
	{
		//constructs room lists
		Room[] single= new Room[MAX_SINGLE_ROOMS];
		for(int i=0;i<MAX_SINGLE_ROOMS;i++)
		{
			single[i] = new Room();    
			single[i].getRoomType().setRoomType(1);
		}

		Room[] doubl = new Room[MAX_DOUBLE_ROOMS];
		for (int i=0;i<MAX_DOUBLE_ROOMS;i++)
		{
			doubl[i]=new Room();
			doubl[i].getRoomType().setRoomType(2);
		}

		Room[] deluxe= new Room[MAX_DELUXE_ROOMS];
		for (int i=0;i<MAX_DELUXE_ROOMS;i++)
		{
			deluxe[i]=new Room();
			deluxe[i].getRoomType().setRoomType(3);
		}

		Room[] president = new Room[MAX_PRESIDENT_ROOMS];
		for (int i=0;i<MAX_PRESIDENT_ROOMS;i++)
		{
			president[i]=new Room();
			president[i].getRoomType().setRoomType(4);
		}
		
		//set room numbers by level
		int number1 = (MAX_SINGLE_ROOMS + MAX_DOUBLE_ROOMS)/10;
		int number2 = number1/2;
		//level 1
		for (int i = 0; i < number1; i++) {
			single[i].setRoomNumber("01-" + (i+1));
		}
		for (int i = number1; i < number2; i++) {
			doubl[i].setRoomNumber("01-" + (i+1));
		}
		//level 2
		for (int i = 0; i < number1; i++) {
			single[i].setRoomNumber("02-" + (i+1));
		}
		for (int i = number1; i < number2; i++) {
			doubl[i].setRoomNumber("02-" + (i+1));
		}
		//level 3
		for (int i = 0; i < number1; i++) {
			single[i].setRoomNumber("03-" + (i+1));
		}
		for (int i = number1; i < number2; i++) {
			doubl[i].setRoomNumber("03-" + (i+1));
		}
		//level 4
		for (int i = 0; i < number1; i++) {
			single[i].setRoomNumber("04-" + (i+1));
		}
		for (int i = number1; i < number2; i++) {
			doubl[i].setRoomNumber("04-" + (i+1));
		}
		//level 5
		for (int i = 0; i < number1; i++) {
			single[i].setRoomNumber("05-" + (i+1));
		}
		for (int i = number1; i < number2; i++) {
			doubl[i].setRoomNumber("05-" + (i+1));
		}
		//level 6
		for (int i = 0; i < MAX_DELUXE_ROOMS; i++) {
			deluxe[i].setRoomNumber("06-" + (i+1));
		}
		//level 7
		for (int i = 0; i < MAX_PRESIDENT_ROOMS; i++) {
			president[i].setRoomNumber("07-" + (i+1));
		}

		singleRooms=single;
		doubleRooms=doubl;
		presidentRooms=president;
		deluxeRooms=deluxe;
	}

	//count room under different status
	//by changing _status to "vacant"/"occupied"/"reserved"/"maintenance"
	public static int countRooms(Room[] rooms, int total_room_number, String _status) {
		int counter = 0;
		for (int i = 0; i < total_room_number; i++) {
			if (rooms[i].getRoomStatus().equals(_status))
				counter++;
		}
		return counter;
	}
	
	//print occupancy info for one room type
	public static void printOccupiedRoomInfo(Room[] rooms, String roomType, int occNo, int total_room_number) {
		System.out.println("----------------------------------------------------------------------");
		System.out.println(roomType + ": " + occNo + " out of " + total_room_number + " occupied.");
		System.out.println("Occupied rooms:");
		for (int i = 0; i < total_room_number; i++) {
			if (rooms[i].getRoomStatus().equals("occupied"))
				System.out.printf("|| %d | %s || ",rooms[i].getRoomID(), rooms[i].getRoomNumber());
		}
		System.out.println("----------------------------------------------------------------------");
	}
	
	public void printOccupiedSingleRooms() {
		int occupied_counter;
		occupied_counter = countRooms(singleRooms, MAX_SINGLE_ROOMS, "occupied");
		printOccupiedRoomInfo(singleRooms, "Single Room", occupied_counter, MAX_SINGLE_ROOMS);
	}
	
	public void printOccupiedDoubleRooms() {
		int occupied_counter;
		occupied_counter = countRooms(doubleRooms, MAX_DOUBLE_ROOMS, "occupied");
		printOccupiedRoomInfo(doubleRooms, "Double Room", occupied_counter, MAX_DOUBLE_ROOMS);
	}
	
	public void printOccupiedDeluxeRooms() {
		int occupied_counter;
		occupied_counter = countRooms(deluxeRooms, MAX_DELUXE_ROOMS, "occupied");
		printOccupiedRoomInfo(deluxeRooms, "Deluxe Room", occupied_counter, MAX_DELUXE_ROOMS);
	}
	
	public void printOccupiedPresidentRooms() {
		int occupied_counter;
		occupied_counter = countRooms(presidentRooms, MAX_PRESIDENT_ROOMS, "occupied");
		printOccupiedRoomInfo(presidentRooms, "President Room", occupied_counter, MAX_PRESIDENT_ROOMS);
	}
	
	//print occupancy summary
	public void printOccupiedRoomSummary() {
		//count occupied rooms
		int single_occupied_counter, double_occupied_counter, deluxe_occupied_counter, president_occupied_counter;
		single_occupied_counter = countRooms(singleRooms, MAX_SINGLE_ROOMS, "occupied");
		double_occupied_counter = countRooms(doubleRooms, MAX_DOUBLE_ROOMS, "occupied");
		deluxe_occupied_counter = countRooms(deluxeRooms, MAX_DELUXE_ROOMS, "occupied");
		president_occupied_counter = countRooms(presidentRooms, MAX_PRESIDENT_ROOMS, "occupied");		
		System.out.println("Room Occupancy Rate:");
		System.out.println("==========================================================");  
		//print summary
		printOccupiedRoomInfo(singleRooms, "Single Room", single_occupied_counter, MAX_SINGLE_ROOMS);
		printOccupiedRoomInfo(doubleRooms, "Double Room", double_occupied_counter, MAX_DOUBLE_ROOMS);
		printOccupiedRoomInfo(deluxeRooms, "Deluxe Room", deluxe_occupied_counter, MAX_DELUXE_ROOMS);
		printOccupiedRoomInfo(presidentRooms, "President Room", president_occupied_counter, MAX_PRESIDENT_ROOMS);
		System.out.println("==========================================================");  
	}
	
	//print room status info for one room type
	public static void printRoomStatusInfo(Room[] rooms, String roomType, int total_room_number, String _status) {
		System.out.println("");
		System.out.println(roomType + ": ");
		for (int i = 0; i < total_room_number; i++) {
			if (rooms[i].getRoomStatus().equals(_status))
				System.out.printf("|| %d | %s || ",rooms[i].getRoomID(), rooms[i].getRoomNumber());
		}
		System.out.println("");
	}
	
	public void printVacantRooms() {
		System.out.println("Vacant Rooms: ");
		System.out.println("==========================================================");  
		printRoomStatusInfo(singleRooms, "Single Room", MAX_SINGLE_ROOMS, "vacant");
		printRoomStatusInfo(doubleRooms, "Double Room", MAX_DOUBLE_ROOMS, "vacant");
		printRoomStatusInfo(deluxeRooms, "Deluxe Room", MAX_DELUXE_ROOMS, "vacant");
		printRoomStatusInfo(presidentRooms, "President Room", MAX_PRESIDENT_ROOMS, "vacant");
		System.out.println("==========================================================");  
	}
	
	public void printOccupiedRooms() {
		System.out.println("Occupied Rooms: ");
		System.out.println("==========================================================");  
		printRoomStatusInfo(singleRooms, "Single Room", MAX_SINGLE_ROOMS, "occupied");
		printRoomStatusInfo(doubleRooms, "Double Room", MAX_DOUBLE_ROOMS, "occupied");
		printRoomStatusInfo(deluxeRooms, "Deluxe Room", MAX_DELUXE_ROOMS, "occupied");
		printRoomStatusInfo(presidentRooms, "President Room", MAX_PRESIDENT_ROOMS, "occupied");
		System.out.println("==========================================================");  
	}
	
	public void printReservedRooms() {
		System.out.println("Reserved Rooms: ");
		System.out.println("==========================================================");  
		printRoomStatusInfo(singleRooms, "Single Room", MAX_SINGLE_ROOMS, "reserved");
		printRoomStatusInfo(doubleRooms, "Double Room", MAX_DOUBLE_ROOMS, "reserved");
		printRoomStatusInfo(deluxeRooms, "Deluxe Room", MAX_DELUXE_ROOMS, "reserved");
		printRoomStatusInfo(presidentRooms, "President Room", MAX_PRESIDENT_ROOMS, "reserved");
		System.out.println("==========================================================");  
	}
	
	public void printRoomsUnderMaintenance() {
		System.out.println("Rooms Under Maintenance: ");
		System.out.println("==========================================================");  
		printRoomStatusInfo(singleRooms, "Single Room", MAX_SINGLE_ROOMS, "maintenance");
		printRoomStatusInfo(doubleRooms, "Double Room", MAX_DOUBLE_ROOMS, "maintenance");
		printRoomStatusInfo(deluxeRooms, "Deluxe Room", MAX_DELUXE_ROOMS, "maintenance");
		printRoomStatusInfo(presidentRooms, "President Room", MAX_PRESIDENT_ROOMS, "maintenance");
		System.out.println("==========================================================");  
	}
	
	//print room status summary
	public void printRoomStatusSummary() {
		//vacant
		System.out.println("");
		System.out.println("Vacant Rooms: ");
		System.out.println("==========================================================");  
		printRoomStatusInfo(singleRooms, "Single Room", MAX_SINGLE_ROOMS, "vacant");
		printRoomStatusInfo(doubleRooms, "Double Room", MAX_DOUBLE_ROOMS, "vacant");
		printRoomStatusInfo(deluxeRooms, "Deluxe Room", MAX_DELUXE_ROOMS, "vacant");
		printRoomStatusInfo(presidentRooms, "President Room", MAX_PRESIDENT_ROOMS, "vacant");
		System.out.println("==========================================================");  
		System.out.println("");
		//occupied
		System.out.println("");
		System.out.println("Occupied Rooms: ");
		System.out.println("==========================================================");  
		printRoomStatusInfo(singleRooms, "Single Room", MAX_SINGLE_ROOMS, "occupied");
		printRoomStatusInfo(doubleRooms, "Double Room", MAX_DOUBLE_ROOMS, "occupied");
		printRoomStatusInfo(deluxeRooms, "Deluxe Room", MAX_DELUXE_ROOMS, "occupied");
		printRoomStatusInfo(presidentRooms, "President Room", MAX_PRESIDENT_ROOMS, "occupied");
		System.out.println("==========================================================");  
		System.out.println("");
		//reserved
		System.out.println("");
		System.out.println("Reserved Rooms: ");
		System.out.println("==========================================================");  
		printRoomStatusInfo(singleRooms, "Single Room", MAX_SINGLE_ROOMS, "reserved");
		printRoomStatusInfo(doubleRooms, "Double Room", MAX_DOUBLE_ROOMS, "reserved");
		printRoomStatusInfo(deluxeRooms, "Deluxe Room", MAX_DELUXE_ROOMS, "reserved");
		printRoomStatusInfo(presidentRooms, "President Room", MAX_PRESIDENT_ROOMS, "reserved");
		System.out.println("==========================================================");  
		System.out.println("");
		//under maintenance
		System.out.println("");
		System.out.println("Rooms Under Maintenance: ");
		System.out.println("==========================================================");  
		printRoomStatusInfo(singleRooms, "Single Room", MAX_SINGLE_ROOMS, "maintenance");
		printRoomStatusInfo(doubleRooms, "Double Room", MAX_DOUBLE_ROOMS, "maintenance");
		printRoomStatusInfo(deluxeRooms, "Deluxe Room", MAX_DELUXE_ROOMS, "maintenance");
		printRoomStatusInfo(presidentRooms, "President Room", MAX_PRESIDENT_ROOMS, "maintenance");
		System.out.println("==========================================================");  
		System.out.println("");
	}
	//
	public Room getRoomByRoomID(int rID) {
    	if (rID > 0 && rID <=MAX_SINGLE_ROOMS) {
    		return this.getSingleRoom(rID-1);
    	} else if (rID > MAX_SINGLE_ROOMS && rID <= BY_DOUBLE_ROOMS) {
    		return this.getDoubleRoom(rID-MAX_SINGLE_ROOMS-1);
    	} else if (rID > BY_DOUBLE_ROOMS && rID <= BY_DELUXE_ROOMS) {
    		return this.getDeluxeRoom(rID-BY_DOUBLE_ROOMS-1);
    	} else {
    		return this.getPresidentRoom(rID-BY_DELUXE_ROOMS-1);
    	}
    }
	
	//getter methods
	public Room[] getSingleRooms() {return singleRooms;}
	
	public Room[] getDoubleRooms() {return doubleRooms;}
	
	public Room[] getPresidentRooms() {return presidentRooms;}
	
	public Room[] getDeluxeRooms() {return deluxeRooms;}
	
	public Room getSingleRoom(int a) {return singleRooms[a];}
	
	public Room getDoubleRoom(int a) {return doubleRooms[a];}
	
	public Room getPresidentRoom(int a) {return presidentRooms[a];}
	
	public Room getDeluxeRoom(int a) {return deluxeRooms[a];}
	
	public int getMaxSingleRooms() {return MAX_SINGLE_ROOMS;}
	
	public int getMaxDoubleRooms() {return MAX_DOUBLE_ROOMS;}
	
	public int getMaxDeluxeRooms() {return MAX_DELUXE_ROOMS;}
	
	public int getMaxPresidentRooms() {return MAX_PRESIDENT_ROOMS;}
}

