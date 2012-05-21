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
        final DBReservationItemPK other = (DBReservationItemPK) obj;
        if (this.idReservations != other.idReservations && (this.idReservations == null || !this.idReservations.equals(other.idReservations)))
        {
            return false;
        }
        if (this.idRoomCategories != other.idRoomCategories && (this.idRoomCategories == null || !this.idRoomCategories.equals(other.idRoomCategories)))
        {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 79 * hash + (this.idReservations != null ? this.idReservations.hashCode() : 0);
        hash = 79 * hash + (this.idRoomCategories != null ? this.idRoomCategories.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString()
    {
        return "hotelsoftware.database.model.ReservationitemsPK[ idReservations=" + idReservations + ", idRoomCategories=" + idRoomCategories + " ]";
    }
    
}
