/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.gui.checkin;

import hotelsoftware.checkin.CheckInController;
import hotelsoftware.model.domain.parties.GuestData;
import hotelsoftware.model.domain.reservation.ReservationData;
import hotelsoftware.model.domain.room.CategoryData;
import hotelsoftware.model.domain.room.RoomData;
import hotelsoftware.model.domain.service.ExtraServiceData;
import hotelsoftware.model.domain.room.Room;
import java.util.Collection;
import java.util.LinkedList;

/**
 *
 * @author Johannes
 */
public class CheckInGuiControler
{

    private static class CheckInGuiControllerHolder
    {
        private static final CheckInGuiControler INSTANCE = new CheckInGuiControler();
    }
    private ReservationData selectedReservation;

    public static CheckInGuiControler getInstance()
    {
        return CheckInGuiControllerHolder.INSTANCE;
    }

    public Collection<ReservationData> searchReservations(String fname, String lname, String reservationNumber) throws InvalidInputException
    {
        Collection<ReservationData> dafuq = new LinkedList<ReservationData>();
        try
        {
            CheckInController cic = CheckInController.getInstance();
            // cic.start();
            if (reservationNumber.length() > 0)
            {
                ReservationData reservation = cic.search(Integer.parseInt(reservationNumber));

                if (reservation != null)
                {
                    dafuq.add(reservation);
                    //return dafuq;
                }
            }
            dafuq.addAll(CheckInController.getInstance().search(fname, lname));

            return dafuq;
        }
        catch (NumberFormatException ex)
        {
            throw new InvalidInputException(reservationNumber);
        }
    }

    public Collection<ReservationData> getAllReservations()
    {
        return CheckInController.getInstance().getAllReservations();
    }

    public ReservationData getSelectedReservation()
    {
        return selectedReservation;
    }

    public Collection<CategoryData> getCategories()
    {
        return CheckInController.getInstance().getAllCategories();
    }

    public Collection<RoomData> getFreeRoomsInCategory(int index, CategoryData c)
    {
        return CheckInController.getInstance().changeRoomCategory(index, c);
    }

    public GuestData getGuest(String fname, String lname, String street, String city, String zip, String email, String phone, String fax, String country)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public Collection<ExtraServiceData> getExtraservices()
    {
        return CheckInController.getInstance().getServices();
    }

    void setSelectedReservation(ReservationData selectedReservation)
    {
        this.selectedReservation = selectedReservation;
    }

    int addRoom()
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void changeRoom(int selectionIndex, String roomNumber)
    {
        CheckInController.getInstance().changeRoom(selectionIndex, roomNumber);
    }
    
    public Collection<RoomData> changeRoomCategory(int selectionIndex, CategoryData category)
    {
        return CheckInController.getInstance().changeRoomCategory(selectionIndex, category);
    }

    public RoomData getRoomData(int roomIndex)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
