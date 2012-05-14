/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.room;

import java.util.Date;
import java.util.Set;

/**
 *
 * @author Kno
 */
public interface ISeason {

    Date getEnd();

    Integer getId();

    String getName();

    Set<IRoomCategoryPrice> getRoomcategoryprices();

    Date getStart();

    void setEnd(Date end);

    void setId(Integer id);

    void setName(String name);

    void setRoomcategoryprices(Set<IRoomCategoryPrice> roomcategoryprices);

    void setStart(Date start);
    
}
