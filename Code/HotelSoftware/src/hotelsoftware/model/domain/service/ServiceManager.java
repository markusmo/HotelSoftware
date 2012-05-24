package hotelsoftware.model.domain.service;

import at.fhv.roomanizer.persistence.ObjectConverter;
import at.fhv.roomanizer.persistence.entity.HabitationEntity;
import hotelsoftware.support.ServiceTypeNotFoundException;
import hotelsoftware.support.ServiceNotFoundException;
import hotelsoftware.model.DynamicMapper;
import hotelsoftware.model.database.reservation.DBReservation;
import hotelsoftware.model.database.reservation.DBReservationItem;
import hotelsoftware.model.database.service.DBExtraService;
import hotelsoftware.model.database.service.DBHabitation;
import hotelsoftware.model.database.service.DBService;
import hotelsoftware.model.database.service.DBServiceType;
import hotelsoftware.model.domain.reservation.IReservation;
import hotelsoftware.model.domain.reservation.IReservationItem;
import hotelsoftware.util.HibernateUtil;
import java.util.*;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 * Ist die Fassadenklasse, die das Package Service verwaltet
 *
 * @author Tobias
 */
public class ServiceManager
{
    private ServiceManager()
    {
    }

    public static ServiceManager getInstance()
    {
        return ServiceFacadeHolder.INSTANCE;
    }

    private static class ServiceFacadeHolder
    {
        private static final ServiceManager INSTANCE = new ServiceManager();
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
    public IExtraService getExtraServiceByName(String name) throws ServiceNotFoundException
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();
        DBExtraService extraService = (DBExtraService) session.createCriteria(DBExtraService.class).add(Restrictions.eq("name", name)).uniqueResult();

        if (extraService == null)
        {
            throw new ServiceNotFoundException();
        }
        return (IExtraService) DynamicMapper.map(extraService);
    }

    /**
     * Gibt alle Verpflegungsarten aus
     *
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
    public IServiceType getServiceTypeByName(String name) throws ServiceTypeNotFoundException
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();
        SQLQuery query = session.createSQLQuery("SELECT * FROM servicetypes WHERE name = :name");
        query.setString("name", name);
        query.addEntity(DBServiceType.class);

        DBServiceType serviceType = (DBServiceType) query.uniqueResult();

        ts.commit();

        if (serviceType == null)
        {
            throw new ServiceTypeNotFoundException();
        }
        return (IServiceType) DynamicMapper.map(serviceType);
    }

    int getHighestHabitationId()
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();

        String query = "Select max(idServices) FROM habitations h";
        SQLQuery sqlquery = session.createSQLQuery(query);


        Integer bd = (Integer) sqlquery.uniqueResult();

        if (bd != null)
        {
            return bd;
        }
        else
        {
            return 0;
        }
    }

    /**
     * Sucht Aufenthalte nach einem Namen eines Gastes
     *
     * @param fname der Nachname eines Gastes
     * @param lname der Vorname eines Gastes
     * @return eine Liste von Aufenthalten, die diesen Gast enthalten
     */
    public Collection<IHabitation> searchHabitationsByName(String fname, String lname)
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();
        String query;
        if (fname == null)
        {
            if (lname == null)
            {
                // VOR UND NACHNAME SIND LEER
                query = ""
                        + "SELECT *"
                        + "FROM habitations h"
                        + "INNER JOIN allocations a ON h.id=a.idService"
                        + "INNER JOIN guests g ON g.id=a.idGuests;";
            }
            else
            {
                // NUR VORNAME IST LEER
                query = ""
                        + "SELECT *"
                        + "FROM habitations h"
                        + "INNER JOIN allocations a ON h.id=a.idService"
                        + "INNER JOIN guests g ON g.id=a.idGuests"
                        + "WHERE lname =" + lname + ";";
            }
        }
        else
        {
            if (lname == null)
            {
                //NUR NACHNAME IST LEER
                query = ""
                        + "SELECT *"
                        + "FROM habitations h"
                        + "INNER JOIN allocations a ON h.id=a.idService"
                        + "INNER JOIN guests g ON g.id=a.idGuests"
                        + "WHERE lname =" + lname + ";";
            }
            else
            {
                //NICHTS IST LEER
                query = ""
                        + "SELECT *"
                        + "FROM habitations h"
                        + "INNER JOIN allocations a ON h.id=a.idService"
                        + "INNER JOIN guests g ON g.id=a.idGuests"
                        + "WHERE lname =" + lname + " AND fname=" + fname + ";";
            }
        }


