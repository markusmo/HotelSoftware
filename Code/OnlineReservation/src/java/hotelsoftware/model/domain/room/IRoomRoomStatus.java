/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.room;

import java.util.Date;

/**
 *
 * @author Kno
 */
public interface IRoomRoomStatus
{
    IRoom getRoom();

    IRoomStatus getRoomstatus();

    void setEnd(Date end);

    void setRoom(IRoom room);

    void setRoomsroomstatusPK(Integer id);

    void setRoomstatus(IRoomStatus roomstatus);

    void setStart(Date start);
}
