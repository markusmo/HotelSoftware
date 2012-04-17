/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.room;

import hotelsoftware.model.database.room.DBRoomoptions;
import java.util.List;

/**
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class RoomOption
{    
    private String name;

    public RoomOption(String name)
    {        
        this.name = name;
        DBRoomoptions.safeNewRoomOption(name);
    }

    public String getName()
    {
        return name;
    }
    
    public static List<RoomOption> getRoomOptions (){
        // convert and return Roomoptions.getRoomoptions();
        return null;
    }
    
    
}
