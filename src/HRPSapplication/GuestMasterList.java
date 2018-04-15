package HRPSapplication;

import HRPSapplication.Guest;
import java.util.Scanner;

public class GuestMasterList {
    
    private Guest[] masterList;
    private static int counter=0;//count the number of guests
    Scanner sc = new Scanner(System.in);
    
    //Constructor
	public GuestMasterList()
	{
		Guest[] emptyGuestList = new Guest[1000];
		for(int i=0;i<1000;i++)
		{
			emptyGuestList[i] = new Guest();
		}
		masterList = emptyGuestList;
	}
	
	public void newGuest()
	{   
		counter++;
	    masterList[counter-1]= new Guest(0);	   
	}
	
	public static Boolean checkValid(int gID)
	{
	    if((gID<=counter)&(gID>=1))
	        return true;
	    else
	    {   
	        System.out.println("Guest does not exist!");
	        return false;
	    }
	}
	
	public Guest searchGuestByKeywords(GuestMasterList gml, String keyword) {
		Guest nullResult = new Guest();
		Guest[] resultList = new Guest[100];
		int count = -1;
		
		for (int i = 0; i < gml.getCounter(); i++) {
			if (gml.getMasterListElement(i).getName().contains(keyword)) {
				count++;
				resultList[count] = gml.getMasterListElement(i);
			}
		}
		
		if (count == 0) {
			System.out.println("Target guest |" + resultList[0].getName() + "| found!");
			return resultList[0];
		} else if (count == -1){
			System.out.println("Target guest does not exist!");
			return nullResult;
		} else {
			int gID;
			System.out.println((count+1) + " guests found: ");
			for (int i = 0; i <= count; i++) {
				System.out.println((i+1) + ": " + resultList[i].getName() + " Guest ID: " + resultList[i].getGuestID());
			}
			System.out.println("Please enter the Guest ID: ");
			gID = sc.nextInt();
			
			for (int i = 0; i <= count; i++) {
				if (resultList[i].getGuestID() == gID) {
					return resultList[i];
				}
			}
			
			System.out.println("Invalid Guest ID! Guest does not exist!");
			return nullResult;
		}
	}
	
	
	public int getCounter() {return counter;}
	
	public Guest[] getMasterList() {return masterList;}
	
	public Guest getMasterListElement(int a) {return masterList[a];}
	
}

