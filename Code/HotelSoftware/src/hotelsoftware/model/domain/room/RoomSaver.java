/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.room;

/**
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class RoomSaver
{
    private RoomSaver()
    {
    }
    
    public static RoomSaver getInstance()
    {
        return RoomSaverHolder.INSTANCE;
    }
    
    private static class RoomSaverHolder
    {
        private static final RoomSaver INSTANCE = new RoomSaver();
    }
    
    public void saveOrUpdate
    
    
}
