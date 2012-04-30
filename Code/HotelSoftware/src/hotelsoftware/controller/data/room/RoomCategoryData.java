/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.controller.data.room;

import hotelsoftware.support.NoPriceDefinedException;
import hotelsoftware.model.domain.room.RoomCategoryPrice;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

/**
 *Dieses Interface enthält alle wichtigen Methoden für die Klasse RoomCategory
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public interface RoomCategoryData
{
    Integer getBedCount();

    String getName();

    Collection<RoomCategoryPrice> getPrice();

    public BigDecimal getPriceFor(Date startDate) throws NoPriceDefinedException;

    String toString();
}
