/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.database.parties;

import hotelsoftware.util.HibernateUtil;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *Dies ist die Klasse für die Tabelle "privatecustomers". Hier werden alle Privatkunden gespeichert.
 * @author mohi
 */
@Entity
@Table(name = "privatecustomer", catalog = "roomanizer", schema = "")
@XmlRootElement
//@NamedQueries(
//{
//    @NamedQuery(name = "Privatecustomer.findAll", query = "SELECT p FROM Privatecustomer p"),
//    @NamedQuery(name = "Privatecustomer.findById", query = "SELECT p FROM Privatecustomer p WHERE p.id = :id"),
//    @NamedQuery(name = "Privatecustomer.findByFname", query = "SELECT p FROM Privatecustomer p WHERE p.fname = :fname"),
//    @NamedQuery(name = "Privatecustomer.findByLname", query = "SELECT p FROM Privatecustomer p WHERE p.lname = :lname")
//})
public class DBPrivateCustomer implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "fname", nullable = false, length = 255)
    private String fname;
    @Basic(optional = false)
    @Column(name = "lname", nullable = false, length = 255)
    private String lname;
    @JoinColumn(name = "customers_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private DBCustomer customersId;

    public DBCustomer getCustomersId()
    {
        return customersId;
    }

    public void setCustomersId(DBCustomer customersId)
    {
        this.customersId = customersId;
    }

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
        return id;
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
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "hotelsoftware.model.database.parties.Privatecustomer[ id=" + id + " ]";
    }
    
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
