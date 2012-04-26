package hotelsoftware.model.database.parties;

import hotelsoftware.model.database.reservation.DBReservation;
import hotelsoftware.model.database.service.DBHabitation;
import hotelsoftware.model.domain.reservation.Reservation;
import hotelsoftware.util.HibernateUtil;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 * Dies ist die Klasse für die Tabelle "guests". Hier werden alle Gäste gespeichert.
 *
 * @author mohi
 */
@Entity
@Table(name = "guests", catalog = "roomanizer", schema = "")
@XmlRootElement
@PrimaryKeyJoinColumn(name = "idParties", referencedColumnName = "idParties")
public class DBGuest extends DBParty implements Serializable
{
    /**
     * Diese Methode sucht nach einem Gast mithilfe einer Reservierung
     *
     * @param reservation ist eine Reservierung die für einen gast reserviert
     * @return gibt ein Objekt vom Gast zurück
     */
    public static Object getGuestFromReservation(Reservation reservation)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }
    
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
        @JoinColumn(name = "idService", referencedColumnName = "idServices", nullable = false)
    })
    @ManyToMany(cascade=CascadeType.ALL)
    private Set<DBHabitation> habitations;
    
    @JoinTable(name = "reservationsGuests", joinColumns =
    {
        @JoinColumn(name = "personsID", referencedColumnName = "idParties", nullable = false)
    }, inverseJoinColumns =
    {
        @JoinColumn(name = "reservationsID", referencedColumnName = "id", nullable = false)
    })
    @ManyToMany(cascade=CascadeType.ALL)
    private Set<DBReservation> reservations;

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
        this.habitations = new LinkedHashSet<DBHabitation>(habitationsCollection);
    }
    
    @XmlTransient
    public Collection<DBReservation> getReservations()
    {
        return reservations;
    }

    public void setReservations(Collection<DBReservation> reservations)
    {
        this.reservations = new LinkedHashSet<DBReservation>(reservations);
    }

    /**
     * sucht einen Gast mithilfe einer reservierungsnummer heraus.
     *
     * @param reservationNumber dies ist die nummer welche die reservierung identifiziert
     * @return gibt den passenden Datensatz vom typ DBGuest zurück
     */
    public static DBGuest getGuestFromReservationNumber(String reservationNumber)
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();

        String query = "SELECT * FROM guests r WHERE r.idParties = ( SELECT idParties FROM reservations g WHERE g.reserationNumber = '" + reservationNumber + "') ";
        SQLQuery sqlquery = session.createSQLQuery(query);

        //Query countQuery = session.getNamedQuery("Reservations.countGuests");
        //countQuery.setInteger("id", this.id);

        //addEntity gibt den rueckgabewert an...
        sqlquery.addEntity(DBGuest.class);
        DBGuest guest = (DBGuest) sqlquery.uniqueResult();
        //;

        return guest;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (getIdParties() != null ? getIdParties().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DBGuest))
        {
            return false;
        }
        DBGuest other = (DBGuest) object;
        if ((this.getIdParties() == null && other.getIdParties() != null)
                || (this.getIdParties() != null && !this.getIdParties().equals(other.getIdParties())))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "hotelsoftware.database.model.Guests[ id=" + getIdParties() + " ]";
    }

    /**
     * Diese Methode sucht nach einen Gast mithilfe des Namens
     *
     * @param firstName dies ist der Vorname des Gastes.
     * @param lastName dies ist der Nachname des Gastes.
     * @return gibt eine Kollektion zurück, welche Objekte vom typ DBGuest enthällt.
     */
    public static Set<DBGuest> getGuestsByName(String firstName, String lastName)
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();
        Criteria criteria = session.createCriteria(DBGuest.class);

        if (firstName.isEmpty() && lastName.isEmpty())
        {
            return null;
        }
        if (firstName.isEmpty())
        {
            criteria = criteria.add(Restrictions.eq(
                    "lname", lastName));
        }
        else
        {
            if (lastName.isEmpty())
            {
                criteria = criteria.add(Restrictions.eq(
                        "fname", firstName));
            }
            else
            {
                criteria = criteria.add(Restrictions.eq(
                        "lname", lastName)).add(Restrictions.eq(
                        "fname", firstName));
            }
        }

        List<DBGuest> retList = criteria.list();
        ;

        return new LinkedHashSet<DBGuest>(retList);
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
