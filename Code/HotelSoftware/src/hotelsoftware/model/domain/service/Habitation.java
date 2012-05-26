package hotelsoftware.model.domain.service;

import hotelsoftware.controller.data.invoice.InvoiceItemData;
import hotelsoftware.controller.data.parties.GuestData;
import hotelsoftware.controller.data.room.RoomData;
import hotelsoftware.controller.data.service.ServiceTypeData;
import hotelsoftware.controller.data.users.UserData;
import hotelsoftware.model.database.manager.ServiceManager;
import hotelsoftware.model.domain.invoice.IInvoiceItem;
import hotelsoftware.model.domain.parties.IGuest;
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
    
    public static Collection<IHabitation> getHabitationsByDate(Date date)
    {
        return ServiceManager.getInstance().getHabitationsByDate(date);
    }
    
    public static Collection<IHabitation> getAllHabitations()
    {
        return ServiceManager.getInstance().getAllHabitations();
    }

    public static int getHighestId()
    {
        return ServiceManager.getInstance().getHighestHabitationId();
    }

    @Override
    public Date getStart()
    {
        return start;
    }

    @Override
    public void setStart(Date start)
    {
        this.start = start;
    }

    @Override
    public Date getEnd()
    {
        return end;
    }

    @Override
    public void setEnd(Date end)
    {
        this.end = end;
    }

    @Override
    public BigDecimal getPrice()
    {
        return price;
    }
    
    @Override
    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }

    @Override
    public Date getCreated()
    {
        return created;
    }

    @Override
    public void setCreated(Date created)
    {
        this.created = created;
    }

    @Override
    public Collection<IGuest> getGuests()
    {
        return guests;
    }

    @Override
    public void setGuests(Collection<IGuest> guests)
    {
        this.guests = guests;
    }

    @Override
    public IRoom getRooms()
    {
        return rooms;
    }

    @Override
    public void setRooms(IRoom rooms)
    {
        this.rooms = rooms;
    }

    @Override
    public IUser getUsers()
    {
        return users;
    }

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

    /**
     * Sucht nach Aufenthalten
     * @param fName Der Vorname eines zum Aufenthalt gehörenden Gastes
     * @param lName Der Nachname eines zum Aufenthalten gehörenden Gastes
     * @param roomNr Die Zimmernummer des zum Aufenthalt gehörenden Zimmers
     * @return Die gefundenen Aufenthalte
     */
    public static Collection<IHabitation> searchHabitations(String fName, String lName, String roomNr)
    {
        Collection<IHabitation> habitations = new LinkedList<IHabitation>();
        
        if (roomNr != null)
        {
           habitations.addAll(ServiceManager.getInstance().searchHabitationsByNumber(roomNr));
        }
        if (fName != null && lName != null)
        {
             habitations.addAll(ServiceManager.getInstance().searchHabitations(fName, lName, roomNr));
        }
        
        return habitations;
        
    }
    
    public static IHabitation getHabitationById(int id)
    {
        return ServiceManager.getInstance().getHabitationById(id);
    }

    @Override
    public String getServiceName()
    {
        return "Room: " + this.rooms.getNumber();
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
