package hotelsoftware.model.database.parties;

import hotelsoftware.model.database.reservation.DBReservation;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *Dies ist die Klasse für die Tabelle "persons". Hier werden alle Personen gespeichert.
 * @author mohi
 */
@Entity
@Table(name = "parties", catalog = "`roomanizer`", schema = "")
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
    public String toString()
    {
        return "hotelsoftware.model.database.parties.DBParty[ id=" + idParties + " ]";
    }
    
}
