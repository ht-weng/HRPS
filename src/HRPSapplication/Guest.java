package HRPSapplication;

import java.util.Scanner;

public class Guest 
{
	//unique identifier
	private int guestID; 
	//personal information
	private String name;
	private String creditCardDetails;
	private String address;
	private String country;
	private String gender;
	private String identity;
	private String nationality;
	private int phone;
	//counter for the number of guests
	private static int totalGuests=0;

	//Constructor
	public Guest(int a)
	{
		Scanner sc= new Scanner(System.in);
		totalGuests++;
		System.out.println("Please enter information for new guest (guestID:"+(totalGuests)+"):");
		guestID = totalGuests;
    
		System.out.println("Please enter name: ");
		name = sc.nextLine();
		System.out.println("Guest name recorded!");

		System.out.println("Please enter credit card number: ");
		creditCardDetails = sc.nextLine();
    
		System.out.println("Please enter address: ");
		address = sc.nextLine();
 
		System.out.println("Please enter country: ");
		country = sc.nextLine();
    
		System.out.println("Please enter gender:");
		gender = sc.nextLine();
    
		System.out.println("Please enter identity: ");
		identity = sc.nextLine();
    
		System.out.println("Please enter nationality: ");
		nationality = sc.nextLine();
    
		System.out.println("Please enter phone number: ");
		phone = sc.nextInt();
		sc.close();
	}

	public Guest() {}

	public static int gettotalGuests()
	{
		return totalGuests;
	}
        
	public void updateGuest()
	{
		Scanner sc = new Scanner(System.in);
		int choice = -1;
		System.out.println("");
   
		do{
			System.out.printf("Choose an attribute to update for Guest"+this.guestID+"\n"
					+"|1. Update Guest Name         |\n"
					+"|2. Update Credit Card Number |\n"
					+"|3. Update Address            |\n"
					+"|4. Update Country            |\n"
					+"|5. Update Gender             |\n"
					+"|6. Update Identity           |\n"     
					+"|7. Update Nationality        |\n"
					+"|8. Update phone Number     |\n"
					+"|9. Exit                      |\n");
    
			choice = sc.nextInt();
			sc.nextLine();
    
			switch(choice)
			{
			case 1:
				//update name
				System.out.println("Enter the updated Guest Name: ");
				String newname = sc.nextLine();
				this.name = newname;
				System.out.println("Guest Name Updated!");
				break;
			case 2:
				//update credit card number
				System.out.println("Enter the updated Credit Card Number: ");
				String newcredit = sc.nextLine();
				creditCardDetails = newcredit;
				System.out.println("Guest Name Updated!");
				break;
			case 3:
				//update address
				System.out.println("Enter the updated address: ");
				String newadd = sc.nextLine();
				address = newadd;
				break;
			case 4:
				//update country
				System.out.println("Enter the updated country of origin: ");
				String newcountry = sc.nextLine();
				country = newcountry;
				break;
			case 5:
				//update gender
				System.out.println("Enter the updated gender(M for Male, F for Female");
				String newgender = sc.nextLine();
				gender = newgender;
            	break;
			case 6:
				//update identity
				System.out.println("Enter the updated identity: ");
				String newidentity = sc.nextLine();
				identity = newidentity;
				break;
			case 7:
				//update nationality
				System.out.println("Enter the updated nationality: ");
				String newnationality = sc.nextLine();
				nationality = newnationality;
				break;
			case 8:
				//update phone
				System.out.println("Enter the updated phone number: ");
				int newphone = sc.nextInt();
				phone = newphone;
				break;    
			case 9:
				//exit
				System.out.println("Record Updated!");
				break;
			default:
				System.out.println("Please enter a number from 1-9.");
				break;
			}
		}while(choice!=9);		
		sc.close();
	}	

	public void printGuestDetail()
	{
		System.out.println("Guest ID: ");
		System.out.println("======================");
		System.out.println("Guest Name: "+name);    
		System.out.println("Guest Credit Number:"+creditCardDetails);    
		System.out.println("Guest Address: "+address);
		System.out.println("Guest Country: "+country);
		System.out.println("Guest Gender: "+gender);
		System.out.println("Guest Identity: "+identity);
		System.out.println("Guest Nationality: "+nationality);
		System.out.println("Guest Phone:  "+phone);
	}
      
	public String getName()
	{
		return name;
	}
	public String getCredit()
	{
		return creditCardDetails;
	}
} 
