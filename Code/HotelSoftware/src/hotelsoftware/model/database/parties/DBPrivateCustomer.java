/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.database.parties;

import hotelsoftware.util.HibernateUtil;
import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *Dies ist die Klasse für die Tabelle "privatecustomers". Hier werden alle Privatkunden gespeichert.
 * @author mohi
 */
@Entity
@Table(name = "privatePerson", catalog = "roomanizer", schema = "")
@XmlRootElement
//@PrimaryKeyJoinColumn(name="idCustomers", referencedColumnName="id")
public class DBPrivateCustomer implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false, insertable=false, updatable=false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "fname", nullable = false, length = 255)
    private String fname;
    @Basic(optional = false)
    @Column(name = "lname", nullable = false, length = 255)
    private String lname;

    public DBPrivateCustomer()
    {
    }

    public DBPrivateCustomer(Integer id)
    {
        this.id = id;
    }

    public DBPrivateCustomer(Integer id, String fname, String lname)
    {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
    }

    public Integer getId()
    {
        return this.id;
    }

    public void setId(Integer id)
    {
        this.id = id;
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
        hash += (id != null ? id.hashCode() : 0);
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
        if ((this.id == null && other.id != null) || 
                (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "hotelsoftware.model.database.parties.Privatecustomer[ idPrivateCustomer=" + id + " ]";
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
        session.close();
        
        return cust;
        
    }
    
}
