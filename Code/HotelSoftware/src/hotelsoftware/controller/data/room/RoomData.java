/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.controller.data.room;

import hotelsoftware.controller.data.service.HabitationData;
import java.util.Collection;
import java.util.Set;

/**
 *Dieses Interaface enthält alle wichtigen Methoden für die Klasse RoomData
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public interface RoomData
{
    RoomCategoryData getCategoryData();

    Collection<HabitationData> getHabitationCollectionData();

    String getNumber();

    Collection<RoomOptionData> getOptionsData();

    Collection<RoomStatusData> getStatusData();
    
}
