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
		int number2 = number1*2;
		//level 1
		for (int i = 0; i < number1; i++) {//1-4
			single[i].setRoomNumber("01-0" + (i+1));
		}
		for (int i = number1; i < number2; i++) {//5-8
			doubl[i-number1].setRoomNumber("01-0" + (i+1));
		}
		//level 2
		for (int i = 0; i < number1; i++) {
			single[i+number1].setRoomNumber("02-0" + (i+1));
		}
		for (int i = number1; i < number2; i++) {
			doubl[i].setRoomNumber("02-0" + (i+1));
		}
		//level 3
		for (int i = 0; i < number1; i++) {
			single[i+2*number1].setRoomNumber("03-0" + (i+1));
		}
		for (int i = number1; i < number2; i++) {
			doubl[i+number1].setRoomNumber("03-0" + (i+1));
		}
		//level 4
		for (int i = 0; i < number1; i++) {
			single[i+3*number1].setRoomNumber("04-0" + (i+1));
		}
		for (int i = number1; i < number2; i++) {
			doubl[i+2*number1].setRoomNumber("04-0" + (i+1));
		}
		//level 5
		for (int i = 0; i < number1; i++) {
			single[i+4*number1].setRoomNumber("05-0" + (i+1));
		}
		for (int i = number1; i < number2; i++) {
			doubl[i+3*number1].setRoomNumber("05-0" + (i+1));
		}
		//level 6
		for (int i = 0; i < MAX_DELUXE_ROOMS; i++) {
			deluxe[i].setRoomNumber("06-0" + (i+1));
		}
		//level 7
		for (int i = 0; i < MAX_PRESIDENT_ROOMS; i++) {
			president[i].setRoomNumber("07-0" + (i+1));
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
		System.out.println("Occupied rooms(Room ID|Room Number):");
		if (occNo > 0) {
			for (int i = 0; i < total_room_number; i++) {
				if (rooms[i].getRoomStatus().equals("occupied"))
					System.out.printf("| %d|%s | ",rooms[i].getRoomID(), rooms[i].getRoomNumber());
			}
		} else {
			System.out.println("None");
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
		System.out.println("Room Occupancy Rate:");
		System.out.println("==========================================================");  
		printOccupiedSingleRooms();
		printOccupiedDoubleRooms();
		printOccupiedDeluxeRooms();
		printOccupiedPresidentRooms();
		System.out.println("==========================================================");  
	}
	
	//print room status info for one room type
	public static void printRoomStatusInfo(Room[] rooms, String roomType, int total_room_number, String _status, int counter) {
		System.out.println(roomType + "(Room ID|Room Number): ");
		if (counter > 0) {
			for (int i = 0; i < total_room_number; i++) {
				if (rooms[i].getRoomStatus().equals(_status))
					System.out.printf("| %d|%s | ",rooms[i].getRoomID(), rooms[i].getRoomNumber());
			}
			System.out.println();
		} else {
			System.out.println("None");
		}
	}
	
	public void printVacantRooms() {
		int singleCounter, doubleCounter, deluxeCounter, presidentCounter;
		singleCounter = countRooms(singleRooms, MAX_SINGLE_ROOMS, "vacant");
		doubleCounter = countRooms(doubleRooms, MAX_DOUBLE_ROOMS, "vacant");
		deluxeCounter = countRooms(deluxeRooms, MAX_DELUXE_ROOMS, "vacant");
		presidentCounter = countRooms(presidentRooms, MAX_PRESIDENT_ROOMS, "vacant");
		System.out.println("Vacant Rooms: ");
		System.out.println("==========================================================");  
		printRoomStatusInfo(singleRooms, "Single Room", MAX_SINGLE_ROOMS, "vacant", singleCounter);
		printRoomStatusInfo(doubleRooms, "Double Room", MAX_DOUBLE_ROOMS, "vacant", doubleCounter);
		printRoomStatusInfo(deluxeRooms, "Deluxe Room", MAX_DELUXE_ROOMS, "vacant", deluxeCounter);
		printRoomStatusInfo(presidentRooms, "President Room", MAX_PRESIDENT_ROOMS, "vacant", presidentCounter);
		System.out.println("==========================================================");  
	}
	
	public void printOccupiedRooms() {
		int singleCounter, doubleCounter, deluxeCounter, presidentCounter;
		singleCounter = countRooms(singleRooms, MAX_SINGLE_ROOMS, "occupied");
		doubleCounter = countRooms(doubleRooms, MAX_DOUBLE_ROOMS, "occupied");
		deluxeCounter = countRooms(deluxeRooms, MAX_DELUXE_ROOMS, "occupied");
		presidentCounter = countRooms(presidentRooms, MAX_PRESIDENT_ROOMS, "occupied");
		System.out.println("Occupied Rooms: ");
		System.out.println("==========================================================");  
		printRoomStatusInfo(singleRooms, "Single Room", MAX_SINGLE_ROOMS, "occupied", singleCounter);
		printRoomStatusInfo(doubleRooms, "Double Room", MAX_DOUBLE_ROOMS, "occupied", doubleCounter);
		printRoomStatusInfo(deluxeRooms, "Deluxe Room", MAX_DELUXE_ROOMS, "occupied", deluxeCounter);
		printRoomStatusInfo(presidentRooms, "President Room", MAX_PRESIDENT_ROOMS, "occupied", presidentCounter);
		System.out.println("==========================================================");  
	}
	
	public void printReservedRooms() {
		int singleCounter, doubleCounter, deluxeCounter, presidentCounter;
		singleCounter = countRooms(singleRooms, MAX_SINGLE_ROOMS, "reserved");
		doubleCounter = countRooms(doubleRooms, MAX_DOUBLE_ROOMS, "reserved");
		deluxeCounter = countRooms(deluxeRooms, MAX_DELUXE_ROOMS, "reserved");
		presidentCounter = countRooms(presidentRooms, MAX_PRESIDENT_ROOMS, "reserved");
		System.out.println("Reserved Rooms: ");
		System.out.println("==========================================================");  
		printRoomStatusInfo(singleRooms, "Single Room", MAX_SINGLE_ROOMS, "reserved", singleCounter);
		printRoomStatusInfo(doubleRooms, "Double Room", MAX_DOUBLE_ROOMS, "reserved", doubleCounter);
		printRoomStatusInfo(deluxeRooms, "Deluxe Room", MAX_DELUXE_ROOMS, "reserved", deluxeCounter);
		printRoomStatusInfo(presidentRooms, "President Room", MAX_PRESIDENT_ROOMS, "reserved", presidentCounter);
		System.out.println("==========================================================");  
	}
	
	public void printRoomsUnderMaintenance() {
		int singleCounter, doubleCounter, deluxeCounter, presidentCounter;
		singleCounter = countRooms(singleRooms, MAX_SINGLE_ROOMS, "maintenance");
		doubleCounter = countRooms(doubleRooms, MAX_DOUBLE_ROOMS, "maintenance");
		deluxeCounter = countRooms(deluxeRooms, MAX_DELUXE_ROOMS, "maintenance");
		presidentCounter = countRooms(presidentRooms, MAX_PRESIDENT_ROOMS, "maintenance");
		System.out.println("Rooms Under Maintenance: ");
		System.out.println("==========================================================");  
		printRoomStatusInfo(singleRooms, "Single Room", MAX_SINGLE_ROOMS, "maintenance", singleCounter);
		printRoomStatusInfo(doubleRooms, "Double Room", MAX_DOUBLE_ROOMS, "maintenance", doubleCounter);
		printRoomStatusInfo(deluxeRooms, "Deluxe Room", MAX_DELUXE_ROOMS, "maintenance", deluxeCounter);
		printRoomStatusInfo(presidentRooms, "President Room", MAX_PRESIDENT_ROOMS, "maintenance", presidentCounter);
		System.out.println("==========================================================");  
	}
	
	//print room status summary
	public void printRoomStatusSummary() {
		System.out.println("");
		printVacantRooms();
		printOccupiedRooms();
		printReservedRooms();
		printRoomsUnderMaintenance();
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

