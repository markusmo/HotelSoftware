/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.checkin;

import hotelsoftware.model.domain.parties.Guest;
import hotelsoftware.model.domain.reservation.Reservation;
import hotelsoftware.model.domain.reservation.ReservationItem;
import hotelsoftware.model.domain.reservation.data.ReservationData;
import hotelsoftware.model.domain.reservation.data.ReservationItemData;
import hotelsoftware.model.domain.room.RoomCategory;
import hotelsoftware.util.HelperFunctions;
import java.util.*;

/**
 * Dieser Status erlaubt es nach vorhandenen Reservierungen zu suchen und mit dieser dann zu arbeiten.
 * Ebenso kann man einen Walk-in-Check-In begonnen werden
 *
 * @author Dunst
 */
class StartState extends CheckInState
{
    StartState(CheckInController context)
    {
        super(context);
    }

    @Override
    Collection<ReservationData> getAllReservations()
    {
        return new HelperFunctions<ReservationData, Reservation>().castCollectionUp(Reservation.getAllReservations());
    }

    /**
     * Sucht anhand des Namen der Person die reserviert hat nach einer Reservierung
     *
     * @param firstName Der Vorname der Person
     * @param lastName Der Nachname der Person
     * @return Eine Liste mit allen zur Suche passenden Reservierungen
     */
    @Override
    Collection<ReservationData> search(String firstName, String lastName, String companyName, String reservationNumber)
    {
        return new HelperFunctions<ReservationData, Reservation>().castCollectionUp(Reservation.search(firstName, lastName, companyName, reservationNumber));
    }

    /**
     * Wählt eine Reservierung aus die abgearbeitet werden soll
     *
     * @param reservation Die Reservierung die abgearbeitet werden soll
     */
    @Override
    void workWithReservation(ReservationData reservation)
    {
        context.setStartDate(reservation.getStartDate());
        context.setEndDate(reservation.getEndDate());
        context.setReservationItems(reservation.getReservationItemCollectionData());

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
    void createWalkIn(Date start, Date end, int amount)
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
    void createWalkIn(int days, int amount)
    {
        Calendar cal = Calendar.getInstance();
        context.setStartDate(cal.getTime());

        cal.add(Calendar.DAY_OF_YEAR, days);
        context.setEndDate(cal.getTime());

        context.setState(new ChangeWalkInDataState(context));
    }
    
    @Override
    void createNewWalkIn()
    {
       /*context.setStartDate(new Date());
        context.setEndDate(new Date());
        Reservation r = new Reservation();
        r.setComment("");
        r.setStartDate(new Date());
        r.setEndDate(new Date());
        r.setReservationNumber("");
        r.setParty(new Guest());
        
        LinkedList<ReservationItem> items = new LinkedList<ReservationItem>();
        ReservationItem ri = new ReservationItem();
        
        ri.setAmount(1);
        ri.setRoomCategory(RoomCategory.getCategoryByName("Double room"));
        
        r.setReservationItems(items);
        items.add(ri);
        
        context.setReservationItems(new HelperFunctions<ReservationItemData, ReservationItem>().castCollectionUp(items));

        
        context.setCounter(0);
        context.setReservation(r);*/

        context.setState(new ChangeWalkInDataState(context));
    }
}
