package hotelsoftware.model.database.invoice;

import hotelsoftware.model.database.FailedToSaveToDatabaseException;
import hotelsoftware.model.database.parties.DBCustomer;
import hotelsoftware.model.database.users.DBUser;
import hotelsoftware.util.HibernateUtil;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 * Bildet die Rechung auf Datenbank ab.
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
public class DBInvoice implements Serializable
{
    @Basic(optional = false)
    @Column(name = "expiration")
    @Temporal(TemporalType.DATE)
    private Date expiration;
    
    @Basic(optional = false)
    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    
    @Basic(optional = false)
    @Column(name = "invoiceNumber", nullable = false, length = 255)
    private String invoiceNumber;
    
    @Column(name = "discount", precision = 4, scale = 2)
    private BigDecimal discount;
    
    @Basic(optional = false)
    @Column(name = "fulfilled", nullable = false)
    private Boolean fulfilled;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "invoice")
    private Set<DBInvoiceItem> invoiceItems;
    
    @JoinColumn(name = "idpaymentMethods", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private DBPaymentMethod paymentMethod;
    
    @JoinColumn(name = "idCustomers", referencedColumnName = "idParties", nullable = false)
    @ManyToOne(optional = false)
    private DBCustomer customer;
    
    @JoinColumn(name = "idUsers", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private DBUser user;

    public DBInvoice()
    {
    }

    public DBInvoice(Integer id)
    {
        this.id = id;
    }

    public DBInvoice(String invoiceNumber, BigDecimal discount, Date expiration,
            Boolean fulfilled, Date created, DBPaymentMethod paymentMethod,
            DBUser user, DBCustomer customer)
    {
        this.invoiceNumber = invoiceNumber;
        this.discount = discount;
        this.expiration = expiration;
        this.fulfilled = fulfilled;
        this.created = created;
        this.paymentMethod = paymentMethod;
        this.user = user;
        this.customer = customer;
    }

    public DBInvoice(Integer id, String invoiceNumber, Date expiration,
            Boolean fulfilled, Date created)
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

    public Boolean getFulfilled()
    {
        return fulfilled;
    }

    public void setFulfilled(Boolean fulfilled)
    {
        this.fulfilled = fulfilled;
    }

    @XmlTransient
    public Set<DBInvoiceItem> getInvoiceItems()
    {
        return invoiceItems;
    }

    public Boolean isFulfilled()
    {
        return fulfilled;
    }

    public void setInvoiceItems(Set<DBInvoiceItem> invoiceitemsCollection)
    {
        this.invoiceItems = invoiceitemsCollection;
    }

    public DBPaymentMethod getPaymentMethod()
    {
        return paymentMethod;
    }

    public void setPaymentMethod(DBPaymentMethod idpaymentMethods)
    {
        this.paymentMethod = idpaymentMethods;
    }

    public DBUser getUser()
    {
        return user;
    }

    public void setUser(DBUser user)
    {
        this.user = user;
    }

    public DBCustomer getCustomer()
    {
        return customer;
    }

    public void setCustomer(DBCustomer customer)
    {
        this.customer = customer;
    }

    /**
     * Kommuniziert mit der Datenbank und liefert eine Rechung zurueck.
     * @param invoicenumber
     * Eine eindeutige Rechungsnummer
     * @return
     * die Rechnung, zu der gegebenen Rechungsnummer
     * @throws HibernateException
     * Dieser Fehler wird geworfen, wenn etwas bei der Transaktion fehlschlaegt
     */
    public static DBInvoice getInvoiceByInvoiceNumber(String invoicenumber) throws HibernateException
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();
        DBInvoice retInvoices = (DBInvoice) session.createCriteria(DBInvoice.class).add(Restrictions.eq(
                "invoiceNumber",
                invoicenumber)).uniqueResult();
        ;
        return retInvoices;
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
        if (!(object instanceof DBInvoice))
        {
            return false;
        }
        DBInvoice other = (DBInvoice) object;
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

    public Date getExpiration()
    {
        return expiration;
    }

    public void setExpiration(Date expiration)
    {
        this.expiration = expiration;
    }

    public Date getCreated()
    {
        return created;
    }

    public void setCreated(Date created)
    {
        this.created = created;
    }
}
