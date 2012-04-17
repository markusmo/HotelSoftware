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
@Table(name = "roomcategories", catalog = "roomanizer", schema = "", uniqueConstraints =
{
    @UniqueConstraint(columnNames =
    {
        "name"
    })
})
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Roomcategories.findAll", query = "SELECT r FROM Roomcategories r"),
    @NamedQuery(name = "Roomcategories.findById", query = "SELECT r FROM Roomcategories r WHERE r.id = :id"),
    @NamedQuery(name = "Roomcategories.findByName", query = "SELECT r FROM Roomcategories r WHERE r.name = :name"),
    @NamedQuery(name = "Roomcategories.findByBedCount", query = "SELECT r FROM Roomcategories r WHERE r.bedCount = :bedCount")
})
public class DBRoomcategories implements Serializable
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
    private Collection<DBReservationitems> reservationitemsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRoomCategories")
    private Collection<DBRooms> roomsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roomcategories")
    private Collection<DBRoomcategoryprices> roomcategorypricesCollection;

    public DBRoomcategories()
    {
    }

    public DBRoomcategories(Integer id)
    {
        this.id = id;
    }

    public DBRoomcategories(Integer id, String name, int bedCount)
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
    public Collection<DBReservationitems> getReservationitems()
    {
        return reservationitemsCollection;
    }

    public void setReservationitems(Collection<DBReservationitems> reservationitemsCollection)
    {
        this.reservationitemsCollection = reservationitemsCollection;
    }

    @XmlTransient
    public Collection<DBRooms> getRooms()
    {
        return roomsCollection;
    }

    public void setRooms(Collection<DBRooms> roomsCollection)
    {
        this.roomsCollection = roomsCollection;
    }

    @XmlTransient
    public Collection<DBRoomcategoryprices> getRoomcategoryprices()
    {
        return roomcategorypricesCollection;
    }

    public void setRoomcategoryprices(Collection<DBRoomcategoryprices> roomcategorypricesCollection)
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
        if(!(object instanceof DBRoomcategories))
        {
            return false;
        }
        DBRoomcategories other = (DBRoomcategories) object;
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
    
}
