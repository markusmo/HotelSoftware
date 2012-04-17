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
public class DBCustomers implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCustomers")
    private Collection<DBInvoices> invoicesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCustomers")
    private Collection<DBCompanies> companiesCollection;
    @JoinColumn(name = "idAddresses", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private DBAddresses idAddresses;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCustomers")
    private Collection<DBPersons> personsCollection;

    public DBCustomers()
    {
    }

    public DBCustomers(Integer id)
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
    public Collection<DBInvoices> getInvoices()
    {
        return invoicesCollection;
    }

    public void setInvoices(Collection<DBInvoices> invoicesCollection)
    {
        this.invoicesCollection = invoicesCollection;
    }

    @XmlTransient
    public Collection<DBCompanies> getCompanies()
    {
        return companiesCollection;
    }

    public void setCompanies(Collection<DBCompanies> companiesCollection)
    {
        this.companiesCollection = companiesCollection;
    }

    public DBAddresses getIdAddresses()
    {
        return idAddresses;
    }

    public void setIdAddresses(DBAddresses idAddresses)
    {
        this.idAddresses = idAddresses;
    }

    @XmlTransient
    public Collection<DBPersons> getPersons()
    {
        return personsCollection;
    }

    public void setPersons(Collection<DBPersons> personsCollection)
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
        if(!(object instanceof DBCustomers))
        {
            return false;
        }
        DBCustomers other = (DBCustomers) object;
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
