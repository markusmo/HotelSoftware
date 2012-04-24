/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.database.parties;

import hotelsoftware.util.HibernateUtil;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 * Dies ist die Klasse für die Tabelle "companies". Hier werden alle Firmen gespeichert.
 *
 * @author mohi
 */
@Entity
@Table(name = "companies", catalog = "roomanizer", schema = "")
@XmlRootElement
//@PrimaryKeyJoinColumn(name = "idCustomers", referencedColumnName = "id")
public class DBCompany implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false, insertable=false, updatable=false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name", nullable = false, length = 255)
    private String name;
    @JoinTable(name = "companiespersons", joinColumns =
    {
        @JoinColumn(name = "idCompanies", referencedColumnName = "id", nullable = false)
    }, inverseJoinColumns =
    {
        @JoinColumn(name = "idPersons", referencedColumnName = "id", nullable = false)
    })
    @ManyToMany
    private Set<DBParty> contactPersons;
    @JoinColumn(name = "idCompanyTypes", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private DBCompanyType idCompanyTypes;

    public DBCompany()
    {
    }

    public DBCompany(Integer id)
    {
        this.id = id;
    }

    public DBCompany(Integer id, String name)
    {
        this.id = id;
        this.name = name;
    }

    //@Override
    public Integer getId()
    {
        return this.id;
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
    public Set<DBParty> getContactPersons()
    {
        return contactPersons;
    }

    public void setContactPersons(Set<DBParty> contactPersons)
    {
        this.contactPersons = contactPersons;
    }

    public DBCompanyType getIdCompanyTypes()
    {
        return idCompanyTypes;
    }

    public void setIdCompanyTypes(DBCompanyType idCompanyTypes)
    {
        this.idCompanyTypes = idCompanyTypes;
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
        // TODO: Warning - this method won't work in the case the id fields are
        // not set
        if (!(object instanceof DBCompany))
        {
            return false;
        }
        DBCompany other = (DBCompany) object;
        if ((this.id == null && other.id != null)
                || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "hotelsoftware.database.model.Companies[ id=" + id + " ]";
    }

    /**
     * Diese Methode sucht nach einer Firma anhand eines Namens.
     *
     * @param name Dies ist der Name der Firma
     * @return Es wird ein Objekt der Klasse DBCompany zurückgegeben
     * @throws HibernateException Dieser Fehler wird geworfen, falls ein Problem mit Hibernate besteht.
     */
    public static DBCompany getCompanyByName(String name)
            throws HibernateException
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();
        Criteria criteria = session.createCriteria(DBCompany.class).add(Restrictions.eq(
                "name", name));
        return (DBCompany) criteria.uniqueResult();
    }
}
