/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.room;

import hotelsoftware.controller.data.room.RoomCategoryData;
import hotelsoftware.controller.data.room.RoomData;
import hotelsoftware.controller.data.room.RoomOptionData;
import hotelsoftware.controller.data.room.RoomStatusData;
import hotelsoftware.controller.data.service.HabitationData;
import java.util.Collection;

/**
 *
 * @author Kno
 */
public interface IRoom extends RoomData{

    void addOption(IRoomOption option);

    void changeStatus(IRoomStatus status);

    IRoomCategory getCategory();

    RoomCategoryData getCategoryData();

    Collection<HabitationData> getHabitationCollectionData();

    Integer getId();

    String getNumber();

    Collection<IRoomOption> getOptions();

    Collection<RoomOptionData> getOptionsData();

    Collection<IRoomStatus> getStatus();

    Collection<RoomStatusData> getStatusData();

    void setCategory(IRoomCategory category);

    void setId(int id);

    void setNumber(String number);

    void setOptions(Collection<IRoomOption> options);

    void setStatus(Collection<IRoomStatus> status);
    
}
