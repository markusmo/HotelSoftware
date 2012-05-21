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
 *
 * @author mohi
 */
@Entity
@Table(name = "paymentmethods", catalog = "`roomanizer-dev`", schema = "", uniqueConstraints =
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
    @OneToMany(mappedBy = "paymentMethod")
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

//    /**
//     * Kommuniziert mit der Datenbank und gibt alle Zahlungsmethoden aus
//     *
//     * @return
//     * Ein Set aus allen verfuegbaren Zahlungsmethoden
//     * @throws HibernateException
//     */
//    public static Set<DBPaymentMethod> getPaymentMethods() throws HibernateException
//    {
//        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//        Transaction ts = session.beginTransaction();
//        ts.begin();
//        Criteria criteria = session.createCriteria(DBPaymentMethod.class);
//        List<DBPaymentMethod> retList = criteria.list();
//
//        return new LinkedHashSet<DBPaymentMethod>(retList);
//    }

//    /**
//     * Kommuniziert mit der Datenbank und gibt eine Zahlungsmethode nach dem eingegebenen Namen aus
//     *
//     * @param method
//     * Die Zahlungsmethode, die gesucht wird
//     * @return
//     * Ein Objekt, dass die Zahlungsmethode abbildet
//     * @throws HibernateException
//     * Dieser Fehler wird geworfen, wenn die Tanksation fehlgeschlagen wird
//     */
//    public static DBPaymentMethod getPaymentMethodByName(String method) throws HibernateException
//    {
//        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//        Transaction ts = session.beginTransaction();
//        ts.begin();
//        Criteria criteria = session.createCriteria(DBPaymentMethod.class);
//        DBPaymentMethod ret = (DBPaymentMethod) criteria.add(Restrictions.eq("method", method)).uniqueResult();
//
//        return ret;
//    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final DBPaymentMethod other = (DBPaymentMethod) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id)))
        {
            return false;
        }
        if ((this.method == null) ? (other.method != null) : !this.method.equals(other.method))
        {
            return false;
        }
        if (this.invoicesCollection != other.invoicesCollection && (this.invoicesCollection == null || !this.invoicesCollection.equals(other.invoicesCollection)))
        {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 37 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 37 * hash + (this.method != null ? this.method.hashCode() : 0);
        hash = 37 * hash + (this.invoicesCollection != null ? this.invoicesCollection.hashCode() : 0);
        return hash;
    }


    @Override
    public String toString()
    {
        return "hotelsoftware.database.model.Paymentmethods[ id=" + id + " ]";
    }
}
