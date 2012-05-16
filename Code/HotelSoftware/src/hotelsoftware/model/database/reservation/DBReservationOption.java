package hotelsoftware.model.database.reservation;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Diese Klasse bildet Reservierunsoptionen auf der Datenbank ab.
 * @author mohi
 */
@Entity
@Table(name = "reservationoptions", catalog = "`roomanizer-dev`", schema = "")
@XmlRootElement
public class DBReservationOption implements Serializable
{
    @Basic(optional = false)
    @Column(name = "expiration", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date expiration;
    
    @Column(name = "comment", length = 255)
    private String comment;
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    
    @Basic(optional = false)
    @Column(name = "prepayment", nullable = false, precision = 10, scale = 2)
    private BigDecimal prepayment;
    
    @Basic(optional = false)
    @Column(name = "fulfilled", nullable = false)
    private Boolean fulfilled;
    
    @JoinColumn(name = "idReservations", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch= FetchType.EAGER)
    private DBReservation reservation;

    public DBReservationOption()
    {
    }

    private DBReservationOption(Integer id)
    {
        this.id = id;
    }

    private DBReservationOption(Integer id, Date expiration, BigDecimal prepayment, Boolean fulfilled)
    {
        this.id = id;
        this.expiration = expiration;
        this.prepayment = prepayment;
        this.fulfilled = fulfilled;
    }

    public static DBReservationOption newReservationoptions()
    {
        return new DBReservationOption();
    }

    public static DBReservationOption newReservationoptions(Integer id)
    {
        return new DBReservationOption(id);
    }

    public static DBReservationOption newReservationoptions(Integer id, Date expiration, BigDecimal prepayment, Boolean fulfilled)
    {
        return new DBReservationOption(id, expiration, prepayment, fulfilled);
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public BigDecimal getPrepayment()
    {
        return prepayment;
    }

    public void setPrepayment(BigDecimal prepayment)
    {
        this.prepayment = prepayment;
    }

    public Boolean getFulfilled()
    {
        return fulfilled;
    }

    public void setFulfilled(Boolean fulfilled)
    {
        this.fulfilled = fulfilled;
    }

    public DBReservation getReservation()
    {
        return reservation;
    }

    public void setReservation(DBReservation idReservations)
    {
        this.reservation = idReservations;
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
        if (!(object instanceof DBReservationOption))
        {
            return false;
        }
        DBReservationOption other = (DBReservationOption) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "hotelsoftware.database.model.Reservationoptions[ id=" + id + " ]";
    }

    public Date getExpiration()
    {
        return expiration;
    }

    public void setExpiration(Date expiration)
    {
        this.expiration = expiration;
    }

    public String getComment()
    {
        return comment;
    }

    public void setComment(String comment)
    {
        this.comment = comment;
    }
}
