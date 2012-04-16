/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.domain.users;

import hotelsoftware.database.model.DBPermission;
import hotelsoftware.database.model.DBUser;
import hotelsoftware.util.DynamicMapper;
import java.util.Collection;

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
        
        return DynamicMapper.map(dbuser, User.class);
    }
    
    public Collection<Permission> getAllPermissions()
    {
        return DynamicMapper.mapCollection(DBPermission.getPermissions(), Permission.class);
    }
    
    public Permission getPermissionByName(String name) throws PermissionNotFoundException
    {
        DBPermission p = DBPermission.getPermissionByName(name);
        
        if (p == null)
        {
            throw new PermissionNotFoundException();
        }
        
        return DynamicMapper.map(p, Permission.class);
    }
}
