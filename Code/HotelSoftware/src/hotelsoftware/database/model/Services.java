/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.database.model;

import java.io.Serializable;
import java.util.Collection;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mohi
 */
@Entity
@Table(name = "services", catalog = "roomanizer", schema = "")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Services.findAll", query = "SELECT s FROM Services s"),
    @NamedQuery(name = "Services.findById", query = "SELECT s FROM Services s WHERE s.id = :id")
})
public class Services implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "services")
    private Collection<Invoiceitems> invoiceitemsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idServices")
    private Collection<Extraservices> extraservicesCollection;
    @JoinColumn(name = "idServiceTypes", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Servicetypes idServiceTypes;

    public Services()
    {
    }

    public Services(Integer id)
    {
        this.id = id;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
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

    @XmlTransient
    public Collection<Extraservices> getExtraservicesCollection()
    {
        return extraservicesCollection;
    }

    public void setExtraservicesCollection(Collection<Extraservices> extraservicesCollection)
    {
        this.extraservicesCollection = extraservicesCollection;
    }

    public Servicetypes getIdServiceTypes()
    {
        return idServiceTypes;
    }

    public void setIdServiceTypes(Servicetypes idServiceTypes)
    {
        this.idServiceTypes = idServiceTypes;
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
        if(!(object instanceof Services))
        {
            return false;
        }
        Services other = (Services) object;
        if((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "hotelsoftware.database.model.Services[ id=" + id + " ]";
    }
    
}
