package hotelsoftware.model.database.service;

import hotelsoftware.model.database.invoice.DBInvoiceItem;
import hotelsoftware.model.database.parties.DBGuest;
import hotelsoftware.model.database.reservation.DBReservationItem;
import hotelsoftware.model.database.room.DBRoom;
import hotelsoftware.model.database.users.DBUser;
import hotelsoftware.util.HibernateUtil;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.*;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

/**
 * Diese Klasse erbt von Service und bildet Aufenthalte auf die Datenbank ab.
 *
 * @author mohi
 */
@Entity
@Table(name = "habitations", catalog = "`roomanizer-dev`", schema = "")
@PrimaryKeyJoinColumn(name = "idServices", referencedColumnName = "idServices")
public class DBHabitation extends DBService implements Serializable
{
    @Basic(optional = false)
    @Column(name = "habitationNr", nullable = false, length = 255)
    private String habitationNumber;
    @Basic(optional = false)
    @Column(name = "startDate", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date start;
    @Basic(optional = false)
    @Column(name = "endDate", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date end;
    @Basic(optional = false)
    @Column(name = "created", nullable = false, columnDefinition = "timestamp default CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;
    @ManyToMany(mappedBy = "habitations")
    private Set<DBGuest> guests;
    @JoinColumn(name = "idRooms", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private DBRoom rooms;
    @JoinColumn(name = "idUsers", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private DBUser users;
    @OneToMany(mappedBy = "habitation", fetch = FetchType.EAGER)
    private Set<DBInvoiceItem> invoiceItems;

    public DBHabitation()
    {
    }

    public DBHabitation(Date start, Date end, BigDecimal price, Date created)
    {
        this.start = start;
        this.end = end;
        this.price = price;
        this.created = created;
    }

    public BigDecimal getPrice()
    {
        return price;
    }

    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }

    @XmlTransient
    public Collection<DBGuest> getGuests()
    {
        return guests;
    }

    public void setGuests(Collection<DBGuest> guestsCollection)
    {
        if (guestsCollection != null)
        {
            this.guests = new LinkedHashSet<DBGuest>(guestsCollection);
        }
    }

    @XmlTransient
    public Collection<DBInvoiceItem> getInvoiceItems()
    {
        return invoiceItems;
    }

    public void setInvoiceItems(Collection<DBInvoiceItem> invoiceItems)
    {
        if (invoiceItems != null)
        {
            this.invoiceItems = new LinkedHashSet<DBInvoiceItem>(invoiceItems);
        }
    }

    public DBRoom getRooms()
    {
        return rooms;
    }

    public void setRooms(DBRoom idRooms)
    {
        this.rooms = idRooms;
    }

    public DBUser getUsers()
    {
        return users;
    }

    public void setUsers(DBUser idUsers)
    {
        this.users = idUsers;
    }

    public String getHabitationNumber()
    {
        return habitationNumber;
    }

    public void setHabitationNumber(String habitationNumber)
    {
        this.habitationNumber = habitationNumber;
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
        final DBHabitation other = (DBHabitation) obj;
        if ((this.habitationNumber == null) ? (other.habitationNumber != null) : !this.habitationNumber.equals(other.habitationNumber))
        {
            return false;
        }
        if (this.start != other.start && (this.start == null || !this.start.equals(other.start)))
        {
            return false;
        }
        if (this.end != other.end && (this.end == null || !this.end.equals(other.end)))
        {
            return false;
        }
        if (this.created != other.created && (this.created == null || !this.created.equals(other.created)))
        {
            return false;
        }
        if (this.price != other.price && (this.price == null || !this.price.equals(other.price)))
        {
            return false;
        }
        if (this.guests != other.guests && (this.guests == null || !this.guests.equals(other.guests)))
        {
            return false;
        }
        if (this.rooms != other.rooms && (this.rooms == null || !this.rooms.equals(other.rooms)))
        {
            return false;
        }
        if (this.users != other.users && (this.users == null || !this.users.equals(other.users)))
        {
            return false;
        }
        if (this.invoiceItems != other.invoiceItems && (this.invoiceItems == null || !this.invoiceItems.equals(other.invoiceItems)))
        {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 59 * hash + (this.habitationNumber != null ? this.habitationNumber.hashCode() : 0);
        hash = 59 * hash + (this.start != null ? this.start.hashCode() : 0);
        hash = 59 * hash + (this.end != null ? this.end.hashCode() : 0);
        hash = 59 * hash + (this.created != null ? this.created.hashCode() : 0);
        hash = 59 * hash + (this.price != null ? this.price.hashCode() : 0);
        hash = 59 * hash + (this.guests != null ? this.guests.hashCode() : 0);
        hash = 59 * hash + (this.rooms != null ? this.rooms.hashCode() : 0);
        hash = 59 * hash + (this.users != null ? this.users.hashCode() : 0);
        hash = 59 * hash + (this.invoiceItems != null ? this.invoiceItems.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString()
    {
        return "hotelsoftware.database.model.Habitations[ id=" + getIdServices() + " ]";
    }

    public Date getStart()
    {
        return start;
    }

    public void setStart(Date start)
    {
        this.start = start;
    }

    public Date getEnd()
    {
        return end;
    }

    public void setEnd(Date end)
    {
        this.end = end;
    }

    public Date getCreated()
    {
        return created;
    }

    public void setCreated(Date created)
    {
        this.created = created;
    }
}
