/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.database.parties;

import hotelsoftware.model.database.service.DBHabitation;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mohi
 */
@Entity
@Table(name = "guests", catalog = "roomanizer", schema = "", uniqueConstraints =
{
    @UniqueConstraint(columnNames =
    {
        "idPersons"
    })
})
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Guests.findAll", query = "SELECT g FROM Guests g"),
    @NamedQuery(name = "Guests.findById", query = "SELECT g FROM Guests g WHERE g.id = :id"),
    @NamedQuery(name = "Guests.findByBirthday", query = "SELECT g FROM Guests g WHERE g.birthday = :birthday")
})
public class DBGuest implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "birthday")
    @Temporal(TemporalType.DATE)
    private Date birthday;
    @JoinTable(name = "allocations", joinColumns =
    {
        @JoinColumn(name = "idGuests", referencedColumnName = "id", nullable = false)
    }, inverseJoinColumns =
    {
        @JoinColumn(name = "idHabitations", referencedColumnName = "id", nullable = false)
    })
    @ManyToMany
    private Collection<DBHabitation> habitationsCollection;
    @JoinColumn(name = "idPersons", referencedColumnName = "id", nullable = false)
    @OneToOne(optional = false)
    private DBPerson idPersons;

    public DBGuest()
    {
    }

    public DBGuest(Integer id)
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

    public Date getBirthday()
    {
        return birthday;
    }

    public void setBirthday(Date birthday)
    {
        this.birthday = birthday;
    }

    @XmlTransient
    public Collection<DBHabitation> getHabitations()
    {
        return habitationsCollection;
    }

    public void setHabitations(Collection<DBHabitation> habitationsCollection)
    {
        this.habitationsCollection = habitationsCollection;
    }

    public DBPerson getIdPersons()
    {
        return idPersons;
    }

    public void setIdPersons(DBPerson idPersons)
    {
        this.idPersons = idPersons;
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
        if(!(object instanceof DBGuest))
        {
            return false;
        }
        DBGuest other = (DBGuest) object;
        if((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "hotelsoftware.database.model.Guests[ id=" + id + " ]";
    }
    
}