        SQLQuery sqlquery = session.createSQLQuery(query);
        sqlquery = sqlquery.addEntity(DBHabitation.class);
        Collection<DBHabitation> retList = sqlquery.list();

        if (retList == null)
        {
            return new LinkedHashSet();
        }

        return DynamicMapper.mapCollection(retList);
    }

    /**
     * Sucht Aufenthalte nach dem Namen eines Gastes und seiner Zimmernummer
     *
     * @param fname der Vorname des Gastes
     * @param lname der Nachname des Gastes
     * @param roomnumber die Zimmernummer des Gastes
     * @return
     */
    public Collection<IHabitation> searchHabitations(String fname, String lname, String roomnumber)
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();

        Query q = session.createQuery("SELECT DISTINCT h FROM DBHabitation as h INNER JOIN h.rooms as r INNER JOIN h.guests g "
                + "JOIN FETCH h.invoiceItems as ii LEFT JOIN FETCH ii.invoice WHERE r.number LIKE :number OR g.fname LIKE :fname OR g.lname LIKE :lname");
        q = q.setString("number", roomnumber);
        q = q.setString("fname", fname);
        q = q.setString("lname", lname);

        List<DBHabitation> retList = q.list();

        if (retList == null)
        {
            return new LinkedHashSet();
        }

        return DynamicMapper.mapCollection(retList);
    }

    public Collection<IHabitation> getHabitationsByDate(Date date)
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();

        Query habitationQuery = session.createQuery("from DBHabitation where :date between startDate and endDate order by startDate");
        habitationQuery.setDate("date", date);

        List<DBHabitation> tmpList = habitationQuery.list();

        return DynamicMapper.mapCollection(tmpList);
    }

    public Collection<IHabitation> searchHabitationsByNumber(String roomNumber)
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();

        Query q = session.createQuery("SELECT DISTINCT h FROM DBHabitation as h INNER JOIN h.rooms as r LEFT JOIN FETCH h.invoiceItems as ii LEFT JOIN FETCH ii.invoice WHERE r.number = :number");
        q = q.setString("number", roomNumber);

        List<DBHabitation> retList = q.list();

        if (retList == null)
        {
            return new LinkedHashSet();
        }

        return DynamicMapper.mapCollection(retList);
    }

    public Collection<IHabitation> getAllHabitations()
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();

        Query habitationQuery = session.createQuery("from DBHabitation order by startDate");

        List<DBHabitation> tmpList = habitationQuery.list();

        return DynamicMapper.mapCollection(tmpList);
    }

    public IHabitation getHabitationById(int id)
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();

        Query habitationQuery = session.createQuery("from DBHabitation WHERE idServices = :id");
        habitationQuery.setInteger("id", id);

        return (IHabitation) DynamicMapper.map(habitationQuery.uniqueResult());
    }

    public void saveService(IService service)
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        t.begin();

        saveService(service, session);

        t.commit();
    }

    public void saveService(IService service, Session session)
    {
        DBService dbs = (DBService) DynamicMapper.map(service);

        if (service.getIdServices() == null)
        {
            Integer id = (Integer)session.save(service);
            service.setIdServices(id);
        }
        else
        {
            session.merge(dbs);
            session.flush();
        }
        
    }

    public void saveHabitation(IHabitation habitation)
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        t.begin();

        saveHabitation(habitation, session);

        t.commit();
    }

    public void saveHabitation(IHabitation habitation, Session session)
    {
        DBHabitation dbp = (DBHabitation) DynamicMapper.map(habitation);

        session.saveOrUpdate(dbp);
        habitation.setIdServices(dbp.getIdServices());
    }
}
