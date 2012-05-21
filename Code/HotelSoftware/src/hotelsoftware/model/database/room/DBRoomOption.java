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
@Table(name = "roomoptions", catalog = "`roomanizer-dev`", schema = "", uniqueConstraints =
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
    public String toString()
    {
        return "hotelsoftware.database.model.Roomoptions[ id=" + id + " ]";
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
