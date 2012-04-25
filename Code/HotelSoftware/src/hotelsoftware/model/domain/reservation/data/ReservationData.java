/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.reservation.data;

import hotelsoftware.model.domain.parties.Party;
import hotelsoftware.model.domain.parties.data.PartyData;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

/**
 *
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

    Date getStartDate();

    public String getReservationNumber();

    Integer getGuestAmount();
}
