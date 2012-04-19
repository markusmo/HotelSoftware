/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.database.service;

import hotelsoftware.model.database.parties.DBGuest;
import hotelsoftware.model.database.users.DBUser;
import hotelsoftware.model.database.room.DBRoom;
import hotelsoftware.util.HibernateUtil;
import hotelsoftware.model.database.invoice.DBInvoiceItem;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author mohi
 */
@Entity
@Table(name = "habitations", catalog = "roomanizer", schema = "")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Habitations.findAll", query = "SELECT h FROM Habitations h"),
    @NamedQuery(name = "Habitations.findById", query = "SELECT h FROM Habitations h WHERE h.id = :id"),
    @NamedQuery(name = "Habitations.findByStart", query = "SELECT h FROM Habitations h WHERE h.start = :start"),
    @NamedQuery(name = "Habitations.findByEnd", query = "SELECT h FROM Habitations h WHERE h.end = :end"),
    @NamedQuery(name = "Habitations.findByPrice", query = "SELECT h FROM Habitations h WHERE h.price = :price"),
    @NamedQuery(name = "Habitations.findByCreated", query = "SELECT h FROM Habitations h WHERE h.created = :created"),
    @NamedQuery(name = "Habitations.findByGuest", query = "Select * "
                + "From habitations h inner join allocation a on h.id = a.idHabitations "
                + "inner join guests g on a.idGuests = g.id "
                + "where g.id = :guestId AND"
                + "h.start >= CURRENT_DATE")
})
public class DBHabitation implements Serializable
{
    @Basic(optional = false)
    @Column(name = "start", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date start;
    @Basic(optional = false)
    @Column(name = "end", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date end;
    @Basic(optional = false)
    @Column(name = "created", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;
    @ManyToMany(mappedBy = "habitationsCollection")
    private Collection<DBGuest> guestsCollection;
    /*@OneToMany(cascade = CascadeType.ALL, mappedBy = "idHabitations")
    private Collection<DBInvoiceItem> invoiceitemsCollection;*/
    @JoinColumn(name = "idRooms", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private DBRoom idRooms;
    @JoinColumn(name = "idUsers", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private DBUser idUsers;
    @JoinColumn(name = "idServices", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private DBService idServices;
    @OneToMany(mappedBy="idHabitations", cascade= CascadeType.ALL, fetch= FetchType.LAZY)
    private Collection<DBInvoiceItem> invoiceItemCollection;

    public DBHabitation()
    {
    }

    public DBHabitation(Integer id)
    {
        this.id = id;
    }

    public DBHabitation(Integer id, Date start, Date end, BigDecimal price, Date created)
    {
        this.id = id;
        this.start = start;
        this.end = end;
        this.price = price;
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

    public BigDecimal getPrice()
    {
        return price;
    }

    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }

    @XmlTransient
    public Collection<DBGuest> getGuests()
    {
        return guestsCollection;
    }

    public void setGuests(Collection<DBGuest> guestsCollection)
    {
        this.guestsCollection = guestsCollection;
    }

    @XmlTransient
    public Collection<DBInvoiceItem> getInvoiceitems()
    {
        return invoiceItemCollection;
    }

    public void setInvoiceitems(Collection<DBInvoiceItem> invoiceitemsCollection)
    {
        this.invoiceItemCollection = invoiceitemsCollection;
    }

    public DBRoom getIdRooms()
    {
        return idRooms;
    }

    public void setIdRooms(DBRoom idRooms)
    {
        this.idRooms = idRooms;
    }

    public DBUser getIdUsers()
    {
        return idUsers;
    }

    public void setIdUsers(DBUser idUsers)
    {
        this.idUsers = idUsers;
    }

    public DBService getIdServices()
    {
        return idServices;
    }

    public void setIdServices(DBService idServices)
    {
        this.idServices = idServices;
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
        if(!(object instanceof DBHabitation))
        {
            return false;
        }
        DBHabitation other = (DBHabitation) object;
        if((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "hotelsoftware.database.model.Habitations[ id=" + id + " ]";
    }
    
    public static DBHabitation getActualHabitationByGuest(DBGuest guest){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();
        Query namedQuery = session.getNamedQuery("Habitations.findByGuest");
        
        namedQuery.setString("guestId", guest.getId().toString());
        DBHabitation habitation = (DBHabitation)namedQuery.uniqueResult();
        session.close();
        
        return habitation;
    }

    public Date getStart()
    {
        return start;
    }

    public void setStart(Date start)
    {
        this.start = start;
    }

    public Date getEnd()
    {
        return end;
    }

    public void setEnd(Date end)
    {
        this.end = end;
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
