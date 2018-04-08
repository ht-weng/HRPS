package HRPSapplication;

import HRPSapplication.RoomType;

public class Room {
	private int roomID;
	private String roomNumber;    
	private String status="vac";//vac,occ,res,man  
	private RoomType roomType; 
	private Boolean smoking = false; 
	private static int counter=0;

	public Room() {
		counter++;
		RoomType dum= new RoomType(5);
		roomType = dum;
		roomID = counter;
	}
	
	public static int getCounter() {return counter;}
	
	public int getRoomID() {return roomID;}

	public RoomType getRoomType() {return roomType;}
	
	public String getRoomStatus() {return status;}
	
	public String getRoomNumber() {return roomNumber;}
	
	public void setRoomType(RoomType type) {roomType = type;}
	
	public void setRoomStatus(String status) {this.status = status;}
	
	public void setRoomNumber(String number) {roomNumber = number;}
	
	public void setSmoking(Boolean smoking) {this.smoking = smoking;}
}
