package HRPSapplication;

import java.util.Scanner;

public class RoomService {
	private String name;
	private int price;
	
	//Null constructor
	public RoomService() {}
	
	//Constructor
	public RoomService(String _name, int _price) {
		name = _name;
		price = _price;
	}
	
	public void updateService() {
		Scanner sc = new Scanner(System.in);
		int choice;
		System.out.println("Select the attribute to update: ");
		System.out.println("1: Room service name");
		System.out.println("2: Room service price");
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
		default:
			System.out.println("Invalid option!");
			break;
		}
		sc.close();
	}
	
	public static void removeRoomService(RoomService[] rmSvcs, int index, int noOfSvcs) {
    	if (index == noOfSvcs) {
    		noOfSvcs--;
    	} else {
    		for (int i = index-1; i < noOfSvcs-1; i++) {
    			rmSvcs[i] = rmSvcs[i+1];
    		}
    		noOfSvcs--;
    	} 
    }
	
	//getter and setter methods
	public String getName() {return name;}
	
	public int getPrice() {return price;}
	
	public void setName(String newName) {name = newName;}
	
	public void setPrice(int newPrice) {price = newPrice;}
}
