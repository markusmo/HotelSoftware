/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.database.room;

import hotelsoftware.model.database.reservation.DBReservationItem;
import hotelsoftware.util.HibernateUtil;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
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
/*@NamedQueries(
{
    @NamedQuery(name = "Roomcategories.findAll", query = "SELECT r FROM Roomcategories r"),
    @NamedQuery(name = "Roomcategories.findById", query = "SELECT r FROM Roomcategories r WHERE r.id = :id"),
    @NamedQuery(name = "Roomcategories.findByName", query = "SELECT r FROM Roomcategories r WHERE r.name = :name"),
    @NamedQuery(name = "Roomcategories.findByBedCount", query = "SELECT r FROM Roomcategories r WHERE r.bedCount = :bedCount")
})*/
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
    private int bedCount;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roomcategories")
    private Collection<DBReservationItem> reservationitemsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRoomCategories", fetch= FetchType.LAZY)
    private Collection<DBRoom> roomsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roomcategories")
    private Collection<DBRoomCategoryPrice> roomcategorypricesCollection;

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

    public int getBedCount()
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
        return reservationitemsCollection;
    }

    public void setReservationitems(Collection<DBReservationItem> reservationitemsCollection)
    {
        this.reservationitemsCollection = reservationitemsCollection;
    }

    @XmlTransient
    public Collection<DBRoom> getRooms()
    {
        return roomsCollection;
    }

    public void setRooms(Collection<DBRoom> roomsCollection)
    {
        this.roomsCollection = roomsCollection;
    }

    @XmlTransient
    public Collection<DBRoomCategoryPrice> getRoomcategoryprices()
    {
        return roomcategorypricesCollection;
    }

    public void setRoomcategoryprices(Collection<DBRoomCategoryPrice> roomcategorypricesCollection)
    {
        this.roomcategorypricesCollection = roomcategorypricesCollection;
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
        session.close();
        return cats;
    }
    
    public static Collection<DBRoomCategory> getAllCategories()
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        
        Collection<DBRoomCategory> cats = session.createCriteria(DBRoomCategory.class).list();
        session.close();
        return cats;
    }
    
    public static Collection<DBRoom> getFreeRooms(DBRoomCategory category, Date start, Date ende)
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        
        Collection<DBRoom> rooms = session.createCriteria(DBRoom.class)
                .setFetchMode("DBHabtiation", FetchMode.JOIN)
                .add(Restrictions.and(Restrictions.not(Restrictions.eq("start", start)), Restrictions.not(Restrictions.eq("end", ende))))
                .add(Restrictions.eq("ididRoomCategories", category))
                .list();
        
        session.close();
        return rooms;
    }
    
}
