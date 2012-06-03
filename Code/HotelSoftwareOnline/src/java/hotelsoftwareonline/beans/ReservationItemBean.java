/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftwareonline.beans;

import hotelsoftware.model.database.manager.ServiceManager;
import hotelsoftware.model.domain.service.IExtraService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Markus Mohanty <markus.mo at gmx.net>
 */
public class ReservationItemBean implements Serializable
{
    private CategoryBean category;
    private int amount;
    private ArrayList<ExtraserviceBean> extraServices;
    
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
    
    /**
     * gibt die Verpflegungsarten aus
     * @return eine Arraylist aller Verplegungsarten
     */
    public ArrayList<String> getBoardCategories()
    {
        Collection<IExtraService> allExtraServices = ServiceManager.getInstance().getAllExtraServices();
        ArrayList<String> boardCategories = new ArrayList<String>();
        for (IExtraService extraservice : allExtraServices)
        {
            if(extraservice.getServiceType().getName().equals("Food"))
            {
                boardCategories.add(extraservice.getName());
            }
        }
        
        return boardCategories;
    }
}
