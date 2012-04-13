/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.domain.room;

import hotelsoftware.database.model.Roomoptions;
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
        Roomoptions.safeNewRoomOption(name);
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
