package hotelsoftware.model.domain.service;

import hotelsoftware.support.ServiceTypeNotFoundException;
import hotelsoftware.support.ServiceNotFoundException;
import hotelsoftware.model.DynamicMapper;
import hotelsoftware.model.database.service.DBExtraService;
import hotelsoftware.model.database.service.DBHabitation;
import hotelsoftware.model.database.service.DBServiceType;
import hotelsoftware.util.HibernateUtil;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
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
        DBExtraService extraService = (DBExtraService) session.createCriteria(DBExtraService.class).add(Restrictions.eq("name", name)).uniqueResult();

        if (extraService == null)
        {
            throw new ServiceNotFoundException();
        }
        return (ExtraService) DynamicMapper.map(extraService);
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

    /**
     * Sucht Aufenthalte nach einem Namen eines Gastes
     *
     * @param fname der Nachname eines Gastes
     * @param lname der Vorname eines Gastes
     * @return eine Liste von Aufenthalten, die diesen Gast enthalten
     */
    public Collection<Habitation> getHabitations(String fname, String lname)
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
    public Collection<Habitation> getHabitation(String fname, String lname, String roomnumber)
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

    public Collection<Habitation> getHabitation(String roomNumber)
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();

        Query q = session.createQuery("SELECT DISTINCT h FROM DBHabitation as h INNER JOIN h.rooms as r JOIN FETCH h.invoiceItems as ii JOIN FETCH ii.invoice WHERE r.number = :number");
        q = q.setString("number", roomNumber);

        List<DBHabitation> retList = q.list();

        if (retList == null)
        {
            return new LinkedHashSet();
        }
        
        return DynamicMapper.mapCollection(retList);
    }
}
