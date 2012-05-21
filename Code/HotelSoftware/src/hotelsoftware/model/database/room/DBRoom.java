package hotelsoftware.model.database.room;

import hotelsoftware.model.database.service.DBHabitation;
import hotelsoftware.util.HibernateUtil;
import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 * Diese Klasse bildet einen Raum in der Datenbank ab.
 * @author mohi
 */
@Entity
@Table(name = "rooms", catalog = "`roomanizer-dev`", schema = "", uniqueConstraints =
{
    @UniqueConstraint(columnNames =
    {
        "roomNumber"
    })
})
@XmlRootElement
public class DBRoom implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    
    @Basic(optional = false)
    @Column(name = "roomNumber", nullable = false)
    private String number;
    
    @ManyToMany(cascade = CascadeType.ALL, fetch= FetchType.EAGER)
    @JoinTable(name = "roomsroomoptions", joinColumns =
    {
        @JoinColumn(name = "idRooms", referencedColumnName = "id")
    }, inverseJoinColumns =
    {
        @JoinColumn(name = "idOptions", referencedColumnName = "id")
    })
    private Set<DBRoomOption> options;
    
    @JoinColumn(name = "idRoomCategories", referencedColumnName = "id", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private DBRoomCategory category;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rooms")
    private Set<DBHabitation> habitations;
    
    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<DBRoomsRoomStatus> status;

    public DBRoom()
    {
    }

    public DBRoom(Integer id)
    {
        this.id = id;
    }

    public DBRoom(Integer id, String roomNumber)
    {
        this.id = id;
        this.number = roomNumber;
    }

    @XmlTransient
    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getNumber()
    {
        return number;
    }

    public void setNumber(String roomNumber)
    {
        this.number = roomNumber;
    }

    @XmlTransient
    public Collection<DBRoomOption> getOptions()
    {
        return options;
    }

    public void setOptions(Set<DBRoomOption> roomoptionsCollection)
    {
        this.options = roomoptionsCollection;
    }

    public DBRoomCategory getCategory()
    {
        return category;
    }

    public void setCategory(DBRoomCategory category)
    {
        this.category = category;
    }

    @XmlTransient
    public Collection<DBHabitation> getHabitations()
    {
        return habitations;
    }

    public void setHabitations(Set<DBHabitation> habitationsCollection)
    {
        this.habitations = habitationsCollection;
    }

    @XmlTransient
    public Set<DBRoomsRoomStatus> getStatus()
    {
        return status;
    }

    public void setStatus(Set<DBRoomsRoomStatus> roomsroomstatusCollection)
    {
        this.status = roomsroomstatusCollection;
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
        final DBRoom other = (DBRoom) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id)))
        {
            return false;
        }
        if ((this.number == null) ? (other.number != null) : !this.number.equals(other.number))
        {
            return false;
        }
        if (this.options != other.options && (this.options == null || !this.options.equals(other.options)))
        {
            return false;
        }
        if (this.category != other.category && (this.category == null || !this.category.equals(other.category)))
        {
            return false;
        }
        if (this.habitations != other.habitations && (this.habitations == null || !this.habitations.equals(other.habitations)))
        {
            return false;
        }
        if (this.status != other.status && (this.status == null || !this.status.equals(other.status)))
        {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 43 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 43 * hash + (this.number != null ? this.number.hashCode() : 0);
        hash = 43 * hash + (this.options != null ? this.options.hashCode() : 0);
        hash = 43 * hash + (this.category != null ? this.category.hashCode() : 0);
        hash = 43 * hash + (this.habitations != null ? this.habitations.hashCode() : 0);
        hash = 43 * hash + (this.status != null ? this.status.hashCode() : 0);
        return hash;
    }
    

    @Override
    public String toString()
    {
        return "hotelsoftware.database.model.Rooms[ id=" + id + " ]";
    }

//    /**
//     * Gibt einen Raum nach der angegebenen Zimmernummer aus
//     * @param number
//     * Die Zimmernummer, nach der gesucht wird
//     * @return 
//     * Das Zimmer mit der Zimmernummer.
//     */
//    public static DBRoom getRoomByNumber(String number)
//    {
//        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//        Transaction ts = session.beginTransaction();
//        ts.begin();
//
//        DBRoom room = (DBRoom) session.createCriteria(DBRoom.class).add(Restrictions.eq("number", number)).uniqueResult();
//
//        return room;
//    }
//
//    /**
//     * Sucht Zimmer nach einer Kategorie und gibt diese aus
//     * @param cat
//     * Die Kategorie, nach der gesucht wird
//     * @return 
//     * Gibt eine Collection mit den Raeumen, der gesuchten Kategorie
//     */
//    public static Collection<DBRoom> getRoomsByCategory(DBRoomCategory cat)
//    {
//        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//        Transaction ts = session.beginTransaction();
//        ts.begin();
//
//        List<DBRoom> rooms = session.createCriteria(DBRoom.class).add(Restrictions.eq("idRoomCategories", cat)).list();
//
//        return new LinkedHashSet<DBRoom>(rooms);
//    }
}
