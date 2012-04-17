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
public class DBReservationitemsPK implements Serializable
{
    @Basic(optional = false)
    @Column(name = "idReservations", nullable = false)
    private int idReservations;
    @Basic(optional = false)
    @Column(name = "idRoomCategories", nullable = false)
    private int idRoomCategories;

    public DBReservationitemsPK()
    {
    }

    public DBReservationitemsPK(int idReservations, int idRoomCategories)
    {
        this.idReservations = idReservations;
        this.idRoomCategories = idRoomCategories;
    }

    public int getIdReservations()
    {
        return idReservations;
    }

    public void setIdReservations(int idReservations)
    {
        this.idReservations = idReservations;
    }

    public int getIdRoomCategories()
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
        if(!(object instanceof DBReservationitemsPK))
        {
            return false;
        }
        DBReservationitemsPK other = (DBReservationitemsPK) object;
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
