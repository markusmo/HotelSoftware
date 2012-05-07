package hotelsoftware.model.domain.service;

import hotelsoftware.support.ServiceTypeNotFoundException;
import hotelsoftware.support.ServiceNotFoundException;
import hotelsoftware.model.DynamicMapper;
import hotelsoftware.model.database.service.DBExtraService;
import hotelsoftware.model.database.service.DBHabitation;
import hotelsoftware.model.database.service.DBServiceType;
import java.util.Collection;

/**
 * Ist die Fassadenklasse, die das Package Service verwaltet
 *
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
     *
     * @return
     * Alle Extraservices, die vorhanden sind
     */
    public Collection getAllExtraServices()
    {
        return DynamicMapper.mapCollection(DBExtraService.getAllExtraServices());
    }

    /**
     * Gibt einen Extraservice gesucht nach Namen aus
     *
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

    /**
     * Diese Methode sucht nach einem Service anhand eines Namens
     *
     * @param name
     * @return
     * Der Service mit dem angegebenen Namen
     * @throws ServiceTypeNotFoundException
     */
    public ServiceType getServiceTypeByName(String name) throws ServiceTypeNotFoundException
    {
        DBServiceType p = DBServiceType.getTypeByName(name);

        if (p == null)
        {
            throw new ServiceTypeNotFoundException();
        }
        return (ServiceType) DynamicMapper.map(p);
    }

    static int getHighestHabitationId()
    {
        return DBHabitation.getHighestId();
    }

    public Collection<Habitation> getHabitations(String fname, String lname)
    {
        Collection<DBHabitation> p = DBHabitation.search(fname, lname);
        return (Collection<Habitation>) DynamicMapper.map(p);
    }

    public Collection<Habitation> getHabitation(Integer roomId)
    {
        Collection<DBHabitation> p = DBHabitation.search(roomId);
        return (Collection<Habitation>) DynamicMapper.map(p);
    }
}
