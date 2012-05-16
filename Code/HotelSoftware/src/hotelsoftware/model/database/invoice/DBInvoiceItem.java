package hotelsoftware.model.database.invoice;

import hotelsoftware.model.database.service.DBHabitation;
import hotelsoftware.model.database.service.DBService;
import hotelsoftware.model.database.users.DBUser;
import hotelsoftware.util.HibernateUtil;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.*;
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
@Table(name = "invoiceitems", catalog = "`roomanizer-dev`", schema = "")
@XmlRootElement
public class DBInvoiceItem implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    protected Integer id;
    
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
    
    @JoinColumn(name = "idHabitations", referencedColumnName = "idServices")
    @ManyToOne(optional = false)
    private DBHabitation habitation;
    
    @JoinColumn(name = "idInvoices", referencedColumnName = "id")
    @ManyToOne(optional = true)
    private DBInvoice invoice;
    
    @JoinColumn(name = "idServices", referencedColumnName = "idServices")
    @ManyToOne(optional = false)
    private DBService service;
    
    @Basic(optional = false)
    @Column(name = "price", nullable = false)
    private Integer price;

    public DBInvoiceItem()
    {
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
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
    
    public Integer getPrice()
    {
        return price;
    }

    public void setPrice(Integer price)
    {
        this.price = price;
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

        return new LinkedHashSet<DBInvoiceItem>(retList);
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
        if (!(object instanceof DBInvoiceItem))
        {
            return false;
        }
        DBInvoiceItem other = (DBInvoiceItem) object;
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
        return "hotelsoftware.database.model.Invoiceitems[ id=" + id + " ]";
    }
}
