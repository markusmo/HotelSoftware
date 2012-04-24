/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.service;

import hotelsoftware.model.DynamicMapper;
import hotelsoftware.model.database.FailedToSaveToDatabaseException;
import hotelsoftware.model.database.service.DBExtraService;
import hotelsoftware.model.database.service.DBServiceType;
import hotelsoftware.util.HibernateUtil;
import java.util.Collection;
import java.util.Set;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
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

    public void saveOrUpdate(Set<ExtraService> extraServices, Set<Habitation> habitations, Set<ServiceType> serviceTypes) throws FailedToSaveToDatabaseException
    {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();

        try
        {
            for (ServiceType serviceType : serviceTypes)
            {
                DBServiceType dbp = (DBServiceType) DynamicMapper.map(serviceType);

                session.saveOrUpdate(dbp);
                serviceType.setId(dbp.getId());
            }

            for (ExtraService extraService : extraServices)
            {
                DBExtraService dbp = (DBExtraService) DynamicMapper.map(extraService);

                session.saveOrUpdate(dbp);
                extraService.setId(dbp.getId());
            }

            for (Habitation habitation : habitations)
            {
                Habitation dbp = (Habitation) DynamicMapper.map(habitation);

                session.saveOrUpdate(dbp);
                habitation.setId(dbp.getId());
            }
            ts.commit();
        }
        catch (HibernateException ex)
        {
            ts.rollback();
            throw new FailedToSaveToDatabaseException();
        }
        finally
        {
            session.close();
        }
    }
}
