package hotelsoftware.model.database.reservation;

import hotelsoftware.model.database.room.DBRoomCategory;
import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Die Klasse bildet alle Reservierungspositionen auf der Datenbank auf.
 * @author mohi
 */
@Entity
@Table(name = "reservationitems", catalog = "roomanizer", schema = "")
@XmlRootElement
public class DBReservationItem implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    protected DBReservationItemPK reservationitemsPK;
    
    @Basic(optional = false)
    @Column(name = "amount", nullable = false)
    private Integer amount;
    
    @JoinColumn(name = "idRoomCategories", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch= FetchType.EAGER)
    private DBRoomCategory roomcategories;
    
    @JoinColumn(name = "idReservations", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private DBReservation reservations;

    public DBReservationItem()
    {
    }
    public DBReservationItem(DBReservationItemPK reservationitemsPK)
    {
        this.reservationitemsPK = reservationitemsPK;
    }

    public DBReservationItem(DBReservationItemPK reservationitemsPK, int amount)
    {
        this.reservationitemsPK = reservationitemsPK;
        this.amount = amount;
    }

    public DBReservationItem(int idReservations, int idRoomCategories)
    {
        this.reservationitemsPK = new DBReservationItemPK(idReservations, idRoomCategories);
    }

    public DBReservationItemPK getReservationitemsPK()
    {
        return reservationitemsPK;
    }

    public void setReservationitemsPK(DBReservationItemPK reservationitemsPK)
    {
        this.reservationitemsPK = reservationitemsPK;
    }

    public Integer getAmount()
    {
        return amount;
    }

    public void setAmount(int amount)
    {
        this.amount = amount;
    }

    public DBRoomCategory getRoomcategory()
    {
        return roomcategories;
    }

    public void setRoomcategory(DBRoomCategory roomcategories)
    {
        this.roomcategories = roomcategories;
    }

    public DBReservation getReservations()
    {
        return reservations;
    }

    public void setReservations(DBReservation reservations)
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
        if(!(object instanceof DBReservationItem))
        {
            return false;
        }
        DBReservationItem other = (DBReservationItem) object;
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
