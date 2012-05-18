/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.room;

import hotelsoftware.controller.data.room.RoomData;
import hotelsoftware.model.domain.service.IHabitation;
import java.util.Collection;

/**
 *
 * @author Kno
 */
public interface IRoom extends RoomData
{

    void addOption(IRoomOption option);

    void changeStatus(IRoomRoomStatus status);

    IRoomCategory getCategory();

    Integer getId();

    Collection<IRoomOption> getOptions();

    Collection<IRoomRoomStatus> getStatus();

    void setCategory(IRoomCategory category);

    void setId(int id);

    void setNumber(String number);

    void setOptions(Collection<IRoomOption> options);

    void setStatus(Collection<IRoomRoomStatus> status);
    
    Collection<IHabitation> getHabitations();
    
    void setHabitations(Collection<IHabitation> habitations);
    
}
