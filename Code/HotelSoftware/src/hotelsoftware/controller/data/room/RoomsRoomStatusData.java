/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.controller.data.room;

import java.util.Date;

/**
 *
 * @author Dunst
 */
public interface RoomsRoomStatusData
{
    Date getEnd();

    RoomData getRoomData();

    Integer getRoomsroomstatusPK();

    RoomStatusData getRoomstatusData();

    Date getStart();

}
