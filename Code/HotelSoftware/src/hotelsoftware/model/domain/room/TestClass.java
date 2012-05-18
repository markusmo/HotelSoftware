/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.room;

import hotelsoftware.model.DynamicMapper;
import hotelsoftware.model.database.room.DBRoom;

/**
 *
 * @author Johannes
 */
public class TestClass
{
    public static void main(String[] args)
    {
        Room r =  new Room();
        r.setNumber("202");
        System.out.println(r.getCategoryData().toString());
    }
}
