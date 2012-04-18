/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.gui.checkin;

import hotelsoftware.checkin.CheckInController;
import hotelsoftware.model.domain.reservation.Reservation;
import java.util.Collection;

/**
 *
 * @author Johannes
 */
public class CheckInGuiControler
{
    private CheckInController cic = CheckInController.getInstance();
    
    private static CheckInGuiControler controller = null;

    public static CheckInGuiControler getInstance()
    {
        if (controller == null)
        {
            controller = new CheckInGuiControler();
        }

        return controller;
    }

    public Collection<Reservation> searchReservations(String fname, String lname, String reservationNumber)
    {
        Collection<Reservation> c = cic.
        
    }
    
    
}
