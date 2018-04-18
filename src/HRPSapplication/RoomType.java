package HRPSapplication;

/**
 * The class recording the room types and daily rate
 */
public class RoomType {
	/**
	 * Room daily rate
	 */
	private int dailyRate;    
	/**
	 * Room type
	 */
	private String roomName;
   
	/**
	 * Constructor to initialize a null room to the selected room type
	 * @param sel Indicate the room type
	 */
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
	
	/**
	 * Set the room to the selected room type
	 * @param sel Indicate the room type
	 */
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
	
	//getter methods
	public String getRoomType() {return roomName;}
	
	public int getRate() {return dailyRate;}
}
