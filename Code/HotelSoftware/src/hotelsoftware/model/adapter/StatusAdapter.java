/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.adapter;

import hotelsoftware.model.domain.room.IRoomStatus;
import hotelsoftware.model.domain.room.RoomStatus;

/**
 *
 * @author Tobias
 */
public class StatusAdapter extends at.fhv.roomanizer.domain.room.Status
        implements hotelsoftware.model.adapter.Adapter<hotelsoftware.model.domain.room.IRoomStatus>
{
    private hotelsoftware.model.domain.room.IRoomStatus ourRoomStatus;

    public StatusAdapter(hotelsoftware.model.domain.room.IRoomStatus roomStatus)
    {
        this.ourRoomStatus = roomStatus;
    }
    
    public StatusAdapter()
    {
    }

    @Override
    public int getId()
    {
        return this.ourRoomStatus.getId();
    }

    @Override
    public String getName()
    {
        return this.ourRoomStatus.getStatusName();
    }

    @Override
    public void setId(int id)
    {
        this.ourRoomStatus.setId(id);
    }

    @Override
    public void setName(String name)
    {
        this.ourRoomStatus.setStatusName(name);
    }

    @Override
    public IRoomStatus getOurType()
    {
        return ourRoomStatus;
    }

    @Override
    public void setOurType(IRoomStatus type)
    {
        this.ourRoomStatus = type;
    }
}
