/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.room.data;

import hotelsoftware.model.domain.room.RoomCategoryPrice;
import java.util.Set;

/**
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public interface RoomCategoryData
{
    Integer getBedCount();

    String getName();

    Set<RoomCategoryPrice> getPrice();
}
