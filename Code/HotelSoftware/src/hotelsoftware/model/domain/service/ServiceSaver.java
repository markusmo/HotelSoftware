package hotelsoftware.model.domain.service;

import hotelsoftware.model.DynamicMapper;
import hotelsoftware.model.database.FailedToSaveToDatabaseException;
import hotelsoftware.model.database.service.DBExtraService;
import hotelsoftware.model.database.service.DBHabitation;
import hotelsoftware.model.database.service.DBServiceType;
import hotelsoftware.util.HibernateUtil;
import java.util.Collection;
import java.util.Set;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Diese Klasse verwaltet die Speicherung aller Objekte des Packages Service in
 * die Datenbank.
 *
 * @author Tobias
 */
public class ServiceSaver
{
    private ServiceSaver()
    {
    }

    public static ServiceSaver getInstance()
    {
        return ServiceSaverHolder.INSTANCE;
    }

    private static class ServiceSaverHolder
    {
        private static final ServiceSaver INSTANCE = new ServiceSaver();
    }

    /**
     * Diese Methode speichert alle Services mittels einer uebergebenen Session
     * in die Datenbank
     *
     * @param extraServices Alle veraenderten/neuen Extraservices
     * @param habitations Alle veraenderten/neuen Aufenthalte
     * @param serviceTypes Alle veraenderten/neuen Servicearten
     * @throws FailedToSaveToDatabaseException Wirft einen Fehler, wenn das
     * Speichern fehlschlaegt
     */
    public void saveOrUpdate(Session session, Collection<IExtraService> extraServices, Collection<IHabitation> habitations,
            Collection<IServiceType> serviceTypes) throws FailedToSaveToDatabaseException
    {

        try
        {
            if (serviceTypes != null)
            {
                for (IServiceType serviceType : serviceTypes)
                {
                    DBServiceType dbp = (DBServiceType) DynamicMapper.map(serviceType);

                    session.saveOrUpdate(dbp);
                    serviceType.setId(dbp.getId());
                }
            }

            if (extraServices != null)
            {
                for (IExtraService extraService : extraServices)
                {
                    DBExtraService dbp = (DBExtraService) DynamicMapper.map(extraService);

                    session.saveOrUpdate(dbp);
                    extraService.setIdServices(dbp.getIdServices());
                }
            }

            if (habitations != null)
            {
                for (IHabitation habitation : habitations)
                {
                    DBHabitation dbp = (DBHabitation) DynamicMapper.map(habitation);

                    session.saveOrUpdate(dbp);
                    habitation.setIdServices(dbp.getIdServices());
                }
            }
        }
        catch (HibernateException ex)
        {
            throw new FailedToSaveToDatabaseException();
        }
    }

    public void saveOrUpdate(Collection<IExtraService> extraServices, Collection<IHabitation> habitations, Collection<IServiceType> serviceTypes)
            throws FailedToSaveToDatabaseException
    {
        if ((extraServices == null) || (habitations == null) || (serviceTypes == null))
        {
        }
        else
        {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction ts = session.beginTransaction();
            ts.begin();

            saveOrUpdate(session, extraServices, habitations, serviceTypes);

            ts.commit();
        }
    }
}
