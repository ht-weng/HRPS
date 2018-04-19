package HRPSapplication;

import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import HRPSapplication.HotelRooms;
import HRPSapplication.RoomService;

/**
 * The class managing reservations
 */
public class Reservation {
	Scanner sc = new Scanner(System.in);
    
	/**
	 * Reservation ID can identify a reservation
	 */
	private int reservationID;
	/**
	 * Guest in the reservation
	 */
	private Guest guest;
	/**
	 * Room in the reservation
	 */
	private Room room;
	/**
	 * Credit card number 
	 */
	private String billingInfo;
	/**
	 * Check-in date
	 */
	private Date checkIn;
	/**
	 * Check-out date
	 */
	private Date checkOut;
	private int noOfAdult;
	private int noOfChildren;
	/**
	 * Reservation status can be Confirmed/Checked-In/Checked-Out/Expired/Cancelled
	 */
	private String status;
	/**
	 * Array of room services in the reservation
	 */
	private RoomService[] roomServices = new RoomService[50];
	/**
	 * To check if the reservation is empty(null)
	 */
	private boolean isEmpty = false;
	
	/**
	 * Count the number of reservations
	 */
	private static int counter = 1; 
	/**
	 * Count the number of room services
	 */
	private static int noOfServices = 0;
	/**
	 * Total number of single rooms
	 */
	private static final int MAX_SINGLE_RMS = HotelRooms.MAX_SINGLE_ROOMS;
	/**
	 * Number of single rooms
	 */
	private static final int BY_SINGLE_RMS = MAX_SINGLE_RMS;
	/**
	 * Number of single and double rooms
	 */
	private static final int BY_DOUBLE_RMS = HotelRooms.BY_DELUXE_ROOMS;
	/**
	 * Number of single, double rooms and deluxe rooms
	 */
	private static final int BY_DELUXE_RMS = HotelRooms.BY_DELUXE_ROOMS; 
	/**
	 * Total number of rooms
	 */
	private static final int BY_PRESIDENT_RMS = HotelRooms.BY_PRESIDENT_ROOMS;
	
	/**
	 * Constructs a null reservation
	 */
	public Reservation() {isEmpty = true;}
	
