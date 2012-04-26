package hotelsoftware.model.database.reservation;

import hotelsoftware.model.database.parties.DBParty;
import hotelsoftware.model.database.users.DBUser;
import hotelsoftware.util.HibernateUtil;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
 * Diese Klasse bildet eine Reservierung auf der Datenbank ab.
 *
 * @author mohi
 */
@Entity
@Table(name = "reservations", catalog = "roomanizer", schema = "", uniqueConstraints =
{
    @UniqueConstraint(columnNames =
    {
        "reservationNumber"
    })
})
@XmlRootElement
public class DBReservation implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    
    @Basic(optional = false)
    @Column(name = "reservationNumber", nullable = false, length = 255)
    private String reservationNumber;
    
    @Basic(optional = false)
    @Column(name = "startDate", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date startDate;
    
    @Basic(optional = false)
    @Column(name = "endDate", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date endDate;
    
    @Column(name = "comment", length = 255)
    private String comment;
    
    @JoinColumn(name = "idParties", referencedColumnName = "idParties", nullable = false, updatable=false, insertable=false)
    @ManyToOne(optional = false)
    private DBParty party;
    
    @Basic(optional = false)
    @Column(name = "created", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    
    @JoinColumn(name = "idUsers", referencedColumnName = "id", updatable=false, insertable=false)
    @ManyToOne(optional = false)
    private DBUser idUsers;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reservation")
    private Set<DBReservationItem> reservationItems;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reservation")
    private Set<DBReservationOption> reservationOptions;

    public DBReservation()
    {
    }

    private DBReservation(Integer id)
    {
        this.id = id;
    }

    private DBReservation(Integer id, String reservationNumber, Date start, Date end, Date created)
    {
        this.id = id;
        this.reservationNumber = reservationNumber;
        this.startDate = start;
        this.endDate = end;
        this.created = created;
    }

    public static DBReservation newReservations()
    {
        return new DBReservation();
    }

    /**
     * Sucht Reservierungen nach einem Vornamen und Nachnamen einer Person, die reserviert hat.
     * Abfrage naehert sich an den namen an.
     *
     * @param fname
     * Der Vorname der Person, die reserviert hat
     * @param lname
     * Der Nachname einer Person, die reserviert hat
     * @return
     * Gibt eine Set aus Reservierungen aus
     */
    public static Collection<DBReservation> getReservationsByNameApprox(String fname, String lname)
    {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction ts = session.beginTransaction();
        ts.begin();

        String query = "SELECT * FROM Reservations r WHERE r.idParties IN ( SELECT idParties FROM guests g WHERE g.fname like '" + fname + "%' AND g.lname like '" + lname + "%') ";
        SQLQuery sqlquery = session.createSQLQuery(query);


        //addEntity gibt den rueckgabewert an...
        sqlquery = sqlquery.addEntity(DBReservation.class);
        List<DBReservation> retList = sqlquery.list();
        //TODO
        //session.close();

        return new LinkedHashSet<DBReservation>(retList);

    }

    /**
     * Gibt ein Reservierungen aus, die nach genauen Namen eienr Person gesucht werden, der reserviert hat
     *
     * @param fname
     * Der Vorname der Person
     * @param lname
     * Der Nachname der Person
     * @return
     * Ein Set aus Reservierungen
     */
    public static Collection<DBReservation> getReservationsByName(String fname, String lname)
    {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction ts = session.beginTransaction();
        ts.begin();

        String query = "SELECT * FROM Reservations r WHERE r.idParties = ( SELECT idParties FROM guests g WHERE g.fname = '" + fname + "' AND g.lname = '" + lname + "') ";
        SQLQuery sqlquery = session.createSQLQuery(query);

        sqlquery.addEntity(DBReservation.class);
        List<DBReservation> retList = sqlquery.list();

        //TODO
        //session.close();

        return new LinkedHashSet<DBReservation>(retList);

    }

    /**
     * Gibt eine Reservierung aus, die nach der eindeutigen Reservierungsnummer identifiziert wird.
     *
     * @param reservationNr
     * Die eindeutige Reservierungsnummer
     * @return
     * Eine Reservierung, mit der angegebenen Reservierungsnummer
     */
    public static DBReservation getReservationByNumber(int reservationNr)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction ts = session.beginTransaction();
        ts.begin();

        Criteria criteria = session.createCriteria(DBReservation.class);
        criteria.add(Restrictions.eq("reservationNumber", reservationNr + ""));
        DBReservation retList = (DBReservation) criteria.uniqueResult();
        //TODO
        // session.close();

        return retList;
    }

    /**
     * Sucht eine Reservierung nach der in der Datenbank hinterlegten ID
     *
     * @param id
     * Die ID der Reservierung
     * @return
     * Die Reservierung, mit der angegebenen ID
     */
    public static DBReservation getReservationById(int id)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction ts = session.beginTransaction();
        ts.begin();

        Criteria criteria = session.createCriteria(DBReservation.class);
        criteria.add(Restrictions.eq("id", id));
        DBReservation retList = (DBReservation) criteria.uniqueResult();
        //TODO
        //session.close();

        return retList;
    }

    /**
     * Gibt alle Reservierungen aus
     *
     * @return
     * Alle Reservierungen, ab den aktuellen Datum verfuebar sind
     */
    public static Collection<DBReservation> getAllReservations()
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction ts = session.beginTransaction();
        ts.begin();

        String query = "SELECT * FROM Reservations r";
        SQLQuery sqlquery = session.createSQLQuery(query);
        sqlquery.addEntity(DBReservation.class);
        //TODO
        //session.close();

        return new LinkedHashSet<DBReservation>(sqlquery.list());
    }

    /**
     * Gibt die Anzahl der Gaeste aus die Reserviert haben
     *
     * @return
     * Anzahl der reservierten Gaeste
     */
    public int getGuestAmount()
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction ts = session.beginTransaction();
        ts.begin();

        String query = "Select sum(ri.amount * c.bedCount) "
                + "From Reservations r INNER JOIN ReservationItems ri on r.id = ri.idReservations "
                + "INNER JOIN roomCategories c on ri.idRoomCategories = c.id "
                + "WHERE r.id = " + this.id;
        SQLQuery sqlquery = session.createSQLQuery(query);


        //addEntity gibt den rueckgabewert an...
        BigDecimal bd = (BigDecimal) sqlquery.uniqueResult();
        int count = bd.intValue();
        //TODO
        // session.close();
        return count;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getReservationNumber()
    {
        return reservationNumber;
    }

    public void setReservationNumber(String reserationNumber)
    {
        this.reservationNumber = reserationNumber;
    }

    public String getComment()
    {
        return comment;
    }

    public void setComment(String comment)
    {
        this.comment = comment;
    }

    @XmlTransient
    public Collection<DBReservationItem> getReservationItems()
    {
        return reservationItems;
    }

    public void setReservationItems(Collection<DBReservationItem> reservationItems)
    {
        this.reservationItems = new LinkedHashSet<DBReservationItem>(reservationItems);
    }

    @XmlTransient
    public Collection<DBReservationOption> getReservationOptions()
    {
        return reservationOptions;
    }

    public void setReservationOptions(Collection<DBReservationOption> reservationOptions)
    {
        this.reservationOptions = new LinkedHashSet<DBReservationOption>(reservationOptions);
    }

    public DBUser getIdUsers()
    {
        return idUsers;
    }

    public void setIdUsers(DBUser idUsers)
    {
        this.idUsers = idUsers;
    }

    public DBParty getParty()
    {
        return party;
    }

    public void setParty(DBParty party)
    {
        this.party = party;
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
        if (!(object instanceof DBReservation))
        {
            return false;
        }
        DBReservation other = (DBReservation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "hotelsoftware.database.model.Reservations[ id=" + id + " ]";
    }

    public Date getStartDate()
    {
        return startDate;
    }

    public void setStartDate(Date startDate)
    {
        this.startDate = startDate;
    }

    public Date getEndDate()
    {
        return endDate;
    }

    public void setEndDate(Date endDate)
    {
        this.endDate = endDate;
    }

    public Date getCreated()
    {
        return created;
    }

    public void setCreated(Date created)
    {
        this.created = created;
    }
}
