package HRPSapplication;

import java.util.Scanner;

public class Guest
{
	Scanner sc= new Scanner(System.in);
	//unique identifier
	private int guestID; 
	//differentiate recorded guests from empty guests
	private boolean isEmpty;
	//personal information
	private String name;
	private String creditCardDetails;
	private String address;
	private String country;
	private String gender;
	private String identity;
	private String nationality;
	private long phone;
	//number of guests
	private static int totalGuests=0;

	//Constructor for recording guest information
	public Guest(int a)
	{
		isEmpty = false;
		totalGuests++;
		System.out.println("Please enter information for new guest (guestID:"+(totalGuests)+"):");
		guestID = totalGuests;
    
		System.out.println("Please enter name: ");
		name = sc.nextLine();
		System.out.println("Guest name recorded!");
		System.out.println("");

		System.out.println("Please enter credit card number: ");
		creditCardDetails = sc.nextLine();
		System.out.println("Guest credit card recorded!");
		System.out.println("");
    
		System.out.println("Please enter address: ");
		address = sc.nextLine();
		System.out.println("Guest address recorded!");
		System.out.println("");
 
		System.out.println("Please enter country of residence: ");
		country = sc.nextLine();
		System.out.println("Guest country of residence recorded!");
		System.out.println("");
    
		System.out.println("Please enter gender(M for Male, F for Female, O for Other:");
		gender = sc.nextLine();
		System.out.println("Guest gender recorded!");
		System.out.println("");
    
		System.out.println("Please enter guest identity: ");
		System.out.println("1: Passport");
		System.out.println("2: Driving License");
		System.out.println("Please choose guest identity type(1/2): ");
		int choice = -1;
		boolean isRecorded = false;
		choice = sc.nextInt();
		do {
			switch(choice) {
			case 1:
				System.out.println("Please enter passport number: ");
				sc.nextLine();
				identity = sc.nextLine();
				isRecorded = true;
				break;
			case 2:
				System.out.println("Please enter driving license number: ");
				sc.nextLine();
				identity = sc.nextLine();
				isRecorded = true;
				break;
			default:
				System.out.println("Invalid option! PLease re-enter your option(1/2): ");
				choice = sc.nextInt();
				break;
			}
		} while(!isRecorded);
		System.out.println("Guest identity recorded!");
		System.out.println("");
    
		System.out.println("Please enter nationality: ");
		nationality = sc.nextLine();
		System.out.println("Guest nationality recorded!");
		System.out.println("");
    
		System.out.println("Please enter phone number: ");
		phone = sc.nextLong();
		System.out.println("Guest phone number recorded!");
		System.out.println("");
	}

	//Constructor for making empty guest list
	public Guest() {isEmpty = true;}

	
	//method for updating guest information
	public void updateGuest()
	{
		int choice;
		System.out.println("");
		do{
			System.out.printf("Choose an attribute to update for Guest " + this.name + " (guest ID: " + this.guestID + ")"+ "\n"
					+"|1. Update Guest Name           |\n"
					+"|2. Update Credit Card Number   |\n"
					+"|3. Update Address              |\n"
					+"|4. Update Country Of Residence |\n"
					+"|5. Update Gender               |\n"
					+"|6. Update Identity             |\n"     
					+"|7. Update Nationality          |\n"
					+"|8. Update phone Number         |\n"
					+"|9. Exit                        |\n");
    
			choice = sc.nextInt();
			sc.nextLine();
    
			switch(choice)
			{
			case 1:
				//update name
				System.out.println("Enter the updated Guest Name: ");
				this.name = sc.nextLine();
				System.out.println("Guest name updated!");
				break;
			case 2:
				//update credit card number
				System.out.println("Enter the updated Credit Card Number: ");
				this.creditCardDetails = sc.nextLine();
				System.out.println("Guest credit card updated!");
				break;
			case 3:
				//update address
				System.out.println("Enter the updated address: ");
				this.address = sc.nextLine();
				System.out.println("Guest address updated!");
				break;
			case 4:
				//update country
				System.out.println("Enter the updated country of residence: ");
				this.country = sc.nextLine();
				System.out.println("Guest country of residence updated!");
				break;
			case 5:
				//update gender
				System.out.println("Enter the updated gender(M for Male, F for Female, O for Other");
				this.gender = sc.nextLine();
				System.out.println("Guest gender updated!");
            	break;
			case 6:
				//update identity
				System.out.println("Enter the updated identity type: ");
				System.out.println("1: Passport");
				System.out.println("2: Driving License");
				int choice2 = -1;
				boolean isRecorded = false;
				choice2 = sc.nextInt();
				do {
					switch(choice2) {
					case 1:
						System.out.println("Please enter passport number: ");
						this.identity = sc.nextLine();
						isRecorded = true;
						break;
					case 2:
						System.out.println("Please enter driving license number: ");
						this.identity = sc.nextLine();
						isRecorded = true;
						break;
					default:
						System.out.println("Invalid option! PLease re-enter your option(1/2): ");
						choice2 = sc.nextInt();
						break;
					}
				} while(!isRecorded);
				System.out.println("Guest identity updated!");		
				break;
			case 7:
				//update nationality
				System.out.println("Enter the updated nationality: ");
				this.nationality = sc.nextLine();
				System.out.println("Guest nationality updated!");	
				break;
			case 8:
				//update phone
				System.out.println("Enter the updated phone number: ");
				this.phone = sc.nextLong();
				System.out.println("Guest phone number updated!");	
				break;    
			case 9:
				//exit
				System.out.println("Exiting...");
				break;
			}
		}while(choice > 0 && choice < 10);		
	}	

	public void printGuestDetail()
	{
		System.out.println("Guest ID: " + guestID);
		System.out.println("======================================");
		System.out.println("Guest Name: " + name);    
		System.out.println("Guest Credit Number:" + creditCardDetails);    
		System.out.println("Guest Address: " + address);
		System.out.println("Guest Country: " + country);
		System.out.println("Guest Gender: " + gender);
		System.out.println("Guest Identity: " + identity);
		System.out.println("Guest Nationality: " + nationality);
		System.out.println("Guest Phone:  " + phone);
	}
    
	public static int getTotalGuests() {return totalGuests;}
	
	public String getName() {return name;}
	
	public String getCredit() {return creditCardDetails;}
	
	public int getGuestID() {return guestID;}
	
	public boolean getIsEmpty() {return isEmpty;}
} 
