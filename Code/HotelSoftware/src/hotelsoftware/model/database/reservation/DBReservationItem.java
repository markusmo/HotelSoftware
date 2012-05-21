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
    public String toString()
    {
        return "hotelsoftware.database.model.Reservationitems[ reservationitemsPK=" + reservationitemsPK + " ]";
    }
    
}
