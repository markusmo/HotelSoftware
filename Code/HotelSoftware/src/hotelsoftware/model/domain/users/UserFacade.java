package hotelsoftware.model.domain.users;

import hotelsoftware.model.DynamicMapper;
import hotelsoftware.model.database.users.DBPermission;
import hotelsoftware.model.database.users.DBUser;
import hotelsoftware.support.LoginFailureException;
import hotelsoftware.support.PermissionNotFoundException;
import hotelsoftware.util.HibernateUtil;
import java.util.Collection;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 * Fassadenklasse, die alle Objekte des Package User verwaltet
 *
 * @author Dunst
 */
public class UserFacade
{
    private UserFacade()
    {
    }

    public static UserFacade getInstance()
    {
        return UserFacadeHolder.INSTANCE;
    }

    private static class UserFacadeHolder
    {
        private static final UserFacade INSTANCE = new UserFacade();
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
    public User login(String username, String password) throws LoginFailureException
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();
        DBUser retUser = (DBUser) session.createCriteria(DBUser.class).add(Restrictions.and(Restrictions.eq("username", username),
                Restrictions.eq("password", password))).uniqueResult();

        if (retUser == null)
        {
            throw new LoginFailureException();
        }

        return (User) DynamicMapper.map(retUser);
    }

    /**
     * Gibt alle vorhandenen Befugnisse aus
     *
     * @return
     * Alle Befugnisse, die vorhanden sind
     */
    public Collection<Permission> getAllPermissions()
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();
        Criteria criteria = session.createCriteria(DBPermission.class);
        List<DBPermission> retList = criteria.list();

        return DynamicMapper.mapCollection(retList);
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
    public Permission getPermissionByName(String name) throws PermissionNotFoundException
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();
        Criteria criteria = session.createCriteria(DBPermission.class);
        List<DBPermission> retValue = criteria.list();

        if (retValue == null)
        {
            throw new PermissionNotFoundException(name);
        }

        return (Permission) DynamicMapper.map(retValue);
    }
}
