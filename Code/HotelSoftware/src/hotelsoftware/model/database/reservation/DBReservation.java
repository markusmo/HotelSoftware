package hotelsoftware.model.database.reservation;

import hotelsoftware.model.database.parties.DBGuest;
import hotelsoftware.model.database.parties.DBParty;
import hotelsoftware.model.database.users.DBUser;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Diese Klasse bildet eine Reservierung auf der Datenbank ab.
 *
 * @author mohi
 */
@Entity
@Table(name = "reservations", catalog = "`roomanizer-dev`", schema = "", uniqueConstraints =
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
    
    @JoinColumn(name = "idParties", referencedColumnName = "idParties")
    @ManyToOne(optional = false)
    private DBParty party;
    
    @Basic(optional = false)
    @Column(name = "created", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    
    @JoinColumn(name = "idUsers", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch= FetchType.EAGER)
    private DBUser user;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reservation", fetch= FetchType.EAGER)
    private Set<DBReservationItem> reservationItems;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reservation", fetch= FetchType.EAGER)
    private Set<DBReservationOption> reservationOptions;

    @ManyToMany(mappedBy = "reservationsCollection", fetch= FetchType.EAGER)
    private Set<DBGuest> guests;

    public DBReservation()
    {
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
    public Collection<DBGuest> getGuests()
    {
        return guests;
    }

    public void setGuests(Collection<DBGuest> guests)
    {
        this.guests = new LinkedHashSet<DBGuest>(guests);
    }
    
    public Collection<DBReservationOption> getReservationOptions()
    {
        return reservationOptions;
    }

    public void setReservationOptions(Collection<DBReservationOption> reservationOptions)
    {
        this.reservationOptions = new LinkedHashSet<DBReservationOption>(reservationOptions);
    }

    public DBUser getUser()
    {
        return user;
    }

    public void setUser(DBUser user)
    {
        this.user = user;
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
