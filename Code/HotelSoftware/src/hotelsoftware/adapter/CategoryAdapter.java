package hotelsoftware.adapter;

import at.fhv.roomanizer.domain.room.Category;
import at.fhv.roomanizer.domain.room.IRoom;
import at.fhv.roomanizer.domain.room.Price;
import at.fhv.roomanizer.domain.room.Room;
import hotelsoftware.model.domain.room.RoomCategory;
import hotelsoftware.util.HelperFunctions;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Johannes
 */
public class CategoryAdapter extends Category implements Adapter<hotelsoftware.model.domain.room.RoomCategory>
{
    private hotelsoftware.model.domain.room.RoomCategory category;

    @Override
    public void addRoom(Room r)
    {
        throw new UnsupportedOperationException("Diese Methode gibt es bei uns nicht");
    }

    @Override
    public List<Room> checkAvailability(Date start, Date end, int amount)
    {
        return new LinkedList(HelperFunctions.castCollectionUp(HelperFunctions.getAdaptedList(category.getFreeRooms(start, end), RoomAdapter.class), Room.class, RoomAdapter.class));
    }

    @Override
    public int getId()
    {
        return category.getId();
    }

    @Override
    public String getName()
    {
        return category.getName();
    }

    @Override
    public List<Price> getPrice()
    {
       throw new UnsupportedOperationException();
    }

    @Override
    public List<Room> getRooms()
    {
        return new LinkedList(HelperFunctions.castCollectionUp(HelperFunctions.getAdaptedList(category.getRooms(), RoomAdapter.class), Room.class, RoomAdapter.class));
    }

    @Override
    public void setId(int id)
    {
       category.setId(id);
    }

    @Override
    public void setName(String name)
    {
       category.setName(name);
    }

    @Override
    public void setPrice(List<Price> price)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setRooms(List<Room> rooms)
    {
        category.setRooms(HelperFunctions.getOurList(HelperFunctions.castCollectionDown(rooms, Room.getClass(), RoomAdapter.class)));
    }

    @Override
    public RoomCategory getOurType()
    {
        return category;
    }

    @Override
    public void setOurType(RoomCategory type)
    {
        category = type;
    }
}
