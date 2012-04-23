/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.database.parties;

import hotelsoftware.model.database.parties.DBAddress;
import hotelsoftware.model.database.parties.DBCompany;
import hotelsoftware.model.database.parties.DBGuest;
import hotelsoftware.model.database.parties.DBCustomer;
import hotelsoftware.model.database.reservation.DBReservation;
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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *Dies ist die Klasse für die Tabelle "persons". Hier werden alle Personen gespeichert.
 * @author mohi
 */
@Entity
@Table(name = "persons", catalog = "roomanizer", schema = "")
@XmlRootElement
//@NamedQueries(
//{
//    @NamedQuery(name = "Persons.findAll", query = "SELECT p FROM Persons p"),
//    @NamedQuery(name = "Persons.findById", query = "SELECT p FROM Persons p WHERE p.id = :id"),
//    @NamedQuery(name = "Persons.findByFname", query = "SELECT p FROM Persons p WHERE p.fname = :fname"),
//    @NamedQuery(name = "Persons.findByLname", query = "SELECT p FROM Persons p WHERE p.lname = :lname")
//})
public class DBPerson implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @ManyToMany(mappedBy = "personsCollection")
    private Collection<DBCompany> companiesCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "idPersons")
    private DBGuest guests;
    @JoinColumn(name = "idCustomers", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private DBCustomer idCustomers;
    @JoinColumn(name = "idAddresses", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private DBAddress idAddresses;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPersons")
    private Collection<DBReservation> reservationsCollection;

    public DBPerson()
    {
    }

    public Collection<DBCompany> getCompaniesCollection()
    {
        return companiesCollection;
    }

    public void setCompaniesCollection(Collection<DBCompany> companiesCollection)
    {
        this.companiesCollection = companiesCollection;
    }

    public Collection<DBReservation> getReservationsCollection()
    {
        return reservationsCollection;
    }

    public void setReservationsCollection(Collection<DBReservation> reservationsCollection)
    {
        this.reservationsCollection = reservationsCollection;
    }

    public DBPerson(Integer id)
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
    public Collection<DBCompany> getCompanies()
    {
        return companiesCollection;
    }

    public void setCompanies(Collection<DBCompany> companiesCollection)
    {
        this.companiesCollection = companiesCollection;
    }

    public DBGuest getGuests()
    {
        return guests;
    }

    public void setGuests(DBGuest guests)
    {
        this.guests = guests;
    }

    public DBCustomer getIdCustomers()
    {
        return idCustomers;
    }

    public void setIdCustomers(DBCustomer idCustomers)
    {
        this.idCustomers = idCustomers;
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
    public Collection<DBReservation> getReservationsCollectio()
    {
        return reservationsCollection;
    }

    public void setReservations(Collection<DBReservation> reservationsCollection)
    {
        this.reservationsCollection = reservationsCollection;
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
        if(!(object instanceof DBPerson))
        {
            return false;
        }
        DBPerson other = (DBPerson) object;
        if((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "hotelsoftware.database.model.Persons[ id=" + id + " ]";
    }
    
}
