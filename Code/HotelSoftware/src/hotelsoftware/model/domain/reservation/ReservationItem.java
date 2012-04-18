/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.reservation;

import hotelsoftware.model.domain.room.Category;
import hotelsoftware.model.domain.room.CategoryData;

/**
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class ReservationItem implements ReservationItemData
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

    @Override
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

    public CategoryData getReservedCategoryData()
    {
        return (CategoryData) getReservedCategory();
    }
}
