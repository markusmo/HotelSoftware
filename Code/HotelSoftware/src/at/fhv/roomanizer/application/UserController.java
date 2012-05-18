/**
 *
 */
package at.fhv.roomanizer.application;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import at.fhv.roomanizer.domain.person.IUser;
import at.fhv.roomanizer.persistence.ManagerFactory;
import at.fhv.roomanizer.persistence.manager.PersonManager;

/**
 * Controlls functions for manipulating Users in the Database
 *
 * @author phils
 *
 */
public class UserController implements IUserController
{
    /*
     * --------------------loading Data for user--------------------------
     */
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
    @Override
    public List<IUser> loadAllUser() throws InstantiationException, IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException
    {
        PersonManager persManager = ManagerFactory.getPersonmanager();
        List<IUser> allUser = new ArrayList<IUser>();
        for (IUser iU : persManager.getUserList())
        {
            allUser.add(iU);
        }
        return allUser;
    }

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
    @Override
    public IUser loadFirstUser() throws InstantiationException, IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException
    {
        return loadAllUser().get(0);
    }
    /*
     * --------------------setting Data for the User--------------------------
     */
}
