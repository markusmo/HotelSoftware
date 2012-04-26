package hotelsoftware.model.database.invoice;

import hotelsoftware.model.database.FaildToDeleteFromDatabaseException;
import hotelsoftware.model.database.FailedToSaveToDatabaseException;
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
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 * Diese Klasse bildet eine Zahlungsmethode auf der Datenbank ab.
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
public class DBPaymentMethod implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name", nullable = false, length = 255)
    private String method;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idpaymentMethods")
    private Set<DBInvoice> invoicesCollection;

    public Set<DBInvoice> getInvoicesCollection()
    {
        return invoicesCollection;
    }

    public void setInvoicesCollection(Set<DBInvoice> invoicesCollection)
    {
        this.invoicesCollection = invoicesCollection;
    }

    public DBPaymentMethod()
    {
    }

    public DBPaymentMethod(Integer id)
    {
        this.id = id;
    }

    public DBPaymentMethod(String name)
    {
        this.method = name;
    }

    public DBPaymentMethod(Integer id, String name)
    {
        this.id = id;
        this.method = name;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getMethod()
    {
        return method;
    }

    public void setMethod(String method)
    {
        this.method = method;
    }

    /**
     * Kommuniziert mit der Datenbank und gibt alle Zahlungsmethoden aus
     * @return
     * Ein Set aus allen verfuegbaren Zahlungsmethoden
     * @throws HibernateException 
     */
    public static Set<DBPaymentMethod> getPaymentMethods() throws HibernateException
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();
        Criteria criteria = session.createCriteria(DBPaymentMethod.class);
        List<DBPaymentMethod> retList = criteria.list();
        ;

        return new LinkedHashSet<DBPaymentMethod>(retList);
    }

    /**
     * Kommuniziert mit der Datenbank und gibt eine Zahlungsmethode nach dem eingegebenen Namen aus
     * @param method
     * Die Zahlungsmethode, die gesucht wird
     * @return
     * Ein Objekt, dass die Zahlungsmethode abbildet
     * @throws HibernateException 
     * Dieser Fehler wird geworfen, wenn die Tanksation fehlgeschlagen wird
     */
    public static DBPaymentMethod getPaymentMethodByName(String method) throws HibernateException
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();
        Criteria criteria = session.createCriteria(DBPaymentMethod.class);
        DBPaymentMethod ret = (DBPaymentMethod) criteria.add(Restrictions.eq("name", method)).uniqueResult();
        ;

        return ret;
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
            session.save(new DBPaymentMethod(name));
            ts.commit();
        } catch (HibernateException e)
        {
            ts.rollback();
            throw new FailedToSaveToDatabaseException();
        } finally
        {
            ;
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
            DBPaymentMethod method = (DBPaymentMethod) session.createCriteria(
                    DBPaymentMethod.class).add(Restrictions.like("name", name)).uniqueResult();
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
        if (!(object instanceof DBPaymentMethod))
        {
            return false;
        }
        DBPaymentMethod other = (DBPaymentMethod) object;
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
