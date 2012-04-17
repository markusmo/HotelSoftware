/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.users;

import hotelsoftware.model.database.FailedToSaveToDatabaseException;
import hotelsoftware.util.HibernateUtil;
import hotelsoftware.model.database.users.DBPermission;
import hotelsoftware.model.database.users.DBRole;
import hotelsoftware.model.database.users.DBUser;
import hotelsoftware.model.DynamicMapper;
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
        Session session = null;
        Transaction ts = null;
                
        try
        {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            ts = session.beginTransaction();
            ts.begin();
        
            saveOrUpdate(session, users, roles, permissions);
            
            ts.commit();
        }
        catch (HibernateException ex)
        {
            if (ts != null)
            {
                ts.rollback();
            }
            
            throw new FailedToSaveToDatabaseException();
        }
        finally
        {
            if (session != null)
            {
                session.close();
            }
        }
    }
    
    public void saveOrUpdate(Session session, Collection<User> users, Collection<Role> roles, Collection<Permission> permissions) throws FailedToSaveToDatabaseException
    {
        for (Permission permission : permissions)
        {                
            DBPermission dbp = (DBPermission) DynamicMapper.map(permission);

            session.saveOrUpdate(dbp);
            permission.setId(dbp.getId());
        }

        for (Role role : roles)
        {
            DBRole dbr = (DBRole) DynamicMapper.map(role);

            session.saveOrUpdate(dbr);
            role.setId(dbr.getId());
        }

        for (User user : users)
        {
            DBUser dbu = (DBUser) DynamicMapper.map(user);

            session.saveOrUpdate(dbu);
            user.setId(dbu.getId());
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
                
                User temp = (User) DynamicMapper.map(dbu);
                user.setPassword(temp.getPassword());
                user.setUsername(temp.getUsername());
                user.setRoles(temp.getRoles());
            }
        }
        
        for (Role role : roles)
        {
            DBRole dbr;

            if (role.getId() != null)
            {
                dbr = (DBRole) session.createCriteria(DBUser.class).add(Restrictions.eq("id", 
                    role.getId())).uniqueResult();
                
                Role temp = (Role) DynamicMapper.map(dbr);
                role.setName(temp.getName());
                role.setPermissions(temp.getPermissions());
            }
        }
        
        for (Permission permission : permissions)
        {
            DBPermission dbp;

            if (permission.getId() != null)
            {
                dbp = (DBPermission) session.createCriteria(DBRole.class).add(Restrictions.eq("id", 
                    permission.getId())).uniqueResult();
                
                Permission temp = (Permission) DynamicMapper.map(dbp);
                permission.setName(temp.getName());
            }
        }
    }
}
