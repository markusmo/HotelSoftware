package hotelsoftware.model.database.reservation;

import hotelsoftware.model.database.room.DBRoomCategory;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Die Klasse bildet alle Reservierungspositionen auf der Datenbank auf.
 * @author mohi
 */
@Entity
@Table(name = "reservationitems", catalog = "`roomanizer`", schema = "")
@XmlRootElement
public class DBReservationItem implements Serializable
{
    @Basic(optional = false)
    @Column(name = "amount")
    private int amount;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dBReservationItem")
    private Set<DBReservedExtraServices> reservedextraservicesSet;
    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    protected DBReservationItemPK reservationitemsPK;
    
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

    public DBReservationItemPK getReservationitemsPK()
    {
        return reservationitemsPK;
    }

    public void setReservationitemsPK(DBReservationItemPK reservationitemsPK)
    {
        this.reservationitemsPK = reservationitemsPK;
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

    public int getAmount()
    {
        return amount;
    }

    public void setAmount(int amount)
    {
        this.amount = amount;
    }

    @XmlTransient
    public Set<DBReservedExtraServices> getReservedextraservicesSet()
    {
        return reservedextraservicesSet;
    }

    public void setReservedextraservicesSet(Set<DBReservedExtraServices> reservedextraservicesSet)
    {
        this.reservedextraservicesSet = reservedextraservicesSet;
    }
    
}
