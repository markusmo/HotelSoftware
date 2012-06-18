package hotelsoftware.model.database.manager;

import hotelsoftware.model.DynamicMapper;
import hotelsoftware.model.database.users.DBPermission;
import hotelsoftware.model.database.users.DBUser;
import hotelsoftware.model.domain.users.IPermission;
import hotelsoftware.model.domain.users.IUser;
import hotelsoftware.support.LoginFailureException;
import hotelsoftware.support.PermissionNotFoundException;
import hotelsoftware.util.HibernateUtil;
import java.util.Collection;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 * Fassadenklasse, die alle Objekte des Package User verwaltet
 *
 * @author Dunst
 */
public class UserManager extends Manager
{
    private UserManager()
    {
    }

    public static UserManager getInstance()
    {
        return UserFacadeHolder.INSTANCE;
    }

    private static class UserFacadeHolder
    {
        private static final UserManager INSTANCE = new UserManager();
    }

    /**
     * Ueberprueft, ob ein User in der Datenbank hinterlegt ist
     *
     * @param username
     * Der Benutzername des Users
     * @param password
     * Das Passwort des Users
     * @return
     * Einen User, der in der Datenbank hinterlegt ist, falls Passwort und Benutzername uebereinstimmen
     * @throws LoginFailureException
     * Wirft einen Fehler, wenn kein User gefunden wurde oder das Passwort oder Benutzername nicht uebereinstimmen
     */
    public IUser login(String username, String password) throws LoginFailureException
    {
        startTransaction();
        
        DBUser retUser = (DBUser) getSession().createCriteria(DBUser.class).add(Restrictions.and(Restrictions.eq("username", username),
                Restrictions.eq("password", password))).uniqueResult();

        if (retUser == null)
        {
            throw new LoginFailureException();
        }
        
        commit();

        return (IUser) DynamicMapper.map(retUser);
    }

    /**
     * Gibt alle vorhandenen Befugnisse aus
     *
     * @return
     * Alle Befugnisse, die vorhanden sind
     */
    public Collection<IPermission> getAllPermissions()
    {
        startTransaction();
        Criteria criteria = getSession().createCriteria(DBPermission.class);
        List<DBPermission> retList = criteria.list();
        commit();
        
        return DynamicMapper.mapCollection(retList);
    }
    
    public List<IUser> getUsers() throws HibernateException
    {
        startTransaction();
        Criteria criteria = getSession().createCriteria(DBUser.class);
        List<DBUser> retList = criteria.list();
        commit();
        
        return (List<IUser>)DynamicMapper.mapCollection(retList);
    }

    /**
     * Gibt eine Befugnis gesucht nach Namen aus
     *
     * @param name
     * der Name der Befugnis
     * @return
     * Die gesuchte Befugnis
     * @throws PermissionNotFoundException
     * Wirft einen Fehler, wenn die Befugnis nicht gefunden wird.
     */
    public IPermission getPermissionByName(String name) throws PermissionNotFoundException
    {
        startTransaction();
        Criteria criteria = getSession().createCriteria(DBPermission.class).add(Restrictions.eq("name", name));
        DBPermission retValue = (DBPermission)criteria.uniqueResult();

        if (retValue == null)
        {
            throw new PermissionNotFoundException(name);
        }
        
        commit();

        return (IPermission) DynamicMapper.map(retValue);
    }
}
