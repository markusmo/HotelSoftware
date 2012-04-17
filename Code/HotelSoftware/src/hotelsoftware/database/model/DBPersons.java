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
 *
 * @author mohi
 */
@Entity
@Table(name = "persons", catalog = "roomanizer", schema = "")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Persons.findAll", query = "SELECT p FROM Persons p"),
    @NamedQuery(name = "Persons.findById", query = "SELECT p FROM Persons p WHERE p.id = :id"),
    @NamedQuery(name = "Persons.findByFname", query = "SELECT p FROM Persons p WHERE p.fname = :fname"),
    @NamedQuery(name = "Persons.findByLname", query = "SELECT p FROM Persons p WHERE p.lname = :lname")
})
public class DBPersons implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "fname", length = 255)
    private String fname;
    @Column(name = "lname", length = 255)
    private String lname;
    @ManyToMany(mappedBy = "personsCollection")
    private Collection<DBCompanies> companiesCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "idPersons")
    private DBGuests guests;
    @JoinColumn(name = "idCustomers", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private DBCustomers idCustomers;
    @JoinColumn(name = "idAddresses", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private DBAddresses idAddresses;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPersons")
    private Collection<DBReservations> reservationsCollection;

    public DBPersons()
    {
    }

    public DBPersons(Integer id)
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

    public String getFname()
    {
        return fname;
    }

    public void setFname(String fname)
    {
        this.fname = fname;
    }

    public String getLname()
    {
        return lname;
    }

    public void setLname(String lname)
    {
        this.lname = lname;
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

    public DBGuests getGuests()
    {
        return guests;
    }

    public void setGuests(DBGuests guests)
    {
        this.guests = guests;
    }

    public DBCustomers getIdCustomers()
    {
        return idCustomers;
    }

    public void setIdCustomers(DBCustomers idCustomers)
    {
        this.idCustomers = idCustomers;
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
    public Collection<DBReservations> getReservationsCollectio()
    {
        return reservationsCollection;
    }

    public void setReservations(Collection<DBReservations> reservationsCollection)
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
        if(!(object instanceof DBPersons))
        {
            return false;
        }
        DBPersons other = (DBPersons) object;
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
