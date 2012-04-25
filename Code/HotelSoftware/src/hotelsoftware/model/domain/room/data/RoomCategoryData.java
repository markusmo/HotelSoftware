/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.room.data;

import hotelsoftware.model.domain.room.NoPriceDefinedException;
import hotelsoftware.model.domain.room.RoomCategoryPrice;
import java.math.BigDecimal;
import java.util.Date;
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

    public BigDecimal getPriceFor(Date startDate) throws NoPriceDefinedException;
}
