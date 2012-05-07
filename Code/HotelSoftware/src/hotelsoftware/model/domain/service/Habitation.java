package hotelsoftware.model.domain.service;

import hotelsoftware.controller.login.LoginController;
import hotelsoftware.model.domain.invoice.InvoiceItem;
import hotelsoftware.controller.data.invoice.InvoiceItemData;
import hotelsoftware.model.domain.parties.Guest;
import hotelsoftware.controller.data.parties.GuestData;
import hotelsoftware.model.domain.reservation.Reservation;
import hotelsoftware.model.domain.room.Room;
import hotelsoftware.controller.data.room.RoomData;
import hotelsoftware.controller.data.service.HabitationData;
import hotelsoftware.controller.data.service.ServiceTypeData;
import hotelsoftware.model.domain.users.User;
import hotelsoftware.controller.data.users.UserData;
import hotelsoftware.model.database.service.DBHabitation;
import hotelsoftware.util.HelperFunctions;
import hotelsoftware.util.HibernateUtil;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Dies Klasse bildet einen Aufenthalt ab, mit der das System arbeitet
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class Habitation extends Service implements HabitationData
{
    private Date start;
    private Date end;
    private Date created;
    private Collection<Guest> guests;
    private Room rooms;
    private User users;
    private Collection<InvoiceItem> invoiceItems;
    private String habitationNumber;

    public Habitation()
    {
        guests = new LinkedHashSet();
        invoiceItems = new LinkedHashSet();
    }

    private Habitation(Date start, Date end)
    {
        this();
        this.start = start;
        this.end = end;
    }

    private Habitation(Date start, Date end, Date created, BigDecimal price, Room room, User user)
    {
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
     *
     * @param start
     * Start der Periode
     * @param end
     * Ende der Periode
     * @return
     * neuen Aufenthalt mit fuer die angegebene Periode
     */
    public static Habitation createHabitation(Date start, Date end)
    {
        return new Habitation(start, end);
    }

    /**
     * Instanziert einen Aufenthalt mit einer vorhandenen Reservierung
     *
     * @param reservation
     * Die Reservierung, aus der ein Aufenthalt werden sollte.
     * @return
     * Eine neue Instanz
     */
    public static Habitation createWithReservationData(Reservation reservation)
    {
        Habitation habitation = new Habitation();
        habitation.setStart(reservation.getStartDate());
        habitation.setEnd(reservation.getEndDate());
        habitation.setUsers(LoginController.getInstance().getCurrentUser());
        return habitation;
    }
    
    public static int getHighestId()
    {
        return ServiceFacade.getHighestHabitationId();
    } //überfacade reservation und invoice

    /**
     * @return the start
     */
    @Override
    public Date getStart()
    {
        return start;
    }

    /**
     * @param start the start to set
     */
    public void setStart(Date start)
    {
        this.start = start;
    }

    /**
     * @return the end
     */
    @Override
    public Date getEnd()
    {
        return end;
    }

    /**
     * @param end the end to set
     */
    public void setEnd(Date end)
    {
        this.end = end;
    }

    /**
     * @return the price
     */
    @Override
    public BigDecimal getPrice()
    {
        return price;
    }

    /**
     * @param price the price to set
     */
    @Override
    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }

    /**
     * @return the created
     */
    @Override
    public Date getCreated()
    {
        return created;
    }

    /**
     * @param created the created to set
     */
    public void setCreated(Date created)
    {
        this.created = created;
    }

    /**
     * @return the guestsCollection
     */
    public Collection<Guest> getGuests()
    {
        return guests;
    }

    /**
     * @param guests the guestsCollection to set
     */
    public void setGuests(Collection<Guest> guests)
    {
        this.guests = guests;
    }

    /**
     * @return the idRooms
     */
    public Room getRooms()
    {
        return rooms;
    }

    /**
     * @param rooms the idRooms to set
     */
    public void setRooms(Room rooms)
    {
        this.rooms = rooms;
    }

    /**
     * @return the idUsers
     */
    public User getUsers()
    {
        return users;
    }

    /**
     * @param users the idUsers to set
     */
    public void setUsers(User users)
    {
        this.users = users;
    }
    
    public String getHabitationNumber()
    {
        return habitationNumber;
    }

    public void setHabitationNumber(String habitationNumber)
    {
        this.habitationNumber = habitationNumber;
    }

    /**
     * @return the invoiceItems
     */
    public Collection<InvoiceItem> getInvoiceItems()
    {
        return invoiceItems;
    }

    /**
     * @param invoiceItems the invoiceItems to set
     */
    public void setInvoiceItems(Collection<InvoiceItem> invoiceItems)
    {
        this.invoiceItems = invoiceItems;
    }

    public void addInvoiceItems(InvoiceItem newInvoiceItem)
    {
        invoiceItems.add(newInvoiceItem);
    }

    public void addGuests(Guest guest)
    {
        guests.add(guest);
    }

    @Override
    public Collection<GuestData> getGuestsData()
    {
        return new HelperFunctions<GuestData, Guest>().castCollectionUp(getGuests());
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

    @Override
    public String toString()
    {
        String newline = "\n";
        StringBuilder builder = new StringBuilder();
        
        
        builder.append("Start: ");
        builder.append(this.start.toString());
        builder.append(newline);
        builder.append("End: ");
        builder.append(this.end.toString());
        builder.append(newline);
        
        builder.append("Room number: ");
        builder.append(rooms.getNumber());
        builder.append(" Category: ");
        builder.append(rooms.getCategory().getName());
        builder.append(newline);
        
        builder.append("<ul>");
        for(Guest g : guests)
        {
            builder.append("<li>");
            builder.append(g.getFname());
            builder.append(" ");
            builder.append(g.getLname());
            builder.append(newline);
            builder.append("</li>");
        }
        builder.append("</ul>");
        builder.append("Price: € ");
        builder.append(this.price.toPlainString());
       
        
        return builder.toString();
    }
    
       public static Collection<Habitation> searchHabitations(String fName, String lName, Integer roomId)
       {
           if(roomId == null)
          return ServiceFacade.getInstance().getHabitations(fName, lName);
       return ServiceFacade.getInstance().getHabitation(roomId);
       }

    @Override
    public String getServiceName() {
        throw new UnsupportedOperationException("Not implemented yet (but in future it will return the name of the habiation!");
    }
      
}
