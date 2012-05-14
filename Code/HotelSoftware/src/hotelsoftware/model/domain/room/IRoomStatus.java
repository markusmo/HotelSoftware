/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.room;

import hotelsoftware.controller.data.room.RoomStatusData;

/**
 *
 * @author Kno
 */
public interface IRoomStatus extends RoomStatusData{

    Integer getId();

    String getStatusName();

    void setId(int id);

    void setStatusName(String statusName);
    
}
