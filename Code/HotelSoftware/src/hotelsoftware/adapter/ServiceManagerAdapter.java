/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.adapter;

import at.fhv.roomanizer.domain.service.Service;
import at.fhv.roomanizer.domain.service.Type;
import at.fhv.roomanizer.persistence.manager.IServiceManager;
import hotelsoftware.model.database.FailedToSaveToDatabaseException;
import hotelsoftware.model.domain.service.IServiceType;
import hotelsoftware.model.domain.service.ServiceSaver;
import hotelsoftware.model.domain.service.ServiceType;
import hotelsoftware.support.ServiceTypeNotFoundException;
import hotelsoftware.util.HelperFunctions;
import hotelsoftware.util.HibernateUtil;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.classic.Session;

/**
 *
 * @author Markus Mohanty <markus.mo at gmx.net>
 */
public class ServiceManagerAdapter implements IServiceManager
{
    private ServiceManagerAdapter()
    {
    }

    public ServiceManagerAdapter getInstance()
    {
        return ServiceManagerAdapterHolder.INSTANCE;
    }

    private static class ServiceManagerAdapterHolder
    {
        private final static ServiceManagerAdapter INSTANCE = new ServiceManagerAdapter();
    }

    @Override
    public List<Type> getAllTypes() throws IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException
    {
        Set<ServiceType> allServiceTypes = ServiceType.getAllServiceTypes();
        Collection<IServiceType> servicetypes = new LinkedList<IServiceType>();

        for (ServiceType s : allServiceTypes)
        {
            servicetypes.add(s);
        }
        //Cast collection of ServieTypes to the TypeAdapter
        List<TypeAdapter> adaptedList = HelperFunctions.getAdaptedList(servicetypes, TypeAdapter.class);

        //Cast collection of TypeAdapter to Type
        Collection<Type> upCastedAdaptedList = HelperFunctions.castCollectionUp(adaptedList, Type.class, TypeAdapter.class);

        //Create new LinkedList
        return new LinkedList<Type>(upCastedAdaptedList);
    }

    @Override
    public Type getTypeByName(String typeName) throws IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException
    {
        try
        {
            return new TypeAdapter(ServiceType.getTypeByName(typeName));
        }
        catch (ServiceTypeNotFoundException ex)
        {
            Logger.getLogger(ServiceManagerAdapter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void saveService(Service service) throws IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        ServiceAdapter adapter = (ServiceAdapter) service;
        LinkedList<hotelsoftware.model.domain.service.IService> services = new LinkedList<hotelsoftware.model.domain.service.IService>();
        services.add(adapter.getOurType());
        try
        {
            ServiceSaver.getInstance().saveOrUpdate(session, null, null, null);
        }
        catch (FailedToSaveToDatabaseException ex)
        {
            Logger.getLogger(ServiceManagerAdapter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
