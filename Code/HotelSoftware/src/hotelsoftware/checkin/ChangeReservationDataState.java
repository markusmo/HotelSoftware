/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.checkin;

import hotelsoftware.model.domain.parties.Guest;
import hotelsoftware.model.domain.parties.GuestData;
import hotelsoftware.model.domain.parties.PartyData;

/**
 *
 * @author Dunst
 */
public class ChangeReservationDataState extends ChangeDataState
{
    public ChangeReservationDataState(CheckInController context)
    {
        super(context);
    }
    
    @Override
    public GuestData getGuest()
    {
        PartyData guest = reservation.getPartyData();
        
        if (guest instanceof Guest)
        {
            return (GuestData) guest;
        }
        
        return null;
    }
}
