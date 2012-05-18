package hotelsoftware.adapter;

import at.fhv.roomanizer.domain.room.Category;
import at.fhv.roomanizer.domain.room.Price;
import at.fhv.roomanizer.domain.room.Room;
import hotelsoftware.model.domain.room.IRoomCategory;
import hotelsoftware.model.domain.room.RoomCategory;
import hotelsoftware.util.HelperFunctions;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Johannes
 */
public class CategoryAdapter extends Category implements Adapter<hotelsoftware.model.domain.room.IRoomCategory>
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
        return new LinkedList(HelperFunctions.castCollectionUp(HelperFunctions.getAdaptedList(category.getPrice(), PriceAdapter.class), Price.class, PriceAdapter.class));
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
        category.setPrice(HelperFunctions.getOurList(HelperFunctions.castCollectionDown(price, Price.class, PriceAdapter.class)));
    }

    @Override
    public void setRooms(List<Room> rooms)
    {
        category.setRooms(HelperFunctions.getOurList(HelperFunctions.castCollectionDown(rooms, Room.class, RoomAdapter.class)));
    }

    @Override
    public IRoomCategory getOurType()
    {
        return category;
    }

    @Override
    public void setOurType(IRoomCategory type)
    {
        category = (RoomCategory) type;
    }
}
