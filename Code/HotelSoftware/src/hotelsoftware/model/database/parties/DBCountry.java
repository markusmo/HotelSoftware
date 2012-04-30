package hotelsoftware.model.database.parties;

import hotelsoftware.model.database.reservation.DBReservation;
import hotelsoftware.util.HibernateUtil;
import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
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
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *Dies ist die Klasse für die Tabelle "countries". Hier werden alle Laender gespeichert.
 * @author mohi
 */
@Entity
@Table(name = "countries", catalog = "roomanizer", schema = "", uniqueConstraints =
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCountry")
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
        if(!(object instanceof DBCountry))
        {
            return false;
        }
        DBCountry other = (DBCountry) object;
        if((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
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
    /**
     * Diese Mthode sucht alle Länder aus der Datenbank
     * @return 
     * Eine Liste mit allen Ländern
     */
    public static Collection<DBCountry> getAllCountries()
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();

        String query = "SELECT * FROM countries c ORDER BY name ASC";
        SQLQuery sqlquery = session.createSQLQuery(query);
        sqlquery.addEntity(DBCountry.class);

        return sqlquery.list();
    }
}
