/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftwareonline.beans;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Markus Mohanty <markus.mo at gmx.net>
 */
public class ReservationItemBean implements Serializable
{
    private CategoryBean category = null;
    private int amount;
    private ArrayList<ExtraserviceBean> extraServices;
    private int nr;
    private static int nummer;
    
    public ArrayList<ExtraserviceBean> getExtraServices()
    {
        return extraServices;
    }

    public void setExtraServices(ArrayList<ExtraserviceBean> extraServices)
    {
        this.extraServices = extraServices;
    }
    
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

    public int getNr()
    {
        return nr;
    }

    public void setNr(int nr)
    {
        this.nr = nr;
    }
    
    public ReservationItemBean()
    {
        nr = nummer++;
    }
    
   
}
