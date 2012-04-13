/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.database.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mohi
 */
@Entity
@Table(name = "reservationitems", catalog = "roomanizer", schema = "")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Reservationitems.findAll", query = "SELECT r FROM Reservationitems r"),
    @NamedQuery(name = "Reservationitems.findByIdReservations", query = "SELECT r FROM Reservationitems r WHERE r.reservationitemsPK.idReservations = :idReservations"),
    @NamedQuery(name = "Reservationitems.findByIdRoomCategories", query = "SELECT r FROM Reservationitems r WHERE r.reservationitemsPK.idRoomCategories = :idRoomCategories"),
    @NamedQuery(name = "Reservationitems.findByAmount", query = "SELECT r FROM Reservationitems r WHERE r.amount = :amount")
})
public class Reservationitems implements Serializable
{
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ReservationitemsPK reservationitemsPK;
    @Basic(optional = false)
    @Column(name = "amount", nullable = false)
    private int amount;
    @JoinColumn(name = "idRoomCategories", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Roomcategories roomcategories;
    @JoinColumn(name = "idReservations", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Reservations reservations;

    private Reservationitems()
    {
    }
    public static Reservationitems newReservationitems()
    {
        return new Reservationitems();
    }

    public Reservationitems(ReservationitemsPK reservationitemsPK)
    {
        this.reservationitemsPK = reservationitemsPK;
    }

    public Reservationitems(ReservationitemsPK reservationitemsPK, int amount)
    {
        this.reservationitemsPK = reservationitemsPK;
        this.amount = amount;
    }

    public Reservationitems(int idReservations, int idRoomCategories)
    {
        this.reservationitemsPK = new ReservationitemsPK(idReservations, idRoomCategories);
    }

    public ReservationitemsPK getReservationitemsPK()
    {
        return reservationitemsPK;
    }

    public void setReservationitemsPK(ReservationitemsPK reservationitemsPK)
    {
        this.reservationitemsPK = reservationitemsPK;
    }

    public int getAmount()
    {
        return amount;
    }

    public void setAmount(int amount)
    {
        this.amount = amount;
    }

    public Roomcategories getRoomcategories()
    {
        return roomcategories;
    }

    public void setRoomcategories(Roomcategories roomcategories)
    {
        this.roomcategories = roomcategories;
    }

    public Reservations getReservations()
    {
        return reservations;
    }

    public void setReservations(Reservations reservations)
    {
        this.reservations = reservations;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (reservationitemsPK != null ? reservationitemsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if(!(object instanceof Reservationitems))
        {
            return false;
        }
        Reservationitems other = (Reservationitems) object;
        if((this.reservationitemsPK == null && other.reservationitemsPK != null) || (this.reservationitemsPK != null && !this.reservationitemsPK.equals(other.reservationitemsPK)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "hotelsoftware.database.model.Reservationitems[ reservationitemsPK=" + reservationitemsPK + " ]";
    }
    
}
