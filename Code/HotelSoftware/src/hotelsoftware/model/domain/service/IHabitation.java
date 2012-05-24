/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.service;

import hotelsoftware.controller.data.service.HabitationData;
import hotelsoftware.model.domain.invoice.IInvoiceItem;
import hotelsoftware.model.domain.parties.IGuest;
import hotelsoftware.model.domain.room.IRoom;
import hotelsoftware.model.domain.users.IUser;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author Kno
 */
public interface IHabitation extends IService, HabitationData
{
    void addGuests(IGuest guest);

    void addInvoiceItems(IInvoiceItem newInvoiceItem);

    /**
     * @return the guestsCollection
     */
    Collection<IGuest> getGuests();

    String getHabitationNumber();

    Collection<IInvoiceItem> getInvoiceItems();

    /**
     * @return the idRooms
     */
    IRoom getRooms();

    /**
     * @return the idUsers
     */
    IUser getUsers();

    /**
     * @param created the created to set
     */
    void setCreated(Date created);

    /**
     * @param end the end to set
     */
    void setEnd(Date end);

    /**
     * @param guests the guestsCollection to set
     */
    void setGuests(Collection<IGuest> guests);

    void setHabitationNumber(String habitationNumber);

    void setInvoiceItems(Collection<IInvoiceItem> invoiceItems);

    /**
     * @param rooms the idRooms to set
     */
    void setRooms(IRoom rooms);

    /**
     * @param start the start to set
     */
    void setStart(Date start);

    /**
     * @param users the idUsers to set
     */
    void setUsers(IUser users);
}
