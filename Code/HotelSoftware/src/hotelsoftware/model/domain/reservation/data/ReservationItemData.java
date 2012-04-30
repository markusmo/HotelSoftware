/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.reservation.data;

import hotelsoftware.model.domain.room.data.RoomCategoryData;

/**
 *Dieses Interface enthält alle wichtigen Methoden für die Klasse ReservationItem
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public interface ReservationItemData
{

    public Integer getAmount();

    RoomCategoryData getReservedCategoryData();
    
}
