package hotelsoftware.model.domain.users;

import hotelsoftware.support.LoginFailureException;
import hotelsoftware.support.PermissionNotFoundException;
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
     * Ueberprueft, ob ein User in der Datenbank hinterlegt ist
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
     * Alle Befugnisse, die vorhanden sind
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
            throw new PermissionNotFoundException(name);
        }
        
        return (Permission) DynamicMapper.map(p);
    }
}
