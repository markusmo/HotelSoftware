/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.database.room;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mohi
 */
@Entity
@Table(name = "roomstatus", catalog = "roomanizer", schema = "", uniqueConstraints =
{
    @UniqueConstraint(columnNames =
    {
        "name"
    })
})
@XmlRootElement
//@NamedQueries(
//{
//    @NamedQuery(name = "Roomstatus.findAll", query = "SELECT r FROM Roomstatus r"),
//    @NamedQuery(name = "Roomstatus.findById", query = "SELECT r FROM Roomstatus r WHERE r.id = :id"),
//    @NamedQuery(name = "Roomstatus.findByName", query = "SELECT r FROM Roomstatus r WHERE r.name = :name")
//})
public class DBRoomStatus implements Serializable
{
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roomstatus", fetch= FetchType.LAZY)
    private Collection<DBRoomsRoomStatus> dBRoomsRoomStatusCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name", nullable = false, length = 255)
    private String name;

    public DBRoomStatus()
    {
    }

    public DBRoomStatus(Integer id)
    {
        this.id = id;
    }

    public DBRoomStatus(Integer id, String name)
    {
        this.id = id;
        this.name = name;
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
        if(!(object instanceof DBRoomStatus))
        {
            return false;
        }
        DBRoomStatus other = (DBRoomStatus) object;
        if((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "hotelsoftware.database.model.Roomstatus[ id=" + id + " ]";
    }

    @XmlTransient
    public Collection<DBRoomsRoomStatus> getDBRoomsRoomStatusCollection()
    {
        return dBRoomsRoomStatusCollection;
    }

    public void setDBRoomsRoomStatusCollection(Collection<DBRoomsRoomStatus> dBRoomsRoomStatusCollection)
    {
        this.dBRoomsRoomStatusCollection = dBRoomsRoomStatusCollection;
    }
    
}
