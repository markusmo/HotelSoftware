/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.controller.data.service;

import hotelsoftware.controller.data.invoice.InvoiceItemData;
import hotelsoftware.controller.data.parties.GuestData;
import hotelsoftware.controller.data.room.RoomData;
import hotelsoftware.controller.data.users.UserData;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

/**
 *Dieses Interface enthält alle wichtigen Methoden die für die Klasse Habitation benötigt werden
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public interface HabitationData extends ServiceData
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
     * @return Die Datenobjekte der Gäste die zum Aufenthalt gehören
     */
    Collection<GuestData> getGuestsData();

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
