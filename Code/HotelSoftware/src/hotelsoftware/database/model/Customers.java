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
@Table(name = "customers", catalog = "roomanizer", schema = "")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Customers.findAll", query = "SELECT c FROM Customers c"),
    @NamedQuery(name = "Customers.findById", query = "SELECT c FROM Customers c WHERE c.id = :id")
})
public class Customers implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCustomers")
    private Collection<Invoices> invoicesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCustomers")
    private Collection<Companies> companiesCollection;
    @JoinColumn(name = "idAddresses", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Addresses idAddresses;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCustomers")
    private Collection<Persons> personsCollection;

    public Customers()
    {
    }

    public Customers(Integer id)
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
    public Collection<Invoices> getInvoicesCollection()
    {
        return invoicesCollection;
    }

    public void setInvoicesCollection(Collection<Invoices> invoicesCollection)
    {
        this.invoicesCollection = invoicesCollection;
    }

    @XmlTransient
    public Collection<Companies> getCompaniesCollection()
    {
        return companiesCollection;
    }

    public void setCompaniesCollection(Collection<Companies> companiesCollection)
    {
        this.companiesCollection = companiesCollection;
    }

    public Addresses getIdAddresses()
    {
        return idAddresses;
    }

    public void setIdAddresses(Addresses idAddresses)
    {
        this.idAddresses = idAddresses;
    }

    @XmlTransient
    public Collection<Persons> getPersonsCollection()
    {
        return personsCollection;
    }

    public void setPersonsCollection(Collection<Persons> personsCollection)
    {
        this.personsCollection = personsCollection;
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
        if(!(object instanceof Customers))
        {
            return false;
        }
        Customers other = (Customers) object;
        if((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "hotelsoftware.database.model.Customers[ id=" + id + " ]";
    }
    
}
