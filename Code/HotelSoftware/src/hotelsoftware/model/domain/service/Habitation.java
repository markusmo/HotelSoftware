/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.service;

import hotelsoftware.login.LoginController;
import hotelsoftware.model.domain.invoice.InvoiceItem;
import hotelsoftware.model.domain.invoice.InvoiceItemData;
import hotelsoftware.model.domain.parties.Guest;
import hotelsoftware.model.domain.parties.GuestData;
import hotelsoftware.model.domain.reservation.Reservation;
import hotelsoftware.model.domain.room.Room;
import hotelsoftware.model.domain.room.data.RoomData;
import hotelsoftware.model.domain.users.User;
import hotelsoftware.model.domain.users.UserData;
import hotelsoftware.util.HelperFunctions;
import java.math.BigDecimal;
import java.util.*;

/**
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class Habitation extends Service implements HabitationData
{
    private Integer id;
    private Date start;
    private Date end;
    private Date created;
    private Set<Guest> guestsCollection;
    private Room idRooms;
    private User idUsers;
    private Set<InvoiceItem> invoiceItems;

    
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
        this.guestsCollection = new LinkedHashSet<Guest>();
        this.idUsers = user;
        this.invoiceItems = new LinkedHashSet<InvoiceItem>();
    }
    
    public static Habitation createHabitation(Date start, Date end){
        return new Habitation(start, end);
    }
    
    public static Habitation createWithReservationData(Reservation reservation){
        Habitation habitation = new Habitation();
        habitation.setStart(reservation.getStartDate());
        habitation.setEnd(reservation.getEndDate());
        habitation.setIdUsers(LoginController.getInstance().getCurrentUser());
        return habitation;
    }
    
    @Override
     public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        if (id == null){
            this.id = id;
        } 
    }

    /**
     * @return the start
     */
    @Override
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
    @Override
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
    @Override
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    @Override
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * @return the created
     */
    @Override
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
    public Set<Guest> getGuestsCollection() {
        return guestsCollection;
    }

    /**
     * @param guestsCollection the guestsCollection to set
     */
    public void setGuestsCollection(Set<Guest> guestsCollection) {
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
    public Set<InvoiceItem> getInvoiceItems() {
        return invoiceItems;
    }

    /**
     * @param invoiceItems the invoiceItems to set
     */
    public void setInvoiceItems(Set<InvoiceItem> invoiceItems) {
        this.invoiceItems = invoiceItems;
    }
    
    public void addInvoiceItem(InvoiceItem newInvoiceItem){
        invoiceItems.add(newInvoiceItem);
    }
    
    public void addGuest(Guest guest){
        guestsCollection.add(guest);
    }

    public Set<GuestData> getGuestsCollectionData()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public RoomData getIdRoomsData()
    {
        return (RoomData) getIdRooms();
    }

    public UserData getIdUsersData()
    {
        return (UserData) getIdUsers();
    }

    public Set<InvoiceItemData> getInvoiceItemsData()
    {
        return new HelperFunctions<InvoiceItemData, InvoiceItem>().castCollectionUp(getInvoiceItems());
    }

    public ServiceTypeData getServiceTypeData()
    {
        return (ServiceType) getServiceType();
    }
}
