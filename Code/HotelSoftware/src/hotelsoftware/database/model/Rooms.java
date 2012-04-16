/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.database.model;

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
public class Rooms implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "roomNumber", nullable = false)
    private int roomNumber;
    @JoinTable(name = "roomsroomoptions", joinColumns =
    {
        @JoinColumn(name = "idRoom", referencedColumnName = "id", nullable = false)
    }, inverseJoinColumns =
    {
        @JoinColumn(name = "idOptions", referencedColumnName = "id", nullable = false)
    })
    @ManyToMany
    private Collection<Roomoptions> roomoptionsCollection;
    @JoinColumn(name = "idRoomCategories", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Roomcategories idRoomCategories;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRooms")
    private Collection<Habitations> habitationsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rooms")
    private Collection<Roomsroomstatus> roomsroomstatusCollection;

    public Rooms()
    {
    }

    public Rooms(Integer id)
    {
        this.id = id;
    }

    public Rooms(Integer id, int roomNumber)
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

    public int getRoomNumber()
    {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber)
    {
        this.roomNumber = roomNumber;
    }

    @XmlTransient
    public Collection<Roomoptions> getRoomoptions()
    {
        return roomoptionsCollection;
    }

    public void setRoomoptions(Collection<Roomoptions> roomoptionsCollection)
    {
        this.roomoptionsCollection = roomoptionsCollection;
    }

    public Roomcategories getIdRoomCategories()
    {
        return idRoomCategories;
    }

    public void setIdRoomCategories(Roomcategories idRoomCategories)
    {
        this.idRoomCategories = idRoomCategories;
    }

    @XmlTransient
    public Collection<Habitations> getHabitations()
    {
        return habitationsCollection;
    }

    public void setHabitations(Collection<Habitations> habitationsCollection)
    {
        this.habitationsCollection = habitationsCollection;
    }

    @XmlTransient
    public Collection<Roomsroomstatus> getRoomsroomstatus()
    {
        return roomsroomstatusCollection;
    }

    public void setRoomsroomstatus(Collection<Roomsroomstatus> roomsroomstatusCollection)
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
        if(!(object instanceof Rooms))
        {
            return false;
        }
        Rooms other = (Rooms) object;
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
    
}
