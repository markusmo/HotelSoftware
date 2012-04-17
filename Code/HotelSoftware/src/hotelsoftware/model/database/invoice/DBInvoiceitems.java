/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.database.invoice;

import hotelsoftware.database.HibernateUtil;
import hotelsoftware.model.database.service.DBHabitation;
import hotelsoftware.model.database.service.DBService;
import hotelsoftware.model.database.users.DBUser;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@NamedQueries(
{
    @NamedQuery(name = "Invoiceitems.findAll", query = "SELECT i FROM Invoiceitems i"),
    @NamedQuery(name = "Invoiceitems.findByIdServices", query = "SELECT i FROM Invoiceitems i WHERE i.invoiceitemsPK.idServices = :idServices"),
    @NamedQuery(name = "Invoiceitems.findByIdInvoice", query = "SELECT i FROM Invoiceitems i WHERE i.invoiceitemsPK.idInvoice = :idInvoice"),
    @NamedQuery(name = "Invoiceitems.findByAmount", query = "SELECT i FROM Invoiceitems i WHERE i.amount = :amount"),
    @NamedQuery(name = "Invoiceitems.findByCreated", query = "SELECT i FROM Invoiceitems i WHERE i.created = :created")
})
public class DBInvoiceitems implements Serializable
{

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DBInvoiceitemsPK invoiceitemsPK;
    @Basic(optional = false)
    @Column(name = "amount", nullable = false)
    private int amount;
    @Basic(optional = false)
    @Column(name = "created", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @JoinColumn(name = "idServices", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private DBService services;
    @JoinColumn(name = "idInvoice", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private DBInvoices invoices;
    @JoinColumn(name = "idUsers", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private DBUser idUsers;
    @JoinColumn(name = "idHabitations", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private DBHabitation idHabitations;

    public DBInvoiceitems()
    {
    }

    public DBInvoiceitems(DBInvoiceitemsPK invoiceitemsPK)
    {
        this.invoiceitemsPK = invoiceitemsPK;
    }

    public DBInvoiceitems(DBInvoiceitemsPK invoiceitemsPK, int amount, Date created)
    {
        this.invoiceitemsPK = invoiceitemsPK;
        this.amount = amount;
        this.created = created;
    }

    public DBInvoiceitems(int idServices, int idInvoice)
    {
        this.invoiceitemsPK = new DBInvoiceitemsPK(idServices, idInvoice);
    }

    public DBInvoiceitemsPK getInvoiceitemsPK()
    {
        return invoiceitemsPK;
    }

    public void setInvoiceitemsPK(DBInvoiceitemsPK invoiceitemsPK)
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

    public DBInvoices getInvoices()
    {
        return invoices;
    }

    public void setInvoices(DBInvoices invoices)
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
     * communicates with the database and retrieves all invoiceitems for an invoice
     * @param invoice
     * the invoice, which owns these items
     * @return
     * a list of invoice items
     * @throws HibernateException 
     */
    public static List<DBInvoiceitems> getInvoiceItemsByInvoice(DBInvoices invoice) throws HibernateException
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();
        List<DBInvoiceitems> retList = session.createCriteria(DBInvoiceitems.class).add(
                Restrictions.eq("idInvoice", invoice)).list();
        session.close();

        return retList;
    }

    /**
     * communicates with the database and retrieves all invoiceitems for a habitation
     * @param habitation
     * the habitation, which owns the invoiceitems
     * @return
     * a list of invoiceitems
     * @throws HibernateException 
     */
    public static List<DBInvoiceitems> getInvoiceItemsByHabitation(
            DBHabitation habitation) throws HibernateException
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();
        List<DBInvoiceitems> retList = session.createCriteria(DBInvoiceitems.class).add(
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
        if (!(object instanceof DBInvoiceitems))
        {
            return false;
        }
        DBInvoiceitems other = (DBInvoiceitems) object;
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
