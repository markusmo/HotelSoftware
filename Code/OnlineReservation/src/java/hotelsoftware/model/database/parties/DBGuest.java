package hotelsoftware.model.database.parties;

import hotelsoftware.model.database.reservation.DBReservation;
import hotelsoftware.model.database.service.DBHabitation;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Dies ist die Klasse für die Tabelle "guests". Hier werden alle Gäste
 * gespeichert.
 *
 * @author mohi
 */
@Entity
@Table(name = "guests", catalog = "`roomanizer-dev`", schema = "")
@XmlRootElement
@PrimaryKeyJoinColumn(name = "idParties", referencedColumnName = "idParties")
public class DBGuest extends DBParty implements Serializable
{
    @Basic(optional = false)
    @Column(name = "fname", nullable = false, length = 255)
    private String fname;
    @Basic(optional = false)
    @Column(name = "lname", nullable = false, length = 255)
    private String lname;
    @Column(name = "birthday")
    @Temporal(TemporalType.DATE)
    private Date birthday;
    private static final long serialVersionUID = 1;
    @JoinTable(name = "allocations", joinColumns =
    {
        @JoinColumn(name = "idGuests", referencedColumnName = "idParties", nullable = false)
    }, inverseJoinColumns =
    {
        @JoinColumn(name = "idHabitations", referencedColumnName = "idServices", nullable = false)
    })
    @ManyToMany
    private Set<DBHabitation> habitations;
    @JoinTable(name = "reservationsGuests", joinColumns =
    {
        @JoinColumn(name = "idGuests", referencedColumnName = "idParties", nullable = false)
    }, inverseJoinColumns =
    {
        @JoinColumn(name = "idReservations", referencedColumnName = "id", nullable = false)
    })
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<DBReservation> reservationsCollection;

    public DBGuest()
    {
    }

    @XmlTransient
    public Collection<DBHabitation> getHabitations()
    {
        return habitations;
    }

    public void setHabitations(Collection<DBHabitation> habitationsCollection)
    {
        if (habitationsCollection != null)
        {
            this.habitations = new LinkedHashSet<DBHabitation>(habitationsCollection);
        }
    }

    public Collection<DBReservation> getReservationsCollection()
    {
        return reservationsCollection;
    }

    public void setReservationsCollection(Collection<DBReservation> reservations)
    {
        if (reservations != null)
        {
            this.reservationsCollection = new LinkedHashSet<DBReservation>(reservations);
        }
    }
    
    @Override
    public String toString()
    {
        return "hotelsoftware.database.model.Guests[ id=" + getIdParties() + " ]";
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

    public Date getBirthday()
    {
        return birthday;
    }

    public void setBirthday(Date birthday)
    {
        this.birthday = birthday;
    }
}
