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
    private CategoryBean category;
    private int amount;
    private ArrayList<ExtraserviceBean> extraServices;

    /*
     * TODO BoardCategory --> ist auch ein ExtraService in DB...
     *                        Ausgemacht ist das Zimmer mit oder ohne
     *                        VP, HP oder Frühstück ist. BoardCategory
     *                        nicht nötig ...
     */
    
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
}
