package HRPSapplication;

import HRPSapplication.RoomType;
import java.util.Scanner;

public class Room {
	Scanner sc = new Scanner(System.in);
	private int roomID;
	private String roomNumber;    
	private String status="vacant";//vacant,occupied,reserved,maintenance  
	private RoomType roomType; 
	private boolean smoking = false; 
	private static int counter=0;
	private String bedSize;//double, queen, king

	//Constructor
	public Room() {
		counter++;
		RoomType dum= new RoomType(5);
		roomType = dum;
		roomID = counter;
	}
	
	//
	public void updateRoomDetails() {
		int choice;
		boolean isRecorded = false;
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
	
	public void setRoomStatus(String stat) {
		if(stat.equals("vacant") || stat.equals("occupied") || stat.equals("reserved") || stat.equals("maintenance"))
			this.status = stat;
		else
			System.out.println("Invalid status!");
	}
	
	public void setRoomNumber(String number) {roomNumber = number;}
	
	public void setSmoking(Boolean smoking) {this.smoking = smoking;}
	
	public void setBedSize(String bed) {this.bedSize = bed;}
}
