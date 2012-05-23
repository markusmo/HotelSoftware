package hotelsoftware.model.domain.service;

import hotelsoftware.support.ServiceNotFoundException;
import hotelsoftware.controller.data.service.ExtraServiceData;
import hotelsoftware.controller.data.service.ServiceTypeData;
import hotelsoftware.model.DynamicMapper;
import hotelsoftware.model.database.service.DBExtraService;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Diese Klasse bildet Extraservices (Essen, Getraenke, usw.) ab, mit der das System arbeitet
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class ExtraService extends Service implements IExtraService
{
    private String name;

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

    public ExtraService()
    {
    }

    private ExtraService(String name, BigDecimal price, IServiceType type)
    {
        super(price, type);
        this.name = name;
    }

    /**
     * Instanziert einen neuen Extraservice
     * @param name
     * Name des Extraservice
     * @param price
     * Preis des Extraservice
     * @param type
     * Typ des Extraservice
     * @return 
     * eine neue Instanz
     */
    public static ExtraService createExtraService(String name, BigDecimal price, IServiceType type)
    {
        return new ExtraService(name, price, type);
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

    @Override
    public ServiceTypeData getServiceTypeData()
    {
        return (ServiceTypeData) getServiceType();
    }

    /**
     * Gibt alle Verpflegungsarten aus
     * @return 
     * Alle Verpflegungsarten, die vorhanden sind.
     */
    public static Collection<IExtraService> getAllHabitationServices()
    {
        return ServiceManager.getInstance().getAllHabitationServices();
    }

    @Override
    public String getServiceName() {
       return getName();
    }
}
