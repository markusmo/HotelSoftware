package hotelsoftware.model.domain.service;

import hotelsoftware.model.DynamicMapper;
import hotelsoftware.model.database.service.DBExtraService;
import java.util.Collection;

/**
 * Ist die Fassadenklasse, die das Package Service verwaltet
 * @author Tobias
 */
public class ServiceFacade
{
    private ServiceFacade()
    {
    }

    public static ServiceFacade getInstance()
    {
        return ServiceFacadeHolder.INSTANCE;
    }

    private static class ServiceFacadeHolder
    {
        private static final ServiceFacade INSTANCE = new ServiceFacade();
    }

    /**
     * Gibt alle Extraservices aus
     * @return 
     * Alle Extraservices, die vorhanden sind
     */
    public Collection getAllExtraServices()
    {
        return DynamicMapper.mapCollection(DBExtraService.getAllExtraServices());
    }

    /**
     * Gibt einen Extraservice gesucht nach Namen aus
     * @param name
     * Der Name des Extraservice
     * @return
     * Der Extraservice mit dem angegebenen Namen
     * @throws ServiceNotFoundException 
     * Wirft einen Fehler, wenn der Service nicht gefunden wird.
     */
    public ExtraService getExtraServiceByName(String name) throws ServiceNotFoundException
    {

        DBExtraService p = DBExtraService.getExtraServiceByName(name);

        if (p == null)
        {

            throw new ServiceNotFoundException();

        }
        return (ExtraService) DynamicMapper.map(p);
    }
}
