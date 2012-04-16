/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.database.model;

import hotelsoftware.database.Exceptions.FailedToSaveToDatabaseException;
import hotelsoftware.database.HibernateUtil;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author mohi
 */
@Entity
@Table(name = "invoices", catalog = "roomanizer", schema = "", uniqueConstraints =
{
    @UniqueConstraint(columnNames =
    {
        "invoiceNumber"
    })
})
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Invoices.findAll", query = "SELECT i FROM Invoices i"),
    @NamedQuery(name = "Invoices.findById", query = "SELECT i FROM Invoices i WHERE i.id = :id"),
    @NamedQuery(name = "Invoices.findByInvoiceNumber", query = "SELECT i FROM Invoices i WHERE i.invoiceNumber = :invoiceNumber"),
    @NamedQuery(name = "Invoices.findByDiscount", query = "SELECT i FROM Invoices i WHERE i.discount = :discount"),
    @NamedQuery(name = "Invoices.findByExpiration", query = "SELECT i FROM Invoices i WHERE i.expiration = :expiration"),
    @NamedQuery(name = "Invoices.findByFulfilled", query = "SELECT i FROM Invoices i WHERE i.fulfilled = :fulfilled"),
    @NamedQuery(name = "Invoices.findByCreated", query = "SELECT i FROM Invoices i WHERE i.created = :created")
})
public class Invoices implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "invoiceNumber", nullable = false, length = 255)
    private String invoiceNumber;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "discount", precision = 4, scale = 2)
    private BigDecimal discount;
    @Basic(optional = false)
    @Column(name = "expiration", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date expiration;
    @Basic(optional = false)
    @Column(name = "fulfilled", nullable = false)
    private boolean fulfilled;
    @Basic(optional = false)
    @Column(name = "created", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "invoices")
    private Collection<Invoiceitems> invoiceitemsCollection;
    @JoinColumn(name = "idpaymentMethods", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Paymentmethods idpaymentMethods;
    @JoinColumn(name = "idUsers", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private DBUser idUsers;
    @JoinColumn(name = "idCustomers", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Customers idCustomers;

    public Invoices()
    {
    }

    public Invoices(Integer id)
    {
        this.id = id;
    }

    public Invoices(String invoiceNumber, BigDecimal discount, Date expiration,
            boolean fulfilled, Date created, Paymentmethods idpaymentMethods,
            DBUser idUsers, Customers idCustomers)
    {
        this.invoiceNumber = invoiceNumber;
        this.discount = discount;
        this.expiration = expiration;
        this.fulfilled = fulfilled;
        this.created = created;
        this.idpaymentMethods = idpaymentMethods;
        this.idUsers = idUsers;
        this.idCustomers = idCustomers;
    }

    public Invoices(Integer id, String invoiceNumber, Date expiration,
            boolean fulfilled, Date created)
    {
        this.id = id;
        this.invoiceNumber = invoiceNumber;
        this.expiration = expiration;
        this.fulfilled = fulfilled;
        this.created = created;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getInvoiceNumber()
    {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber)
    {
        this.invoiceNumber = invoiceNumber;
    }

    public BigDecimal getDiscount()
    {
        return discount;
    }

    public void setDiscount(BigDecimal discount)
    {
        this.discount = discount;
    }

    public Date getExpiration()
    {
        return expiration;
    }

    public void setExpiration(Date expiration)
    {
        this.expiration = expiration;
    }

    public boolean getFulfilled()
    {
        return fulfilled;
    }

    public void setFulfilled(boolean fulfilled)
    {
        this.fulfilled = fulfilled;
    }

    public Date getCreated()
    {
        return created;
    }

    public void setCreated(Date created)
    {
        this.created = created;
    }

    @XmlTransient
    public Collection<Invoiceitems> getInvoiceitemsCollection()
    {
        return invoiceitemsCollection;
    }

    public void setInvoiceitemsCollection(
            Collection<Invoiceitems> invoiceitemsCollection)
    {
        this.invoiceitemsCollection = invoiceitemsCollection;
    }

    public Paymentmethods getIdpaymentMethods()
    {
        return idpaymentMethods;
    }

    public void setIdpaymentMethods(Paymentmethods idpaymentMethods)
    {
        this.idpaymentMethods = idpaymentMethods;
    }

    public DBUser getIdUsers()
    {
        return idUsers;
    }

    public void setIdUsers(DBUser idUsers)
    {
        this.idUsers = idUsers;
    }

    public Customers getIdCustomers()
    {
        return idCustomers;
    }

    public void setIdCustomers(Customers idCustomers)
    {
        this.idCustomers = idCustomers;
    }

    /**
     * communicates with the database and retrieves a single invoice, by assuming
     * that all invoicenumbers are unique.
     * @param invoicenumber
     * a unique invoicenumber
     * @return
     * a invoice
     * @throws HibernateException 
     */
    public static Invoices getInvoiceByInvoiceNumber(String invoicenumber) throws HibernateException
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();
        Invoices retInvoices = (Invoices) session.createCriteria(Invoices.class).add(Restrictions.eq(
                "invoiceNumber",
                invoicenumber)).uniqueResult();
        session.close();
        return retInvoices;
    }

    /**
     * communicates with the database and saves a invoice
     * @param invoice
     * the invoice to be saved
     * @throws FailedToSaveToDatabaseException 
     */
    public static void saveInvoice(Invoices invoice) throws FailedToSaveToDatabaseException
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();
        try
        {
            session.save(invoice);
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
        if (!(object instanceof Invoices))
        {
            return false;
        }
        Invoices other = (Invoices) object;
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
        return "hotelsoftware.database.model.Invoices[ id=" + id + " ]";
    }
}