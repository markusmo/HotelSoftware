/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.checkin;

import hotelsoftware.model.DynamicMapper;
import hotelsoftware.model.database.room.DBRoom;
import hotelsoftware.model.database.room.DBRoomCategory;
import hotelsoftware.model.domain.reservation.Reservation;
import hotelsoftware.model.domain.reservation.ReservationData;
import hotelsoftware.model.domain.reservation.ReservationItemData;
import hotelsoftware.model.domain.room.Room;
import hotelsoftware.model.domain.room.RoomCategory;
import hotelsoftware.util.HelperFunctions;
import java.util.*;

/**
 *
 * @author Dunst
 */
public class StartState extends CheckInState
{
    public StartState(CheckInController context)
    {
        super(context);
    }

    @Override
    public Collection<ReservationData> getAllReservations()
    {
        return new HelperFunctions<ReservationData, Reservation>().castCollectionUp(Reservation.getAllReservations());
    }

    /**
     * Sucht nach einer Reservierung
     *
     * @param reservationNr Die bei der Reservierung erstelle Reservierungsnummer
     * @return Die zur Reservierungsnummer gehörende Reservierung
     */
    @Override
    public ReservationData search(int reservationNr)
    {
        return Reservation.getReservationByNumber(reservationNr);
    }

    @Override
    public Collection<ReservationData> searchApprox(String firstName, String lastName)
    {
        Collection<Reservation> reservations = Reservation.getReservationsByNameApprox(firstName, lastName);
        Collection<ReservationData> reservationData = new LinkedList<ReservationData>();
        if (reservations != null)
        {
            for (Reservation res : reservations)
            {
                reservationData.add(res);
            }
        }

        return reservationData;

    }

    /**
     * Sucht anhand des Namen der Person die reserviert hat nach einer Reservierung
     *
     * @param firstName Der Vorname der Person
     * @param lastName Der Nachname der Person
     * @return Eine Liste mit allen zur Suche passenden Reservierungen
     */
    @Override
    public Collection<ReservationData> search(String firstName, String lastName)
    {
        Collection<Reservation> reservations = Reservation.getReservationsByName(firstName, lastName);
        Collection<ReservationData> reservationData = new LinkedList<ReservationData>();
        if (reservations != null)
        {
            for (Reservation res : reservations)
            {
                reservationData.add(res);
            }
        }

        return reservationData;

    }

    /**
     * Wählt eine Reservierung aus die abgearbeitet werden soll
     *
     * @param reservation Die Reservierung die abgearbeitet werden soll
     */
    @Override
    public void workWithReservation(ReservationData reservation)
    {
        this.startDate = reservation.getStartDate();
        this.endDate = reservation.getEndDate();
        this.reservationItems = reservation.getReservationItemCollectionData();

        roomSelections = new HashMap<Integer, RoomSelection>();
        counter = 0;
        for (ReservationItemData data : reservation.getReservationItemCollectionData())
        {
            Room r = new Room();
            //r.setCategory((RoomCategory)data.getReservedCategoryData());
            r.setCategory((RoomCategory) DynamicMapper.map(DBRoomCategory.getRoomCategoryByName("Luxus Suite")));
            roomSelections.put(counter++, new RoomSelection(data.getReservedCategoryData(), r));
        }


        context.setState(new ChangeReservationDataState(context, counter, roomSelections, reservationItems));
    }

    /**
     * Startet einen Check In Vorgang für einen Walk In Gast
     *
     * @param start Das Startdatum des Aufenthalts
     * @param end Das Enddatum des Aufenthalts
     * @param amount Die Anzahl an Personen
     */
    @Override
    public void createWalkIn(Date start, Date end, int amount)
    {
        this.startDate = start;
        this.endDate = end;

        context.setState(new ChangeWalkInDataState(context));
    }

    /**
     * Startet einen Check In Vorgang für einen Walk In Gast
     *
     * @param days Die Aufenthaltsdauer in Tagen
     * @param amount Die Anzahl an Personen
     */
    @Override
    public void createWalkIn(int days, int amount)
    {
        Calendar cal = Calendar.getInstance();
        this.startDate = cal.getTime();

        cal.add(Calendar.DAY_OF_YEAR, days);
        this.endDate = cal.getTime();

        context.setState(new ChangeWalkInDataState(context));
    }
}
