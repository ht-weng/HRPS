package HRPSapplication;

import HRPSapplication.RoomType;
import java.util.Scanner;

/**
 * The class managing one room's information
 */
public class Room {
	Scanner sc = new Scanner(System.in);
	/**
	 * Uniquely identify a room
	 */
	private int roomID;
	/**
	 * Room number
	 */
	private String roomNumber;    
	/**
	 * Room status can be vacant/occupied/reserved/maintenance. Default status is vacant
	 */
	private String status="vacant";
	/**
	 * Bed size can be double/queen/king, depending on the room type
	 */
	private String bedSize;
	/**
	 * Room type can be single/double/deluxe/president
	 */
	private RoomType roomType; 
	/**
	 * Smoking condition
	 */
	private boolean smoking = false; 
	/**
	 * Count the number of rooms to set the room IF
	 */
	private static int counter=0;
	
	/**
	 * Constructor to initialize a null room
	 */
	public Room() {
		counter++;
		RoomType dum= new RoomType(5);
		roomType = dum;
		roomID = counter;
	}
	
	/**
	 * Update room details
	 */
	public void updateRoomDetails() {
		int choice;
		boolean isRecorded = false;
		System.out.println("Current room info: ");
		printRoomInfo();
		do {
			System.out.println("Select the attribute to update:");
			System.out.println("1.Status");
			System.out.println("2.Smoking");
			System.out.println("3.Exit");
			choice = sc.nextInt();
			
			switch(choice) {
			case 1:
				System.out.println("Enter the new room status(vacant/occupied/reserved/maintenance):");
				sc.nextLine();
				this.status = sc.nextLine();
				isRecorded = true;
				break;
			case 2:
				System.out.println("Enter the new smoking condition(true/false):");
				sc.nextLine();
				this.smoking = sc.nextBoolean();
				isRecorded = true;
				break;
			case 3:
				System.out.println("Exiting...");
				isRecorded = true;
				break;
			default :
				System.out.println("Invalid option!");
				break;	
			}
		}while (!isRecorded);
		//print new room info
		System.out.println("New room info: ");
		printRoomInfo();
	}
	
	/**
	 * Print room information
	 */
	public void printRoomInfo(){
		System.out.println("             Room Information");
		System.out.println("==============================================");
		System.out.println("Room ID: " + roomID);
		System.out.println("----------------------------------------------");
		System.out.println("Room Number: " + roomNumber);    
		System.out.println("----------------------------------------------");
		System.out.println("Status: " + status);
		System.out.println("----------------------------------------------");
		System.out.println("Room Type: " + roomType.getRoomType());
		System.out.println("----------------------------------------------");
		System.out.println("Smoking condition: : " + smoking);
		System.out.println("----------------------------------------------");
		System.out.println("Bed size: " + bedSize);
		System.out.println("----------------------------------------------");
		System.out.println("==============================================");
	}
	
	//getter and setter methods
	public static int getCounter() {return counter;}
	
	public int getRoomID() {return roomID;}

	public RoomType getRoomType() {return roomType;}
	
	public String getRoomStatus() {return status;}
	
	public String getRoomNumber() {return roomNumber;}
	
	public boolean getSmoking() {return smoking;} 
	
	public String getBedSize() {return bedSize;}
	
	public void setRoomType(RoomType type) {roomType = type;}
	
	public void setRoomStatus(String stat) {this.status = stat;}
	
	public void setRoomNumber(String number) {roomNumber = number;}
	
	public void setSmoking(Boolean smoking) {this.smoking = smoking;}
	
	public void setBedSize(String bed) {this.bedSize = bed;}
}
