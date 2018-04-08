package HRPSapplication;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Reservation {
    
	private int reservationID;
	private Guest guest;
	private Room room;
	private String billingInfo;
	private Date checkIn;
	private Date checkOut;
	private int noOfAdult;
	private int noOfChildren;
	private String status="Confirmed";// Confirmed, Checked-In, Expired, Cancelled
	
	private static int counter = 1;
        
        

    Scanner reader= new Scanner(System.in);
        
public void acknowledge(){
	System.out.println("Reservation ID: " +reservationID);
	System.out.println("======================");
	System.out.println("Guest: " +guest.getName());
	System.out.println("Room Number: " +room.getRoomNumber());
	System.out.println("Room Type: " +room.getRoomType().getRoomType());
	System.out.println("Billing Info: "+billingInfo);
	System.out.println("Check In Date:" + checkIn);
	System.out.println("Check Out Date: "+ checkOut);
	System.out.println("Number of Adults: "+noOfAdult);
	System.out.println("Number of Children: "+noOfChildren);	
	
	
}


public void checkExpiry(Reservation c){
	 // this method checks if the reservation has expired, and update the room status and reservation accordingly
      Date now;
      Calendar cal = Calendar.getInstance();
      cal.add(Calendar.HOUR_OF_DAY, 1);
      now = cal.getTime();
      c.getCheckIn();
      if(c.getCheckIn().after(now)){
		updateStatus("Expired");
		this.room.setRoomStatus("vac");
      }
}

public void checkedIn(){
	this.room.setRoomStatus("occ");
	this.status="Checked-In";
}

public Reservation(HotelRooms po,GuestMasterList go)
{
    System.out.println("=======Enter a new Reservation!=====");
    int choice=-1;
    
    System.out.println("Enter a Guest ID: ");
    int check=reader.nextInt();
    System.out.println("Your choice Guest ID is "+check);
    if(GuestMasterList.checkValid(check)==false);
        
    else
    {
        po.printRoomInformation();
        this.guest=  go.getMasterListElement(check); 
        
        System.out.println("");
        System.out.println("Enter a Room ID(1-50): ");
        
        int check2=reader.nextInt();
        
        
        
        //checks if room is empty
        do{
            if(check2<1 || check2>50)
            {
                System.out.println("Number not in 1-50!");
                System.out.println("1| Enter 1 to key in another room number.");
                System.out.println("2| Enter any other button to Exit");
                if(choice!=1)
                    return;
                else
                {
                    System.out.println("Enter a new room number");
                    check2=reader.nextInt();
                    continue;
                }
                           
            }
        
            else if(check2>0 && check2<20)  // if entered room is a Single Room
            {
            
                if(po.getSingleRoom(check2-1).getRoomStatus()=="occ")
                {
                    System.out.println("Room is occupied ");
                    System.out.println("===============");
                    System.out.println("1| Enter 1 to key in another room number.");
                    System.out.println("2| Enter any other button to Exit");
                   choice= reader.nextInt();
                   if (choice!=1)
                       return;
                   else
                    {
                        System.out.println("Enter a new room number");
                        check2=reader.nextInt();
                    }
                }
                // otherwise, room is OKAY!
                else
                {
                    room=po.getSingleRoom(check2-1);
                    po.getSingleRoom(check2-1).setRoomStatus("occ");
                    choice=-2;
                    System.out.println("Room Reserved:" +" 01-"+check2);
                }
            }   

              else if(check2>=20 && check2<=29)  // if entered room is a Double Room
            {
            
                if(po.getDoubleRoom(check2-20).getRoomStatus()=="occ")
                {
                    System.out.println("Room is occupied ");
                    System.out.println("===============");
                    System.out.println("1| Enter 1 to key in another room number.");
                    System.out.println("2| Enter any other button to Exit");
                   choice= reader.nextInt();
                   if (choice!=1)
                       return;
                   else
                    {
                        System.out.println("Enter a new room number");
                        check2=reader.nextInt();
                    }
                }
                // otherwise, room is OKAY!
                else
                {
                    room=po.getDoubleRoom(check2-20);
                    po.getDoubleRoom(check2-20).setRoomStatus("res");
                    choice=-2;
                    System.out.println("Room Reserved:" +" 01-"+check2);
                }
            } 
                else if(check2>=30 && check2<=39)  // if entered room is a Deluxe Room
            {
            
                if(po.getDeluxeRoom(check2-31).getRoomStatus()=="occ")
                {
                    System.out.println("Room is occupied ");
                    System.out.println("===============");
                    System.out.println("1| Enter 1 to key in another room number.");
                    System.out.println("2| Enter any other button to Exit");
                   choice= reader.nextInt();
                   if (choice!=1)
                       return;
                   else
                    {
                        System.out.println("Enter a new room number");
                        check2=reader.nextInt();
                    }
                }
            
                else
                {
                    room=po.getDeluxeRoom(check2-31);
                    po.getDeluxeRoom(check2-31).setRoomStatus("res");
                    choice=-2;
                    System.out.println("Room Reserved:" +" 02-"+check2);
                }
            }  
                
                 else if(check2>=40 && check2<=49)  // if entered room is a Presidential Room
            {
            
                if(po.getPresidentRoom(check2-41).getRoomStatus()=="occ")
                {
                    System.out.println("Room is occupied ");
                    System.out.println("===============");
                    System.out.println("1| Enter 1 to key in another room number.");
                    System.out.println("2| Enter any other button to Exit");
                   choice= reader.nextInt();
                   if (choice!=1)
                       return;
                   else
                    {
                        System.out.println("Enter a new room number");
                        check2=reader.nextInt();
                    }
                }
                // otherwise, room is OKAY!
                else
                {
                    room=po.getPresidentRoom(check2-41);
                    po.getPresidentRoom(check2-41).setRoomStatus("res");
                    choice=-2;
                    System.out.println("Room Reserved:" +" 04-"+check2);
                }
            }
        } while(choice!=-2);
      
        
        //This part is the billing info
        System.out.println("Please enter billing info for cilent");
        System.out.println("|1. Press 1 to enter  new credit card number");
        System.out.println("|2. Press any other number to use default credit number");
        
        int choice1= reader.nextInt();
        if(choice1==1)
        { 
          System.out.println("Enter the alternate credit card number ");
          reader.nextLine();
          this.billingInfo= reader.nextLine();
        }
        else 
            this.billingInfo= guest.getCredit();
        
     
        
        
        System.out.println("Enter the number of adults");
        this.noOfAdult=reader.nextInt();
        System.out.println("Enter the number of children");
        this.noOfChildren=reader.nextInt();
        
        //This part is the date part
        int choiceD=0;
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date cIn = null,cOut,now,checkD = null;
        Calendar cal = Calendar.getInstance();
        now = cal.getTime();
        do{
        System.out.println("Please enter check in Date (19/02/2018)");       
        String inDate = reader.next();
        System.out.println("Please enter check in Time (14:30)");
        String inTime = reader.next();
        String in = inDate + " " + inTime;   
        
        	try{
        		
        		cIn = df.parse(in);
        		if(cIn.compareTo(now) <0){
        			System.out.println("Check in date is before Today!");
        		}
        		else
        		{
        			this.checkIn = cIn;
        			checkD= cIn;
        			choiceD = 1;
        		}
        	}catch (ParseException e){
        		System.out.println("Incorrect format");
        		e.printStackTrace();
        	}
        }while(choiceD!=1);
        
        do
        {
            System.out.println("Please enter check out Date (22/02/2018)");       
            String outDate = reader.next();
            System.out.println("Please enter check out Time (14:30)");
            String outTime = reader.next();
            String out = outDate + " " + outTime;
            	try{
            		cOut = df.parse(out);
               		if(cOut.compareTo(checkD) <0){
               			System.out.println("Check out date is before Check in date!");
               		}
               		else{
               			this.checkOut = cOut;
               			choiceD = 0;
               		}
               	}catch (ParseException e){
               		System.out.println("Incorrect format");
               	}
        }while(choiceD!=0);
        
        
       }
        
}
        

        
        
public int checkReservation(Reservation[] List,Date date,int roomId){ 
	// This method checks if there's any reservation, returns 0 if there isn't and 1 if there is.
	int check =0;
	for(int i=0;i<List.length;i++)
	{
		int rID;
		Date cIn,cOut;
		rID = List[i].getRoom().getRoomID();
		cIn = List[i].getCheckIn();
		cOut = List[i].getCheckOut();
		if(rID == roomId)
		{
			if(date.after(cIn) || date.before(cOut) )
			{
				check =1;
			}
		}
	}
	return check;
}


    
    
    
    
    
    
    
    
    



public Reservation(Guest guest,Room room,String bill,Date in,Date out,int adult,int child){
	setReservationID();
	setGuest(guest);
	setRoom(room);
	setBillingInfo(bill);
	setCheckIn(in);
	setCheckOut(out);
	setNoOfAdult(adult);
	setNoOfChildren(child);
	updateStatus("Confirmed");
	
}

public void updateStatus(String stat){
	if(stat == "Confirmed" || stat == "Checked-In")
		setStatus(stat);
	else if (stat == "Expired" || stat == "Cancelled")
		setStatus(stat);
	else
		System.out.println("Invalid Status");
}



//below is the getter and setter method 
public void setReservationID(){
	reservationID=counter;
	counter++;
	
}
public int getReservationID() {
	return reservationID;
}


public Guest getGuest() {
	return guest;
}

public void setGuest(Guest guest) {
	this.guest = guest;
}

public String getBillingInfo() {
	return billingInfo;
}

public void setBillingInfo(String billingInfo) {
	this.billingInfo = billingInfo;
}

public Date getCheckIn() {
	return checkIn;
}

public void setCheckIn(Date checkIn) {
	this.checkIn = checkIn;
}

public Date getCheckOut() {
	return checkOut;
}

public void setCheckOut(Date checkOut) {
	this.checkOut = checkOut;
}

public int getNoOfAdult() {
	return noOfAdult;
}

public void setNoOfAdult(int noOfAdult) {
	this.noOfAdult = noOfAdult;
}

public int getNoOfChildren() {
	return noOfChildren;
}

public void setNoOfChildren(int noOfChildren) {
	this.noOfChildren = noOfChildren;
}

public String getStatus() {
	return status;
}

private void setStatus(String status) {
	this.status = status;
}


public Room getRoom() {
	return room;
}


public void setRoom(Room room) {
	this.room = room;
}
}

