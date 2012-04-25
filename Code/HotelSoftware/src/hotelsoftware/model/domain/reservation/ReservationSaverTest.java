/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.reservation;

import hotelsoftware.model.database.FailedToSaveToDatabaseException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dunst
 */
public class ReservationSaverTest
{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        Collection<Reservation> reservations = Reservation.getAllReservations();
        
        for (Reservation r : reservations)
        {
            r.setComment("Test Reservation Saver");
        }
        try
        {
            ReservationSaver.getInstance().saveOrUpdate(reservations, new LinkedList(), new LinkedList());
        }
        catch (FailedToSaveToDatabaseException ex)
        {
            Logger.getLogger(ReservationSaverTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
