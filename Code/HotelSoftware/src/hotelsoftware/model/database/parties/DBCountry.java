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
@Table(name = "countries", catalog = "`roomanizer`", schema = "", uniqueConstraints =
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
