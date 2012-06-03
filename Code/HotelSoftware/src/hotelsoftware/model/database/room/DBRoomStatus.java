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
@Table(name = "roomstatus", catalog = "`roomanizer`", schema = "", uniqueConstraints =
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
