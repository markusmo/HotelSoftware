/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.room;

import hotelsoftware.controller.data.room.RoomCategoryData;
import hotelsoftware.support.NoPriceDefinedException;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author Kno
 */
public interface IRoomCategory extends RoomCategoryData
{
    boolean equals(Object obj);

    Collection<IRoomCategoryPrice> getPrice();

    Integer getBedCount();

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

    void setId(int id);

    void setName(String name);

    void setPrice(Collection<IRoomCategoryPrice> price);
    
    Collection<IRoom> getRooms();
    
    void setRooms(Collection<IRoom> rooms);
}
