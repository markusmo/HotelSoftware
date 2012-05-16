package hotelsoftware.model.database.parties;

import hotelsoftware.util.HibernateUtil;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *Dies ist die Klasse für die Tabelle "privatecustomers". Hier werden alle Privatkunden gespeichert.
 * @author mohi
 */
@Entity
@Table(name = "privatePersons", catalog = "`roomanizer-dev`", schema = "")
@XmlRootElement
@PrimaryKeyJoinColumn(name="idParties", referencedColumnName="idParties")
public class DBPrivateCustomer extends DBCustomer implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @Column(name = "fname", nullable = false, length = 255)
    private String fname;
    
    @Basic(optional = false)
    @Column(name = "lname", nullable = false, length = 255)
    private String lname;

    public DBPrivateCustomer()
    {
    }


    public DBPrivateCustomer(String fname, String lname)
    {
        this.fname = fname;
        this.lname = lname;
    }

    public String getFname()
    {
        return fname;
    }

    public void setFname(String fname)
    {
        this.fname = fname;
    }

    public String getLname()
    {
        return lname;
    }

    public void setLname(String lname)
    {
        this.lname = lname;
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
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DBPrivateCustomer))
        {
            return false;
        }
        DBPrivateCustomer other = (DBPrivateCustomer) object;
        if ((this.getIdParties() == null && other.getIdParties() != null) || 
                (this.getIdParties() != null && !this.getIdParties().equals(other.getIdParties())))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "hotelsoftware.model.database.parties.Privatecustomer[ id=" + getIdParties() + " ]";
    }
   /**
    * Diese Methode sucht nach einem Privatkunden mithilfe eines namens. Hierbei reicht der Vor- oder der Nachname
    * @param firstName der Vorname der Privatperson.
    * @param lastName Dies ist der Nachname der PrivatPerson.
    * @return Diese Methode gibt ein Objekt vom typ DBPrivteCustomer zurück.
    */
    public static DBPrivateCustomer getPrivateCustomerByName(String firstName, String lastName)
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();
        
        DBPrivateCustomer cust = (DBPrivateCustomer) session.createCriteria(DBPrivateCustomer.class)
                .add(Restrictions.and(Restrictions.eq("fname", firstName), Restrictions.eq("lname", lastName)))
                .uniqueResult();
        
        return cust;
        
    }
    
    public static Set<DBPrivateCustomer> getPrivateCustomerByFName(String firstName)
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();
        
        Criteria criteria = session.createCriteria(DBPrivateCustomer.class);
        criteria = criteria.add(Restrictions.eq(
                        "fname", firstName));
        List<DBPrivateCustomer> cust = criteria.list();
        return new LinkedHashSet<DBPrivateCustomer>(cust);
    }
    
    public static Set<DBPrivateCustomer> getPrivateCustomerByLName(String lastName)
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();
        
        Criteria criteria = session.createCriteria(DBPrivateCustomer.class);
        criteria = criteria.add(Restrictions.eq(
                        "lname", lastName));
        List<DBPrivateCustomer> cust = criteria.list();
        return new LinkedHashSet<DBPrivateCustomer>(cust);
    }
}



