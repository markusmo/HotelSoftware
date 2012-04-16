/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.database.model;

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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

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
    @NamedQuery(name = "Habitations.findByCreated", query = "SELECT h FROM Habitations h WHERE h.created = :created")
})
public class Habitations implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "start", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date start;
    @Basic(optional = false)
    @Column(name = "end", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date end;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;
    @Basic(optional = false)
    @Column(name = "created", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @ManyToMany(mappedBy = "habitationsCollection")
    private Collection<Guests> guestsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idHabitations")
    private Collection<Invoiceitems> invoiceitemsCollection;
    @JoinColumn(name = "idRooms", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Rooms idRooms;
    @JoinColumn(name = "idUsers", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private DBUser idUsers;
    @JoinColumn(name = "idServices", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Services idServices;

    public Habitations()
    {
    }

    public Habitations(Integer id)
    {
        this.id = id;
    }

    public Habitations(Integer id, Date start, Date end, BigDecimal price, Date created)
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

    public BigDecimal getPrice()
    {
        return price;
    }

    public void setPrice(BigDecimal price)
    {
        this.price = price;
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
    public Collection<Guests> getGuestsCollection()
    {
        return guestsCollection;
    }

    public void setGuestsCollection(Collection<Guests> guestsCollection)
    {
        this.guestsCollection = guestsCollection;
    }

    @XmlTransient
    public Collection<Invoiceitems> getInvoiceitemsCollection()
    {
        return invoiceitemsCollection;
    }

    public void setInvoiceitemsCollection(Collection<Invoiceitems> invoiceitemsCollection)
    {
        this.invoiceitemsCollection = invoiceitemsCollection;
    }

    public Rooms getIdRooms()
    {
        return idRooms;
    }

    public void setIdRooms(Rooms idRooms)
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

    public Services getIdServices()
    {
        return idServices;
    }

    public void setIdServices(Services idServices)
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
        if(!(object instanceof Habitations))
        {
            return false;
        }
        Habitations other = (Habitations) object;
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
    
}
