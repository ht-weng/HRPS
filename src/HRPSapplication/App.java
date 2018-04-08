package HRPSapplication;

import HRPSapplication.GuestMasterList;
import HRPSapplication.HotelRooms;
import HRPSapplication.Reservation;

public class App {
    public static void main(String args[])
    {
         
       /* ===========To test functionality of Guest Class=================================
        Guest test= new Guest();
        Guest test2= new Guest();
        
        test.printGuestDetail();
        test2.printGuestDetail();
        
        //test.updateGuest();//change to female
        //test.printGuestDetail();
        
        //test2.updateGuest();//change to thai nationality and thai conutry of birth
        //test2.printGuestDetail();
       */
        
       //===========To test functionality of GuestList class=========================
        GuestMasterList go= new GuestMasterList();
        go.newGuest(); //1st guest
        go.newGuest(); //2nd guest
       
       /*-----------Print info of Guest in the Guest List class----------------------
        go.getMasterList1(0).printGuestDetail(); //1st guest information
        go.getMasterList1(1).printGuestDetail(); //2nd guest information  
       */
        
        // ========= To test functionality of HotelRooms/Reservation Class====================
        
       // Room number is in this format AA-BB, where AA is the type of room and BB is the roomID.
       HotelRooms po= new HotelRooms();
       
       Reservation res1= new Reservation (po, go);
       Reservation res2= new Reservation (po, go);
       po.printRoomInformation();
      
        
    }
}