	/**
	 * Construct a valid reservation P.S.isWalkIn is to differentiate the check-ins by walk-in from the ones by reservation
	 * @param rooms Array of rooms
	 * @param gml Guest master list
	 * @param isWalkIn Indicate if the reservation is Walk-In or By Reservation
	 * @param reservations Array of reservations
	 * @param noOfReservations Number of reservations
	 */
	public Reservation(HotelRooms rooms,GuestMasterList gml, boolean isWalkIn, Reservation[] reservations, int noOfReservations) {
		setReservationID();
		if(isWalkIn) {
			this.status = "Checked-In";
		} else {
			this.status = "Confirmed";
		}
	    System.out.println("Enter a new reservation");
	    System.out.println("===========================================");
	    int choice = -1;
	    
	    //select guest from guest list
	    System.out.println("Enter a Guest ID: ");
	    int gID = sc.nextInt();
	    System.out.println("Your choice of Guest ID is " + gID);
	    if(GuestMasterList.checkValid(gID)) {
	    	System.out.println("==========================================");
	        rooms.printRoomStatusSummary();
	        System.out.println("==========================================");
	        this.guest = gml.getMasterListElement(gID-1); 
	        System.out.println("Guest name: " + this.guest.getName());
	        
	        //record check in and check out date
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
	        
	        //select room by room ID
	        System.out.println("");
	        System.out.println("Enter a Room ID(1-" + BY_PRESIDENT_RMS + "): ");
	        int rID = sc.nextInt();
	        String rmStatus = rooms.getRoomByRoomID(rID).getRoomStatus();
	        
	        //check if room is available
	        boolean isRecorded = false;
	        do {
	        	//check if room ID is in the range
	            if(rID < 1 || rID > BY_PRESIDENT_RMS) {
	                System.out.println("Invalid room ID!");
	                System.out.println("1.Enter another room ID");
	                System.out.println("2.Exit");
	                choice = sc.nextInt();
	                if(choice == 1) {
	                	System.out.println("Enter a new room ID: ");
                    	rID = sc.nextInt();
	                } else {
	                	return;
	                }
	            } else if(rID > 0 && rID <= BY_SINGLE_RMS) { 
	            	//reserve a Single Room
	            	if (rmStatus.equals("vacant") || rmStatus.equals("maintenance")) {//available
	            		room = rooms.getSingleRoom(rID-1);
	                    if (isWalkIn) {
	                    	rooms.getSingleRoom(rID-1).setRoomStatus("occupied");
	                    	System.out.println("Single room " + room.getRoomNumber() + " checked-in!");
	                    } else {
	                    	rooms.getSingleRoom(rID-1).setRoomStatus("reserved");
	                    	System.out.println("Single room " + room.getRoomNumber() + " reserved!");
	                    }
	                    isRecorded = true;
	            	} else if((rmStatus.equals("reserved") || rmStatus.equals("occupied")) && 
	            			checkReservation(reservations, this.checkIn, this.checkOut, rID, noOfReservations)) {//check if room is available
	            		System.out.println("Room is unavailable!");
	                    System.out.println("=========================================");
	                    System.out.println("1.Enter another room ID");
		                System.out.println("2.Exit");
	                    choice= sc.nextInt();
	                    if(choice == 1) {
		                	System.out.println("Enter a new room ID: ");
	                    	rID = sc.nextInt();
		                } else {
		                	return;
		                }
	                } else {//available
	                    room = rooms.getSingleRoom(rID-1);
	                    if (isWalkIn) {
	                    	rooms.getSingleRoom(rID-1).setRoomStatus("occupied");
	                    	System.out.println("Single room " + room.getRoomNumber() + " checked-in!");
	                    } else {
	                    	rooms.getSingleRoom(rID-1).setRoomStatus("reserved");
	                    	System.out.println("Single room " + room.getRoomNumber() + " reserved!");
	                    }
	                    isRecorded = true;
	                }
	            } else if(rID > BY_SINGLE_RMS && rID <= BY_DOUBLE_RMS) { 
	            	//reserve a Double Room
	            	if (rmStatus.equals("vacant") || rmStatus.equals("maintenance")) {//available
	            		room=rooms.getDoubleRoom(rID-BY_SINGLE_RMS-1);
	                	if (isWalkIn) {
	                    	rooms.getDoubleRoom(rID-BY_SINGLE_RMS-1).setRoomStatus("occupied");
	                    	System.out.println("Double room " + room.getRoomNumber() + " checked-in!");
	                    } else {
	                    	rooms.getDoubleRoom(rID-BY_SINGLE_RMS-1).setRoomStatus("reserved");
	                    	System.out.println("Double room " + room.getRoomNumber() + " reserved!");
	                    }
	                    isRecorded = true;
	            	}else if((rmStatus.equals("reserved") || rmStatus.equals("occupied")) && 
	            			checkReservation(reservations, this.checkIn, this.checkOut, rID, noOfReservations)) {//check if room is available
	            		System.out.println("Room is unavailable!");
	            		System.out.println("=========================================");
	                    System.out.println("1.Enter another room ID");
		                System.out.println("2.Exit");
	                    choice= sc.nextInt();
	                    if(choice == 1) {
		                	System.out.println("Enter a new room ID: ");
	                    	rID = sc.nextInt();
		                } else {
		                	return;
		                }
	                } else {//available
	                	room=rooms.getDoubleRoom(rID-BY_SINGLE_RMS-1);
	                	if (isWalkIn) {
	                    	rooms.getDoubleRoom(rID-BY_SINGLE_RMS-1).setRoomStatus("occupied");
	                    	System.out.println("Double room " + room.getRoomNumber() + " checked-in!");
	                    } else {
	                    	rooms.getDoubleRoom(rID-BY_SINGLE_RMS-1).setRoomStatus("reserved");
	                    	System.out.println("Double room " + room.getRoomNumber() + " reserved!");
	                    }
	                    isRecorded = true;
	                }
	            } else if(rID>BY_DOUBLE_RMS && rID<=BY_DELUXE_RMS) {  
	            	//reserve a Deluxe Room
	            	if (rmStatus.equals("vacant") || rmStatus.equals("maintenance")) {//available
	            		 room=rooms.getDeluxeRoom(rID-BY_DOUBLE_RMS-1);
		                    if (isWalkIn) {
		                    	rooms.getDeluxeRoom(rID-BY_DOUBLE_RMS-1).setRoomStatus("occupied");
		                    	System.out.println("Deluxe room " + room.getRoomNumber() + " checked-in!");
		                    } else {
		                    	rooms.getDeluxeRoom(rID-BY_DOUBLE_RMS-1).setRoomStatus("reserved");
		                    	System.out.println("Deluxe room " + room.getRoomNumber() + " reserved!");
		                    }
		                    isRecorded = true;
	            	} else if((rmStatus.equals("reserved") || rmStatus.equals("occupied")) && 
	            			checkReservation(reservations, this.checkIn, this.checkOut, rID, noOfReservations)) {//check if room has a reservation
	            		System.out.println("Room is unavailable!");
	            		System.out.println("=========================================");
	                    System.out.println("1.Enter another room ID");
		                System.out.println("2.Exit");
	                    choice= sc.nextInt();
	                    if(choice == 1) {
		                	System.out.println("Enter a new room ID: ");
	                    	rID = sc.nextInt();
		                } else {
		                	return;
		                }
	                } else {//available
	                    room=rooms.getDeluxeRoom(rID-BY_DOUBLE_RMS-1);
	                    if (isWalkIn) {
	                    	rooms.getDeluxeRoom(rID-BY_DOUBLE_RMS-1).setRoomStatus("occupied");
	                    	System.out.println("Deluxe room " + room.getRoomNumber() + " checked-in!");
	                    } else {
	                    	rooms.getDeluxeRoom(rID-BY_DOUBLE_RMS-1).setRoomStatus("reserved");
	                    	System.out.println("Deluxe room " + room.getRoomNumber() + " reserved!");
	                    }
	                    isRecorded = true;
	                }
	            } else if(rID>BY_DELUXE_RMS && rID<=BY_PRESIDENT_RMS) {
	            	//reserve a Presidential Room
	            	if (rmStatus.equals("vacant") || rmStatus.equals("maintenance")) {//available
	            		room=rooms.getPresidentRoom(rID-BY_DELUXE_RMS-1);
	                    if (isWalkIn) {
	                    	rooms.getPresidentRoom(rID-BY_DELUXE_RMS-1).setRoomStatus("occupied");
	                    	System.out.println("President room " + room.getRoomNumber() + " checked-in!");
	                    } else {
	                    	rooms.getPresidentRoom(rID-BY_DELUXE_RMS-1).setRoomStatus("reserved");
	                    	System.out.println("President room " + room.getRoomNumber() + " reserved!");
	                    }
	                    isRecorded = true;
	            	}else if((rmStatus.equals("reserved") || rmStatus.equals("occupied")) && 
	            			checkReservation(reservations, this.checkIn, this.checkOut, rID, noOfReservations)) {//check if room is available
	            		System.out.println("Room is unavailable!");
	            		System.out.println("=========================================");
	                    System.out.println("1.Enter another room ID");
		                System.out.println("2.Exit");
	                    choice= sc.nextInt();
	                    if(choice == 1) {
		                	System.out.println("Enter a new room ID: ");
	                    	rID = sc.nextInt();
		                } else {
		                	return;
		                }
	                } else {//available
	                    room=rooms.getPresidentRoom(rID-BY_DELUXE_RMS-1);
	                    if (isWalkIn) {
	                    	rooms.getPresidentRoom(rID-BY_DELUXE_RMS-1).setRoomStatus("occupied");
	                    	System.out.println("President room " + room.getRoomNumber() + " checked-in!");
	                    } else {
	                    	rooms.getPresidentRoom(rID-BY_DELUXE_RMS-1).setRoomStatus("reserved");
	                    	System.out.println("President room " + room.getRoomNumber() + " reserved!");
	                    }
	                    isRecorded = true;
	                }
	            }  
	        } while(!isRecorded);
	        
	        //record number of guests in a room
	        System.out.println("Enter the number of adults(up to 2)");
	        this.noOfAdult=sc.nextInt();
	        System.out.println("Enter the number of children(up to 2)");
	        this.noOfChildren=sc.nextInt();
	        
	        //confirm credit card number
	        System.out.println("Please enter billing info for cilent");
	        System.out.println("1.Use original credit number"); 
	        System.out.println("2.Enter new credit card number");
	        int choice1= sc.nextInt();
	        if(choice1==1) { 
	        	sc.nextLine();
	        	this.billingInfo= guest.getCredit();
	        } else {
	            System.out.println("Enter the alternate credit card number ");
	        	sc.nextLine();
	        	this.billingInfo= sc.nextLine();
	        }
	        
	        //record bed size
	        isRecorded = false;
	        do {
	        	String bed;
	        	if (this.getRoom().getRoomType().getRoomType().equals("single")) {
	        		System.out.println("Enter the bed size(queen/king): ");
	        		bed = sc.nextLine();
	        		if (bed.equals("queen") || bed.equals("king")) {
	        			this.getRoom().setBedSize(bed);
	        			isRecorded = true;
	        		} else {
	        			System.out.println("Invalid bed size!");
	        		}
	        	} else if (this.getRoom().getRoomType().getRoomType().equals("double")) {
	        		System.out.println("Double room's bed size is double and cannot be changed!");
	        		isRecorded = true;
	        	} else if (this.getRoom().getRoomType().getRoomType().equals("deluxe") ||
	        			this.getRoom().getRoomType().getRoomType().equals("president")) {
	        		System.out.println("Enter the bed size(double/queen/king): ");
	        		bed = sc.nextLine();
	        		if (bed.equals("double") || bed.equals("queen") || bed.equals("king")) {
	        			this.getRoom().setBedSize(bed);
	        			isRecorded = true;
	        		} else {
	        			System.out.println("Invalid bed size!");
	        		}
	        	} else {
	        		System.out.println("Invalid room type!");
	        		isRecorded = true;
	        	}
	        } while (!isRecorded);
	        
	        //print receipt
	        reservationReceipt();
	    } else {
	    	//guest not in the guest list
	    	System.out.println("Guest does not exist!");
	    }
	}
	
