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
@PrimaryKeyJoinColumn(name = "idParties", referencedColumnName = "idParties")
public class DBCompany extends DBCustomer implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @Column(name = "name", nullable = false, length = 255)
    private String name;
    
    @JoinTable(name = "companiespersons", joinColumns =
    {
        @JoinColumn(name = "idCompanies", referencedColumnName = "idParties", nullable = false)
    }, inverseJoinColumns =
    {
        @JoinColumn(name = "idParties", referencedColumnName = "idParties", nullable = false)
    })
    @ManyToMany
    private Set<DBParty> contactPersons;
    
    @JoinColumn(name = "idCompanyTypes", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private DBCompanyType companyType;

    public DBCompany()
    {
    }


    public DBCompany(Integer id, String name)
    {
        this.name = name;
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

    public DBCompanyType getCompanyType()
    {
        return companyType;
    }

    public void setCompanyType(DBCompanyType companyType)
    {
        this.companyType = companyType;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (getIdParties() != null ? getIdParties().hashCode() : 0);
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
        if ((this.getIdParties() == null && other.getIdParties() != null)
                || (this.getIdParties() != null && !this.getIdParties().equals(other.getIdParties())))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "hotelsoftware.database.model.Companies[ id=" + getIdParties() + " ]";
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
