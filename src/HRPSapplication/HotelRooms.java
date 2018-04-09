package HRPSapplication;

import HRPSapplication.Room;

public class HotelRooms {
	 
	private Room[] singleRooms;
	private Room[] doubleRooms;
	private Room[] deluxeRooms;
	private Room[] presidentRooms;

	private static final int MAX_SINGLE_ROOMS=20;
	private static final int MAX_DOUBLE_ROOMS=20;
	private static final int MAX_DELUXE_ROOMS=6;
	private static final int MAX_PRESIDENT_ROOMS=2;

	public HotelRooms()
	{
    
		Room[] single= new Room[MAX_SINGLE_ROOMS];
		for(int i=0;i<MAX_SINGLE_ROOMS;i++)
		{
			single[i] = new Room();    
			single[i].setRoomNumber("01-"+single[i].getRoomID());
			single[i].getRoomType().setRoomType(1);
		}

		Room[] doubl = new Room[MAX_DOUBLE_ROOMS];
		for (int i=0;i<MAX_DOUBLE_ROOMS;i++)
		{
			doubl[i]=new Room();
			doubl[i].setRoomNumber("02-"+doubl[i].getRoomID());
			doubl[i].getRoomType().setRoomType(2);
		}

		Room[] deluxe= new Room[MAX_DELUXE_ROOMS];
		for (int i=0;i<MAX_DELUXE_ROOMS;i++)
		{
			deluxe[i]=new Room();
			deluxe[i].setRoomNumber("03-"+deluxe[i].getRoomID());
			deluxe[i].getRoomType().setRoomType(3);
		}

		Room[] president = new Room[MAX_PRESIDENT_ROOMS];
		for (int i=0;i<MAX_PRESIDENT_ROOMS;i++)
		{
			president[i]=new Room();
			president[i].setRoomNumber("04-"+president[i].getRoomID());
			president[i].getRoomType().setRoomType(4);
		}

		singleRooms=single;
		doubleRooms=doubl;
		presidentRooms=president;
		deluxeRooms=deluxe;
	}

	public static int countVacantRooms(Room[] rooms, int total_room_number) {
		int counter = 0;
		for (int i = 0; i < total_room_number; i++) {
			if (rooms[i].getRoomStatus().equals("vacant"))
				counter++;
		}
		return counter;
	}
	
	public static void printRoomSummary(Room[] rooms, String roomType, int vacantNo, int total_room_number) {
		System.out.println(roomType + ": " + vacantNo + " out of " + total_room_number + " vacant.");
		System.out.println("Vacant rooms:");
		for (int i = 0; i < total_room_number; i++) {
			if (rooms[i].getRoomStatus().equals("vacant"))
				System.out.printf("%s, ",rooms[i].getRoomNumber());
		}
		System.out.println("");
	}
	
	public void printRoomInformation()
	{
		//count vacant rooms
		int single_vacant_counter, double_vacant_counter, deluxe_vacant_counter, president_vacant_counter;
		single_vacant_counter = countVacantRooms(singleRooms, MAX_SINGLE_ROOMS);
		double_vacant_counter = countVacantRooms(doubleRooms, MAX_DOUBLE_ROOMS);
		deluxe_vacant_counter = countVacantRooms(deluxeRooms, MAX_DELUXE_ROOMS);
		president_vacant_counter = countVacantRooms(presidentRooms, MAX_PRESIDENT_ROOMS);
		
		System.out.println("Room Occupancy Rate:");
		System.out.println("==========================================================");
    
		//PRINT SUMMARY
		printRoomSummary(singleRooms, "Single Room", single_vacant_counter, MAX_SINGLE_ROOMS);
		printRoomSummary(doubleRooms, "Double Room", double_vacant_counter, MAX_DOUBLE_ROOMS);
		printRoomSummary(deluxeRooms, "Deluxe Room", deluxe_vacant_counter, MAX_DELUXE_ROOMS);
		printRoomSummary(presidentRooms, "President Room", president_vacant_counter, MAX_PRESIDENT_ROOMS);
		
	}
	
	public Room[] getSingleRooms() {return singleRooms;}
	
	public Room[] getDoubleRooms() {return doubleRooms;}
	
	public Room[] getPresidentRooms() {return presidentRooms;}
	
	public Room[] getDeluxeRooms() {return deluxeRooms;}
	
	public Room getSingleRoom(int a) {return singleRooms[a];}
	
	public Room getDoubleRoom(int a) {return doubleRooms[a];}
	
	public Room getPresidentRoom(int a) {return presidentRooms[a];}
	
	public Room getDeluxeRoom(int a) {return deluxeRooms[a];}
	
}

