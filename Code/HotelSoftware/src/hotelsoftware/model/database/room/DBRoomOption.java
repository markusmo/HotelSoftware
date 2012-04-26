package hotelsoftware.model.database.room;

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

/**
 * Diese Klasse bildet die Optionen(Raucherzimmer, Tiere erlaubt ...) auf ein Zimmer in der Datenbank ab.
 * @author mohi
 */
@Entity
@Table(name = "roomoptions", catalog = "roomanizer", schema = "", uniqueConstraints =
{
    @UniqueConstraint(columnNames =
    {
        "name"
    })
})
@XmlRootElement
public class DBRoomOption implements Serializable
{
    @ManyToMany(mappedBy = "options", cascade= CascadeType.ALL, fetch= FetchType.EAGER)
    private Set<DBRoom> dBRoomCollection;
    
    private static final long serialVersionUID = 1L;

    public static void safeNewRoomOption(String name)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public static void getRoomoptions()
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name", nullable = false, length = 255)
    private String name;

    public DBRoomOption()
    {
    }

    public DBRoomOption(Integer id)
    {
        this.id = id;
    }

    public DBRoomOption(Integer id, String name)
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
        if(!(object instanceof DBRoomOption))
        {
            return false;
        }
        DBRoomOption other = (DBRoomOption) object;
        if((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "hotelsoftware.database.model.Roomoptions[ id=" + id + " ]";
    }
    
    /**
     * Gibt alle Optionen aus
     * @return 
     * Gibt ein Set mit den Zimmeroptionen aus, die verfuegbar sind
     */
    public static Set<DBRoomOption> getRoomOptions()
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();
        
        List<DBRoomOption> options = session.createCriteria(DBRoomOption.class).list();
        
        ;
        
        return new LinkedHashSet<DBRoomOption>(options);
    }

    @XmlTransient
    public Set<DBRoom> getDBRoomCollection()
    {
        return dBRoomCollection;
    }

    public void setDBRoomCollection(Set<DBRoom> dBRoomCollection)
    {
        this.dBRoomCollection = dBRoomCollection;
    }
}
