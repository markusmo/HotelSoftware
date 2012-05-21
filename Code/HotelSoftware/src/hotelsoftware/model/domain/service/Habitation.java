package hotelsoftware.model.domain.service;

import hotelsoftware.controller.data.invoice.InvoiceItemData;
import hotelsoftware.controller.data.parties.GuestData;
import hotelsoftware.controller.data.room.RoomData;
import hotelsoftware.controller.data.service.ServiceTypeData;
import hotelsoftware.controller.data.users.UserData;
import hotelsoftware.controller.login.LoginController;
import hotelsoftware.model.domain.invoice.IInvoiceItem;
import hotelsoftware.model.domain.parties.IGuest;
import hotelsoftware.model.domain.reservation.Reservation;
import hotelsoftware.model.domain.room.IRoom;
import hotelsoftware.model.domain.users.IUser;
import hotelsoftware.util.HelperFunctions;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.LinkedList;

/**
 * Dies Klasse bildet einen Aufenthalt ab, mit der das System arbeitet
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class Habitation extends Service implements IHabitation
{
    private Date start;
    private Date end;
    private Date created;
    private Collection<IGuest> guests;
    private IRoom rooms;
    private IUser users;
    private Collection<IInvoiceItem> invoiceItems;
    private String habitationNumber;

    public Habitation()
    {
    }

    private Habitation(Date start, Date end)
    {
        this();
        this.start = start;
        this.end = end;
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
    public static IHabitation createHabitation(Date start, Date end)
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
    public static IHabitation createWithReservationData(Reservation reservation)
    {
        Habitation habitation = new Habitation();
        habitation.setStart(reservation.getStartDate());
        habitation.setEnd(reservation.getEndDate());
        habitation.setUsers(LoginController.getInstance().getCurrentUser());
        return habitation;
    }
    
    public static Collection<IHabitation> getHabitationsByDate(Date date)
    {
        return ServiceFacade.getInstance().getHabitationsByDate(date);
    }
    
    public static Collection<IHabitation> getAllHabitations()
    {
        return ServiceFacade.getInstance().getAllHabitations();
    }

    public static int getHighestId()
    {
        return ServiceFacade.getInstance().getHighestHabitationId();
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
    @Override
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
    @Override
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
    @Override
    public void setCreated(Date created)
    {
        this.created = created;
    }

    /**
     * @return the guestsCollection
     */
    @Override
    public Collection<IGuest> getGuests()
    {
        return guests;
    }

    /**
     * @param guests the guestsCollection to set
     */
    @Override
    public void setGuests(Collection<IGuest> guests)
    {
        this.guests = guests;
    }

    /**
     * @return the idRooms
     */
    @Override
    public IRoom getRooms()
    {
        return rooms;
    }

    /**
     * @param rooms the idRooms to set
     */
    @Override
    public void setRooms(IRoom rooms)
    {
        this.rooms = rooms;
    }

    /**
     * @return the idUsers
     */
    @Override
    public IUser getUsers()
    {
        return users;
    }

    /**
     * @param users the idUsers to set
     */
    @Override
    public void setUsers(IUser users)
    {
        this.users = users;
    }

    @Override
    public String getHabitationNumber()
    {
        return habitationNumber;
    }

    @Override
    public void setHabitationNumber(String habitationNumber)
    {
        this.habitationNumber = habitationNumber;
    }

    @Override
    public Collection<IInvoiceItem> getInvoiceItems()
    {
        return invoiceItems;
    }

    @Override
    public void setInvoiceItems(Collection<IInvoiceItem> invoiceItems)
    {
        if (invoiceItems != null)
        {
            this.invoiceItems = new LinkedHashSet<IInvoiceItem>(invoiceItems);
        }
    }

    @Override
    public void addInvoiceItems(IInvoiceItem newInvoiceItem)
    {
        if (invoiceItems == null)
        {
            invoiceItems = new LinkedList<IInvoiceItem>();
        }

        invoiceItems.add(newInvoiceItem);
    }

    @Override
    public void addGuests(IGuest guest)
    {
        if (guests == null)
        {
            guests = new LinkedList<IGuest>();
        }

        guests.add(guest);
    }

    @Override
    public Collection<GuestData> getGuestsData()
    {
        return new HelperFunctions<GuestData, IGuest>().castCollectionUp(getGuests());
    }

    @Override
    public RoomData getRoomsData()
    {
        return (RoomData) getRooms();
    }

    @Override
    public UserData getUsersData()
    {
        return (UserData) getUsers();
    }

    @Override
    public Collection<InvoiceItemData> getInvoiceItemsData()
    {
        return new HelperFunctions<InvoiceItemData, IInvoiceItem>().castCollectionUp(getInvoiceItems());
    }

    @Override
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
        for (IGuest g : guests)
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

    public static Collection<IHabitation> searchHabitations(String fName, String lName, String roomId)
    {
        if (roomId == null)
        {
            return ServiceFacade.getInstance().getHabitations(fName, lName);
        }
        if (fName != null && lName != null)
        {
            return ServiceFacade.getInstance().getHabitation(fName, lName, roomId);
        }
        return ServiceFacade.getInstance().getHabitation(roomId);
    }

    public static IHabitation searchHabitation(String roomnr)
    {
        Collection<IHabitation> temp = searchHabitations(null, null, roomnr);
        if (temp == null)
        {
            return null;
        }
        return (Habitation) temp.iterator().next();
    }
    
    public static IHabitation getHabitationById(int id)
    {
        return ServiceFacade.getInstance().getHabitationById(id);
    }

    @Override
    public String getServiceName()
    {
        return this.habitationNumber;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final Habitation other = (Habitation) obj;
        if ((this.habitationNumber == null) ? (other.habitationNumber != null) : !this.habitationNumber.equals(other.habitationNumber))
        {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 37 * hash + (this.habitationNumber != null ? this.habitationNumber.hashCode() : 0);
        return hash;
    }
}
