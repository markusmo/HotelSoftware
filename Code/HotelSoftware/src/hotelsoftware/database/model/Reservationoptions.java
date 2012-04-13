/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.database.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mohi
 */
@Entity
@Table(name = "reservationoptions", catalog = "roomanizer", schema = "")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Reservationoptions.findAll", query = "SELECT r FROM Reservationoptions r"),
    @NamedQuery(name = "Reservationoptions.findById", query = "SELECT r FROM Reservationoptions r WHERE r.id = :id"),
    @NamedQuery(name = "Reservationoptions.findByExpiration", query = "SELECT r FROM Reservationoptions r WHERE r.expiration = :expiration"),
    @NamedQuery(name = "Reservationoptions.findByPrepayment", query = "SELECT r FROM Reservationoptions r WHERE r.prepayment = :prepayment"),
    @NamedQuery(name = "Reservationoptions.findByFulfilled", query = "SELECT r FROM Reservationoptions r WHERE r.fulfilled = :fulfilled")
})
public class Reservationoptions implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "expiration", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date expiration;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "prepayment", nullable = false, precision = 10, scale = 2)
    private BigDecimal prepayment;
    @Basic(optional = false)
    @Column(name = "fulfilled", nullable = false)
    private boolean fulfilled;
    @JoinColumn(name = "idReservations", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Reservations idReservations;

    private Reservationoptions()
    {
    }

    private Reservationoptions(Integer id)
    {
        this.id = id;
    }

    private Reservationoptions(Integer id, Date expiration, BigDecimal prepayment, boolean fulfilled)
    {
        this.id = id;
        this.expiration = expiration;
        this.prepayment = prepayment;
        this.fulfilled = fulfilled;
    }

    public static Reservationoptions newReservationoptions()
    {
        return new Reservationoptions();
    }

    public static Reservationoptions newReservationoptions(Integer id)
    {
        return new Reservationoptions(id);
    }

    public static Reservationoptions newReservationoptions(Integer id, Date expiration, BigDecimal prepayment, boolean fulfilled)
    {
        return new Reservationoptions(id, expiration, prepayment, fulfilled);
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Date getExpiration()
    {
        return expiration;
    }

    public void setExpiration(Date expiration)
    {
        this.expiration = expiration;
    }

    public BigDecimal getPrepayment()
    {
        return prepayment;
    }

    public void setPrepayment(BigDecimal prepayment)
    {
        this.prepayment = prepayment;
    }

    public boolean getFulfilled()
    {
        return fulfilled;
    }

    public void setFulfilled(boolean fulfilled)
    {
        this.fulfilled = fulfilled;
    }

    public Reservations getIdReservations()
    {
        return idReservations;
    }

    public void setIdReservations(Reservations idReservations)
    {
        this.idReservations = idReservations;
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
        if (!(object instanceof Reservationoptions))
        {
            return false;
        }
        Reservationoptions other = (Reservationoptions) object;
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
}
