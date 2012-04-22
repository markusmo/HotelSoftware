/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.room;

import java.math.BigDecimal;

/**
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public interface CategoryData
{

    int getBedCount();

    BigDecimal getMinPrice();

    String getName();

    BigDecimal getPrice();
    
}
