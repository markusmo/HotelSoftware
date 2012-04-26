package hotelsoftware.model.database.room;

import hotelsoftware.model.database.reservation.DBReservationItem;
import hotelsoftware.util.HibernateUtil;
import java.io.Serializable;
import java.util.*;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 * Diese Klasse bildet die Zimmerkategorie auf die Datenbank ab.
 * @author mohi
 */
@Entity
@Table(name = "roomcategories", catalog = "roomanizer", schema = "", uniqueConstraints =
{
    @UniqueConstraint(columnNames =
    {
        "name"
    })
})
@XmlRootElement
public class DBRoomCategory implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    
    @Basic(optional = false)
    @Column(name = "name", nullable = false, length = 255)
    private String name;
    
    @Basic(optional = false)
    @Column(name = "bedCount", nullable = false)
    private Integer bedCount;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roomcategories")
    private Set<DBReservationItem> reservationitems;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category", fetch= FetchType.EAGER)
    private Set<DBRoom> rooms;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roomcategories", fetch= FetchType.EAGER)
    private Set<DBRoomCategoryPrice> price;

    public DBRoomCategory()
    {
    }

    public DBRoomCategory(Integer id)
    {
        this.id = id;
    }

    public DBRoomCategory(Integer id, String name, int bedCount)
    {
        this.id = id;
        this.name = name;
        this.bedCount = bedCount;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Integer getBedCount()
    {
        return bedCount;
    }

    public void setBedCount(int bedCount)
    {
        this.bedCount = bedCount;
    }

    @XmlTransient
    public Collection<DBReservationItem> getReservationitems()
    {
        return reservationitems;
    }

    public void setReservationitems(Collection<DBReservationItem> reservationitems)
    {
        this.reservationitems = new LinkedHashSet(reservationitems);
    }

    @XmlTransient
    public Collection<DBRoom> getRooms()
    {
        return rooms;
    }

    public void setRooms(Collection<DBRoom> rooms)
    {
        this.rooms = new LinkedHashSet(rooms);
    }

    @XmlTransient
    public Collection<DBRoomCategoryPrice> getPrice()
    {
        return price;
    }

    public void setPrice(Collection<DBRoomCategoryPrice> prices)
    {
        this.price = new LinkedHashSet(prices);
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if(!(object instanceof DBRoomCategory))
        {
            return false;
        }
        DBRoomCategory other = (DBRoomCategory) object;
        if((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "hotelsoftware.database.model.Roomcategories[ id=" + id + " ]";
    }
    
    public static DBRoomCategory getRoomCategoryByName(String name)
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        
        DBRoomCategory cats = (DBRoomCategory) session.createCriteria(DBRoomCategory.class).add(Restrictions.eq("name", name)).uniqueResult();
        ;
        return cats;
    }
    
    /**
     * Diese Methode liefert alle Zimmerkategorieen aus
     * @return
     * Alle verfuebaren Kategorieen
     */
    public static Collection<DBRoomCategory> getAllCategories()
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        
        List<DBRoomCategory> cats = session.createCriteria(DBRoomCategory.class).list();
        ;
        return new LinkedHashSet<DBRoomCategory>(cats);
    }
    
    /**
     * Sucht allen freien Zimmer einer Zeitspanne
     * @param start
     * Der Start der Zeitspanne
     * @param ende
     * Das Ende der Zeitspanne
     * @return
     * Ein Set von Zimmern, die in der angegebenen Zeitspanne frei sind.
     */
    public Set<DBRoom> getFreeRooms(Date start, Date ende)
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        
        
        SQLQuery query = session.createSQLQuery("SELECT * FROM rooms r WHERE "
                + "(SELECT count(*) FROM habitations h WHERE h.startDate < :end AND h.endDate > :start and h.idRooms = r.id) = 0 AND idRoomCategories = :id");
        query.setDate("end", ende);
        query.setDate("start", start);
        query.setInteger("id", id);
        
        query.addEntity(DBRoom.class);
         
        List<DBRoom> rooms = query.list();
        
        //TODO
        //;
        return new LinkedHashSet<DBRoom>(rooms);
    }
    
}
