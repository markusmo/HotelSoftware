/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.room;

import hotelsoftware.model.DynamicMapper;
import hotelsoftware.model.database.room.DBRoomOption;
import java.util.Collection;

/**
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class RoomOption implements RoomOptionData
{    
    private String name;

    public RoomOption()
    {}
    
    public static RoomOption create(String name)
    {
        return new RoomOption(name);
    }
    
    private RoomOption(String name)
    {        
        this.name = name;
    }
    
    @Override
    public String getName()
    {
        return name;
    }
    
    public static Collection<RoomOption> getRoomOptions ()
    {
        return (Collection<RoomOption>) DynamicMapper.mapCollection(DBRoomOption.getRoomOptions());
    }
    
    
}
