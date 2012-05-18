/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.adapter;

import at.fhv.roomanizer.domain.room.*;
import at.fhv.roomanizer.persistence.manager.IRoomManager;
import hotelsoftware.model.domain.room.RoomCategory;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Dunst
 */
public class RoomManagerAdapter implements IRoomManager
{

    @Override
    public List<Category> getAllCategories() throws IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException
    {
        RoomCategory.getAllCategorys();
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Room> getAllRooms() throws IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Status> getAllStatus() throws IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Category getCategoryByName(String name) throws IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Price getCategoryPriceByDate(Category category, Date date) throws IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public RoomStatus getRoomStatusByDate(Room room, Date date) throws IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Room> getRoomsByCategory(Category category) throws IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Season getSeasonByDate(Date date) throws IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Status getStatusByName(String name) throws IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void saveRoomStatus(RoomStatus status) throws IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
