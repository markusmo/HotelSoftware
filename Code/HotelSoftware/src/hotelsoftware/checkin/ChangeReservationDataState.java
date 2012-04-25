/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.checkin;

import hotelsoftware.checkin.CheckInState.RoomSelection;
import hotelsoftware.model.domain.parties.Guest;
import hotelsoftware.model.domain.parties.data.GuestData;
import hotelsoftware.model.domain.parties.data.PartyData;
import hotelsoftware.model.domain.reservation.data.ReservationItemData;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

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

    ChangeReservationDataState(CheckInController context, int counter, Map<Integer, RoomSelection> roomSelections, Collection<ReservationItemData> reservationItems)
    {
        super(context, counter, roomSelections, reservationItems);
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
