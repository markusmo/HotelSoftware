/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.room;

import java.util.Collection;
import java.util.Date;

/**
 *
 * @author Kno
 */
public interface IRoomCategory
{
    Collection<IRoomCategoryPrice> getPrice();

    /**
     * sucht nach freien Räumen in einer Periode in der Datenbank
     *
     * @param start Der Start der Periode
     * @param ende Das ende der Periode
     * @return Alle freien Zimmer in der angegebenen Periode
     */
    Collection<IRoom> getFreeRooms(Date start, Date ende);

    Integer getId();

    void setBedCount(int bedCount);

    Integer getBedCount();
    
    void setId(int id);

    void setName(String name);

    String getName();
    
    void setPrice(Collection<IRoomCategoryPrice> price);
    
    Collection<IRoom> getRooms();
    
    void setRooms(Collection<IRoom> rooms);
}
