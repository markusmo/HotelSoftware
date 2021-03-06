package hotelsoftware.model.database.parties;

import hotelsoftware.model.database.reservation.DBReservation;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *Dies ist die Klasse für die Tabelle "persons". Hier werden alle Personen gespeichert.
 * @author mohi
 */
@Entity
@Table(name = "parties", catalog = "roomanizer", schema = "")
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
    private Set<DBCompany> companiesCollection;
    
    @JoinColumn(name = "idAddresses", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = true)
    private DBAddress address;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "party")
    private Set<DBReservation> reservationsCollection;

    public DBParty()
    {
    }

    public Set<DBCompany> getCompaniesCollection()
    {
        return companiesCollection;
    }

    public void setCompaniesCollection(Set<DBCompany> companiesCollection)
    {
        this.companiesCollection = companiesCollection;
    }

    public Set<DBReservation> getReservationsCollection()
    {
        return reservationsCollection;
    }

    public void setReservationsCollection(Set<DBReservation> reservationsCollection)
    {
        this.reservationsCollection = reservationsCollection;
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

    @XmlTransient
    public Set<DBCompany> getCompanies()
    {
        return companiesCollection;
    }

    public void setCompanies(Set<DBCompany> companiesCollection)
    {
        this.companiesCollection = companiesCollection;
    }

    public DBAddress getAddress()
    {
        return address;
    }

    public void setAddress(DBAddress address)
    {
        this.address = address;
    }

    @XmlTransient
    public Set<DBReservation> getReservationsCollectio()
    {
        return reservationsCollection;
    }

    public void setReservations(Set<DBReservation> reservationsCollection)
    {
        this.reservationsCollection = reservationsCollection;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idParties != null ? idParties.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if(!(object instanceof DBParty))
        {
            return false;
        }
        DBParty other = (DBParty) object;
        if((this.idParties == null && other.idParties != null) || (this.idParties != null && !this.idParties.equals(other.idParties)))
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
