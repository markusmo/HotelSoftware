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
    private Integer amount;
    @Basic(optional = false)
    @Column(name = "created", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @JoinColumn(name = "idUsers", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private DBUser idUser;
    @JoinColumn(name = "idHabitations", referencedColumnName = "id", nullable = false)
    @ManyToOne(fetch= FetchType.LAZY)
    private DBHabitation idHabitation;
    @JoinColumn(name = "idInvoice", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private DBInvoice invoice;
    @JoinColumn(name = "idServices", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
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

    public DBUser getIdUser()
    {
        return idUser;
    }

    public void setIdUser(DBUser idUsers)
    {
        this.idUser = idUsers;
    }

    public DBHabitation getIdHabitation()
    {
        return idHabitation;
    }

    public void setIdHabitation(DBHabitation idHabitations)
    {
        this.idHabitation = idHabitations;
    }

    /**
     * communicates with the database and retrieves all invoiceitems for a habitation
     * @param habitation
     * the habitation, which owns the invoiceitems
     * @return
     * a list of invoiceitems
     * @throws HibernateException 
     */
    public static Set<DBInvoiceItem> getInvoiceItemsByHabitation(
            DBHabitation habitation) throws HibernateException
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();
        List<DBInvoiceItem> retList = session.createCriteria(DBInvoiceItem.class).add(
                Restrictions.eq("idHabitations", habitation)).list();
        session.close();

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
