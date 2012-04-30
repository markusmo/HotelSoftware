/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.controller.checkin;

import hotelsoftware.model.domain.room.Room;
import hotelsoftware.model.domain.room.RoomCategory;
import hotelsoftware.controller.data.room.RoomCategoryData;
import hotelsoftware.controller.data.room.RoomData;

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
    
    public NoRoomsInCategoryAvailableException(RoomCategory cat, Room room)
    {
        this.category = cat;
        this.room = room;
    }
    
}
