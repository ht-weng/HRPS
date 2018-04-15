package HRPSapplication;

import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import HRPSapplication.HotelRooms;
import HRPSapplication.RoomService;

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
	private String status;// Confirmed, Checked-In, Checked-Out, Expired, Cancelled
	private RoomService[] roomServices = new RoomService[50];
	private boolean isEmpty = false;
	
	private static int counter = 1; 
	private static int noOfServices = 0;
	
	public Reservation() {isEmpty = true;}
	
	//isWalkIn is to differentiate the check-ins by walk-in from the ones by reservation
	public Reservation(HotelRooms rooms,GuestMasterList gml, boolean isWalkIn) {
		setReservationID();
		this.status = "Confirmed";
	    System.out.println("=======Enter a new reservation!=====");
	    int choice = -1;
	    
	    System.out.println("Enter a Guest ID: ");
	    int gID = sc.nextInt();
	    System.out.println("Your choice Guest ID is " + gID);
	    if(GuestMasterList.checkValid(gID)) {
	        rooms.printRoomInformation();
	        this.guest = gml.getMasterListElement(gID-1); 
	        
	        System.out.println("");
	        System.out.println("Enter a Room ID(1-48): ");
	        
	        int rID = sc.nextInt();
	        boolean isRecorded = false;
	        //check if room is available
	        do {
	            if(rID < 1 || rID > 48) {
	                System.out.println("Number not in 1-48!");
	                System.out.println("1| Enter 1 to key in another room number.");
	                System.out.println("2| Exit");
	                choice = sc.nextInt();
	                if(choice != 1)
	                	return;  
	                else {
	                    System.out.println("Enter a new room number: ");
	                    rID = sc.nextInt();
	                }          
	            } else if(rID > 0 && rID <= 20) { //reserve a Single Room
	            	if(rooms.getSingleRoom(rID-1).getRoomStatus().equals("occupied")) {
	            		System.out.println("Room is occupied ");
	                    System.out.println("===============");
	                    System.out.println("1| Enter 1 to key in another room number.");
	                    System.out.println("2| Exit");
	                    choice= sc.nextInt();
	                    if (choice != 1)
	                    	return;
	                    else {
	                        System.out.println("Enter a new room number: ");
	                        rID = sc.nextInt();
	                    }
	                } else {
	                	// otherwise, room is available 
	                    room = rooms.getSingleRoom(rID-1);
	                    rooms.getSingleRoom(rID-1).setRoomStatus("reserved");
	                    isRecorded = true;
	                    System.out.println("Room reserved:" +" 01-"+rID);
	                }
	            } else if(rID > 20 && rID <= 40) { //reserve a Double Room
	                if(rooms.getDoubleRoom(rID-21).getRoomStatus().equals("occupied")) {
	                    System.out.println("Room is occupied ");
	                    System.out.println("===============");
	                    System.out.println("1| Enter 1 to key in another room number.");
	                    System.out.println("2| Exit");
	                    choice= sc.nextInt();
	                    if (choice!=1)
	                    	return;
	                    else {
	                        System.out.println("Enter a new room number: ");
	                        rID = sc.nextInt();
	                    }
	                } else {
	                // otherwise, room is vacant
	                	room=rooms.getDoubleRoom(rID-21);
	                    rooms.getDoubleRoom(rID-21).setRoomStatus("reserved");
	                    isRecorded = true;
	                    System.out.println("Room reserved:" +" 02-"+rID);
	                }
	            } else if(rID>40 && rID<=46) {  //reserve a Deluxe Room
	                if(rooms.getDeluxeRoom(rID-41).getRoomStatus().equals("occupied")) {
	                    System.out.println("Room is occupied ");
	                    System.out.println("===============");
	                    System.out.println("1| Enter 1 to key in another room number.");
	                    System.out.println("2| Exit");
	                   choice= sc.nextInt();
	                   if (choice!=1)
	                       return;
	                   else {
	                        System.out.println("Enter a new room number: ");
	                        rID=sc.nextInt();
	                   }
	                } else {
	                    room=rooms.getDeluxeRoom(rID-41);
	                    rooms.getDeluxeRoom(rID-41).setRoomStatus("reserved");
	                    isRecorded = true;
	                    System.out.println("Room reserved:" +" 03-"+rID);
	                }
	            } else if(rID>46 && rID<=48) { //reserve a Presidential Room
	            	if(rooms.getPresidentRoom(rID-47).getRoomStatus().equals("occupied")) {
	                    System.out.println("Room is occupied ");
	                    System.out.println("===============");
	                    System.out.println("1| Enter 1 to key in another room number.");
	                    System.out.println("2| Exit");
	                   choice= sc.nextInt();
	                   if (choice!=1)
	                       return;
	                   else {
	                        System.out.println("Enter a new room number: ");
	                        rID=sc.nextInt();
	                   }
	                } else {
	                // otherwise, room is available!
	                    room=rooms.getPresidentRoom(rID-47);
	                    if (isWalkIn) {
	                    	rooms.getPresidentRoom(rID-47).setRoomStatus("occupied");
	                    	System.out.println("Room checked-in:" +" 04-"+rID);
	                    } else {
	                    	rooms.getPresidentRoom(rID-47).setRoomStatus("reserved");
	                    	System.out.println("Room reserved:" +" 04-"+rID);
	                    }
	                    isRecorded = true;
	                }
	            }
	        } while(!isRecorded);
	     
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
	        
	        if (isWalkIn) {
	            Date now;
	            Calendar cal = Calendar.getInstance();
	            now = cal.getTime();
	        	setCheckIn(now);
	        	setStatus("Checked-In");
	        } else {
	        	enterCheckInDate();
	        }
	        enterCheckOutDate();
	        reservationReceipt();
	    }
	}
	
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
		System.out.println(status);	
	}

	//checks if the reservation has expired, and updates the room status and reservation accordingly
	public void checkExpiry() {	 
		Date now;
	    Calendar cal = Calendar.getInstance();
	    cal.add(Calendar.HOUR_OF_DAY, 1);
	    now = cal.getTime();
	    if(now.after(checkIn) && status.equals("Confirmed")) {
	    	updateStatus("Expired");
			room.setRoomStatus("vacant");
	    }
	}

	public void checkedIn() {
		if (this.room.getRoomStatus().equals("reserved")) {
			Date now;
	        Calendar cal = Calendar.getInstance();
	        now = cal.getTime();
			this.room.setRoomStatus("occupied");
			this.setStatus("Checked-In");
			this.setCheckIn(now);
			System.out.println("Room checked-in successfully!");
		} else if (this.room.getRoomStatus().equals("occupied")) {
			System.out.println("Room not available right now!");
		}else {
			System.out.println("Room not reserved!");
		}
	}
	
	public void checkedOut() {
		if (this.room.getRoomStatus().equals("occupied")) {
			Date now;
	        Calendar cal = Calendar.getInstance();
	        now = cal.getTime();
			this.room.setRoomStatus("vacant");
			this.setStatus("Checked-Out");
			this.setCheckOut(now);
			System.out.println("Room checked-out successfully!");
			//print the bill
			this.reservationReceipt();
			System.out.println("-----------------------------------");
			System.out.println("            bill invoice           ");
			System.out.println("-----------------------------------");
			int days = this.daysBetween(checkIn, checkOut);
			System.out.println("Duration of stay: " + days);
			int rmFees = days*this.getRoom().getRoomType().getRate();
			System.out.println("Total room charge: " + rmFees);
			int rmSvcFees = 0;
			for (int i = 0; i < this.getNoOfRoomServices(); i++) {
				rmSvcFees += this.getRoomServices()[i].getPrice();
			}
			System.out.println("Room service fees: " + rmSvcFees);
			System.out.println("-----------------------------------");
			System.out.println("Total bill to pay: " + (rmFees + rmSvcFees));
			System.out.println("-----------------------------------");
			
		} else if (this.room.getRoomStatus().equals("reserved")) {
			System.out.println("Room not checked-in!");
		}else {
			System.out.println("Room not reserved!");
		}
	}
	
	public int daysBetween(Date one, Date two) {
		long difference = (one.getTime() - two.getTime())/8640000;
		return (int)Math.abs(difference);
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
	
	public void updateReservation(HotelRooms rms) {
		boolean isRecorded = false, _isRecorded = false;
		int choice;
		int maxSgRms = rms.getMaxSingleRooms(), maxDbRms = rms.getMaxDoubleRooms(),
				maxDlRms = rms.getMaxDeluxeRooms(), maxPreRms = rms.getMaxPresidentRooms();
		int toSgRms = maxSgRms, toDbRms = maxSgRms + maxDbRms, toDlRms = maxSgRms + maxDbRms + maxDlRms, 
				toPreRms = maxSgRms + maxDbRms + maxDlRms + maxPreRms;
		do {
			System.out.println("Select the attribute to update:");
			System.out.println("1.Guest");
			System.out.println("2.Room type or room number");
			System.out.println("3.Check-In Date");
			System.out.println("4.Check-Out Date");
			System.out.println("5.Status");
			System.out.println("6.Number of Adult");
			System.out.println("7.Number of Children");
			System.out.println("8.Billing Info");
			System.out.println("9.Exit");
			choice = sc.nextInt();
		
			switch(choice) {
			case 1:
				this.guest.updateGuest();
				System.out.println("Guest information updated!");
				isRecorded = true;
				break;
			case 2:
				do {
					System.out.println("The available rooms: ");
					System.out.println("==========================================");
					rms.printRoomInformation();
					System.out.println("==========================================");
					System.out.println("Enter the new roomID: ");
					int rmNo = sc.nextInt();
					if (rmNo > 0 && rmNo <= toSgRms) {
						if (rms.getSingleRoom(rmNo-1).getRoomStatus().equals("vacant")) {
							this.room = rms.getSingleRoom(rmNo-1);
							_isRecorded = true;
						} else {
							System.out.println("The selected room is not available! Please choose another room!");
						}
					} else if (rmNo > toSgRms && rmNo <= toDbRms) {
						if (rms.getSingleRoom(rmNo-toSgRms-1).getRoomStatus().equals("vacant")) {
							this.room = rms.getSingleRoom(rmNo-toSgRms-1);
							_isRecorded = true;
						} else {
							System.out.println("The selected room is not available! Please choose another room!");
						}
					} else if (rmNo > toDbRms && rmNo <= toDlRms) {
						if (rms.getSingleRoom(rmNo-maxDbRms-1).getRoomStatus().equals("vacant")) {
							this.room = rms.getSingleRoom(rmNo-toDbRms-1);
							_isRecorded = true;
						} else {
							System.out.println("The selected room is not available! Please choose another room!");
						}
					} else if (rmNo > toDlRms && rmNo <= toPreRms) {
						if (rms.getSingleRoom(rmNo-toDlRms-1).getRoomStatus().equals("vacant")) {
							this.room = rms.getSingleRoom(rmNo-toDlRms-1);
							_isRecorded = true;
						} else {
							System.out.println("The selected room is not available! Please choose another room!");
						}
					} else {
						System.out.println("Invalid RoomID!");
					}
				} while (!_isRecorded);
				System.out.println("Room information updated!");
				isRecorded = true;
				break;
			case 3:
				enterCheckInDate();
				isRecorded = true;
				System.out.println("Check-in date updated!");
				break;
			case 4:
				enterCheckOutDate();
				isRecorded = true;
				System.out.println("Check-out date updated!");
				break;
			case 5:
				System.out.println("The current status: " + this.getStatus());
				System.out.println("Please enter the new status(Confirmed/Checked-In/Expired/Cancelled)");
				sc.nextLine();
				String stat = sc.nextLine();
				updateStatus(stat);
				isRecorded = true;
				System.out.println("Reservation status updated!");
				break;
			case 6:
				System.out.println("Enter the new number of adult:");
				this.noOfAdult = sc.nextInt();
				isRecorded = true;
				System.out.println("Number of adults updated!");
				break;
			case 7:
				System.out.println("Enter the new number of children:");
				this.noOfChildren = sc.nextInt();
				isRecorded = true;
				System.out.println("Number of children updated!");
				break;
			case 8:
				System.out.println("Please enter the new credit card number for cilent");
				sc.nextLine();
		        this.billingInfo= sc.nextLine();	       
				isRecorded = true;
				System.out.println("Credit card information updated!");
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
	
	//search reservation by guest name keywords
  	public Reservation searchReservationByName(Reservation[] resList, String keyword, int noOfRes) {
  		Reservation nullResult = new Reservation();
  		Reservation[] resultList = new Reservation[100];
  		int count = -1;
  		
  		for (int i = 0; i < noOfRes; i++) {
  			if (resList[i].getGuest().getName().contains(keyword) && resList[i].getStatus().equals("Confirmed")) {
  				count++;
  				resultList[count] = resList[i];
  			}
  		}
  		
  		if (count == 0) {
  			System.out.println("Target reservation found!");
  			System.out.println("");
  			resultList[0].reservationReceipt();
  			return resultList[0];
  		} else if (count == -1){
  			System.out.println("Target reservation does not exist!");
  			System.out.println("");
  			return nullResult;
  		} else {
  			int rID;
  			System.out.println((count+1) + " reservations found: ");
  			for (int i = 0; i <= count; i++) {
  				System.out.println((i+1) + ": ");
  				resultList[i].reservationReceipt();
  			}
  			System.out.println("Please enter the reservation ID: ");
  			rID = sc.nextInt();
  			
  			for (int i = 0; i <= count; i++) {
  				if (resultList[i].getReservationID() == rID) {
  					System.out.println("Target reservation found!");
  					System.out.println("");
  					return resultList[i];
  				}
  			}
  			
  			System.out.println("Invalid Reservation ID! Reservation does not exist!");
  			System.out.println("");
  			return nullResult;
  		}
  	}
  	
  	public static Reservation searchReservationByRoomNo(Reservation[] resList, String rmNo, int noOfRes) {
  		Reservation nullResult = new Reservation();
  		
  		for (int i = 0; i < noOfRes; i++) {
  			if (resList[i].getRoom().getRoomNumber().contains(rmNo)) {
  				return resList[i];
  			}
  		}	
  		System.out.println("Invalid room numner!");
  		return nullResult;
  	}
	
	//search reservation by reservation ID
	public static int searchReservation(Reservation[] list, int resID, int length) {
    	int result = -1;
    	for (int i = 0; i < length; i++) {
    		if (list[i].getReservationID() == resID)
    			result = i;   		
    	}
    	return result;
    }
	
	public int checkReservation(Reservation[] List, Date date, int roomId){ 
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
	
	public void updateStatus(String stat) {
		if(stat.equals("Confirmed") || stat.equals("Checked-In") || stat.equals("Checked-Out") || stat.equals("Expired") || stat.equals("Cancelled"))
			setStatus(stat);
		else
			System.out.println("Invalid Status");
	}
	
	public void enterRoomService(RoomService rmSvc) {
		noOfServices++;
		roomServices[noOfServices-1] = rmSvc;
		System.out.println("Room service reserved!");
	}

	//below is the getter and setter method 
	public void setReservationID() {
		reservationID=counter;
		counter++;
	}
	
	public boolean getIsEmpty() {return isEmpty;}
	
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
	
	public int getNoOfRoomServices() {return noOfServices;}
	
	public RoomService[] getRoomServices() {return roomServices;}
	
}
