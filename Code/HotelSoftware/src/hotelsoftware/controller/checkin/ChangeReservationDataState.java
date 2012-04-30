/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.controller.checkin;

import hotelsoftware.model.domain.parties.Guest;
import hotelsoftware.controller.data.parties.GuestData;
import hotelsoftware.controller.data.parties.PartyData;
import java.util.Collection;

/**
 * Dieser Status ist dazu da um Reservations-Check-Ins von Walk-In-Check-Ins zu abstrahieren.
 * @author Dunst
 */
public class ChangeReservationDataState extends ChangeDataState
{
    public ChangeReservationDataState(CheckInController context)
    {
        super(context);
    }

    @Override
    public Collection<GuestData> getGuests()
    {
        return context.getReservation().getGuestsData();
    }
}
