/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.controller.checkin;

import hotelsoftware.controller.data.room.RoomCategoryData;
import hotelsoftware.controller.data.room.RoomData;
import hotelsoftware.model.domain.room.IRoom;
import hotelsoftware.model.domain.room.IRoomCategory;
import hotelsoftware.model.domain.room.Room;
import hotelsoftware.model.domain.room.RoomCategory;

/**
 *
 * @author Dunst
 */
public class NoRoomsInCategoryAvailableException extends Exception
{
    private RoomCategoryData category;
    private RoomData room;

    public RoomCategoryData getCategory()
    {
        return category;
    }

    public RoomData getRoom()
    {
        return room;
    }
    
    public NoRoomsInCategoryAvailableException(IRoomCategory cat, IRoom room)
    {
        this.category = cat;
        this.room = room;
    }
    
}
