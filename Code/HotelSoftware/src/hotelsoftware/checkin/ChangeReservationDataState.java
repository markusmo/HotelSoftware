/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.checkin;

import hotelsoftware.model.datainterfaces.GuestData;
import hotelsoftware.model.domain.parties.Guest;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Dunst
 */
public class ChangeReservationDataState extends ChangeDataState
{
    @Override
    public List<GuestData> getGuests()
    {
        List<Guest> guests = reservation.getAllGuests();
        List<GuestData> guestData = new LinkedList<GuestData>();
        
        for (Guest guest : guests)
        {
            guestData.add(guest);
        }
        
        return guestData;
    }
}
