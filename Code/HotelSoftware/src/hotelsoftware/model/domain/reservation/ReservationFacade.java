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
    
    public static Collection<Reservation> getReservationsByName(String fname)
    {
        return (Collection<Reservation>) DynamicMapper.map(DBReservation.getReservationsByFName(fname));
    }
}
