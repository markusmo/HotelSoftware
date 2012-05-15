/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.controller.data.reservation;

import hotelsoftware.controller.data.room.RoomCategoryData;

/**
 *Dieses Interface enthält alle wichtigen Methoden für die Klasse ReservationItem
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public interface ReservationItemData
{

    public Integer getAmount();

    RoomCategoryData getReservedCategoryData();
    
}
