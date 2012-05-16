package hotelsoftware.model.domain.service;

import hotelsoftware.support.ServiceTypeNotFoundException;
import hotelsoftware.support.ServiceNotFoundException;
import hotelsoftware.model.DynamicMapper;
import hotelsoftware.model.database.service.DBExtraService;
import hotelsoftware.model.database.service.DBHabitation;
import hotelsoftware.model.database.service.DBServiceType;
import hotelsoftware.util.HibernateUtil;
import java.util.Collection;
import java.util.List;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

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
    public Collection<IExtraService> getAllExtraServices()
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();
        List<DBExtraService> extraServices = (List<DBExtraService>) session.createCriteria(DBExtraService.class).list();
        
        return DynamicMapper.mapCollection(extraServices);
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
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();
        DBExtraService extraService = (DBExtraService) session.createCriteria(DBExtraService.class)
                .add(Restrictions.eq("name", name))
                .uniqueResult();
        
        if (extraService == null)
        {
            throw new ServiceNotFoundException();
        }
        return (ExtraService) DynamicMapper.map(extraService);
    }
    
    /**
     * Gibt alle Verpflegungsarten aus
     * @return 
     * Alle Verpflegungsarten, die vorhanden sind.
     */
    public Collection<IExtraService> getAllHabitationServices()
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();
        
        SQLQuery query = session.createSQLQuery("SELECT * from extraservices es INNER JOIN services s ON es.idServices = s.idServices "
                + "INNER JOIN servicetypes st ON s.idServiceTypes = st.id WHERE st.name = 'Habitation'");
        
        query.addEntity(DBExtraService.class);
        Collection<DBExtraService> retList = query.list();
        
        return DynamicMapper.mapCollection(retList);
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
        return (Collection<Habitation>) DynamicMapper.mapCollection(p);
    }

      public Collection<Habitation> getHabitation(String fname, String lname, String roomId)
    {
        Collection<DBHabitation> p = DBHabitation.search(fname, lname, roomId);
        return (Collection<Habitation>) DynamicMapper.mapCollection(p);
    }
    
    public Collection<Habitation> getHabitation(String roomId)
    {
        Collection<DBHabitation> p = DBHabitation.search(roomId);
        return (Collection<Habitation>) DynamicMapper.mapCollection(p);
    }
}
