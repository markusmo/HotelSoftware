package hotelsoftwareonline.controller;

import hotelsoftware.model.domain.room.IRoomCategory;
import hotelsoftware.model.domain.room.RoomCategory;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

/**
 *
 * @author Johannes
 */
public class ReservationController
{
    public static Collection<IRoomCategory> getFreeCategories(Date start, Date end)
    {
        Collection<IRoomCategory> categories = new LinkedList<IRoomCategory>();
        for (IRoomCategory cat : RoomCategory.getAllCategorys())
        {
            if (!cat.getFreeRooms(start, end).isEmpty())
            {
                categories.add(cat);
            }
        }
        return categories;
    }
}
