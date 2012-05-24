package hotelsoftware.model.database.reservation;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Diese Klasse bildet den Primaerschluessel einer Reservierungsposition ab
 * @author mohi
 */
@Embeddable
public class DBReservationItemPK implements Serializable
{
    @Basic(optional = false)
    @Column(name = "idReservations", nullable = false)
    private Integer idReservations;
    @Basic(optional = false)
    @Column(name = "idRoomCategories", nullable = false)
    private Integer idRoomCategories;

    public DBReservationItemPK()
    {
    }

    public DBReservationItemPK(int idReservations, int idRoomCategories)
    {
        this.idReservations = idReservations;
        this.idRoomCategories = idRoomCategories;
    }

    public Integer getIdReservations()
    {
        return idReservations;
    }

    public void setIdReservations(int idReservations)
    {
        this.idReservations = idReservations;
    }

    public Integer getIdRoomCategories()
    {
        return idRoomCategories;
    }

    public void setIdRoomCategories(int idRoomCategories)
    {
        this.idRoomCategories = idRoomCategories;
    }

    @Override
    public String toString()
    {
        return "hotelsoftware.database.model.ReservationitemsPK[ idReservations=" + idReservations + ", idRoomCategories=" + idRoomCategories + " ]";
    }
    
}
