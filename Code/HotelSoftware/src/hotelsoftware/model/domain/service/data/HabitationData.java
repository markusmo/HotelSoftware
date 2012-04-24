/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.service.data;

import hotelsoftware.model.domain.invoice.InvoiceItemData;
import hotelsoftware.model.domain.parties.GuestData;
import hotelsoftware.model.domain.room.data.RoomData;
import hotelsoftware.model.domain.users.data.UserData;
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
    RoomData getRoomsData();

    /**
     * @return the idUsers
     */
    UserData getUsersData();

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