/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.database.parties;

import hotelsoftware.model.database.reservation.DBReservation;
import hotelsoftware.model.database.service.DBHabitation;
import hotelsoftware.model.domain.reservation.Reservation;
import hotelsoftware.util.HibernateUtil;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

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
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *Dies ist die Klasse für die Tabelle "guests". Hier werden alle Gäste gespeichert.
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
//@NamedQueries(
//{
//    @NamedQuery(name = "Guests.findAll", query = "SELECT g FROM Guests g"),
//    @NamedQuery(name = "Guests.findById", query = "SELECT g FROM Guests g WHERE g.id = :id"),
//    @NamedQuery(name = "Guests.findByBirthday", query = "SELECT g FROM Guests g WHERE g.birthday = :birthday")
//})
public class DBGuest implements Serializable
{
	/**
	 * Diese Methode sucht nach einem Gast mithilfe einer Reservierung
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
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
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
/**
 * sucht einen Gast mithilfe einer reservierungsnummer heraus.
 * @param reservationNumber dies ist die nummer welche die reservierung identifiziert
 * @return gibt den passenden Datensatz vom typ DBGuest zurück
 */
    public static DBGuest getGuestFromReservationNumber(String reservationNumber)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction ts = session.beginTransaction();
        ts.begin();

        String query = "SELECT * FROM guests r WHERE r.idPersons = ( SELECT idPersons FROM reservations g WHERE g.reserationNumber = '" + reservationNumber + "') ";
        SQLQuery sqlquery = session.createSQLQuery(query);

        //Query countQuery = session.getNamedQuery("Reservations.countGuests");
        //countQuery.setInteger("id", this.id);

        //addEntity gibt den rueckgabewert an...
        sqlquery.addEntity(DBGuest.class);
        DBGuest guest = (DBGuest) sqlquery.uniqueResult();
        //session.close();

        return guest;
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
        if (!(object instanceof DBGuest))
        {
            return false;
        }
        DBGuest other = (DBGuest) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
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
/**
 * Diese Methode sucht nach einen Gast mithilfe des Namens
 * @param firstName dies ist der Vorname des Gastes.
 * @param lastName dies ist der Nachname des Gastes.
 * @return gibt eine Kollektion zurück, welche Objekte vom typ DBGuest enthällt.
 */
    public static Collection<DBGuest> getGuestsByName(String firstName, String lastName)
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
        session.close();

        return retList;
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
