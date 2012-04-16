/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.domain.users;

import hotelsoftware.database.model.DBPermission;
import hotelsoftware.database.model.DBUser;
import java.util.Collection;
import java.util.LinkedList;

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
    
    public User login(String username, String password)
    {
        DBUser dbuser = DBUser.login(username, password);
        
        return new User(dbuser.getUsername(), dbuser.getPassword());
    }
    
    public Collection<Permission> getAllPermissions()
    {
        Collection<Permission> permissions = new LinkedList<Permission>();
        
        for (DBPermission p : DBPermission.getPermissions())
        {
            permissions.add(new Permission(p.getName()));
        }
        
        return permissions;
    }
    
    public Permission getPermissionByName(String name) throws PermissionNotFoundException
    {
        DBPermission p = DBPermission.getPermissionByName(name);
        
        if (p == null)
        {
            throw new PermissionNotFoundException();
        }
        
        return new Permission(p.getName());
    }
}
