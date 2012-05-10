package hotelsoftware.model.domain.room;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Bildet den Status eines Zimmer auf einen Zeitraum ab.
 * @author Dunst
 */

public class RoomsRoomStatus implements IRoomsRoomStatus 
{
    private Date start;
    private Date end;
    protected RoomsRoomStatusPK roomsroomstatusPK;
    private RoomStatus roomstatus;
    private Room room;

    public RoomsRoomStatus()
    {
    }

    public Room getRoom()
    {
        return room;
    }

    public void setRoom(Room room)
    {
        this.room = room;
    }
    
    

    public RoomsRoomStatusPK getRoomsroomstatusPK()
    {
        return roomsroomstatusPK;
    }

    public void setRoomsroomstatusPK(RoomsRoomStatusPK roomsroomstatusPK)
    {
        this.roomsroomstatusPK = roomsroomstatusPK;
    }

    public RoomStatus getRoomstatus()
    {
        return roomstatus;
    }

    public void setRoomstatus(RoomStatus roomstatus)
    {
        this.roomstatus = roomstatus;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (roomsroomstatusPK != null ? roomsroomstatusPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if(!(object instanceof RoomsRoomStatus))
        {
            return false;
        }
        RoomsRoomStatus other = (RoomsRoomStatus) object;
        if((this.roomsroomstatusPK == null && other.roomsroomstatusPK != null) || (this.roomsroomstatusPK != null && !this.roomsroomstatusPK.equals(other.roomsroomstatusPK)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "hotelsoftware.database.model.Roomsroomstatus[ roomsroomstatusPK=" + roomsroomstatusPK + " ]";
    }

    public Date getStart()
    {
        return start;
    }

    public void setStart(Date start)
    {
        this.start = start;
    }

    public Date getEnd()
    {
        return end;
    }

    public void setEnd(Date end)
    {
        this.end = end;
    }
    
}

