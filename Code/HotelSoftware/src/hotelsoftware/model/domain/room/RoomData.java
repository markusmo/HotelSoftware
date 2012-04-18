/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.room;

import hotelsoftware.model.domain.service.HabitationData;
import java.util.Collection;

/**
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public interface RoomData
{

    CategoryData getCategoryData();

    Collection<HabitationData> getHabitationCollectionData();

    int getNumber();

    Collection<RoomOptionData> getOptionsData();

    Collection<RoomStatusData> getStatusData();
    
}
