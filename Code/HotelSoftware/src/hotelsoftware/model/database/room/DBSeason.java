package hotelsoftware.model.database.room;

import hotelsoftware.model.database.room.DBRoomCategoryPrice;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Diese Klasse bildet eine Hotelsaison auf der Datenbank ab.
 * @author mohi
 */
@Entity
@Table(name = "seasons", catalog = "`roomanizer-dev`", schema = "", uniqueConstraints =
{
    @UniqueConstraint(columnNames =
    {
        "name"
    })
})
@XmlRootElement
public class DBSeason implements Serializable
{
    @Basic(optional = false)
    @Column(name = "startDate", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date start;
    
    @Basic(optional = false)
    @Column(name = "endDate", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date end;
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    
    @Basic(optional = false)
    @Column(name = "name", nullable = false, length = 255)
    private String name;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "seasons", fetch = FetchType.EAGER)
    private Set<DBRoomCategoryPrice> roomcategoryprices;

    public DBSeason()
    {
    }

    public DBSeason(Integer id)
    {
        this.id = id;
    }

    public DBSeason(Integer id, String name, Date start, Date end)
    {
        this.id = id;
        this.name = name;
        this.start = start;
        this.end = end;
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

    @XmlTransient
    public Set<DBRoomCategoryPrice> getRoomcategoryprices()
    {
        return roomcategoryprices;
    }

    public void setRoomcategoryprices(Set<DBRoomCategoryPrice> roomcategorypricesCollection)
    {
        this.roomcategoryprices = roomcategorypricesCollection;
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
        final DBSeason other = (DBSeason) obj;
        if (this.start != other.start && (this.start == null || !this.start.equals(other.start)))
        {
            return false;
        }
        if (this.end != other.end && (this.end == null || !this.end.equals(other.end)))
        {
            return false;
        }
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id)))
        {
            return false;
        }
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name))
        {
            return false;
        }
        if (this.roomcategoryprices != other.roomcategoryprices && (this.roomcategoryprices == null || !this.roomcategoryprices.equals(other.roomcategoryprices)))
        {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 29 * hash + (this.start != null ? this.start.hashCode() : 0);
        hash = 29 * hash + (this.end != null ? this.end.hashCode() : 0);
        hash = 29 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 29 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 29 * hash + (this.roomcategoryprices != null ? this.roomcategoryprices.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString()
    {
        return "hotelsoftware.database.model.Seasons[ id=" + id + " ]";
    }

    public Date getStart()
    {
        return start;
    }

    public void setStart(Date start)
    {
        this.start = start;
    }

    public Date getEnd()
    {
        return end;
    }

    public void setEnd(Date end)
    {
        this.end = end;
    }
    
}
