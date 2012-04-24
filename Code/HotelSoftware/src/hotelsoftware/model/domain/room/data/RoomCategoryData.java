/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.room.data;

import java.math.BigDecimal;

/**
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public interface RoomCategoryData
{

    Integer getBedCount();

    BigDecimal getMinPrice();

    String getName();

    BigDecimal getPrice();
    
}
