package hotelsoftware.model.database.parties;

import hotelsoftware.util.HibernateUtil;
import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
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
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Dies ist die Klasse für die Tabelle "companytypes". Hier werden alle Firmentypen gespeichert.
 *
 * @author mohi
 */
@Entity
@Table(name = "companytypes", catalog = "`roomanizer`", schema = "", uniqueConstraints =
{
    @UniqueConstraint(columnNames =
    {
        "name"
    })
})
@XmlRootElement
public class DBCompanyType implements Serializable
{
    @OneToMany(mappedBy = "companyType")
    private Set<DBCompany> dBCompanyCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name", nullable = false, length = 255)
    private String name;

    public DBCompanyType()
    {
    }

    public DBCompanyType(Integer id)
    {
        this.id = id;
    }

    public DBCompanyType(Integer id, String name)
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
        return "hotelsoftware.database.model.Companytypes[ id=" + id + " ]";
    }

    @XmlTransient
    public Set<DBCompany> getDBCompanyCollection()
    {
        return dBCompanyCollection;
    }

    public void setDBCompanyCollection(Set<DBCompany> dBCompanyCollection)
    {
        this.dBCompanyCollection = dBCompanyCollection;
    }
}
