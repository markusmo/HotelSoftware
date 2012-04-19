/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.room;

import java.util.Collection;

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
    
    public Room getRoomByNumber(int number)
    {
        return Room.getRoomByNumber(number);
    }
    
    public Collection<Room> getRoomsByCategory(RoomCategory cat)
    {
        return Room.getRoomsByCategory(cat);
    }
}
