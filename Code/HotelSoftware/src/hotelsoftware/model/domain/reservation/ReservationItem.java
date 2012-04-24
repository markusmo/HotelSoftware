/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.reservation;

import hotelsoftware.model.domain.room.data.RoomCategoryData;
import hotelsoftware.model.domain.room.RoomCategory;

/**
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class ReservationItem implements ReservationItemData
{
    private Integer amount;
    private RoomCategory reservedCategory;
    private ReservationItemPK reservationItemPK;

    public ReservationItem()
    {
    }

    public static ReservationItem newReservationItem()
    {
        return new ReservationItem();
    }


    public ReservationItemPK getReservationitemsPK()
    {
        return reservationItemPK;
    }

    public void setReservationitemsPK(ReservationItemPK reservationItemPK)
    {
        if (this.reservationItemPK == null)
        {
            this.reservationItemPK = reservationItemPK;
        }
    }

    public Integer getAmount()
    {
        return amount;
    }

    public void setAmount(int amount)
    {
        this.amount = amount;
    }

    public RoomCategory getRoomcategory()
    {
        return reservedCategory;
    }

    public void setRoomcategory(RoomCategory reservedCategory)
    {
        this.reservedCategory = reservedCategory;
    }

    public RoomCategoryData getReservedCategoryData()
    {
        return getRoomcategory();
    }
}
