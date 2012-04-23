/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.database.invoice;

import hotelsoftware.util.HibernateUtil;
import hotelsoftware.model.database.service.DBHabitation;
import hotelsoftware.model.database.service.DBService;
import hotelsoftware.model.database.users.DBUser;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author mohi
 */
@Entity
@Table(name = "invoiceitems", catalog = "roomanizer", schema = "")
@XmlRootElement
//@NamedQueries(
//{
//    @NamedQuery(name = "Invoiceitems.findAll", query = "SELECT i FROM Invoiceitems i"),
//    @NamedQuery(name = "Invoiceitems.findByIdServices", query = "SELECT i FROM Invoiceitems i WHERE i.invoiceitemsPK.idServices = :idServices"),
//    @NamedQuery(name = "Invoiceitems.findByIdInvoice", query = "SELECT i FROM Invoiceitems i WHERE i.invoiceitemsPK.idInvoice = :idInvoice"),
//    @NamedQuery(name = "Invoiceitems.findByAmount", query = "SELECT i FROM Invoiceitems i WHERE i.amount = :amount"),
//    @NamedQuery(name = "Invoiceitems.findByCreated", query = "SELECT i FROM Invoiceitems i WHERE i.created = :created")
//})
public class DBInvoiceItem implements Serializable
{

private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DBInvoiceItemPK invoiceitemsPK;
    @Basic(optional = false)
    @Column(name = "amount", nullable = false)
    private int amount;
    @Basic(optional = false)
    @Column(name = "created", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @JoinColumn(name = "idUsers", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private DBUser idUsers;
    @JoinColumn(name = "idHabitations", referencedColumnName = "id", nullable = false)
    @ManyToOne(fetch= FetchType.LAZY)
    private DBHabitation idHabitations;
    @JoinColumn(name = "idInvoice", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private DBInvoice invoices;
    @JoinColumn(name = "idServices", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private DBService services;

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

    public int getAmount()
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

    public DBService getServices()
    {
        return services;
    }

    public void setServices(DBService services)
    {
        this.services = services;
    }

    public DBInvoice getInvoices()
    {
        return invoices;
    }

    public void setInvoices(DBInvoice invoices)
    {
        this.invoices = invoices;
    }

    public DBUser getIdUsers()
    {
        return idUsers;
    }

    public void setIdUsers(DBUser idUsers)
    {
        this.idUsers = idUsers;
    }

    public DBHabitation getIdHabitations()
    {
        return idHabitations;
    }

    public void setIdHabitations(DBHabitation idHabitations)
    {
        this.idHabitations = idHabitations;
    }

    /**
     * communicates with the database and retrieves all invoiceitems for a habitation
     * @param habitation
     * the habitation, which owns the invoiceitems
     * @return
     * a list of invoiceitems
     * @throws HibernateException 
     */
    public static Collection<DBInvoiceItem> getInvoiceItemsByHabitation(
            DBHabitation habitation) throws HibernateException
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();
        Collection<DBInvoiceItem> retList = session.createCriteria(DBInvoiceItem.class).add(
                Restrictions.eq("idHabitations", habitation)).list();
        session.close();

        return retList;
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