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
import hotelsoftware.model.domain.service.IHabitation;
import java.util.Collection;

/**
 *
 * @author Kno
 */
public interface IRoom extends RoomData
{

    void addOption(IRoomOption option);

    void changeStatus(IRoomStatus status);

    IRoomCategory getCategory();

    Integer getId();

    Collection<IRoomOption> getOptions();

    Collection<IRoomStatus> getStatus();

    void setCategory(IRoomCategory category);

    void setId(int id);

    void setNumber(String number);

    void setOptions(Collection<IRoomOption> options);

    void setStatus(Collection<IRoomStatus> status);
    
    Collection<IHabitation> getHabitations();
    
    void setHabitations(Collection<IHabitation> habitations);
    
}
