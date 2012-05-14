package hotelsoftware.model.domain.users;

import hotelsoftware.model.DynamicMapper;
import hotelsoftware.model.database.FailedToSaveToDatabaseException;
import hotelsoftware.model.database.users.DBPermission;
import hotelsoftware.model.database.users.DBRole;
import hotelsoftware.model.database.users.DBUser;
import hotelsoftware.util.HibernateUtil;
import java.util.Collection;
import java.util.Set;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 * Diese Klasse verwaltet die Sicherung aller Klassen des Packages User in die
 * Datenbank.
 *
 * @author Dunst
 */
public class UserSaver {

    private UserSaver() {
    }

    public static UserSaver getInstance() {
        return UserSaverHolder.INSTANCE;
    }

    private static class UserSaverHolder {

        private static final UserSaver INSTANCE = new UserSaver();
    }

    /**
     * Diese Klasse speichert falls änderungen vorhanden sind, diese in der
     * Datenbank ab
     *
     * @param users
     * @param roles
     * @param permissions
     * @throws FailedToSaveToDatabaseException
     */
    public void saveOrUpdate(Set<User> users, Set<Role> roles, Set<Permission> permissions) throws FailedToSaveToDatabaseException {
        Session session = null;
        Transaction ts = null;

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            ts = session.beginTransaction();
            ts.begin();

            saveOrUpdate(session, users, roles, permissions);

            ts.commit();
        } catch (HibernateException ex) {
            if (ts != null) {
                ts.rollback();
            }

            throw new FailedToSaveToDatabaseException();
        } finally {
            if (session != null) {
                ;
            }
        }
    }

    /**
     * Diese Methode sichert/updated mittels einer uebergebenen
     * Hibernate-Session alle veraenderten oder neuen uebergebenen Objekte
     *
     * @param session Die Hibernate-Session auf der die Sicherung/Update
     * geschieht
     * @param users User, die veraendert/neu sind
     * @param roles Rollen, die veraendert/neu sind
     * @param permissions Befugnisse, die veraendert/neu sind
     * @throws FailedToSaveToDatabaseException Wirft diesen Fehler, wenn die
     * Sicherung fehlschlaegt.
     */
    public void saveOrUpdate(Session session, Set<User> users, Set<Role> roles, Set<Permission> permissions) throws FailedToSaveToDatabaseException {

        if (permissions != null) {
            for (Permission permission : permissions) {
                DBPermission dbp = (DBPermission) DynamicMapper.map(permission);

                session.saveOrUpdate(dbp);
                permission.setId(dbp.getId());
            }
        }

        if (roles != null) {
            for (Role role : roles) {
                DBRole dbr = (DBRole) DynamicMapper.map(role);

                session.saveOrUpdate(dbr);
                role.setId(dbr.getId());
            }
        }

        if (users != null) {
            for (User user : users) {
                DBUser dbu = (DBUser) DynamicMapper.map(user);

                session.saveOrUpdate(dbu);
                user.setId(dbu.getId());
            }
        }
    }

    /**
     * Stellt alle Daten aus der Datenbank
     *
     * @param users User, die wiederhergestellt werden muessen
     * @param roles Rollen, die wiederhergestellt werden muessen
     * @param permissions Befugnisse, die wiederhergestellt werden muessen
     */
    public void rollback(Set<User> users, Set<Role> roles, Set<Permission> permissions) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();

        for (User user : users) {
            DBUser dbu;

            if (user.getId() != null) {
                dbu = (DBUser) session.createCriteria(DBUser.class).add(Restrictions.eq("id",
                        user.getId())).uniqueResult();

                User temp = (User) DynamicMapper.map(dbu);
                user.setPassword(temp.getPassword());
                user.setUsername(temp.getUsername());
                user.setRoles(temp.getRoles());
            }
        }

        for (Role role : roles) {
            DBRole dbr;

            if (role.getId() != null) {
                dbr = (DBRole) session.createCriteria(DBUser.class).add(Restrictions.eq("id",
                        role.getId())).uniqueResult();

                Role temp = (Role) DynamicMapper.map(dbr);
                role.setName(temp.getName());
                role.setPermissions(temp.getPermissions());
            }
        }

        for (Permission permission : permissions) {
            DBPermission dbp;

            if (permission.getId() != null) {
                dbp = (DBPermission) session.createCriteria(DBRole.class).add(Restrictions.eq("id",
                        permission.getId())).uniqueResult();

                Permission temp = (Permission) DynamicMapper.map(dbp);
                permission.setName(temp.getName());
            }
        }
    }
}
