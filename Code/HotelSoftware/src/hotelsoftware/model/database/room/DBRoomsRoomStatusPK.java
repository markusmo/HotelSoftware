package hotelsoftware.model.database.room;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Bildet den Primaerschluessel fuer DBRoomsRoomStatus ab.
 * @author mohi
 */
@Embeddable
public class DBRoomsRoomStatusPK implements Serializable
{
    @Basic(optional = false)
    @Column(name = "idRooms", nullable = false)
    private Integer idRooms;
    @Basic(optional = false)
    @Column(name = "idRoomStatus", nullable = false)
    private Integer idRoomStatus;

    public DBRoomsRoomStatusPK()
    {
    }

    public DBRoomsRoomStatusPK(int idRooms, int idRoomStatus)
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
        DBRoomsRoomStatusPK other = (DBRoomsRoomStatusPK) object;
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
