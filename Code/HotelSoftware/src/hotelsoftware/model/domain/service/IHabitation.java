/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.service;

import hotelsoftware.controller.data.invoice.InvoiceItemData;
import hotelsoftware.controller.data.parties.GuestData;
import hotelsoftware.controller.data.room.RoomData;
import hotelsoftware.controller.data.service.HabitationData;
import hotelsoftware.controller.data.service.ServiceTypeData;
import hotelsoftware.controller.data.users.UserData;
import hotelsoftware.model.domain.invoice.IInvoiceItem;
import hotelsoftware.model.domain.invoice.InvoiceItem;
import hotelsoftware.model.domain.parties.Guest;
import hotelsoftware.model.domain.parties.IGuest;
import hotelsoftware.model.domain.room.IRoom;
import hotelsoftware.model.domain.room.Room;
import hotelsoftware.model.domain.users.IUser;
import hotelsoftware.model.domain.users.User;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author Kno
 */
public interface IHabitation extends IService, HabitationData {

    void addGuests(IGuest guest);

    void addInvoiceItems(IInvoiceItem newInvoiceItem);

    boolean equals(Object obj);

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
    Collection<IGuest> getGuests();

    Collection<GuestData> getGuestsData();

    String getHabitationNumber();

    Collection<IInvoiceItem> getInvoiceItems();

    Collection<InvoiceItemData> getInvoiceItemsData();

    /**
     * @return the price
     */
    BigDecimal getPrice();

    /**
     * @return the idRooms
     */
    IRoom getRooms();

    RoomData getRoomsData();

    String getServiceName();

    ServiceTypeData getServiceTypeData();

    /**
     * @return the start
     */
    Date getStart();

    /**
     * @return the idUsers
     */
    IUser getUsers();

    UserData getUsersData();

    int hashCode();

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
     * @param price the price to set
     */
    void setPrice(BigDecimal price);

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

    String toString();
    
}