	/**
	 * Print reservation receipt
	 */
	public void reservationReceipt() {
		System.out.println("           Reservation Receipt");
		System.out.println("===========================================");
		System.out.println("Reservation ID: " + reservationID);
		System.out.println("-------------------------------------------");
		System.out.println("Guest: " + guest.getName());
		System.out.println("-------------------------------------------");
		System.out.println("Room Number: " + room.getRoomNumber());
		System.out.println("Room Type: " + room.getRoomType().getRoomType());
		System.out.println("-------------------------------------------");
		System.out.println("Bed size: " + this.getRoom().getBedSize());
		System.out.println("-------------------------------------------");
		System.out.println("Credit Card Number: " + billingInfo);
		System.out.println("-------------------------------------------");
		System.out.println("Check In Date:" + checkIn);
		System.out.println("Check Out Date: " + checkOut);
		System.out.println("-------------------------------------------");
		System.out.println("Number of Adults: " + noOfAdult);
		System.out.println("Number of Children: " + noOfChildren);
		System.out.println("-------------------------------------------");
		System.out.println("Status: " + status);	
		System.out.println("===========================================");
	}

	/**
	 * Check if the reservation has expired, and updates the room status and reservation accordingly
	 */
	public void checkExpiry() {	 
		Date now;
	    Calendar cal = Calendar.getInstance();
	    cal.add(Calendar.HOUR_OF_DAY, -1);
	    now = cal.getTime();
	    if(now.after(checkIn) && status.equals("Confirmed")) {
	    	updateStatus("Expired");
			room.setRoomStatus("vacant");
	    }
	}

