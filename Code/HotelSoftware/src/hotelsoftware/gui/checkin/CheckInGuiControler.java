/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.gui.checkin;

import hotelsoftware.checkin.CheckInController;
import hotelsoftware.model.domain.parties.Guest;
import hotelsoftware.model.domain.reservation.Reservation;
import hotelsoftware.model.domain.room.Category;
import hotelsoftware.model.domain.room.Room;
import hotelsoftware.model.domain.service.ExtraService;
import java.util.Collection;

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

    public Collection<Reservation> searchReservations(String fname, String lname, String reservationNumber)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public Collection<Reservation> getAllReservations()
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public Reservation getSelectedReservation(int index)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public Collection<Category> getCategories()
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public Collection<Room> getFreeRoomsInCategory(Category c)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public Guest getGuest(String fname, String lname, String street, String city, String zip, String email, String phone, String fax, String country)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public Collection<ExtraService> getExtraservices()
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }
    
}
