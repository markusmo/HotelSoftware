/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.checkin;

import hotelsoftware.model.domain.room.Room;
import hotelsoftware.model.domain.room.RoomCategory;
import hotelsoftware.model.domain.room.data.RoomCategoryData;
import hotelsoftware.model.domain.room.data.RoomData;

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
