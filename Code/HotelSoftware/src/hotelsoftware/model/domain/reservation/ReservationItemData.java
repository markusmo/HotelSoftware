/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.reservation;

import hotelsoftware.model.domain.room.CategoryData;

/**
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public interface ReservationItemData
{

    int getAmount();

    CategoryData getReservedCategoryData();
    
}
