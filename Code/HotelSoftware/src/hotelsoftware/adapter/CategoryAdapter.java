package hotelsoftware.adapter;

import at.fhv.roomanizer.domain.room.Category;
import at.fhv.roomanizer.domain.room.Price;
import at.fhv.roomanizer.domain.room.Room;
import java.util.Date;
import java.util.List;


/**
 *
 * @author Johannes
 */
public class CategoryAdapter extends Category
 {

    @Override
    public void addRoom(Room r)
    {
        super.addRoom(r);
    }

    @Override
    public List<Room> checkAvailability(Date start, Date end, int amount)
    {
        return super.checkAvailability(start, end, amount);
    }

    @Override
    public int getId()
    {
        return super.getId();
    }

    @Override
    public String getName()
    {
        return super.getName();
    }

    @Override
    public List<Price> getPrice()
    {
        return super.getPrice();
    }

    @Override
    public List<Room> getRooms()
    {
        return super.getRooms();
    }

    @Override
    public void setId(int id)
    {
        super.setId(id);
    }

    @Override
    public void setName(String name)
    {
        super.setName(name);
    }

    @Override
    public void setPrice(List<Price> price)
    {
        super.setPrice(price);
    }

    @Override
    public void setRooms(List<Room> rooms)
    {
        super.setRooms(rooms);
    }

}
