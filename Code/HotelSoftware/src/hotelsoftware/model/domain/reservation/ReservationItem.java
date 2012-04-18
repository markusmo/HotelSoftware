/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.reservation;

import hotelsoftware.model.domain.room.Category;

/**
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class ReservationItem
{
    private int amount;
    private Category reservedCategory;
    private ReservationItemPK reservationItemPK;

    private ReservationItem()
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

    public int getAmount()
    {
        return amount;
    }

    public void setAmount(int amount)
    {
        this.amount = amount;
    }

    public Category getReservedCategory()
    {
        return reservedCategory;
    }

    public void setReservedCategory(Category reservedCategory)
    {
        this.reservedCategory = reservedCategory;
    }
}
