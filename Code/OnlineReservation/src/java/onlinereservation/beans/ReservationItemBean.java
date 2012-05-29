/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package onlinereservation.beans;

import java.beans.*;
import java.io.Serializable;

/**
 *
 * @author Markus Mohanty <markus.mo at gmx.net>
 */
public class ReservationItemBean implements Serializable
{
    private CategoryBean category;
    private int amount;

    public int getAmount()
    {
        return amount;
    }

    public void setAmount(int amount)
    {
        this.amount = amount;
    }

    public CategoryBean getCategory()
    {
        return category;
    }

    public void setCategory(CategoryBean category)
    {
        this.category = category;
    }
}
