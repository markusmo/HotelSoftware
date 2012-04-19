/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.reservation;

import hotelsoftware.model.DynamicMapper;
import hotelsoftware.model.database.reservation.DBReservation;
import java.util.Collection;

/**
 *
 * @author Johannes
 */
public class ReservationFacade
{
    private ReservationFacade()
    {}
    
    public static ReservationFacade getInstance()
    {
        return ReservationFacadeHolder.INSTANCE;
    }

    private static class ReservationFacadeHolder
    {
        private static final ReservationFacade INSTANCE = new ReservationFacade();
    }
    
    public Collection<Reservation> getReservationsByName(String fname, String lname)
    {
        return (Collection<Reservation>) DynamicMapper.mapCollection(DBReservation.getReservationsByName(fname, lname));
    }
    
    public Reservation getReservationById(int id)
    {
        return (Reservation) DynamicMapper.map(DBReservation.getReservationById(id));
    }
    
    public Collection<Reservation> getAllReservations()
    {
        return (Collection<Reservation>) DynamicMapper.map(DBReservation.getAllReservations());
    }
    
    public int getGuestAmount(Reservation reservation)
    {
        DBReservation res = (DBReservation) DynamicMapper.map(reservation);
        return res.getGuestAmount();
    }
}
