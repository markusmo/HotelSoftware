/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.checkin;

import hotelsoftware.model.domain.service.ExtraService;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 *
 * @author Dunst
 */
public class OptionsState extends CheckInState
{
    @Override
    public void initKeys()
    {
        throw new NotImplementedException();
    }
    
    @Override
    public Collection<ExtraServiceData> getServices()
    {
        Collection<ExtraService> services = Service.getAllServices();
        Collection<ExtraServiceData> serviceData = new LinkedList<ExtraServiceData>();
        
        for (service : services)
        {
            serviceData.add(service);
        }
        
        return serviceData;
    }
    
    @Override
    public void selectServices(Map<ExtraServiceData, Integer> services)
    {
        for (Entry<ExtraService, Integer> entry : services.entrySet())
        {
            habitation.addExtraService(entry.getKey(), entry.getValue());
        }
    }
}