	/**
	 * Update the reservation and room status when checked-in
	 */
	public void checkedIn() {
		if (this.room.getRoomStatus().equals("reserved")) {
			Date now;
	        Calendar cal = Calendar.getInstance();
	        now = cal.getTime();
			this.room.setRoomStatus("occupied");
			this.setStatus("Checked-In");
			this.setCheckIn(now);
			System.out.println("Room checked-in successfully!");
		} else if (this.room.getRoomStatus().equals("occupied") || this.room.getRoomStatus().equals("maintenance")) {
			System.out.println("Room not available right now!");
		}else {
			System.out.println("Room not reserved!");
		}
	}
	
	/**
	 * Update the reservation and room status when checked-out
	 */
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
			System.out.println();
			System.out.println("===================================");
			System.out.println("            Bill Invoice           ");
			System.out.println("-----------------------------------");
			int days = this.daysBetween(checkIn, checkOut);
			System.out.println("Duration of stay: " + days);
			System.out.println("-----------------------------------");
			int rmFees = days*this.getRoom().getRoomType().getRate();
			System.out.println("Total room fee: " + rmFees);
			System.out.println("-----------------------------------");
			int rmSvcFees = 0;
			for (int i = 0; i < this.getNoOfRoomServices(); i++) {
				rmSvcFees += this.getRoomServices()[i].getPrice();
			}
			System.out.println("Room service fee: " + rmSvcFees);
			System.out.println("-----------------------------------");
			System.out.println("Total bill to pay: " + (rmFees + rmSvcFees));
			System.out.println("===================================");	
		} else if (this.room.getRoomStatus().equals("reserved")) {
			System.out.println("Room not checked-in!");
		}else {
			System.out.println("Room not reserved!");
		}
	}
	
	/**
	 * Calculate the days between two dates to help generate the room fee
	 * @param one First date
	 * @param two Second date
	 * @return Number of days
	 */
	public int daysBetween(Date one, Date two) {
		long difference = (one.getTime() - two.getTime())/8640000;
		return (int)Math.abs(difference);
	}
	
	/**
	 * Record check-in date
	 */
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
        	//handle the exception
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
	
	/**
	 * Record check-out date
	 */
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
            //handle the exception
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
	
	/**
	 * Update reservation attributes
	 * @param rms Array of rooms
	 * @param reservations Array of reservations
	 * @param noOfReservations Number of reservations
	 */
	public void updateReservation(HotelRooms rms, Reservation[] reservations, int noOfReservations) {
		boolean isRecorded = false, _isRecorded = false;
		int choice;
		String rmStatus = this.getRoom().getRoomStatus();
		
		System.out.println("Current Reservation Info: ");
		reservationReceipt();
		
		do {
			System.out.println("Select the attribute to update:");
			System.out.println("1. Guest info");
			System.out.println("2. Room type or room number");
			System.out.println("3. Check-In Date");
			System.out.println("4. Check-Out Date");
			System.out.println("5. Status");
			System.out.println("6. Number of Adult");
			System.out.println("7. Number of Children");
			System.out.println("8. Billing Info");
			System.out.println("9. Bed size");
			System.out.println("10.Exit");
			choice = sc.nextInt();
		
			switch(choice) {
			case 1://update guest info
				System.out.println("Current guest info");
				this.guest.printGuestDetail();
				this.guest.updateGuest();
				System.out.println("Guest info updated!");
				isRecorded = true;
				break;
			case 2://update room type or room number
				System.out.println("Current room type: " + this.getRoom().getRoomType().getRoomType());
				System.out.println("Current room number: " + this.getRoom().getRoomNumber());
				do {
					System.out.println("==========================================");
					rms.printRoomStatusSummary();
					System.out.println("==========================================");
					System.out.println("Enter the new roomID: ");
					int rmID = sc.nextInt();
					if (rmID > 0 && rmID <= BY_SINGLE_RMS) {
						if ((rmStatus.equals("vacant") || rmStatus.equals("maintenance")) || !checkReservation(reservations, this.checkIn, this.checkOut, rmID, noOfReservations)) {
							this.room = rms.getSingleRoom(rmID-1);
							_isRecorded = true;
						} else {
							System.out.println("The selected room is not available! Please choose another room!");
						}
					} else if (rmID > BY_SINGLE_RMS && rmID <= BY_DOUBLE_RMS) {
						if ((rmStatus.equals("vacant") || rmStatus.equals("maintenance")) || !checkReservation(reservations, this.checkIn, this.checkOut, rmID, noOfReservations)) {
							this.room = rms.getDoubleRoom(rmID-BY_SINGLE_RMS-1);
							_isRecorded = true;
						} else {
							System.out.println("The selected room is not available! Please choose another room!");
						}
					} else if (rmID > BY_DOUBLE_RMS && rmID <= BY_DELUXE_RMS) {
						if ((rmStatus.equals("vacant") || rmStatus.equals("maintenance")) || !checkReservation(reservations, this.checkIn, this.checkOut, rmID, noOfReservations)) {
							this.room = rms.getDeluxeRoom(rmID-BY_DOUBLE_RMS-1);
							_isRecorded = true;
						} else {
							System.out.println("The selected room is not available! Please choose another room!");
						}
					} else if (rmID > BY_DELUXE_RMS && rmID <= BY_PRESIDENT_RMS) {
						if ((rmStatus.equals("vacant") || rmStatus.equals("maintenance")) || !checkReservation(reservations, this.checkIn, this.checkOut, rmID, noOfReservations)) {
							this.room = rms.getPresidentRoom(rmID-BY_DELUXE_RMS-1);
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
			case 3://update check-in date
				System.out.println("Current check-in date: " + this.getCheckIn());
				Date temp = this.checkIn;
				enterCheckInDate();
				if (!checkReservation(reservations, this.checkIn, this.checkOut, this.getRoom().getRoomID(), noOfReservations)) {
					isRecorded = true;
					System.out.println("Check-in date updated!");
				} else {
					System.out.println("Room is not available in the new time range!");
					this.checkIn = temp;
				}
				break;
			case 4://update check-out date
				System.out.println("Current check-out date: " + this.getCheckOut());
				temp = this.checkOut;
				enterCheckOutDate();
				if (!checkReservation(reservations, this.checkIn, this.checkOut, this.getRoom().getRoomID(), noOfReservations)) {
					isRecorded = true;
					System.out.println("Check-out date updated!");
				} else {
					System.out.println("Room is not available in the new time range!");
					this.checkOut = temp;
				}
				break;
			case 5://update status
				System.out.println("Current status: " + this.getStatus());
				if (this.getStatus().equals("Confirmed")) {
					System.out.println("The current status: " + this.getStatus());
					System.out.println("Please enter the new status(Cancelled)");
					sc.nextLine();
					String stat = sc.nextLine();
					if (stat.equals("Cancelled")) {
						updateStatus(stat);
						isRecorded = true;
					} else {
						System.out.println("This reservation can only be updated to 'Cancelled'");
					}
					System.out.println("Reservation status updated!");
				} else {
					System.out.println("Status Checked-In/Checked-Out/Expired/Cancelled cannot be updated by this function!");
				}
				break;
			case 6://update number of adult
				System.out.println("Current number of adults: " + this.getNoOfAdult());
				this.noOfAdult = sc.nextInt();
				isRecorded = true;
				System.out.println("Number of adults updated!");
				break;
			case 7://update number of children
				System.out.println("Current number of children: " + this.getNoOfChildren());
				System.out.println("Enter the new number of children(up to 2):");
				this.noOfChildren = sc.nextInt();
				isRecorded = true;
				System.out.println("Number of children updated!");
				break;
			case 8://update credit card info
				System.out.println("Current credit card number: " + this.getBillingInfo());
				System.out.println("Please enter the new credit card number for the cilent");
				sc.nextLine();
		        this.billingInfo= sc.nextLine();	       
				isRecorded = true;
				System.out.println("Credit card number updated!");
				break;
			case 9://update bed size
		        isRecorded = false;
		        System.out.println("Current bed size: " + this.getRoom().getBedSize());
		        do {
		        	String bed;
		        	if (this.getRoom().getRoomType().getRoomType().equals("single")) {
		        		System.out.println("Enter the new bed size(queen/king): ");
		        		sc.nextLine();
		        		bed = sc.nextLine();
		        		if (bed.equals("queen") || bed.equals("king")) {
		        			this.getRoom().setBedSize(bed);
		        			isRecorded = true;
		        		} else {
		        			System.out.println("Invalid bed size!");
		        		}
		        	} else if (this.getRoom().getRoomType().getRoomType().equals("double")) {
		        		System.out.println("Double room bed size is double, which cannot be changed!");
		        		isRecorded = true;
		        	} else if (this.getRoom().getRoomType().getRoomType().equals("deluxe") ||
		        			this.getRoom().getRoomType().getRoomType().equals("president")) {
		        		System.out.println("Enter the new bed size(double/queen/king): ");
		        		sc.nextLine();
		        		bed = sc.nextLine();
		        		if (bed.equals("double") || bed.equals("queen") || bed.equals("king")) {
		        			this.getRoom().setBedSize(bed);
		        			isRecorded = true;
		        		} else {
		        			System.out.println("Invalid bed size!");
		        		}
		        	} else {
		        		System.out.println("Invalid room type!");
		        		isRecorded = true;
		        	}
		        } while (!isRecorded);
			case 10://exit
				System.out.println("Exiting...");
				isRecorded = true;
				break;
			default:
				System.out.println("Invalid Option!");
				break;		
			}
		} while (!isRecorded);	
		//print new reservation info
		System.out.println("New reservation info: ");
		reservationReceipt();
	}
	
	/**
	 * Search and return confirmed reservation by guest name keywords
	 * @param resList Array of reservations
	 * @param keyword Guest name keywords
	 * @param noOfRes Number of reservations
	 * @return Reservation
	 */
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
  	
  	/**
  	 * Search and return reservation by room number
  	 * @param resList Array of reservations
  	 * @param rmNo Room number
  	 * @param noOfRes Number of reservations
  	 * @return Reservation
  	 */
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
	
	/**
	 * Search and return index of reservation in the array of reservations by reservation ID
	 * @param list Array of reservations
	 * @param resID Reservation ID
	 * @param length Number of reservations
	 * @return Index of reservation
	 */
	public static int searchReservation(Reservation[] list, int resID, int length) {
    	int result = -1;
    	for (int i = 0; i < length; i++) {
    		if (list[i].getReservationID() == resID)
    			result = i;   		
    	}
    	return result;
    }
	
	/**
	 * Check if there's any reservation between the check in and check out date, returns false if there isn't and true if there is.
	 * @param List Array of reservations
	 * @param checkIn Check-in date
	 * @param checkOut Check-out date
	 * @param roomId Room ID
	 * @param noOfReservations Number of reservations
	 * @return Boolean result
	 */
	public static boolean checkReservation(Reservation[] List, Date checkIn, Date checkOut, int roomId, int noOfReservations){ 
		boolean check =false;
		for(int i=0;i<noOfReservations;i++)
		{
			int rID;
			Date cIn,cOut;
			rID = List[i].getRoom().getRoomID();
			cIn = List[i].getCheckIn();
			cOut = List[i].getCheckOut();
			String _status = List[i].getStatus();
			if(rID == roomId)
			{
				//if the room is reserved between the check-in and check-out date, the room is unavailable
				if(((checkIn.after(cIn) && checkIn.before(cOut)) || (checkOut.after(cIn) && checkOut.before(cOut)) ||
						(checkIn.before(cIn) && checkOut.after(cOut))) && (_status.equals("Confirmed") || _status.equals("Checked-In")))
				{
					check =true;
				}
			}
		}
		return check;
	}
	
	/**
	 * Update reservation status
	 * @param stat New status
	 */
	public void updateStatus(String stat) {
		if(stat.equals("Confirmed") || stat.equals("Checked-In") || stat.equals("Checked-Out") || stat.equals("Expired") || stat.equals("Cancelled"))
			setStatus(stat);
		else
			System.out.println("Invalid Status");
	}
	
	/**
	 * Reserve room service
	 * @param rmSvc Room service
	 */
	public void enterRoomService(RoomService rmSvc) {
		noOfServices++;
		roomServices[noOfServices-1] = rmSvc;
		System.out.println("Room service reserved!");
	}

	//getter and setter methods
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
	
	private void setStatus(String stat) {this.status = stat;}
	
	public Room getRoom() {return room;}
	
	public void setRoom(Room room) {this.room = room;}
	
	public int getNoOfRoomServices() {return noOfServices;}
	
	public RoomService[] getRoomServices() {return roomServices;}
	
}
