/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.controller.data.reservation;

import hotelsoftware.controller.data.parties.GuestData;
import hotelsoftware.controller.data.parties.PartyData;
import java.util.Collection;
import java.util.Date;

/**
 *Dieses Interface enthält alle wichtigen Methoden für die Klasse Reservation
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public interface ReservationData
{
    String getComment();

    Date getCreated();

    Date getEndDate();

    Collection<ReservationOptionData> getOptionCollectionData();

    PartyData getPartyData();

    Collection<ReservationItemData> getReservationItemCollectionData();
    
    Collection<GuestData> getGuestsData();

    Date getStartDate();

    public String getReservationNumber();

    /**
     * Gibt die Anzahl der reservierten Gaeste aus
     *
     * @return Die Anzahl, der Gaeste in einer Reservierung.
     */
    Integer getGuestAmount();
}
