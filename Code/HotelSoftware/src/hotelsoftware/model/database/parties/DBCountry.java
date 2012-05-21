package hotelsoftware.model.database.parties;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *Dies ist die Klasse für die Tabelle "countries". Hier werden alle Laender gespeichert.
 * @author mohi
 */
@Entity
@Table(name = "countries", catalog = "`roomanizer-dev`", schema = "", uniqueConstraints =
{
    @UniqueConstraint(columnNames =
    {
        "name"
    }),
    @UniqueConstraint(columnNames =
    {
        "nameShort"
    })
})
@XmlRootElement
public class DBCountry implements Serializable
{
    @OneToMany(mappedBy = "idCountry")
    private Set<DBAddress> dBAddressCollection;
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
    @Column(name = "nameShort", nullable = false, length = 2)
    private String nameShort;

    public DBCountry()
    {
    }

    public DBCountry(Integer id)
    {
        this.id = id;
    }

    public DBCountry(Integer id, String name, String nameShort)
    {
        this.id = id;
        this.name = name;
        this.nameShort = nameShort;
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

    public String getNameShort()
    {
        return nameShort;
    }

    public void setNameShort(String nameShort)
    {
        this.nameShort = nameShort;
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
        final DBCountry other = (DBCountry) obj;
        if (this.dBAddressCollection != other.dBAddressCollection && (this.dBAddressCollection == null || !this.dBAddressCollection.equals(other.dBAddressCollection)))
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
        if ((this.nameShort == null) ? (other.nameShort != null) : !this.nameShort.equals(other.nameShort))
        {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 61 * hash + (this.dBAddressCollection != null ? this.dBAddressCollection.hashCode() : 0);
        hash = 61 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 61 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 61 * hash + (this.nameShort != null ? this.nameShort.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString()
    {
        return "hotelsoftware.database.model.Countries[ id=" + id + " ]";
    }

    @XmlTransient
    public Set<DBAddress> getDBAddressCollection()
    {
        return dBAddressCollection;
    }

    public void setDBAddressCollection(Set<DBAddress> dBAddressCollection)
    {
        this.dBAddressCollection = dBAddressCollection;
    }
}
