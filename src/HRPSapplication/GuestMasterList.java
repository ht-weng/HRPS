package HRPSapplication;

import HRPSapplication.Guest;

public class GuestMasterList {
    
    private Guest[] masterList;
    private static int counter=0;//count the number of guests
    
    //Constructor
	public GuestMasterList()
	{
		Guest[] emptyGuestList = new Guest[1000];
		for(int i=0;i<1000;i++)
		{
			emptyGuestList[i] = new Guest();
		}
		masterList = emptyGuestList;
		    System.out.println("Record of 1000 Empty Guest Profile created!" );
	}
	
	public void newGuest()
	{
	    counter++;
	    masterList[counter]= new Guest(0);	   
	}
	
	public static Boolean checkValid(int gID)
	{
	    if((gID<=counter)&(gID>=1))
	    {
	        return true;
	    } 
	    else
	    {   
	        System.out.println("Guest does not exist!");
	        return false;
	    }
	}
	
	public Guest[] getMasterList() {return masterList;}
	
	public Guest getMasterListElement(int a) {return masterList[a];}
	
}

