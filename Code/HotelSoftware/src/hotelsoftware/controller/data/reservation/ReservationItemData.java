/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.controller.data.reservation;

import hotelsoftware.controller.data.room.RoomCategoryData;
import hotelsoftware.model.domain.reservation.ReservedExtraServices;
import java.util.Collection;

/**
 * Dieses Interface enthält alle wichtigen Methoden für die Klasse ReservationItem
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public interface ReservationItemData
{
    Integer getAmount();

    RoomCategoryData getReservedCategoryData();
    
    Collection<ReservedExtraServices> getReservedExtraServices();
}
