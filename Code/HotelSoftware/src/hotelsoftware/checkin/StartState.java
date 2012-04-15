/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.checkin;

import hotelsoftware.domain.reservation.Reservation;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Dunst
 */
public class StartState extends CheckInState
{

    /**
     * Sucht nach einer Reservierung
     * @param reservationNr Die bei der Reservierung erstelle Reservierungsnummer
     * @return Die zur Reservierungsnummer gehörende Reservierung 
     */
    public ReservationData search(int reservationNr)
    {
        return Reservation.getReservationByNumber(reservationNr);
    }
    
    /**
     * Sucht anhand des Namen der Person die reserviert hat nach einer Reservierung
     * @param firstName Der Vorname der Person
     * @param lastName Der Nachname der Person
     * @return Eine Liste mit allen zur Suche passenden Reservierungen
     */
    public List<ReservationData> search(String firstName, String lastName)
    {
        List<Reservation> reservations = Reservation.getReservationsByName(firstName, lastName);
        List<ReservationData> reservationData = new LinkedList<ReservationData>();
        
        for (Reservation reservation: reservations)
        {
            reservationData.add(reservation);
        }
        
        return reservationData;
    }
    
    /**
     * Wählt eine Reservierung aus die abgearbeitet werden soll
     * @param reservation Die Reservierung die abgearbeitet werden soll
     */
    public void workWithReservation(ReservationData reservation)
    {
        this.startDate = reservation.getStart();
        this.endDate = reservation.getEnd();
        
        context.setState(new ChangeDataState());
    }
    
    /**
     * Startet einen Check In Vorgang für einen Walk In Gast
     * @param start Das Startdatum des Aufenthalts
     * @param end Das Enddatum des Aufenthalts
     * @param amount Die Anzahl an Personen
     */
    @Override
    public void createWalkIn(Date start, Date end, int amount)
    {
        this.startDate = start;
        this.endDate = end;
        
        context.setState(new ChangeDataState());
    }
    
    /**
     * Startet einen Check In Vorgang für einen Walk In Gast
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
        
        context.setState(new ChangeDataState());
    }    
}
