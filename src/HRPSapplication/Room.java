package HRPSapplication;

import HRPSapplication.RoomType;

public class Room{
	private int roomID;
	private String roomNumber;    
	private String status="vac";//vac,occ,res,man  
	private RoomType roomType; 
	private Boolean smoking=false; 
	private static int counter=0;

	public Room()
	{
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
	
	public void setRoomType(RoomType a) {roomType = a;}
	
	public void setRoomStatus(String a) {status = a;}
	
	public void setRoomNumber(String a) {this.roomNumber = a;}
	
	public void setSmoking(Boolean a) {smoking = a;}
}
