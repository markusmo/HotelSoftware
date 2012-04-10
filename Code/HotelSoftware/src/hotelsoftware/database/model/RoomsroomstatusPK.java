/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.database.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author mohi
 */
@Embeddable
public class RoomsroomstatusPK implements Serializable
{
    @Basic(optional = false)
    @Column(name = "idRooms", nullable = false)
    private int idRooms;
    @Basic(optional = false)
    @Column(name = "idRoomStatus", nullable = false)
    private int idRoomStatus;

    public RoomsroomstatusPK()
    {
    }

    public RoomsroomstatusPK(int idRooms, int idRoomStatus)
    {
        this.idRooms = idRooms;
        this.idRoomStatus = idRoomStatus;
    }

    public int getIdRooms()
    {
        return idRooms;
    }

    public void setIdRooms(int idRooms)
    {
        this.idRooms = idRooms;
    }

    public int getIdRoomStatus()
    {
        return idRoomStatus;
    }

    public void setIdRoomStatus(int idRoomStatus)
    {
        this.idRoomStatus = idRoomStatus;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (int) idRooms;
        hash += (int) idRoomStatus;
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if(!(object instanceof RoomsroomstatusPK))
        {
            return false;
        }
        RoomsroomstatusPK other = (RoomsroomstatusPK) object;
        if(this.idRooms != other.idRooms)
        {
            return false;
        }
        if(this.idRoomStatus != other.idRoomStatus)
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "hotelsoftware.database.model.RoomsroomstatusPK[ idRooms=" + idRooms + ", idRoomStatus=" + idRoomStatus + " ]";
    }
    
}
