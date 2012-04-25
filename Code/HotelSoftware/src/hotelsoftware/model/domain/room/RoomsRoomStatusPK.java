package hotelsoftware.model.domain.room;

import hotelsoftware.model.database.room.DBRoomsRoomStatusPK;
import javax.persistence.Basic;
import javax.persistence.Column;

/**
 * Identifiziert einen Zimmerstatus auf einen Zeitraum eindeutig ab.
 * @author Dunst
 */
public class RoomsRoomStatusPK
{
    private Integer idRooms;
    private Integer idRoomStatus;

    public RoomsRoomStatusPK()
    {
    }

    public RoomsRoomStatusPK(int idRooms, int idRoomStatus)
    {
        this.idRooms = idRooms;
        this.idRoomStatus = idRoomStatus;
    }

    public Integer getIdRooms()
    {
        return idRooms;
    }

    public void setIdRooms(int idRooms)
    {
        this.idRooms = idRooms;
    }

    public Integer getIdRoomStatus()
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
        if(!(object instanceof DBRoomsRoomStatusPK))
        {
            return false;
        }
        RoomsRoomStatusPK other = (RoomsRoomStatusPK) object;
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
