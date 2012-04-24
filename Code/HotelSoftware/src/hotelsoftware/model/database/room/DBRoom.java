/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.database.room;

import hotelsoftware.model.database.service.DBHabitation;
import hotelsoftware.util.HibernateUtil;
import java.io.Serializable;
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
 *
 * @author mohi
 */
@Entity
@Table(name = "rooms", catalog = "roomanizer", schema = "", uniqueConstraints =
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
    
    @ManyToMany(cascade = CascadeType.ALL)
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
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRooms")
    private Set<DBHabitation> habitations;
    
    @OneToMany(mappedBy = "rooms", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
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
    public Set<DBRoomOption> getOptions()
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
    public Set<DBHabitation> getHabitations()
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
        if (!(object instanceof DBRoom))
        {
            return false;
        }
        DBRoom other = (DBRoom) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "hotelsoftware.database.model.Rooms[ id=" + id + " ]";
    }

    public static DBRoom getRoomByNumber(String number)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction ts = session.beginTransaction();
        ts.begin();

        DBRoom room = (DBRoom) session.createCriteria(DBRoom.class).add(Restrictions.eq("number", number)).uniqueResult();

        session.close();
        return room;
    }

    public static Set<DBRoom> getRoomsByCategory(DBRoomCategory cat)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction ts = session.beginTransaction();
        ts.begin();

        List<DBRoom> rooms = session.createCriteria(DBRoom.class).add(Restrictions.eq("idRoomCategories", cat)).list();

        session.close();
        return new LinkedHashSet<DBRoom>(rooms);
    }
}
