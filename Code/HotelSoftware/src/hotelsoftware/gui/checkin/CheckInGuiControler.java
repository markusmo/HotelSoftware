/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.gui.checkin;

import hotelsoftware.checkin.CheckInController;
import hotelsoftware.model.domain.parties.GuestData;
import hotelsoftware.model.domain.reservation.ReservationData;
import hotelsoftware.model.domain.room.data.RoomCategoryData;
import hotelsoftware.model.domain.room.data.RoomData;
import hotelsoftware.model.domain.service.data.ExtraServiceData;
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

    public Collection<ReservationData> searchReservations(String fname, String lname, String reservationNumber) throws InvalidInputException
    {
        Collection<ReservationData> res = new LinkedHashSet<ReservationData>();
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

    public Collection<ReservationData> getAllReservations()
    {
        return CheckInController.getInstance().getAllReservations();
    }

    public ReservationData getSelectedReservation()
    {
        return selectedReservation;
    }

    public Collection<RoomCategoryData> getCategories()
    {
        return CheckInController.getInstance().getAllCategories();
    }

    public Collection<RoomData> getFreeRoomsInCategory(int index, RoomCategoryData c)
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

    public Collection<RoomData> changeRoomCategory(int selectionIndex, RoomCategoryData category)
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

    public List<ExtraServiceData> getAllHabitationServices()
    {
        return (List<ExtraServiceData>) CheckInController.getInstance().getAllHabitationServices();
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
