/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.room;

import java.util.Collection;
import java.util.Set;

/**
 *
 * @author mohi
 */
public class RoomFacade
{
    private RoomFacade()
    {
    }
    
    public static RoomFacade getInstance()
    {
        return RoomFacadeHolder.INSTANCE;
    }
    
    private static class RoomFacadeHolder
    {
        private static final RoomFacade INSTANCE = new RoomFacade();
    }
    
    public Room getRoomByNumber(String number)
    {
        return Room.getRoomByNumber(number);
    }
    
    public Set<Room> getRoomsByCategory(RoomCategory cat)
    {
        return Room.getRoomsByCategory(cat);
    }
}
