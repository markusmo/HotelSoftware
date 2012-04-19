/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.database.room;

import hotelsoftware.model.database.service.DBHabitation;
import hotelsoftware.util.HibernateUtil;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
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
@NamedQueries(
{
    @NamedQuery(name = "Rooms.findAll", query = "SELECT r FROM Rooms r"),
    @NamedQuery(name = "Rooms.findById", query = "SELECT r FROM Rooms r WHERE r.id = :id"),
    @NamedQuery(name = "Rooms.findByRoomNumber", query = "SELECT r FROM Rooms r WHERE r.roomNumber = :roomNumber")
})
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
    private String roomNumber;
    @JoinTable(name = "roomsroomoptions", joinColumns =
    {
        @JoinColumn(name = "idRoom", referencedColumnName = "id", nullable = false)
    }, inverseJoinColumns =
    {
        @JoinColumn(name = "idOptions", referencedColumnName = "id", nullable = false)
    })
    @ManyToMany
    private Collection<DBRoomoption> roomoptionsCollection;
    @JoinColumn(name = "idRoomCategories", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private DBRoomcategory idRoomCategories;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRooms")
    private Collection<DBHabitation> habitationsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rooms")
    private Collection<DBRoomsroomstatus> roomsroomstatusCollection;

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
        this.roomNumber = roomNumber;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getRoomNumber()
    {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber)
    {
        this.roomNumber = roomNumber;
    }

    @XmlTransient
    public Collection<DBRoomoption> getRoomoptions()
    {
        return roomoptionsCollection;
    }

    public void setRoomoptions(Collection<DBRoomoption> roomoptionsCollection)
    {
        this.roomoptionsCollection = roomoptionsCollection;
    }

    public DBRoomcategory getIdRoomCategories()
    {
        return idRoomCategories;
    }

    public void setIdRoomCategories(DBRoomcategory idRoomCategories)
    {
        this.idRoomCategories = idRoomCategories;
    }

    @XmlTransient
    public Collection<DBHabitation> getHabitations()
    {
        return habitationsCollection;
    }

    public void setHabitations(Collection<DBHabitation> habitationsCollection)
    {
        this.habitationsCollection = habitationsCollection;
    }

    @XmlTransient
    public Collection<DBRoomsroomstatus> getRoomsroomstatus()
    {
        return roomsroomstatusCollection;
    }

    public void setRoomsroomstatus(Collection<DBRoomsroomstatus> roomsroomstatusCollection)
    {
        this.roomsroomstatusCollection = roomsroomstatusCollection;
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
        if(!(object instanceof DBRoom))
        {
            return false;
        }
        DBRoom other = (DBRoom) object;
        if((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
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
    
    public static DBRoom getRoomByNumber(int number)
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();
        
        DBRoom room = (DBRoom) session.createCriteria(DBRoom.class).add(Restrictions.eq("number",number)).uniqueResult();
        
        session.close();
        return room;
    }
    
    public static Collection<DBRoom> getRoomsByCategory(DBRoomcategory cat)
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();
        
        Collection<DBRoom> rooms = session.createCriteria(DBRoom.class).add(Restrictions.eq("idRoomCategories", cat)).list();
        
        session.close();
        return rooms;
    }
}
