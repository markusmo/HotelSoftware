/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.service;

import hotelsoftware.model.domain.invoice.InvoiceItem;
import hotelsoftware.model.domain.invoice.InvoiceItemData;
import hotelsoftware.model.domain.parties.Guest;
import hotelsoftware.model.domain.parties.GuestData;
import hotelsoftware.model.domain.room.Room;
import hotelsoftware.model.domain.room.RoomData;
import hotelsoftware.model.domain.users.User;
import hotelsoftware.model.domain.users.UserData;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public interface HabitationData
{

    /**
     * @return the created
     */
    Date getCreated();

    /**
     * @return the end
     */
    Date getEnd();

    /**
     * @return the guestsCollection
     */
    Collection<GuestData> getGuestsCollectionData();

    /**
     * @return the idRooms
     */
    RoomData getIdRoomsData();

    /**
     * @return the idUsers
     */
    UserData getIdUsersData();

    /**
     * @return the invoiceItems
     */
    Collection<InvoiceItemData> getInvoiceItemsData();

    /**
     * @return the price
     */
    BigDecimal getPrice();

    /**
     * @return the start
     */
    Date getStart();
    
}
