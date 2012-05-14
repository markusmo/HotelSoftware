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
        Set<IExtraService> extraServices = (Set<IExtraService>) DynamicMapper.map(DBExtraService.getAllExtraServices());
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
    public static ExtraService getExtraServiceByName(String name) throws ServiceNotFoundException
    {
        ExtraService extraService = (ExtraService) DynamicMapper.map(DBExtraService.getExtraServiceByName(name));

        if (extraService == null)
        {
            throw new ServiceNotFoundException();
        }

        return extraService;
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
    public static Collection<ExtraService> getAllHabitationServices()
    {
        Collection<ExtraService> extraServices = (Collection<ExtraService>) DynamicMapper.mapCollection(DBExtraService.getAllHabitationServices());
        return extraServices;
    }

    @Override
    public String getServiceName() {
       return getName();
    }
}
