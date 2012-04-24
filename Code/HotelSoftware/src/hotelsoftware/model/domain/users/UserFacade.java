/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.users;

import hotelsoftware.model.database.users.DBPermission;
import hotelsoftware.model.database.users.DBUser;
import hotelsoftware.model.DynamicMapper;
import java.util.Collection;
import java.util.Set;

/**
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
    
    public User login(String username, String password) throws LoginFailureException
    {
        DBUser dbuser = DBUser.login(username, password);
        
        if (dbuser == null)
        {
            throw new LoginFailureException();
        }
        
        return (User) DynamicMapper.map(dbuser);
    }
    
    public Set<Permission> getAllPermissions()
    {
        return DynamicMapper.mapCollection(DBPermission.getPermissions());
    }
    
    public Permission getPermissionByName(String name) throws PermissionNotFoundException
    {
        DBPermission p = DBPermission.getPermissionByName(name);
        
        if (p == null)
        {
            throw new PermissionNotFoundException();
        }
        
        return (Permission) DynamicMapper.map(p);
    }
}
