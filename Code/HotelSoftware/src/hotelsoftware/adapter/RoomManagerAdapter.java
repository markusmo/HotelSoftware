/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.adapter;

import at.fhv.roomanizer.domain.room.*;
import at.fhv.roomanizer.persistence.manager.IRoomManager;
import hotelsoftware.model.database.FailedToSaveToDatabaseException;
import hotelsoftware.model.domain.room.RoomCategory;
import hotelsoftware.model.domain.room.RoomSaver;
import hotelsoftware.model.domain.room.RoomsRoomStatus;
import hotelsoftware.util.HelperFunctions;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    /**
     * Returns the singleton-instance of the HabitationManagerAdapter
     *
     * @return
     */
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
        StatusAdapter sa = new StatusAdapter();
        sa.setOurType(hotelsoftware.model.domain.room.RoomStatus.getRoomStatusByName(name));
        return sa;
    }

    @Override
    public void saveRoomStatus(RoomStatus status) throws IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException
    {
        try
        {
            RoomsRoomStatus ourStatus = ((Adapter<RoomsRoomStatus>)status).getOurType();
            Set<RoomsRoomStatus> statusse = new HashSet<RoomsRoomStatus>();
            statusse.add(ourStatus);
            RoomSaver.getInstance().saveOrUpdate(null, null, null, statusse);
            throw new UnsupportedOperationException("Not supported yet.");
        }
        catch (FailedToSaveToDatabaseException ex)
        {
            Logger.getLogger(RoomManagerAdapter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
