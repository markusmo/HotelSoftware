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
    public int hashCode()
    {
        int hash = 0;
        hash += (getIdServices() != null ? getIdServices().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DBHabitation))
        {
            return false;
        }
        DBHabitation other = (DBHabitation) object;
        if ((this.getIdServices() == null && other.getIdServices() != null) || (this.getIdServices() != null && !this.getIdServices().equals(other.getIdServices())))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "hotelsoftware.database.model.Habitations[ id=" + getIdServices() + " ]";
    }

    public static int getHighestId()
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();

        String query = "Select max(idServices) FROM habitations h";
        SQLQuery sqlquery = session.createSQLQuery(query);


        Integer bd = (Integer) sqlquery.uniqueResult();

        if (bd != null)
        {
            return bd;
        }
        else
        {
            return 0;
        }
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
