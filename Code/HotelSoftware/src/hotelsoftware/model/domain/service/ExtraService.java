package hotelsoftware.model.domain.service;

import hotelsoftware.controller.data.service.ServiceTypeData;
import hotelsoftware.model.database.manager.ServiceManager;
import hotelsoftware.support.ServiceNotFoundException;
import java.util.Collection;
import java.util.Set;

/**
 * Diese Klasse bildet Extraservices (Essen, Getraenke, usw.) ab, mit der das System arbeitet
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class ExtraService extends Service implements IExtraService
{
    private String name;
    
    public ExtraService()
    {
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public ServiceTypeData getServiceTypeData()
    {
        return (ServiceTypeData) getServiceType();
    }

    @Override
    public String getServiceName()
    {
        return getName();
    }

    /**
     * Gibt alle Extraservices aus
     * @return 
     * Alle Extraservices die vorhanden sind
     */
    public static Set<IExtraService> getAllExtraServices()
    {
        Set<IExtraService> extraServices = (Set<IExtraService>) ServiceManager.getInstance().getAllExtraServices();
        return extraServices;
    }

    /**
     * Gibt einen Extraservice gesucht nach Namen aus
     * @param name
     * Der Name des Extraservice
     * @return
     * Der Extraservice mit dem gesuchten Namen
     * @throws ServiceNotFoundException 
     * Wirft einen Fehler, wenn der Service nicht gefunden wird.
     */
    public static IExtraService getExtraServiceByName(String name) throws ServiceNotFoundException
    {
        return ServiceManager.getInstance().getExtraServiceByName(name);
    }
    
    /**
     * Gibt alle Verpflegungsarten aus
     *
     * @return
     * Alle Verpflegungsarten, die vorhanden sind.
     */
    public static Collection<IExtraService> getAllHabitationServices()
    {
        return ServiceManager.getInstance().getAllHabitationServices();
    }
}
