/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.database.parties;

import hotelsoftware.model.database.invoice.DBInvoice;
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
public class DBCustomer implements Serializable
{
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customersId")
    private Collection<DBPrivateCustomer> dBPrivateCustomerCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCustomers")
    private Collection<DBInvoice> invoicesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCustomers")
    private Collection<DBCompany> companiesCollection;
    @JoinColumn(name = "idAddresses", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private DBAddress idAddresses;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCustomers")
    private Collection<DBPerson> personsCollection;

    public DBCustomer()
    {
    }

    public DBCustomer(Integer id)
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
    public Collection<DBInvoice> getInvoices()
    {
        return invoicesCollection;
    }

    public void setInvoices(Collection<DBInvoice> invoicesCollection)
    {
        this.invoicesCollection = invoicesCollection;
    }

    @XmlTransient
    public Collection<DBCompany> getCompanies()
    {
        return companiesCollection;
    }

    public void setCompanies(Collection<DBCompany> companiesCollection)
    {
        this.companiesCollection = companiesCollection;
    }

    public DBAddress getIdAddresses()
    {
        return idAddresses;
    }

    public void setIdAddresses(DBAddress idAddresses)
    {
        this.idAddresses = idAddresses;
    }

    @XmlTransient
    public Collection<DBPerson> getPersons()
    {
        return personsCollection;
    }

    public void setPersons(Collection<DBPerson> personsCollection)
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
        if(!(object instanceof DBCustomer))
        {
            return false;
        }
        DBCustomer other = (DBCustomer) object;
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

    @XmlTransient
    public Collection<DBPrivateCustomer> getDBPrivateCustomerCollection()
    {
        return dBPrivateCustomerCollection;
    }

    public void setDBPrivateCustomerCollection(Collection<DBPrivateCustomer> dBPrivateCustomerCollection)
    {
        this.dBPrivateCustomerCollection = dBPrivateCustomerCollection;
    }
    
}
