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
    @OneToMany(mappedBy = "habitation", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
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
    public Collection<DBInvoiceItem> getInvoiceitems()
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

    /**
     * Sucht einen Aufenthalt nach einem Gast und gibt diesen aus
     *
     * @param guest Der Gast, nach dem gesucht wird
     * @return Der Aufenthalt, der dem Gast entspricht
     */
    public static DBHabitation getActualHabitationByGuest(DBGuest guest)
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();
//        Query namedQuery = session.getNamedQuery("Habitations.findByGuest");
//        
//        namedQuery.setString("guestId", guest.getId().toString());
        String query = "Select * "
                + "From habitations h inner join allocation a on h.id = a.idHabitations "
                + "inner join guests g on a.idGuests = g.id "
                + "where g.id = " + guest.getIdParties().toString() + " AND"
                + "h.start >= CURRENT_DATE";
        SQLQuery sqlquery = session.createSQLQuery(query);

        DBHabitation habitation = (DBHabitation) sqlquery.uniqueResult();

        return habitation;
    }

    public static Collection<DBHabitation> search(String fname, String lname)
    {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();
        String query;
        if (fname == null)
        {
            if (lname == null)
            {
                // VOR UND NACHNAME SIND LEER
                query = ""
                        + "SELECT *"
                        + "FROM habitations h"
                        + "INNER JOIN allocations a ON h.id=a.idService"
                        + "INNER JOIN guests g ON g.id=a.idGuests;";
            }
            else
            {
                // NUR VORNAME IST LEER
                query = ""
                        + "SELECT *"
                        + "FROM habitations h"
                        + "INNER JOIN allocations a ON h.id=a.idService"
                        + "INNER JOIN guests g ON g.id=a.idGuests"
                        + "WHERE lname =" + lname + ";";
            }
        }
        else
        {
            if (lname == null)
            {
                //NUR NACHNAME IST LEER
                query = ""
                        + "SELECT *"
                        + "FROM habitations h"
                        + "INNER JOIN allocations a ON h.id=a.idService"
                        + "INNER JOIN guests g ON g.id=a.idGuests"
                        + "WHERE lname =" + lname + ";";
            }
            else
            {
                //NICHTS IST LEER
                query = ""
                        + "SELECT *"
                        + "FROM habitations h"
                        + "INNER JOIN allocations a ON h.id=a.idService"
                        + "INNER JOIN guests g ON g.id=a.idGuests"
                        + "WHERE lname =" + lname + " AND fname=" + fname + ";";
            }
        }


        SQLQuery sqlquery = session.createSQLQuery(query);


        //addEntity gibt den rueckgabewert an...
        sqlquery = sqlquery.addEntity(DBHabitation.class);
        List<DBHabitation> retList = sqlquery.list();
        if (retList == null)
        {
            return new LinkedHashSet();
        }
        return new LinkedHashSet<DBHabitation>(retList);
    }

    public static Collection<DBHabitation> search(Integer roomnr)
    {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();
       /* String query = "SELECT * "
                + "FROM habitations h "
                + "INNER JOIN rooms r ON h.idRooms = r.id "
                + "WHERE r.roomNumber ='" + roomnr.toString() + "'";

        SQLQuery sqlquery = session.createSQLQuery(query);

        //addEntity gibt den rueckgabewert an...
        sqlquery = sqlquery.addEntity(DBHabitation.class);
        List<DBHabitation> retList = sqlquery.list();*/
        
        //Criteria criteria = session.createCriteria(DBHabitation.class);
        //criteria = criteria.add(Restrictions.eq("rooms", 1));
        
        Query q = session.createQuery("SELECT h FROM DBHabitation as h INNER JOIN h.rooms as r WHERE r.number = :number");
        q = q.setString("number", roomnr.toString());

        List<DBHabitation> retList = q.list();
        
        if (retList == null)
        {
            return new LinkedHashSet();
        }
        return new LinkedHashSet<DBHabitation>(retList);
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
