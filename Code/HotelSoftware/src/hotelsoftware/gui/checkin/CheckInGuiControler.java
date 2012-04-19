/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.gui.checkin;

import hotelsoftware.checkin.CheckInController;
<<<<<<< HEAD
import hotelsoftware.model.domain.parties.GuestData;
import hotelsoftware.model.domain.reservation.ReservationData;
import hotelsoftware.model.domain.room.Category;
import hotelsoftware.model.domain.room.CategoryData;
import hotelsoftware.model.domain.room.RoomData;
import hotelsoftware.model.domain.service.ExtraServiceData;
import java.text.ParseException;
=======
import hotelsoftware.model.domain.parties.Guest;
import hotelsoftware.model.domain.reservation.Reservation;
import hotelsoftware.model.domain.room.RoomCategory;
import hotelsoftware.model.domain.room.Room;
import hotelsoftware.model.domain.service.ExtraService;
>>>>>>> f691635ff1877864b8b81a3e5b46bc9ead6ff6e6
import java.util.Collection;
import java.util.LinkedList;

/**
 *
 * @author Johannes
 */
public class CheckInGuiControler
{
    private CheckInController cic = CheckInController.getInstance();
    private static CheckInGuiControler controller = null;

    public static CheckInGuiControler getInstance()
    {
        if (controller == null)
        {
            controller = new CheckInGuiControler();
        }

        return controller;
    }

    public Collection<ReservationData> searchReservations(String fname, String lname, String reservationNumber) throws InvalidInputException
    {
        Collection<ReservationData> dafuq = new LinkedList<ReservationData>();
        try
        {
            ReservationData reservation = cic.search(Integer.parseInt(reservationNumber));

            if (reservation != null)
            {
                dafuq.add(reservation);
            }
            else
            {
                dafuq.addAll(cic.search(fname, lname));
            }
            
            return dafuq;
        }
        catch (NumberFormatException ex)
        {
            throw new InvalidInputException(reservationNumber);
        }
    }

    public Collection<ReservationData> getAllReservations()
    {
        return cic.getAllReservations();
    }

    public ReservationData getSelectedReservation(int index)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

<<<<<<< HEAD
    public Collection<CategoryData> getCategories()
=======
    public Collection<RoomCategory> getCategories()
>>>>>>> f691635ff1877864b8b81a3e5b46bc9ead6ff6e6
    {
        return cic.getAllCategories();
    }

<<<<<<< HEAD
    public Collection<RoomData> getFreeRoomsInCategory(int index, CategoryData c)
=======
    public Collection<Room> getFreeRoomsInCategory(RoomCategory c)
>>>>>>> f691635ff1877864b8b81a3e5b46bc9ead6ff6e6
    {
        return cic.changeRoomCategory(index, c);
    }

    public GuestData getGuest(String fname, String lname, String street, String city, String zip, String email, String phone, String fax, String country)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public Collection<ExtraServiceData> getExtraservices()
    {
        return cic.getServices();
    }
    
}
