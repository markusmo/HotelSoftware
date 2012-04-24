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
import java.util.*;
import javax.swing.JPanel;

/**
 *
 * @author Johannes
 */
public class CheckInGuiControler
{
    private JPanel contentpane;

    int getCounter()
    {
        return CheckInController.getInstance().getCounter();
    }

    private static class CheckInGuiControllerHolder
    {
        private static final CheckInGuiControler INSTANCE = new CheckInGuiControler();
    }
    private ReservationData selectedReservation;

    public static CheckInGuiControler getInstance()
    {
        return CheckInGuiControllerHolder.INSTANCE;
    }

    public Set<ReservationData> searchReservations(String fname, String lname, String reservationNumber) throws InvalidInputException
    {
        Set<ReservationData> res = new LinkedHashSet<ReservationData>();
        try
        {
            CheckInController cic = CheckInController.getInstance();
            // cic.start();
            if (reservationNumber.length() > 0)
            {
                ReservationData reservation = cic.search(Integer.parseInt(reservationNumber));

                if (reservation != null)
                {
                    res.add(reservation);
                    //return dafuq;
                }
            }
            res.addAll(CheckInController.getInstance().searchApprox(fname, lname));
            //dafuq.addAll(CheckInController.getInstance().search(fname, lname));

            return res;
        }
        catch (NumberFormatException ex)
        {
            throw new InvalidInputException(reservationNumber);
        }
    }

    public Set<ReservationData> getAllReservations()
    {
        return CheckInController.getInstance().getAllReservations();
    }

    public ReservationData getSelectedReservation()
    {
        return selectedReservation;
    }

    public Set<CategoryData> getCategories()
    {
        return CheckInController.getInstance().getAllCategories();
    }

    public Set<RoomData> getFreeRoomsInCategory(int index, CategoryData c)
    {
        return CheckInController.getInstance().changeRoomCategory(index, c);
    }

    public GuestData getGuest(String fname, String lname, String street, String city, String zip, String email, String phone, String fax, String country)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public Set<ExtraServiceData> getExtraservices()
    {
        return CheckInController.getInstance().getServices();
    }

    void setSelectedReservation(ReservationData selectedReservation)
    {
        this.selectedReservation = selectedReservation;
        CheckInController.getInstance().workWithReservation(selectedReservation);
    }

    int addRoom()
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void changeRoom(int selectionIndex, String roomNumber)
    {
        CheckInController.getInstance().changeRoom(selectionIndex, roomNumber);
    }

    public Set<RoomData> changeRoomCategory(int selectionIndex, CategoryData category)
    {
        return CheckInController.getInstance().changeRoomCategory(selectionIndex, category);
    }

    public RoomData getRoomData(int roomIndex)
    {
        return CheckInController.getInstance().getRoomData(roomIndex);
    }

    public int addRoomSelection()
    {
        return CheckInController.getInstance().addRoomSelection();
    }

    public Set<ExtraServiceData> getAllHabitationServices()
    {
        return CheckInController.getInstance().getAllHabitationServices();
    }

    public JPanel getContentpane()
    {
        return contentpane;
    }

    public void setContentpane(JPanel contentpane)
    {
        if (this.contentpane == null)
        {
            this.contentpane = contentpane;
        }
    }
}
