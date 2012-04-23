/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.database.reservation;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
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
    public int hashCode()
    {
        int hash = 0;
        hash += (int) idReservations;
        hash += (int) idRoomCategories;
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if(!(object instanceof DBReservationItemPK))
        {
            return false;
        }
        DBReservationItemPK other = (DBReservationItemPK) object;
        if(this.idReservations != other.idReservations)
        {
            return false;
        }
        if(this.idRoomCategories != other.idRoomCategories)
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "hotelsoftware.database.model.ReservationitemsPK[ idReservations=" + idReservations + ", idRoomCategories=" + idRoomCategories + " ]";
    }
    
}
