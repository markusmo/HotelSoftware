/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.room.data;

import hotelsoftware.model.domain.room.RoomOptionData;
import hotelsoftware.model.domain.service.data.HabitationData;
import java.util.Collection;
import java.util.Set;

/**
 *
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
