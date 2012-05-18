/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.fhv.roomanizer.application;

import at.fhv.roomanizer.domain.person.IUser;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 *
 * @author Markus Mohanty <markus.mo at gmx.net>
 */
public interface IUserController
{

    /**
     * Returns a list of all Users
     *
     * @return List of all IUser
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws ClassNotFoundException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    List<IUser> loadAllUser() throws InstantiationException, IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException;

    /**
     * Returns the first loaded User from the database
     *
     * @deprecated Is only for temporary use for the createHabitation() in the CheckInController
     * @return the first loaded User
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws ClassNotFoundException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    @Deprecated
    IUser loadFirstUser() throws InstantiationException, IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException;
    
}
