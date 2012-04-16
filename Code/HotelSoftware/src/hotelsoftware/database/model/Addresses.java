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
@Table(name = "addresses", catalog = "roomanizer", schema = "")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Addresses.findAll", query = "SELECT a FROM Addresses a"),
    @NamedQuery(name = "Addresses.findById", query = "SELECT a FROM Addresses a WHERE a.id = :id"),
    @NamedQuery(name = "Addresses.findByStreet", query = "SELECT a FROM Addresses a WHERE a.street = :street"),
    @NamedQuery(name = "Addresses.findByCity", query = "SELECT a FROM Addresses a WHERE a.city = :city"),
    @NamedQuery(name = "Addresses.findByZip", query = "SELECT a FROM Addresses a WHERE a.zip = :zip"),
    @NamedQuery(name = "Addresses.findByEmail", query = "SELECT a FROM Addresses a WHERE a.email = :email"),
    @NamedQuery(name = "Addresses.findByPhone", query = "SELECT a FROM Addresses a WHERE a.phone = :phone"),
    @NamedQuery(name = "Addresses.findByFax", query = "SELECT a FROM Addresses a WHERE a.fax = :fax")
})
public class Addresses implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "street", length = 255)
    private String street;
    @Column(name = "city", length = 255)
    private String city;
    @Column(name = "zip", length = 255)
    private String zip;
    @Column(name = "email", length = 255)
    private String email;
    @Column(name = "phone", length = 255)
    private String phone;
    @Column(name = "fax", length = 255)
    private String fax;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAddresses")
    private Collection<Customers> customersCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAddresses")
    private Collection<Persons> personsCollection;
    @JoinColumn(name = "idCountries", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Countries idCountries;

    public Addresses()
    {
    }

    public Addresses(Integer id)
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

    public String getStreet()
    {
        return street;
    }

    public void setStreet(String street)
    {
        this.street = street;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getZip()
    {
        return zip;
    }

    public void setZip(String zip)
    {
        this.zip = zip;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getFax()
    {
        return fax;
    }

    public void setFax(String fax)
    {
        this.fax = fax;
    }

    @XmlTransient
    public Collection<Customers> getCustomersCollection()
    {
        return customersCollection;
    }

    public void setCustomersCollection(Collection<Customers> customersCollection)
    {
        this.customersCollection = customersCollection;
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

    public Countries getIdCountries()
    {
        return idCountries;
    }

    public void setIdCountries(Countries idCountries)
    {
        this.idCountries = idCountries;
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
        if(!(object instanceof Addresses))
        {
            return false;
        }
        Addresses other = (Addresses) object;
        if((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "hotelsoftware.database.model.Addresses[ id=" + id + " ]";
    }
    
}