package HRPSapplication;

import java.util.Scanner;

/**
 * The class managing room service information
 */
public class RoomService {
	private String name;
	private int price;
	private String description;
	Scanner sc = new Scanner(System.in);
	
	/**
	 * Constructor to generate a null room service
	 */
	public RoomService() {}
	
	/**
	 * Constructor to enter a room service
	 * @param _name Service name
	 * @param _price Service price
	 * @param _description Service description
	 */
	public RoomService(String _name, int _price, String _description) {
		name = _name;
		price = _price;
		description = _description;
	}
	
	/**
	 * Update service information
	 */
	public void updateService() {
		int choice;
		System.out.println("Select the attribute to update: ");
		System.out.println("1: Room service name");
		System.out.println("2: Room service price");
		System.out.println("3: Room service description");
		choice = sc.nextInt();
		switch(choice) {
		case 1:
			System.out.println("Enter the new room service name: ");
			sc.nextLine();
			name = sc.nextLine();
			System.out.println("Service name updated!");
			break;
		case 2:
			System.out.println("Enter the new room service price: ");
			price = sc.nextInt();
			System.out.println("Service price updated!");
			break;
		case 3:
			System.out.println("Enter the new room service description: ");
			sc.nextLine();
			description = sc.nextLine();
			System.out.println("Service description updated!");
			break;
		default:
			System.out.println("Invalid option!");
			break;
		}
	}
	
	/**
	 * Remove a room service
	 * @param rmSvcs Array of room services
	 * @param index The index of the room service to be removed
	 * @param noOfSvcs Number of room services
	 */
	public static void removeRoomService(RoomService[] rmSvcs, int index, int noOfSvcs) {
    	if (index == noOfSvcs) {
    	} else {
    		for (int i = index-1; i < noOfSvcs-1; i++) {
    			rmSvcs[i] = rmSvcs[i+1];
    		}
    	} 
    }
	
	//getter and setter methods
	public String getName() {return name;}
	
	public int getPrice() {return price;}
	
	public String getDescription() {return description;}
	
	public void setName(String newName) {name = newName;}
	
	public void setPrice(int newPrice) {price = newPrice;}
	
	public void setDescription(String newDescription) {description = newDescription;}
}
