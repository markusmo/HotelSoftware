package hotelsoftware.model.domain.reservation;

import hotelsoftware.model.DynamicMapper;
import hotelsoftware.model.database.reservation.DBReservation;
import java.util.Collection;
import java.util.Set;

/**
 * Fassadenobjekt, welches das Reservation-Package verwaltet.
 *
 * @author Johannes
 */
public class ReservationFacade
{

    private ReservationFacade()
    {
    }

    public static ReservationFacade getInstance()
    {
        return ReservationFacadeHolder.INSTANCE;
    }

    

    private static class ReservationFacadeHolder
    {

        private static final ReservationFacade INSTANCE = new ReservationFacade();
    }

    /**
     * Gibt eine Reservierung, nach der eindeutigen Reservierungsnummer aus
     *
     * @param reservationNr Die eindeutige Reservierungsnummer
     * @return Die Reservierung, die gesucht wurde
     */
    public Reservation getReservationByNumber(String reservationNr)
    {
        return (Reservation) DynamicMapper.map(DBReservation.getReservationByNumber(reservationNr));
    }

    /**
     * Gibt eine Reservierung nach dem Gast/Kunden aus, der reserviert hat
     *
     * @param fname Der Vorname des Gastes/Kunden
     * @param lname Der Nachname des Gastes/Kunden
     * @return Die gesuchte Reservierung
     */
    public Collection<Reservation> getReservationsByName(String fname, String lname)
    {
        return (Collection<Reservation>) DynamicMapper.mapCollection(DBReservation.getReservationsByName(
                fname, lname));
    }

    public Collection<Reservation> getReservationsByNameApprox(String fname,
            String lname)
    {
        return (Collection<Reservation>) DynamicMapper.mapCollection(DBReservation.getReservationsByNameApprox(
                fname, lname));
    }
    
    public Collection<Reservation> getReservationsByCompanyNameApprox(String companyName)
    {
        return (Collection<Reservation>) DynamicMapper.mapCollection(DBReservation.getReservationsByCompanyNameApprox(companyName));
    }

    /**
     * Gibt eine Reservierung, nach ihrer eindeutigen ID aus
     *
     * @param id Die eindeutige ID
     * @return Die gesuchte Reservierung
     */
    public Reservation getReservationById(int id)
    {
        return (Reservation) DynamicMapper.map(DBReservation.getReservationById(
                id));
    }

    /**
     * Gibt alle Reservierungen aus
     *
     * @return Alle vorhandenen Reservierungen
     */
    public Collection<Reservation> getAllReservations()
    {
        return (Collection<Reservation>) DynamicMapper.mapCollection(DBReservation.getAllReservations());
    }

    /**
     * Gibt die Anzahl der reservierten Gaeste aus
     *
     * @param reservation Die Reservierung
     * @return Die Anzahl, der Gaeste in einer Reservierung.
     */
    public Integer getGuestAmount(Reservation reservation)
    {
        DBReservation res = (DBReservation) DynamicMapper.map(reservation);
        return res.getGuestAmount();
    }
}
