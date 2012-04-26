/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.checkin;

import hotelsoftware.model.domain.reservation.Reservation;
import hotelsoftware.model.domain.reservation.data.ReservationData;
import hotelsoftware.util.HelperFunctions;
import java.util.*;

/**
 * Dieser Status erlaubt es nach vorhandenen Reservierungen zu suchen und mit dieser dann zu arbeiten.
 * Ebenso kann man einen Walk-in-Check-In begonnen werden
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
        Collection<ReservationData> reservationData = new LinkedHashSet<ReservationData>();
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
        Collection<ReservationData> reservationData = new LinkedHashSet<ReservationData>();
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
        context.setStartDate(reservation.getStartDate());
        context.setEndDate(reservation.getEndDate());
        context.setReservationItems(reservation.getReservationItemCollectionData());

        context.setRoomSelections(new HashMap<Integer, RoomSelection>());
        context.setCounter(0);
        context.setReservation((Reservation) reservation);
        /*
         * for (ReservationItemData data : context.getReservationItems())
         * {
         * Room r = new Room();
         * r.setCategory((RoomCategory)data.getReservedCategoryData());
         * //r.setCategory((RoomCategory) DynamicMapper.map(DBRoomCategory.getRoomCategoryByName("Luxus Suite")));
         * context.getRoomSelections().put(context.increaseCounter(), new RoomSelection(data.getReservedCategoryData(), r));
         * }
         */

        context.setState(new ChangeReservationDataState(context));
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
        context.setStartDate(start);
        context.setEndDate(end);

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
        context.setStartDate(cal.getTime());

        cal.add(Calendar.DAY_OF_YEAR, days);
        context.setEndDate(cal.getTime());

        context.setState(new ChangeWalkInDataState(context));
    }
}
