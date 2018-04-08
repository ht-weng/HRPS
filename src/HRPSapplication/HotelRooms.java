package HRPSapplication;

import HRPSapplication.Room;

public class HotelRooms {
	 
	private Room[] singleRooms;
	private Room[] doubleRooms;
	private Room[] deluxeRooms;
	private Room[] presidentRooms;

	private static final int TOTAL_ROOMS=50;
	private static final int MAX_SINGLE_ROOMS=20;
	private static final int MAX_DOUBLE_ROOMS=10;
	private static final int MAX_DELUXE_ROOMS=10;
	private static final int MAX_PRESIDENT_ROOMS=10;

	public HotelRooms()
	{
    
		Room[] single= new Room[20];
		for(int i=0;i<20;i++)
		{
			single[i] = new Room();    
			single[i].setRoomNumber("01-"+single[i].getRoomID());
			single[i].getRoomType().setRoomType(1);
		}

		Room[] doubl = new Room[10];
		for (int i=0;i<10;i++)
		{
			doubl[i]=new Room();
			doubl[i].setRoomNumber("02-"+doubl[i].getRoomID());
			doubl[i].getRoomType().setRoomType(2);
		}

		Room[] deluxe= new Room[10];
		for (int i=0;i<10;i++)
		{
			deluxe[i]=new Room();
			deluxe[i].setRoomNumber("03-"+deluxe[i].getRoomID());
			deluxe[i].getRoomType().setRoomType(3);
		}

		Room[] president = new Room[10];
		for (int i=0;i<10;i++)
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

	public void printRoomInformation()
	{
		System.out.println("Room Occupancy Rate:");
		System.out.println("====================");
    
		int single_empty_counter=0;
		for (int i=0;i<20;i++)
		{
			if (singleRooms[i].getRoomStatus()!="occ")
			{
				single_empty_counter++;
			}
		}
    
		int double_empty_counter=0;
		for (int i=0;i<10;i++)
		{
			if (doubleRooms[i].getRoomStatus()!="occ")
			{
				double_empty_counter++;
			}
		}
    
		int deluxe_empty_counter=0;
		for (int i=0;i<10;i++)
		{
			if (deluxeRooms[i].getRoomStatus()!="occ")
			{
				deluxe_empty_counter++;
			}
		}
    
		int president_empty_counter=0;
		for (int i=0;i<10;i++)
		{
			if (presidentRooms[i].getRoomStatus()!="occ")
			{
				president_empty_counter++;
			}
		}
    
		//PRINT SUMMARY
		System.out.println("Single:"  +(MAX_SINGLE_ROOMS-single_empty_counter)+ " out of 20 filled");
		System.out.printf("Empty Rooms: ");
		for(int a=0;a<20;a++)
		{
			if(!(singleRooms[a].getRoomStatus()=="occ"||singleRooms[a].getRoomStatus()=="res"))
			{
				System.out.printf("%s ",singleRooms[a].getRoomNumber());
			}
		}
		System.out.println("");
    
		System.out.println("Double:"  +(MAX_DOUBLE_ROOMS-double_empty_counter)+ " out of 10 filled");
		System.out.printf("Empty Rooms: ");
		for(int b=0;b<10;b++)
		{
			if(!(doubleRooms[b].getRoomStatus()=="occ"||doubleRooms[b].getRoomStatus()=="res"))
			{
				System.out.printf("%s ",doubleRooms[b].getRoomNumber());
			}
		}
		System.out.println("");
    
		System.out.println("Deluxe:"  +(MAX_DELUXE_ROOMS-deluxe_empty_counter)+ " out of 10 filled");
		System.out.printf("Empty Rooms: ");
		for(int c=0;c<10;c++)
		{
			if(!(deluxeRooms[c].getRoomStatus()=="occ"||deluxeRooms[c].getRoomStatus()=="res"))
			{
				System.out.printf("%s ",deluxeRooms[c].getRoomNumber());
			}
		}
		System.out.println("");
    
		System.out.println("President:"  +(MAX_PRESIDENT_ROOMS-president_empty_counter)+ " out of 10 filled");
		System.out.printf("Empty Rooms: ");
		for(int d=0;d<10;d++)
		{
			if(!(presidentRooms[d].getRoomStatus()=="occ"||singleRooms[d].getRoomStatus()=="res"))
			{
				System.out.printf("%s ",presidentRooms[d].getRoomNumber());
			}
		}

		System.out.println("");
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

