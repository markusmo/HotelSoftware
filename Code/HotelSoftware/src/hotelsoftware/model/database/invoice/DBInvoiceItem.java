package hotelsoftware.model.database.invoice;

import hotelsoftware.util.HibernateUtil;
import hotelsoftware.model.database.service.DBHabitation;
import hotelsoftware.model.database.service.DBService;
import hotelsoftware.model.database.users.DBUser;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 * Bildet eine Rechungsposition auf der Datenbank ab.
 *
 * @author mohi
 */
@Entity
@Table(name = "invoiceitems", catalog = "roomanizer", schema = "")
@XmlRootElement
public class DBInvoiceItem implements Serializable
{
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DBInvoiceItemPK invoiceitemsPK;
    
    @Basic(optional = false)
    @Column(name = "amount", nullable = false)
    private Integer amount;
    
    @Basic(optional = false)
    @Column(name = "created", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    
    @JoinColumn(name = "idUsers", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private DBUser user;
    
    @JoinColumn(name = "idHabitations", referencedColumnName = "idServices", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private DBHabitation habitation;
    
    @JoinColumn(name = "idInvoice", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private DBInvoice invoice;
    
    @JoinColumn(name = "idServices", referencedColumnName = "idServices", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private DBService service;

    public DBInvoiceItem()
    {
    }

    public DBInvoiceItem(DBInvoiceItemPK invoiceitemsPK)
    {
        this.invoiceitemsPK = invoiceitemsPK;
    }

    public DBInvoiceItem(DBInvoiceItemPK invoiceitemsPK, int amount, Date created)
    {
        this.invoiceitemsPK = invoiceitemsPK;
        this.amount = amount;
        this.created = created;
    }

    public DBInvoiceItem(int idServices, int idInvoice)
    {
        this.invoiceitemsPK = new DBInvoiceItemPK(idServices, idInvoice);
    }

    public DBInvoiceItemPK getInvoiceitemsPK()
    {
        return invoiceitemsPK;
    }

    public void setInvoiceitemsPK(DBInvoiceItemPK invoiceitemsPK)
    {
        this.invoiceitemsPK = invoiceitemsPK;
    }

    public Integer getAmount()
    {
        return amount;
    }

    public void setAmount(int amount)
    {
        this.amount = amount;
    }

    public Date getCreated()
    {
        return created;
    }

    public void setCreated(Date created)
    {
        this.created = created;
    }

    public DBService getService()
    {
        return service;
    }

    public void setService(DBService service)
    {
        this.service = service;
    }

    public DBInvoice getInvoice()
    {
        return invoice;
    }

    public void setInvoice(DBInvoice invoices)
    {
        this.invoice = invoices;
    }

    public DBUser getUser()
    {
        return user;
    }

    public void setUser(DBUser user)
    {
        this.user = user;
    }

    public DBHabitation getHabitation()
    {
        return habitation;
    }

    public void setHabitation(DBHabitation habitation)
    {
        this.habitation = habitation;
    }

    /**
     * Kommuniziert mit der Datenbank und holt ein alle Rechungspositionen fuer eine Belegung ein
     *
     * @param habitation
     * Die Belegung, der die Rechungsposition gehoert
     * @return
     * eine Set aus Rechungspositionen
     * @throws HibernateException
     * Dieser Fehler wird geworfen, wenn die Transaktion fehlschlaegt
     */
    public static Set<DBInvoiceItem> getInvoiceItemsByHabitation(
            DBHabitation habitation) throws HibernateException
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();
        List<DBInvoiceItem> retList = session.createCriteria(DBInvoiceItem.class).add(
                Restrictions.eq("idHabitations", habitation)).list();
        ;

        return new LinkedHashSet<DBInvoiceItem>(retList);
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (invoiceitemsPK != null ? invoiceitemsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DBInvoiceItem))
        {
            return false;
        }
        DBInvoiceItem other = (DBInvoiceItem) object;
        if ((this.invoiceitemsPK == null && other.invoiceitemsPK != null) || (this.invoiceitemsPK != null && !this.invoiceitemsPK.equals(
                other.invoiceitemsPK)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "hotelsoftware.database.model.Invoiceitems[ invoiceitemsPK=" + invoiceitemsPK + " ]";
    }
}
