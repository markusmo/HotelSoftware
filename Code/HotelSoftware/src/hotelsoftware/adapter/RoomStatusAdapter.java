/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.adapter;

import at.fhv.roomanizer.domain.room.*;
import java.util.Date;

/**
 *
 * @author Tobias
 */
public class RoomStatusAdapter extends at.fhv.roomanizer.domain.room.RoomStatus implements hotelsoftware.adapter.Adapter{

    private hotelsoftware.model.domain.room.RoomsRoomStatus ourRoomsRoomStatus;
    
    public RoomStatusAdapter(hotelsoftware.model.domain.room.RoomsRoomStatus roomsRoomStatus){
        this.ourRoomsRoomStatus = roomsRoomStatus;
    }
    
    @Override
    public Date getEnd() {
        return this.ourRoomsRoomStatus.getEnd();
    }

    @Override
    public IStatus getIStatus() {
        return (IStatus)this.ourRoomsRoomStatus.getRoomstatus();
    }

    @Override
    public int getId() {
        return this.ourRoomsRoomStatus.getRoomsroomstatusPK();
    }

    @Override
    public Room getRoom() {
        return this.ourRoomsRoomStatus.getRoom();
    }

    @Override
    public Date getStart() {
        return this.ourRoomsRoomStatus.getStart();
    }

    @Override
    public Status getStatus() {
        return (Status)this.ourRoomsRoomStatus.getRoomstatus();
    }

    @Override
    public void setEnd(Date end) {
        this.ourRoomsRoomStatus.setEnd(end);
    }

    @Override
    public void setId(int id) {
        this.ourRoomsRoomStatus.setRoomsroomstatusPK(id);
    }

    @Override
    public void setRoom(Room room) {
        this.ourRoomsRoomStatus.setRoom((IRoom)room);
    }

    @Override
    public void setStart(Date start) {
        this.ourRoomsRoomStatus.setStart(start);
    }

    @Override
    public void setStatus(Status status) {
        this.ourRoomsRoomStatus.setRoomstatus((IRoomStatus)status);
    }

    @Override
    public Object getOurType() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setOurType(Object type) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}

