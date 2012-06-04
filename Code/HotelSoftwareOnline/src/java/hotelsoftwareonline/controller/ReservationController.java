package hotelsoftwareonline.controller;

import hotelsoftware.model.database.manager.ServiceManager;
import hotelsoftware.model.domain.parties.Country;
import hotelsoftware.model.domain.parties.ICountry;
import hotelsoftware.model.domain.room.IRoomCategory;
import hotelsoftware.model.domain.room.RoomCategory;
import hotelsoftware.model.domain.service.IExtraService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

/**
 *
 * @author Johannes
 */
public class ReservationController
{
    public static Collection<IRoomCategory> getFreeCategories(Date start, Date end)
    {
        Collection<IRoomCategory> categories = new LinkedList<IRoomCategory>();
        for (IRoomCategory cat : RoomCategory.getAllCategorys())
        {
            if (!cat.getFreeRooms(start, end).isEmpty())
            {
                categories.add(cat);
            }
        }
        return categories;
    }
    
     /**
     * gibt die Verpflegungsarten aus
     * @return eine Arraylist aller Verplegungsarten
     */
    public static ArrayList<String> getBoardCategories()
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
    
     /**
     * gibt die Länder aus
     * @return eine Arraylist aller Länder
     */
    public static ArrayList<String> getCountries()
    {
        Collection<ICountry> allCountries = Country.getAllCountries();
        ArrayList<String> countries = new ArrayList<String>();
        for (ICountry c : allCountries)
        {
           countries.add(c.getName());
        }
        
        return countries;
    }
}
