package HRPSapplication;

public class RoomType {
	private int dailyRate;    
	private String roomName;
   
	//Constructor
	public RoomType(int sel) {
		switch (sel) {
		case 1:
			roomName="single";
			dailyRate=50;
			break;
		case 2:
			roomName="double";
			dailyRate=100;
			break;
		case 3:
			roomName="deluxe";
			dailyRate=300;
			break;
		case 4:
			roomName="president";
			dailyRate=500; 
			break;
		default:
			roomName="NIL";
			dailyRate=0;
			break;
		}
	}
	
	public void setRoomType(int sel) {
		switch (sel) {
		case 1:
			roomName="single";
			dailyRate=50;
			break;
		case 2:
			roomName="double";
			dailyRate=100;
			break;
		case 3:
			roomName="deluxe";
			dailyRate=300;
			break;
		case 4:
			roomName="president";
			dailyRate=500; 
			break;
		default:
			roomName="NIL";
			dailyRate=0;
			break;
		}
	}
	
	public String getRoomType() {return roomName;}
	
	public int getRate() {return dailyRate;}
}
