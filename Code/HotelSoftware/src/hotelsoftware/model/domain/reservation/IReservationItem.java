/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.reservation;

import hotelsoftware.controller.data.reservation.ReservationItemData;
import hotelsoftware.model.domain.room.IRoomCategory;
import java.util.Collection;

/**
 *
 * @author Kno
 */
public interface IReservationItem extends ReservationItemData
{
    IReservation getReservation();

    IReservationItemPK getReservationitemsPK();

    IRoomCategory getRoomCategory();

    void setAmount(int amount);

    void setReservation(IReservation reservation);

    void setReservationitemsPK(IReservationItemPK reservationItemPK);

    void setRoomCategory(IRoomCategory reservedCategory);
    
    void setReservedExtraServices(Collection<ReservedExtraServices> reservedExtraServices);
}
