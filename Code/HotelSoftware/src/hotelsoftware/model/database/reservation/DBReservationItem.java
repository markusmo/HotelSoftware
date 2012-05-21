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
@Table(name = "reservationitems", catalog = "`roomanizer-dev`", schema = "")
@XmlRootElement
public class DBReservationItem implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    protected DBReservationItemPK reservationitemsPK;
    
    @Basic(optional = false)
    @Column(name = "amount", nullable = false)
    private Integer amount;
    
    @JoinColumn(name = "idRoomCategories", referencedColumnName = "id", updatable=false, insertable=false)
    @ManyToOne(optional = false, fetch= FetchType.EAGER)
    private DBRoomCategory roomCategory;
    
    @JoinColumn(name = "idReservations", referencedColumnName = "id", updatable=false, insertable=false)
    @ManyToOne(optional = false)
    private DBReservation reservation;

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

    public DBRoomCategory getRoomCategory()
    {
        return roomCategory;
    }

    public void setRoomCategory(DBRoomCategory roomCategory)
    {
        this.roomCategory = roomCategory;
    }

    public DBReservation getReservation()
    {
        return reservation;
    }

    public void setReservation(DBReservation reservations)
    {
        this.reservation = reservations;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final DBReservationItem other = (DBReservationItem) obj;
        if (this.reservationitemsPK != other.reservationitemsPK && (this.reservationitemsPK == null || !this.reservationitemsPK.equals(other.reservationitemsPK)))
        {
            return false;
        }
        if (this.amount != other.amount && (this.amount == null || !this.amount.equals(other.amount)))
        {
            return false;
        }
        if (this.roomCategory != other.roomCategory && (this.roomCategory == null || !this.roomCategory.equals(other.roomCategory)))
        {
            return false;
        }
        if (this.reservation != other.reservation && (this.reservation == null || !this.reservation.equals(other.reservation)))
        {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 29 * hash + (this.reservationitemsPK != null ? this.reservationitemsPK.hashCode() : 0);
        hash = 29 * hash + (this.amount != null ? this.amount.hashCode() : 0);
        hash = 29 * hash + (this.roomCategory != null ? this.roomCategory.hashCode() : 0);
        hash = 29 * hash + (this.reservation != null ? this.reservation.hashCode() : 0);
        return hash;
    }


    @Override
    public String toString()
    {
        return "hotelsoftware.database.model.Reservationitems[ reservationitemsPK=" + reservationitemsPK + " ]";
    }
    
}
