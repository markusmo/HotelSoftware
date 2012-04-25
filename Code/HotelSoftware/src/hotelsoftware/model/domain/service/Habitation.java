package hotelsoftware.model.domain.service;

import hotelsoftware.model.domain.service.data.HabitationData;
import hotelsoftware.model.domain.service.data.ServiceTypeData;
import hotelsoftware.login.LoginController;
import hotelsoftware.model.domain.invoice.InvoiceItem;
import hotelsoftware.model.domain.invoice.InvoiceItemData;
import hotelsoftware.model.domain.parties.Guest;
import hotelsoftware.model.domain.parties.GuestData;
import hotelsoftware.model.domain.reservation.Reservation;
import hotelsoftware.model.domain.room.Room;
import hotelsoftware.model.domain.room.data.RoomData;
import hotelsoftware.model.domain.users.User;
import hotelsoftware.model.domain.users.data.UserData;
import hotelsoftware.util.HelperFunctions;
import java.math.BigDecimal;
import java.util.*;

/**
 * Dies Klasse bildet einen Aufenthalt ab, mit der das System arbeitet
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class Habitation extends Service implements HabitationData
{
    private Integer id;
    private Date start;
    private Date end;
    private Date created;
    private Collection<Guest> guests;
    private Room rooms;
    private User users;
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
        this.rooms = room;
        this.guests = new LinkedHashSet<Guest>();
        this.users = user;
        this.invoiceItems = new LinkedHashSet<InvoiceItem>();
    }
    
    /**
     * Instanziert einen Aufenthalt fuer eine Periode (fuer Walk-In)
     * @param start
     * Start der Periode
     * @param end
     * Ende der Periode
     * @return 
     */
    public static Habitation createHabitation(Date start, Date end){
        return new Habitation(start, end);
    }
    
    /**
     * Instanziert einen Aufenthalt mit einer vorhandenen Reservierung
     * @param reservation
     * Die Reservierung, aus der ein Aufenthalt werden sollte.
     * @return 
     * Eine neue Instanz
     */
    public static Habitation createWithReservationData(Reservation reservation){
        Habitation habitation = new Habitation();
        habitation.setStart(reservation.getStartDate());
        habitation.setEnd(reservation.getEndDate());
        habitation.setUsers(LoginController.getInstance().getCurrentUser());
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
    public Collection<Guest> getGuests() {
        return guests;
    }

    /**
     * @param guests the guestsCollection to set
     */
    public void setGuests(Collection<Guest> guests) {
        this.guests = guests;
    }

    /**
     * @return the idRooms
     */
    public Room getRooms() {
        return rooms;
    }

    /**
     * @param rooms the idRooms to set
     */
    public void setRooms(Room rooms) {
        this.rooms = rooms;
    }

    /**
     * @return the idUsers
     */
    public User getUsers() {
        return users;
    }

    /**
     * @param users the idUsers to set
     */
    public void setUsers(User users) {
        this.users = users;
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
    
    public void addInvoiceItems(InvoiceItem newInvoiceItem){
        invoiceItems.add(newInvoiceItem);
    }
    
    public void addGuests(Guest guest){
        guests.add(guest);
    }

    /**
     * not implemented
     * @return 
     */
    public Collection<GuestData> getGuestsCollectionData()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public RoomData getRoomsData()
    {
        return (RoomData) getRooms();
    }

    public UserData getUsersData()
    {
        return (UserData) getUsers();
    }
    
    public Collection<InvoiceItemData> getInvoiceItemsData()
    {
        return new HelperFunctions<InvoiceItemData, InvoiceItem>().castCollectionUp(getInvoiceItems());
    }

    public ServiceTypeData getServiceTypeData()
    {
        return (ServiceType) getServiceType();
    }
}
