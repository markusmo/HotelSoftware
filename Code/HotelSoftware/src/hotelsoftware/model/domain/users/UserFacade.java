package hotelsoftware.model.domain.users;

import hotelsoftware.model.database.users.DBPermission;
import hotelsoftware.model.database.users.DBUser;
import hotelsoftware.model.DynamicMapper;
import java.util.Collection;
import java.util.Set;

/**
 * Fassadenklasse, die alle Objekte des Package User verwaltet
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
     * Logincontroller?
     * @param username
     * @param password
     * @return
     * @throws LoginFailureException 
     */
    public User login(String username, String password) throws LoginFailureException
    {
        DBUser dbuser = DBUser.login(username, password);
        
        if (dbuser == null)
        {
            throw new LoginFailureException();
        }
        
        return (User) DynamicMapper.map(dbuser);
    }
    
    /**
     * Gibt alle vorhandenen Befugnisse aus
     * @return 
     */
    public Collection<Permission> getAllPermissions()
    {
        return DynamicMapper.mapCollection(DBPermission.getPermissions());
    }
    
    /**
     * Gibt eine Befugnis gesucht nach Namen aus
     * @param name
     * der Name der Befugnis
     * @return
     * Die gesuchte Befugnis
     * @throws PermissionNotFoundException 
     * Wirft einen Fehler, wenn die Befugnis nicht gefunden wird.
     */
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
