/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.database.model;

import hotelsoftware.database.Exceptions.*;
import hotelsoftware.database.HibernateUtil;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
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
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author mohi
 */
@Entity
@Table(name = "paymentmethods", catalog = "roomanizer", schema = "", uniqueConstraints =
{
    @UniqueConstraint(columnNames =
    {
        "name"
    })
})
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Paymentmethods.findAll", query = "SELECT p FROM Paymentmethods p"),
    @NamedQuery(name = "Paymentmethods.findById", query = "SELECT p FROM Paymentmethods p WHERE p.id = :id"),
    @NamedQuery(name = "Paymentmethods.findByName", query = "SELECT p FROM Paymentmethods p WHERE p.name = :name")
})
public class Paymentmethods implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name", nullable = false, length = 255)
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idpaymentMethods")
    private Collection<Invoices> invoicesCollection;

    public Paymentmethods()
    {
    }

    public Paymentmethods(Integer id)
    {
        this.id = id;
    }

    public Paymentmethods(String name)
    {
        this.name = name;
    }

    public Paymentmethods(Integer id, String name)
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

    @XmlTransient
    public Collection<Invoices> getInvoicesCollection()
    {
        return invoicesCollection;
    }

    public void setInvoicesCollection(Collection<Invoices> invoicesCollection)
    {
        this.invoicesCollection = invoicesCollection;
    }

    /**
     * establishes a connection to the database and retrieves all paymentmethods
     * available in the database
     * @return
     * a list of paymentmethods
     * @throws HibernateException 
     */
    public static List<Paymentmethods> getPaymentMethods() throws HibernateException
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();
        Criteria criteria = session.createCriteria(Paymentmethods.class);
        List<Paymentmethods> retList = criteria.list();
        session.close();

        return retList;
    }

    /**
     * establishes a connection to the database and retrieves a single paymentmethod
     * by name
     * @param method
     * the method to be fetched
     * @return
     * a model class of paymentmethods
     * @throws HibernateException 
     */
    public static Paymentmethods getPaymentMethodByName(String method) throws HibernateException
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();
        Criteria criteria = session.createCriteria(Paymentmethods.class);
        List<Paymentmethods> retList = criteria.list();
        session.close();

        for (Paymentmethods paymentmethods : retList)
        {
            if (paymentmethods.getName().equals(method))
            {
                return paymentmethods;
            }
        }
        return null;
    }

    /**
     * establishes a connection to the database and creates a new paymentmethod
     * in the database
     * @param name
     * the new paymentmethod to be created
     * @throws HibernateException
     * @throws FailedToSaveToDatabaseException 
     */
    public static void savePaymentMethod(String name) throws FailedToSaveToDatabaseException
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();
        try
        {
            session.save(new Paymentmethods(name));
            ts.commit();
        } catch (HibernateException e)
        {
            ts.rollback();
            throw new FailedToSaveToDatabaseException();
        } finally
        {
            session.close();
        }
    }

    /**
     * deletes a paymentmethod from the database
     * @param name
     * the name of the method to delete
     * @throws FaildToDeleteFromDatabaseException 
     */
    public static void deletePaymentMethod(String name) throws FaildToDeleteFromDatabaseException
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();
        try
        {
            Paymentmethods method = (Paymentmethods) session.createCriteria(
                    Paymentmethods.class).add(Restrictions.like("name", name)).uniqueResult();
            session.delete(method);
        } catch (HibernateException ex)
        {
            ts.rollback();
            throw new FaildToDeleteFromDatabaseException();
        }
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
        if (!(object instanceof Paymentmethods))
        {
            return false;
        }
        Paymentmethods other = (Paymentmethods) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(
                other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "hotelsoftware.database.model.Paymentmethods[ id=" + id + " ]";
    }
}