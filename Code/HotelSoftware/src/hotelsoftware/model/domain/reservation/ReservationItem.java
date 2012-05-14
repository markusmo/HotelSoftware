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
 *Diese Klasse beinhaltet die Items in einer Reservierung
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class ReservationItem implements IReservationItem
{
    private Integer amount;
    private IRoomCategory reservedCategory;
    private IReservationItemPK reservationItemPK;
    private IReservation reservation;

    public ReservationItem()
    {
    }

    @Override
    public IReservation getReservation()
    {
        return reservation;
    }

    @Override
    public void setReservation(IReservation reservation)
    {
        this.reservation = reservation;
    }
    
    
    public static ReservationItem newReservationItem()
    {
        return new ReservationItem();
    }


    @Override
    public IReservationItemPK getReservationitemsPK()
    {
        return reservationItemPK;
    }

    @Override
    public void setReservationitemsPK(IReservationItemPK reservationItemPK)
    {
        if (this.reservationItemPK == null)
        {
            this.reservationItemPK = reservationItemPK;
        }
    }

    @Override
    public Integer getAmount()
    {
        return amount;
    }

    @Override
    public void setAmount(int amount)
    {
        this.amount = amount;
    }

    @Override
    public IRoomCategory getRoomCategory()
    {
        return reservedCategory;
    }

    @Override
    public void setRoomCategory(IRoomCategory reservedCategory)
    {
        this.reservedCategory = reservedCategory;
    }

    @Override
    public RoomCategoryData getReservedCategoryData()
    {
        return getRoomCategory();
    }
}
