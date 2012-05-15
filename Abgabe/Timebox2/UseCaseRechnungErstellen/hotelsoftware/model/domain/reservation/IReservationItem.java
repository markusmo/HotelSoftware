/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.reservation;

import hotelsoftware.controller.data.reservation.ReservationItemData;
import hotelsoftware.controller.data.room.RoomCategoryData;
import hotelsoftware.model.domain.room.IRoomCategory;
import hotelsoftware.model.domain.room.RoomCategory;

/**
 *
 * @author Kno
 */
public interface IReservationItem extends ReservationItemData{

    Integer getAmount();

    IReservation getReservation();

    IReservationItemPK getReservationitemsPK();

    IRoomCategory getRoomCategory();

    void setAmount(int amount);

    void setReservation(IReservation reservation);

    void setReservationitemsPK(IReservationItemPK reservationItemPK);

    void setRoomCategory(IRoomCategory reservedCategory);
    
}
