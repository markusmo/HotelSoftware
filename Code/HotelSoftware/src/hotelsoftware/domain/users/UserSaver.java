/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.domain.users;

import hotelsoftware.database.FailedToSaveToDatabaseException;
import hotelsoftware.database.HibernateUtil;
import hotelsoftware.database.model.DBPermission;
import hotelsoftware.database.model.DBRole;
import hotelsoftware.database.model.DBUser;
import hotelsoftware.util.DynamicMapper;
import java.util.Collection;
import java.util.LinkedList;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Dunst
 */
public class UserSaver
{
    private UserSaver()
    {
    }
    
    public static UserSaver getInstance()
    {
        return UserSaverHolder.INSTANCE;
    }
    
    private static class UserSaverHolder
    {
        private static final UserSaver INSTANCE = new UserSaver();
    }
    
    public void saveOrUpdate(Collection<User> users, Collection<Role> roles, Collection<Permission> permissions) throws FailedToSaveToDatabaseException
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();
        
        try
        {
            for (User user : users)
            {
                DBUser dbu;
            
                if (user.getId() != null)
                {
                    dbu = (DBUser) session.createCriteria(DBUser.class).add(Restrictions.eq("id", 
                        user.getId())).uniqueResult();
                }
                else
                {
                    dbu = new DBUser();
                }

                dbu.setUsername(user.getUsername());
                dbu.setPassword(user.getPassword());
                /*Collection<DBRole> newRoles = new LinkedList<DBRole>();
                for (Role role : user.getAllRoles())
                {
                    newRoles.add((DBRole)DynamicMapper.map(role, DBRole.class));
                }*/
                
                Collection<DBRole> newRoles = DynamicMapper.mapCollection(user.getRoles(), DBRole.class);
                
                dbu.setRolesCollection(newRoles);

                session.saveOrUpdate(dbu);
                
                user.setId(dbu.getId());
            }

            for (Role role : roles)
            {
                DBRole dbr = (DBRole) session.createCriteria(DBRole.class).add(Restrictions.eq("name", 
                        role.getName())).uniqueResult();

                if (dbr == null)
                {
                    dbr = new DBRole();
                    dbr.setName(role.getName());
                }

                Collection<DBPermission> newPermissions = new LinkedList<DBPermission>();
                for (Permission permission : role.getPermissions())
                {
                    newPermissions.add((DBPermission)DynamicMapper.map(permission, DBPermission.class));
                }
                dbr.setPermissionsCollection(newPermissions);

                session.saveOrUpdate(dbr);
            }

            for (Permission permission : permissions)
            {
                DBPermission dbp = (DBPermission) session.createCriteria(DBPermission.class).add(Restrictions.eq("name", 
                        permission.getPermission())).uniqueResult();

                if (dbp == null)
                {
                    dbp = new DBPermission();
                    dbp.setName(permission.getPermission());
                }

                session.saveOrUpdate(dbp);
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
