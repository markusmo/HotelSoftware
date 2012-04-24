/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.database.parties;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Dies ist die Klasse für die Tabelle "addresses". Hier werden alle Addressen für Personen, Firmen und Grupierungen gespeichert.
 * @author mohi
 */
@Entity
@Table(name = "addresses", catalog = "roomanizer", schema = "")
@XmlRootElement
public class DBAddress implements Serializable
{
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAddresses")
    private Set<DBParty> dBPersonCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "invoiceAddress")
    private Set<DBCustomer> dBCustomerCollection;
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
    @JoinColumn(name = "idCountries", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private DBCountry idCountry;

    public DBAddress()
    {
    }

    public DBAddress(Integer id)
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

    public DBCountry getIdCountry()
    {
        return idCountry;
    }

    public void setIdCountry(DBCountry idCountries)
    {
        this.idCountry = idCountries;
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
        if(!(object instanceof DBAddress))
        {
            return false;
        }
        DBAddress other = (DBAddress) object;
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

    @XmlTransient
    public Set<DBCustomer> getDBCustomerCollection()
    {
        return dBCustomerCollection;
    }

    public void setDBCustomerCollection(Set<DBCustomer> dBCustomerCollection)
    {
        this.dBCustomerCollection = dBCustomerCollection;
    }

    @XmlTransient
    public Set<DBParty> getDBPersonCollection()
    {
        return dBPersonCollection;
    }

    public void setDBPersonCollection(Set<DBParty> dBPersonCollection)
    {
        this.dBPersonCollection = dBPersonCollection;
    }
    
}
