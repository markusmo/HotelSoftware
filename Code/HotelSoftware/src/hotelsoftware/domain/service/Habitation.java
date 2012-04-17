/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.domain.service;

import hotelsoftware.database.model.DBHabitations;
import hotelsoftware.domain.invoice.InvoiceItem;
import hotelsoftware.domain.parties.Guest;
import hotelsoftware.domain.room.Room;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class Habitation
{
    private Date start;
    private Date end;
    private Date created;
    private DBHabitations model;
    private List<InvoiceItem> invoiceItem;
    private List<Room> rooms;
    private List<Guest> guests;

    public Habitation(DBHabitations habitation)
    {
        this.model = habitation;
    }

    public DBHabitations getModel()
    {
        return model;
    }

    /**
     * @return the start
     */
    public Date getStart() {
        return start;
    }

    /**
     * @param start the start to set
     */
    public void setStart(Date start) {
        this.start = start;
    }

    /**
     * @return the end
     */
    public Date getEnd() {
        return end;
    }

    /**
     * @param end the end to set
     */
    public void setEnd(Date end) {
        this.end = end;
    }

    /**
     * @return the created
     */
    public Date getCreated() {
        return created;
    }

    /**
     * @param created the created to set
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * @param model the model to set
     */
    public void setModel(DBHabitations model) {
        this.model = model;
    }

    /**
     * @return the guest
     */
    public List<InvoiceItem> getInvoiceItem() {
        return invoiceItem;
    }

    /**
     * @param guest the guest to set
     */
    public void setInvoiceItem(List<InvoiceItem> InvoiceItem) {
        this.setInvoiceItem(InvoiceItem);
    }

    /**
     * @param invoiceItem the invoiceItem to set
     */

    /**
     * @return the rooms
     */
    public List<Room> getRooms() {
        return rooms;
    }

    /**
     * @param rooms the rooms to set
     */
    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    /**
     * @return the guests
     */
    public List<Guest> getGuests() {
        return guests;
    }

    /**
     * @param guests the guests to set
     */
    public void setGuests(List<Guest> guests) {
        this.guests = guests;
    }
    
    public void addGuest(Guest newGuest){
        guests.add(newGuest);
    }
    
    public void addInvoiceItem(InvoiceItem newInvoiceItem){
        invoiceItem.add(newInvoiceItem);
    }
}
