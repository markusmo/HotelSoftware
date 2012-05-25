/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.adapter;

import at.fhv.roomanizer.domain.room.*;
import at.fhv.roomanizer.persistence.manager.IRoomManager;
import hotelsoftware.model.domain.room.IRoomRoomStatus;
import hotelsoftware.model.database.manager.RoomManager;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Dunst
 */
public class RoomManagerAdapter implements IRoomManager
{
    private RoomManagerAdapter()
    {
    }
    private static RoomManagerAdapter INSTANCE;

    public static RoomManagerAdapter getInstance()
    {
        if (INSTANCE == null)
        {
            INSTANCE = new RoomManagerAdapter();
        }

        return INSTANCE;
    }

    @Override
    public List<Category> getAllCategories() throws IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException
    {
        //return HelperFunctions.getAdaptedList(RoomCategory.getAllCategorys(), CategoryAdapter.class);
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Room> getAllRooms() throws IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException
    {
        // return HelperFunctions.getAdaptedList(hotelsoftware.model.domain.room.Room.getAllRooms(), Room.class);
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
        //RoomCategory.getCategoryByName(name)
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
        return new StatusAdapter(hotelsoftware.model.domain.room.RoomStatus.getRoomStatusByName(name));
    }

    @Override
    public void saveRoomStatus(RoomStatus status) throws IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException
    {
        IRoomRoomStatus ourStatus = ((Adapter<IRoomRoomStatus>) status).getOurType();
        RoomManager.getInstance().saveRoomsRoomStatus(ourStatus);
    }
}
