/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.domain.reservation;

import hotelsoftware.domain.room.Category;

/**
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class ReservationItem
{
    private int amount;
    private Category reservedCategory;

    private ReservationItem()
    {
    }

    public static ReservationItem newReservationItem()
    {
        return new ReservationItem();
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
