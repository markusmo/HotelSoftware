/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.service;

import hotelsoftware.model.domain.invoice.Invoice;
import hotelsoftware.model.domain.invoice.InvoiceItem;
import hotelsoftware.model.domain.parties.Guest;
import hotelsoftware.model.domain.reservation.Reservation;
import hotelsoftware.model.domain.room.Room;
import hotelsoftware.model.domain.users.User;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

/**
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class Habitation extends Service
{
    private Integer id;
    private Date start;
    private Date end;
    private Date created;
    private Collection<Guest> guestsCollection;
    private Room idRooms;
    private User idUsers;
    private Collection<InvoiceItem> invoiceItems;

    
    public Habitation(){
        
    }
    
    private Habitation(Date start, Date end){
        this.start = start;
        this.end = end;
    }
    
    private Habitation(Date start, Date end, Date created, BigDecimal price, Room room, User user){
        super();
        this.start = start;
        this.end = end;
        this.created = created;
        super.setPrice(price);
        this.idRooms = room;
        this.guestsCollection = new LinkedList<Guest>();
        this.idUsers = user;
        this.invoiceItems = new LinkedList<InvoiceItem>();
    }
    
    public static Habitation createHabitation(Date start, Date end){
        return new Habitation(start, end);
    }
    
    public static Habitation createWithReservationData(Reservation reservation){
        Habitation habitation = new Habitation();
        habitation.setStart(reservation.getStart());
        habitation.setEnd(reservation.getEnd());
        habitation.setIdUsers(LoginController.getInstance().getCurrentUser());
        return habitation;
    }
    
     public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        if (id == null){
            this.id = id;
        } 
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
     * @return the price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
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
     * @return the guestsCollection
     */
    public Collection<Guest> getGuestsCollection() {
        return guestsCollection;
    }

    /**
     * @param guestsCollection the guestsCollection to set
     */
    public void setGuestsCollection(Collection<Guest> guestsCollection) {
        this.guestsCollection = guestsCollection;
    }

    /**
     * @return the idRooms
     */
    public Room getIdRooms() {
        return idRooms;
    }

    /**
     * @param idRooms the idRooms to set
     */
    public void setIdRooms(Room idRooms) {
        this.idRooms = idRooms;
    }

    /**
     * @return the idUsers
     */
    public User getIdUsers() {
        return idUsers;
    }

    /**
     * @param idUsers the idUsers to set
     */
    public void setIdUsers(User idUsers) {
        this.idUsers = idUsers;
    }

    /**
     * @return the invoiceItems
     */
    public Collection<InvoiceItem> getInvoiceItems() {
        return invoiceItems;
    }

    /**
     * @param invoiceItems the invoiceItems to set
     */
    public void setInvoiceItems(Collection<InvoiceItem> invoiceItems) {
        this.invoiceItems = invoiceItems;
    }
    
    public void addInvoiceItem(InvoiceItem newInvoiceItem){
        invoiceItems.add(newInvoiceItem);
    }
    
    public void addGuest(Guest guest){
        guestsCollection.add(guest);
    }
}
