/**
 *
 */
package at.fhv.roomanizer.application;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import at.fhv.roomanizer.domain.service.Type;
import at.fhv.roomanizer.persistence.ManagerFactory;
import at.fhv.roomanizer.persistence.manager.IServiceManager;
import at.fhv.roomanizer.persistence.manager.ServiceManager;

/**
 * Controlls functions for manipulating services in the Database
 *
 * @author phils
 */
public class ServiceController
{
    /*
     * --------------------loading Data for service--------------------------
     */
    /**
     * Returns a list of all types
     *
     * @return a list of all types
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws ClassNotFoundException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    public List<Type> loadAllTypes() throws InstantiationException, IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException
    {
        IServiceManager servManager = ManagerFactory.getServiceManager();
        List<Type> allTypes = new ArrayList<Type>();
        for (Type t : servManager.getAllTypes())
        {
            allTypes.add(t);
        }
        return allTypes;
    }

    /**
     * Returns a type searched by name
     *
     * @param name
     * @return a type
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws ClassNotFoundException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    public Type loadTypeByName(String name) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException
    {
        IServiceManager servManager = ManagerFactory.getServiceManager();
        return servManager.getTypeByName(name);
    }
    /*
     * --------------------setting Data for the Service--------------------------
     */
}
