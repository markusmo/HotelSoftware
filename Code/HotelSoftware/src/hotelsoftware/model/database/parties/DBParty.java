package hotelsoftware.model.database.parties;

import hotelsoftware.model.database.reservation.DBReservation;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *Dies ist die Klasse für die Tabelle "persons". Hier werden alle Personen gespeichert.
 * @author mohi
 */
@Entity
@Table(name = "parties", catalog = "`roomanizer-dev`", schema = "")
@Inheritance(strategy = InheritanceType.JOINED)
@XmlRootElement
public class DBParty implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idParties", nullable = false)
    private Integer idParties;
    
    @ManyToMany(mappedBy = "contactPersons")
    private Set<DBCompany> companies;
    
    @JoinColumn(name = "idAddresses", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, cascade= CascadeType.ALL)
    private DBAddress address;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "party")
    private Set<DBReservation> reservations;

    public DBParty()
    {
    }

    public Collection<DBCompany> getCompanies()
    {
        return companies;
    }

    public void setCompanies(Set<DBCompany> companies)
    {
        this.companies = companies;
    }

    public Collection<DBReservation> getReservations()
    {
        return reservations;
    }

    public void setReservations(Set<DBReservation> reservations)
    {
        this.reservations = reservations;
    }

    public DBParty(Integer id)
    {
        this.idParties = id;
    }

    public Integer getIdParties()
    {
        return idParties;
    }

    public void setIdParties(Integer id)
    {
        this.idParties = id;
    }

    public DBAddress getAddress()
    {
        return address;
    }

    public void setAddress(DBAddress address)
    {
        this.address = address;
    }

    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 47 * hash + (this.idParties != null ? this.idParties.hashCode() : 0);
        hash = 47 * hash + (this.companies != null ? this.companies.hashCode() : 0);
        hash = 47 * hash + (this.address != null ? this.address.hashCode() : 0);
        hash = 47 * hash + (this.reservations != null ? this.reservations.hashCode() : 0);
        return hash;
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
        final DBParty other = (DBParty) obj;
        if (this.idParties != other.idParties && (this.idParties == null || !this.idParties.equals(other.idParties)))
        {
            return false;
        }
        if (this.companies != other.companies && (this.companies == null || !this.companies.equals(other.companies)))
        {
            return false;
        }
        if (this.address != other.address && (this.address == null || !this.address.equals(other.address)))
        {
            return false;
        }
        if (this.reservations != other.reservations && (this.reservations == null || !this.reservations.equals(other.reservations)))
        {
            return false;
        }
        return true;
    }


    @Override
    public String toString()
    {
        return "hotelsoftware.database.model.Persons[ id=" + idParties + " ]";
    }
    
}
