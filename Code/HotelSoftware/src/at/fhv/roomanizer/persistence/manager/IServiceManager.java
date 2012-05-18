/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.fhv.roomanizer.persistence.manager;

import at.fhv.roomanizer.domain.service.Service;
import at.fhv.roomanizer.domain.service.Type;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 *
 * @author Markus Mohanty <markus.mo at gmx.net>
 */
public interface IServiceManager
{

    /**
     * Returns all Service-Types from the database
     * @return All Service-Types which are stored in the database
     * @throws IllegalArgumentException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    List<Type> getAllTypes() throws IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException;

    /**
     * Returns the type, associated with the given name
     * @param typeName Name of the type
     * @return Returns the type, which is associated with the given name
     * @throws IllegalArgumentException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    Type getTypeByName(String typeName) throws IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException;

    void saveService(Service service) throws IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException;
    
}
