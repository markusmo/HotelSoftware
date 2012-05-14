/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.room;

import hotelsoftware.controller.data.room.RoomOptionData;

/**
 *
 * @author Kno
 */
public interface IRoomOption extends RoomOptionData{

    IRoomOption create(String name);

    Integer getId();

    String getName();

    void setId(Integer id);

    void setName(String name);
    
}
