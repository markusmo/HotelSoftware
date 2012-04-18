/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.database.reservation;

import hotelsoftware.model.database.parties.DBPerson;
import hotelsoftware.model.database.users.DBUser;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 *
 * @author mohi
 */
@Entity
@Table(name = "reservations", catalog = "roomanizer", schema = "", uniqueConstraints =
{
    @UniqueConstraint(columnNames =
    {
        "reserationNumber"
    })
})
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Reservations.findAll", query = "SELECT r FROM Reservations r"),
    @NamedQuery(name = "Reservations.findById", query = "SELECT r FROM Reservations r WHERE r.id = :id"),
    @NamedQuery(name = "Reservations.findByReserationNumber", query = "SELECT r FROM Reservations r WHERE r.reserationNumber = :reserationNumber"),
    @NamedQuery(name = "Reservations.findByStart", query = "SELECT r FROM Reservations r WHERE r.start = :start"),
    @NamedQuery(name = "Reservations.findByEnd", query = "SELECT r FROM Reservations r WHERE r.end = :end"),
    @NamedQuery(name = "Reservations.findByComment", query = "SELECT r FROM Reservations r WHERE r.comment = :comment"),
    @NamedQuery(name = "Reservations.findByCreated", query = "SELECT r FROM Reservations r WHERE r.created = :created"),
    @NamedQuery(name = "Reservations.findByFName", query = "FROM Reservations r WHRE r.id = (SELECET persons.id WHERE persons.fname like %:fname%)"),
    @NamedQuery(name = "Reservations.findByLName", query = "FROM Reservations r WHRE r.id = (SELECET persons.id WHERE persons.lname like %:lname%)")
})
public class DBReservations implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "reserationNumber", nullable = false, length = 255)
    private String reserationNumber;
    @Basic(optional = false)
    @Column(name = "start", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date start;
    @Basic(optional = false)
    @Column(name = "end", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date end;
    @Column(name = "comment", length = 255)
    private String comment;
    @Basic(optional = false)
    @Column(name = "created", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reservations")
    private Collection<DBReservationitems> reservationitemsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idReservations")
    private Collection<DBReservationoptions> reservationoptionsCollection;
    @JoinColumn(name = "idUsers", referencedColumnName = "id")
    @ManyToOne
    private DBUser idUsers;
    @JoinColumn(name = "idPersons", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private DBPerson idPersons;

    public DBReservations()
    {
    }

    public Collection<DBReservationitems> getReservationitemsCollection()
    {
        return reservationitemsCollection;
    }

    public void setReservationitemsCollection(Collection<DBReservationitems> reservationitemsCollection)
    {
        this.reservationitemsCollection = reservationitemsCollection;
    }

    public Collection<DBReservationoptions> getReservationoptionsCollection()
    {
        return reservationoptionsCollection;
    }

    public void setReservationoptionsCollection(Collection<DBReservationoptions> reservationoptionsCollection)
    {
        this.reservationoptionsCollection = reservationoptionsCollection;
    }

    private DBReservations(Integer id)
    {
        this.id = id;
    }

    private DBReservations(Integer id, String reserationNumber, Date start, Date end, Date created)
    {
        this.id = id;
        this.reserationNumber = reserationNumber;
        this.start = start;
        this.end = end;
        this.created = created;
    }

    public static DBReservations newReservations()
    {
        return new DBReservations();
    }
    public static Collection<DBReservations> getReservationsByFName(String Fname)
    {   
         throw new UnsupportedOperationException("Not yet implemented");
    }
    public static DBReservations newReservations(Integer id)
    {
        return new DBReservations(id);
    }

    public static DBReservations newReservations(Integer id, String reserationNumber, Date start, Date end, Date created)
    {
        return new DBReservations(id, reserationNumber, start, end, created);
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getReserationNumber()
    {
        return reserationNumber;
    }

    public void setReserationNumber(String reserationNumber)
    {
        this.reserationNumber = reserationNumber;
    }

    public Date getStart()
    {
        return start;
    }

    public void setStart(Date start)
    {
        this.start = start;
    }

    public Date getEnd()
    {
        return end;
    }

    public void setEnd(Date end)
    {
        this.end = end;
    }

    public String getComment()
    {
        return comment;
    }

    public void setComment(String comment)
    {
        this.comment = comment;
    }

    public Date getCreated()
    {
        return created;
    }

    public void setCreated(Date created)
    {
        this.created = created;
    }

    @XmlTransient
    public Collection<DBReservationitems> getReservationitems()
    {
        return reservationitemsCollection;
    }

    public void setReservationitems(Collection<DBReservationitems> reservationitemsCollection)
    {
        this.reservationitemsCollection = reservationitemsCollection;
    }

    @XmlTransient
    public Collection<DBReservationoptions> getReservationoptions()
    {
        return reservationoptionsCollection;
    }

    public void setReservationoptions(Collection<DBReservationoptions> reservationoptionsCollection)
    {
        this.reservationoptionsCollection = reservationoptionsCollection;
    }

    public DBUser getIdUsers()
    {
        return idUsers;
    }

    public void setIdUsers(DBUser idUsers)
    {
        this.idUsers = idUsers;
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
        if (!(object instanceof DBReservations))
        {
            return false;
        }
        DBReservations other = (DBReservations) object;
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
}
