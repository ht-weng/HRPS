package HRPSapplication;

import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Reservation {
	Scanner sc = new Scanner(System.in);
    
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
        
	public void reservationReceipt() {
		System.out.println("reservation ID: " + reservationID);
		System.out.println("=====================================");
		System.out.println("Guest: " + guest.getName());
		System.out.println("Room Number: " + room.getRoomNumber());
		System.out.println("Room Type: " + room.getRoomType().getRoomType());
		System.out.println("Billing Info: " + billingInfo);
		System.out.println("Check In Date:" + checkIn);
		System.out.println("Check Out Date: " + checkOut);
		System.out.println("Number of Adults: " + noOfAdult);
		System.out.println("Number of Children: " + noOfChildren);					
	}

	//checks if the reservation has expired, and updates the room status and reservation accordingly
	public void checkExpiry() {	 
		Date now;
	    Calendar cal = Calendar.getInstance();
	    cal.add(Calendar.HOUR_OF_DAY, 1);
	    now = cal.getTime();
	    if(checkIn.after(now)) {
	    	updateStatus("Expired");
			room.setRoomStatus("vacant");
	    }
	}

	public void checkedIn() {
		this.room.setRoomStatus("occupied");
		this.status="Checked-In";
	}
	
	public Reservation(HotelRooms rooms,GuestMasterList gml) {
		setReservationID();
	    System.out.println("=======Enter a new reservation!=====");
	    int choice = -1;
	    
	    System.out.println("Enter a Guest ID: ");
	    int gID = sc.nextInt();
	    System.out.println("Your choice Guest ID is " + gID);
	    if(GuestMasterList.checkValid(gID)) {
	        rooms.printRoomInformation();
	        this.guest = gml.getMasterListElement(gID); 
	        
	        System.out.println("");
	        System.out.println("Enter a Room ID(1-48): ");
	        
	        int rID = sc.nextInt();
	        
	        //checks if room is empty
	        do {
	            if(rID < 1 || rID > 48) {
	                System.out.println("Number not in 1-48!");
	                System.out.println("1| Enter 1 to key in another room number.");
	                System.out.println("2| Enter any other number to Exit");
	                choice = sc.nextInt();
	                if(choice != 1)
	                	return;  
	                else {
	                    System.out.println("Enter a new room number");
	                    rID = sc.nextInt();
	                }          
	            } else if(rID > 0 && rID <= 20) { //reserve a Single Room
	            	if(rooms.getSingleRoom(rID-1).getRoomStatus().equals("occupied")) {
	            		System.out.println("Room is occupied ");
	                    System.out.println("===============");
	                    System.out.println("1| Enter 1 to key in another room number.");
	                    System.out.println("2| Enter any other number to Exit");
	                    choice= sc.nextInt();
	                    if (choice != 1)
	                    	return;
	                    else {
	                        System.out.println("Enter a new room number");
	                        rID = sc.nextInt();
	                    }
	                } else {
	                	// otherwise, room is available 
	                    room = rooms.getSingleRoom(rID-1);
	                    rooms.getSingleRoom(rID-1).setRoomStatus("reserved");
	                    choice = -2;
	                    System.out.println("Room reserved:" +" 01-"+rID);
	                }
	            } else if(rID > 20 && rID <= 40) { //reserve a Double Room
	                if(rooms.getDoubleRoom(rID-21).getRoomStatus().equals("occupied")) {
	                    System.out.println("Room is occupied ");
	                    System.out.println("===============");
	                    System.out.println("1| Enter 1 to key in another room number.");
	                    System.out.println("2| Enter any other number to Exit");
	                    choice= sc.nextInt();
	                    if (choice!=1)
	                    	return;
	                    else {
	                        System.out.println("Enter a new room number");
	                        rID = sc.nextInt();
	                    }
	                } else {
	                // otherwise, room is vacant
	                	room=rooms.getDoubleRoom(rID-21);
	                    rooms.getDoubleRoom(rID-21).setRoomStatus("reserved");
	                    choice=-2;
	                    System.out.println("Room reserved:" +" 02-"+rID);
	                }
	            } else if(rID>40 && rID<=46) {  //reserve a Deluxe Room
	                if(rooms.getDeluxeRoom(rID-41).getRoomStatus().equals("occupied")) {
	                    System.out.println("Room is occupied ");
	                    System.out.println("===============");
	                    System.out.println("1| Enter 1 to key in another room number.");
	                    System.out.println("2| Enter any other number to Exit");
	                   choice= sc.nextInt();
	                   if (choice!=1)
	                       return;
	                   else {
	                        System.out.println("Enter a new room number");
	                        rID=sc.nextInt();
	                   }
	                } else {
	                    room=rooms.getDeluxeRoom(rID-41);
	                    rooms.getDeluxeRoom(rID-41).setRoomStatus("reserved");
	                    choice=-2;
	                    System.out.println("Room reserved:" +" 03-"+rID);
	                }
	            } else if(rID>46 && rID<=48) { //reserve a Presidential Room
	            	if(rooms.getPresidentRoom(rID-47).getRoomStatus().equals("occupied")) {
	                    System.out.println("Room is occupied ");
	                    System.out.println("===============");
	                    System.out.println("1| Enter 1 to key in another room number.");
	                    System.out.println("2| Enter any other button to Exit");
	                   choice= sc.nextInt();
	                   if (choice!=1)
	                       return;
	                   else {
	                        System.out.println("Enter a new room number");
	                        rID=sc.nextInt();
	                   }
	                } else {
	                // otherwise, room is OKAY!
	                    room=rooms.getPresidentRoom(rID-47);
	                    rooms.getPresidentRoom(rID-47).setRoomStatus("reserved");
	                    choice=-2;
	                    System.out.println("Room reserved:" +" 04-"+rID);
	                }
	            }
	        } while(choice!=-2);
	     
	        System.out.println("Enter the number of adults");
	        this.noOfAdult=sc.nextInt();
	        System.out.println("Enter the number of children");
	        this.noOfChildren=sc.nextInt();
	        
	        //This part is the billing info
	        System.out.println("Please enter billing info for cilent");
	        System.out.println("|1. Press 1 to enter new credit card number");
	        System.out.println("|2. Press 2 to use original credit number");
	        
	        int choice1= sc.nextInt();
	        if(choice1==1) { 
	        	System.out.println("Enter the alternate credit card number ");
	        	sc.nextLine();
	        	this.billingInfo= sc.nextLine();
	        } else {
	            this.billingInfo= guest.getCredit();
	        }
	           
	        
	        //This part is the date part
	        /*boolean isRecorded = false;
	        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	        Date cIn = null,cOut,now,checkD = null;
	        Calendar cal = Calendar.getInstance();
	        now = cal.getTime();
	        do {
	        	System.out.println("Please enter check in Date (dd/mm/yyyy)");       
	        	String inDate = sc.next();
	        	System.out.println("Please enter check in Time (hh:mm)");
	        	String inTime = sc.next();
	        	String in = inDate + " " + inTime;   
	        
	        	try {
	        		cIn = df.parse(in);
	        		if(cIn.compareTo(now) < 0) {
	        			System.out.println("Check in date is before Today!");
	        		} else {
	        			this.checkIn = cIn;
	        			checkD= cIn;
	        			isRecorded = true;
	        		}
	        	} catch (ParseException e){
	        		System.out.println("Incorrect format!");
	        		e.printStackTrace();
	        	}
	        } while(!isRecorded);
	        
	        isRecorded = false;
	        do {
	            System.out.println("Please enter check out Date (dd/mm/yyyy)");       
	            String outDate = sc.next();
	            System.out.println("Please enter check out Time (hh:mm)");
	            String outTime = sc.next();
	            String out = outDate + " " + outTime;
	            	try{
	            		cOut = df.parse(out);
	               		if(cOut.compareTo(checkD) <0) {
	               			System.out.println("Check out date is before Check in date!");
	               		} else {
	               			this.checkOut = cOut;
	               			isRecorded = true;
	               		}
	               	}catch (ParseException e){
	               		System.out.println("Incorrect format!");
	               	}
	        } while(!isRecorded); */
	        enterCheckInDate();
	        enterCheckOutDate();
	        reservationReceipt();
	    }
	}
	 
	public void enterCheckInDate() {
		boolean isRecorded = false;
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date cIn = null,now;
        Calendar cal = Calendar.getInstance();
        now = cal.getTime();
        do {
        	System.out.println("Please enter check in Date (dd/mm/yyyy)");       
        	String inDate = sc.next();
        	System.out.println("Please enter check in Time (hh:mm)");
        	String inTime = sc.next();
        	String in = inDate + " " + inTime;   
        
        	try {
        		cIn = df.parse(in);
        		if(cIn.compareTo(now) < 0) {
        			System.out.println("Check in date is before Today!");
        		} else {
        			this.checkIn = cIn;
        			isRecorded = true;
        		}
        	} catch (ParseException e){
        		System.out.println("Incorrect format!");
        		e.printStackTrace();
        	}
        } while(!isRecorded);
	}
	
	public void enterCheckOutDate() {
		boolean isRecorded = false;
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date cOut;
        do {
            System.out.println("Please enter check out Date (dd/mm/yyyy)");       
            String outDate = sc.next();
            System.out.println("Please enter check out Time (hh:mm)");
            String outTime = sc.next();
            String out = outDate + " " + outTime;
            	try{
            		cOut = df.parse(out);
               		if(cOut.compareTo(this.getCheckIn()) <0) {
               			System.out.println("Check out date is before Check in date!");
               		} else {
               			this.checkOut = cOut;
               			isRecorded = true;
               		}
               	}catch (ParseException e){
               		System.out.println("Incorrect format!");
               	}
        } while(!isRecorded); 	
	}
	
	public void updateReservation() {
		boolean isRecorded = false;
		int choice;
		do {
			System.out.println("Select the attribute to update:");
			System.out.println("1.Guest");
			System.out.println("2.Room//TODO");
			System.out.println("3.Check In Date");
			System.out.println("4.Check Out Date");
			System.out.println("5.Status");
			System.out.println("6.Number of Adult");
			System.out.println("7.Number of Children");
			System.out.println("8.Billing Info");
			System.out.println("9.Exit");
			choice = sc.nextInt();
		
			switch(choice) {
			case 1:
				this.guest.updateGuest();
				isRecorded = true;
				break;
			case 2:
				//TODO:update room type or room number
				isRecorded = true;
				break;
			case 3:
				enterCheckInDate();
				isRecorded = true;
				break;
			case 4:
				enterCheckOutDate();
				isRecorded = true;
				break;
			case 5:
				System.out.println("Please enter the new status");
				sc.nextLine();
				String stat = sc.nextLine();
				updateStatus(stat);
				isRecorded = true;
				break;
			case 6:
				System.out.println("Enter the new number of adult:");
				this.noOfAdult = sc.nextInt();
				isRecorded = true;
				break;
			case 7:
				System.out.println("Enter the new number of children:");
				this.noOfChildren = sc.nextInt();
				isRecorded = true;
				break;
			case 8:
				System.out.println("Please enter the new credit card number for cilent");
				sc.nextLine();
		        this.billingInfo= sc.nextLine();	       
				isRecorded = true;
				break;
			case 9:
				System.out.println("Exiting...");
				isRecorded = true;
				break;
			default:
				System.out.println("Invalid Option!");
				break;		
			}
		} while (!isRecorded);
		
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

	public Reservation(Guest guest,Room room,String bill,Date in,Date out,int adult,int child) {
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
	
	public void updateStatus(String stat) {
		if(stat.equals("Confirmed") || stat.equals("Checked-In") || stat.equals("Expired") || stat.equals("Cancelled"))
			setStatus(stat);
		else
			System.out.println("Invalid Status");
	}

	//below is the getter and setter method 
	public void setReservationID() {
		reservationID=counter;
		counter++;
	}
	
	public int getReservationID() {return reservationID;}
	
	public Guest getGuest() {return guest;}
	
	public void setGuest(Guest guest) {this.guest = guest;}
	
	public String getBillingInfo() {return billingInfo;}
	
	public void setBillingInfo(String billingInfo) {this.billingInfo = billingInfo;}
	
	public Date getCheckIn() {return checkIn;}
	
	public void setCheckIn(Date checkIn) {this.checkIn = checkIn;}
	
	public Date getCheckOut() {return checkOut;}
	
	public void setCheckOut(Date checkOut) {this.checkOut = checkOut;}
	
	public int getNoOfAdult() {return noOfAdult;}
	
	public void setNoOfAdult(int noOfAdult) {this.noOfAdult = noOfAdult;}
	
	public int getNoOfChildren() {return noOfChildren;}
	
	public void setNoOfChildren(int noOfChildren) {this.noOfChildren = noOfChildren;}
	
	public String getStatus() {return status;}
	
	private void setStatus(String status) {this.status = status;}
	
	public Room getRoom() {return room;}
	
	public void setRoom(Room room) {this.room = room;}
	
}
