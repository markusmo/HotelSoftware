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
@Table(name = "addresses", catalog = "`roomanizer-dev`", schema = "")
@XmlRootElement
public class DBAddress implements Serializable
{
    @OneToMany(mappedBy = "address")
    private Set<DBParty> dBPersonCollection;
    @OneToMany(mappedBy = "invoiceAddress")
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
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final DBAddress other = (DBAddress) obj;
        if (this.dBPersonCollection != other.dBPersonCollection && (this.dBPersonCollection == null || !this.dBPersonCollection.equals(other.dBPersonCollection)))
        {
            return false;
        }
        if (this.dBCustomerCollection != other.dBCustomerCollection && (this.dBCustomerCollection == null || !this.dBCustomerCollection.equals(other.dBCustomerCollection)))
        {
            return false;
        }
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id)))
        {
            return false;
        }
        if ((this.street == null) ? (other.street != null) : !this.street.equals(other.street))
        {
            return false;
        }
        if ((this.city == null) ? (other.city != null) : !this.city.equals(other.city))
        {
            return false;
        }
        if ((this.zip == null) ? (other.zip != null) : !this.zip.equals(other.zip))
        {
            return false;
        }
        if ((this.email == null) ? (other.email != null) : !this.email.equals(other.email))
        {
            return false;
        }
        if ((this.phone == null) ? (other.phone != null) : !this.phone.equals(other.phone))
        {
            return false;
        }
        if ((this.fax == null) ? (other.fax != null) : !this.fax.equals(other.fax))
        {
            return false;
        }
        if (this.idCountry != other.idCountry && (this.idCountry == null || !this.idCountry.equals(other.idCountry)))
        {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 79 * hash + (this.dBPersonCollection != null ? this.dBPersonCollection.hashCode() : 0);
        hash = 79 * hash + (this.dBCustomerCollection != null ? this.dBCustomerCollection.hashCode() : 0);
        hash = 79 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 79 * hash + (this.street != null ? this.street.hashCode() : 0);
        hash = 79 * hash + (this.city != null ? this.city.hashCode() : 0);
        hash = 79 * hash + (this.zip != null ? this.zip.hashCode() : 0);
        hash = 79 * hash + (this.email != null ? this.email.hashCode() : 0);
        hash = 79 * hash + (this.phone != null ? this.phone.hashCode() : 0);
        hash = 79 * hash + (this.fax != null ? this.fax.hashCode() : 0);
        hash = 79 * hash + (this.idCountry != null ? this.idCountry.hashCode() : 0);
        return hash;
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
