/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.adapter;

import at.fhv.roomanizer.domain.room.*;
import hotelsoftware.model.domain.room.IRoomRoomStatus;
import java.util.Date;

/**
 *
 * @author Tobias
 */
public class RoomStatusAdapter extends at.fhv.roomanizer.domain.room.RoomStatus
        implements hotelsoftware.model.adapter.Adapter<hotelsoftware.model.domain.room.IRoomRoomStatus>
{
    private hotelsoftware.model.domain.room.IRoomRoomStatus ourRoomsRoomStatus;
    
    public RoomStatusAdapter()
    {
    }

    public RoomStatusAdapter(hotelsoftware.model.domain.room.IRoomRoomStatus roomsRoomStatus)
    {
        this.ourRoomsRoomStatus = roomsRoomStatus;
    }

    @Override
    public Date getEnd()
    {
        return this.ourRoomsRoomStatus.getEnd();
    }

    @Override
    public IStatus getIStatus()
    {
        return new StatusAdapter(this.ourRoomsRoomStatus.getRoomstatus());
    }

    @Override
    public int getId()
    {
        return this.ourRoomsRoomStatus.getRoomsroomstatusPK();
    }

    @Override
    public Room getRoom()
    {
        return new RoomAdapter(this.ourRoomsRoomStatus.getRoom());
    }

    @Override
    public Date getStart()
    {
        return this.ourRoomsRoomStatus.getStart();
    }

    @Override
    public Status getStatus()
    {
        return new StatusAdapter(this.ourRoomsRoomStatus.getRoomstatus());
    }

    @Override
    public void setEnd(Date end)
    {
        this.ourRoomsRoomStatus.setEnd(end);
    }

    @Override
    public void setId(int id)
    {
        this.ourRoomsRoomStatus.setRoomsroomstatusPK(id);
    }

    @Override
    public void setRoom(Room room)
    {
        RoomAdapter adapter = (RoomAdapter) room;
        this.ourRoomsRoomStatus.setRoom(adapter.getOurType());
    }

    @Override
    public void setStart(Date start)
    {
        this.ourRoomsRoomStatus.setStart(start);
    }

    @Override
    public void setStatus(Status status)
    {
        StatusAdapter adapter = (StatusAdapter) status;
        this.ourRoomsRoomStatus.setRoomstatus(adapter.getOurType());
    }

    @Override
    public IRoomRoomStatus getOurType()
    {
        return ourRoomsRoomStatus;
    }

    @Override
    public void setOurType(IRoomRoomStatus type)
    {
        this.ourRoomsRoomStatus = type;
    }
}
