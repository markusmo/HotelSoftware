/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.room;

import hotelsoftware.controller.data.room.RoomData;
import hotelsoftware.model.domain.service.IHabitation;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author Kno
 */
public interface IRoom extends RoomData
{
    /**
     * Fügt der Option Collection eine Option hinzu
     * @param option 
     */
    void addOption(IRoomOption option);

    /**
     * Fügt der Status Collection einen Status hinzu
     * @param status 
     */
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
    
    /**
     * Überprüft ob ein Raum in einem gegebenen Zeitraum frei ist
     * @param start Der STarttermin des gesuchten Zeitraums
     * @param end Der Endtermin des gesuchten Zeitraums
     * @return TRUE wenn der Raum frei ist
     */
    boolean isFree(Date start, Date end);
    
}
