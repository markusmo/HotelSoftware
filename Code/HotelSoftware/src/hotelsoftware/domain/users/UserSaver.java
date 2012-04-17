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
            for (Permission permission : permissions)
            {
                DBPermission dbp;
                
                if (permission.getId() != null)
                {
                    dbp = (DBPermission) session.createCriteria(DBPermission.class).add(Restrictions.eq("id", 
                        permission.getId())).uniqueResult();
                }
                else
                {
                    dbp = new DBPermission();
                }
                
                dbp.setName(permission.getPermission());

                session.saveOrUpdate(dbp);
                permission.setId(dbp.getId());
            }
            
            for (Role role : roles)
            {
                DBRole dbr;
                
                if (role.getId() != null)
                {
                    dbr = (DBRole) session.createCriteria(DBRole.class).add(Restrictions.eq("id", 
                        role.getId())).uniqueResult();
                }
                else
                {
                    dbr = new DBRole();
                }
                
                dbr.setName(role.getName());

                Collection<DBPermission> newPermissions = DynamicMapper.mapCollection(role.getPermissions(), DBPermission.class);
                dbr.setPermissions(newPermissions);

                session.saveOrUpdate(dbr);
                role.setId(dbr.getId());
            }
            
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
                dbu.setRoles(newRoles);

                session.saveOrUpdate(dbu);
                user.setId(dbu.getId());
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
    
    public void rollback(Collection<User> users, Collection<Role> roles, Collection<Permission> permissions)
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();
        
        for (User user : users)
        {
            DBUser dbu;

            if (user.getId() != null)
            {
                dbu = (DBUser) session.createCriteria(DBUser.class).add(Restrictions.eq("id", 
                    user.getId())).uniqueResult();
                
                user.setUsername(dbu.getUsername());
                user.setPassword(dbu.getPassword());

                Collection<Role> newRoles = DynamicMapper.mapCollection(dbu.getRoles(), Role.class);
                user.setRoles(newRoles);
            }
        }
        
        for (Role role : roles)
        {
            DBRole dbr;

            if (role.getId() != null)
            {
                dbr = (DBRole) session.createCriteria(DBUser.class).add(Restrictions.eq("id", 
                    role.getId())).uniqueResult();
                
                role.setName(dbr.getName());

                Collection<Permission> newRoles = DynamicMapper.mapCollection(dbr.getPermissions(), Permission.class);
                role.setPermissions(newRoles);
            }
        }
        
        for (Permission permission : permissions)
        {
            DBPermission dbp;

            if (permission.getId() != null)
            {
                dbp = (DBPermission) session.createCriteria(DBRole.class).add(Restrictions.eq("id", 
                    permission.getId())).uniqueResult();
                
                permission.setName(dbp.getName());
            }
        }
    }
}
