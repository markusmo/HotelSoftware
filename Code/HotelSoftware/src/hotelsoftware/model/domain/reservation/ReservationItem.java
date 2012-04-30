/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.reservation;

import hotelsoftware.controller.data.reservation.ReservationItemData;
import hotelsoftware.controller.data.room.RoomCategoryData;
import hotelsoftware.model.domain.room.RoomCategory;

/**
 *Diese Klasse beinhaltet die Items in einer Reservierung
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class ReservationItem implements ReservationItemData
{
    private Integer amount;
    private RoomCategory reservedCategory;
    private ReservationItemPK reservationItemPK;
    private Reservation reservation;

    public ReservationItem()
    {
    }

    public Reservation getReservation()
    {
        return reservation;
    }

    public void setReservation(Reservation reservation)
    {
        this.reservation = reservation;
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

    public RoomCategory getRoomCategory()
    {
        return reservedCategory;
    }

    public void setRoomCategory(RoomCategory reservedCategory)
    {
        this.reservedCategory = reservedCategory;
    }

    public RoomCategoryData getReservedCategoryData()
    {
        return getRoomCategory();
    }
}
