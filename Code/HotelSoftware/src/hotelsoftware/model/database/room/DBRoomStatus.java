package hotelsoftware.model.database.room;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Bildet einen Status auf ein Zimmer in der Datenbank ab.
 * @author mohi
 */
@Entity
@Table(name = "roomstatus", catalog = "`roomanizer-dev`", schema = "", uniqueConstraints =
{
    @UniqueConstraint(columnNames =
    {
        "name"
    })
})
@XmlRootElement
public class DBRoomStatus implements Serializable
{
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roomstatus", fetch= FetchType.EAGER)
    private Set<DBRoomsRoomStatus> dBRoomsRoomStatusCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name", nullable = false, length = 255)
    private String statusName;

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
        this.statusName = name;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getStatusName()
    {
        return statusName;
    }

    public void setStatusName(String name)
    {
        this.statusName = name;
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
        final DBRoomStatus other = (DBRoomStatus) obj;
        if (this.dBRoomsRoomStatusCollection != other.dBRoomsRoomStatusCollection && (this.dBRoomsRoomStatusCollection == null || !this.dBRoomsRoomStatusCollection.equals(other.dBRoomsRoomStatusCollection)))
        {
            return false;
        }
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id)))
        {
            return false;
        }
        if ((this.statusName == null) ? (other.statusName != null) : !this.statusName.equals(other.statusName))
        {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 23 * hash + (this.dBRoomsRoomStatusCollection != null ? this.dBRoomsRoomStatusCollection.hashCode() : 0);
        hash = 23 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 23 * hash + (this.statusName != null ? this.statusName.hashCode() : 0);
        return hash;
    }


    @Override
    public String toString()
    {
        return "hotelsoftware.database.model.Roomstatus[ id=" + id + " ]";
    }

    @XmlTransient
    public Set<DBRoomsRoomStatus> getDBRoomsRoomStatusCollection()
    {
        return dBRoomsRoomStatusCollection;
    }

    public void setDBRoomsRoomStatusCollection(Set<DBRoomsRoomStatus> dBRoomsRoomStatusCollection)
    {
        this.dBRoomsRoomStatusCollection = dBRoomsRoomStatusCollection;
    }
    
}
