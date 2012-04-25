package hotelsoftware.model.domain.users;

import hotelsoftware.model.domain.users.data.PermissionData;
import java.util.Collection;

/**
 * Stellt eine Befungnis (veraendern von Daten, erstellen von Reservierugen, etc.)
 * dar, mit der das System arbeitet
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class Permission implements PermissionData
{
    private Integer id;
    private String name;

    @Override
    public String getName()
    {
        return name;
    }

    void setName(String permission)
    {
        this.name = permission;
    }
    
    public Integer getId()
    {
        return id;
    }

    void setId(Integer id)
    {
        if (id == null)
        {
            this.id = id;
        }
    }
    
    Permission()
    {
    }
    
    private Permission(String name)
    {
        this.name = name;
    }

    /**
     * Instanziert eine neue Befungnis mit einem Namen
     * @param name
     * Name der Befugnis
     * @return 
     * eine neue Instanz.
     */
    public static Permission create(String name)
    {
        return new Permission(name);
    }

    /**
     * Gibt alle Befugnisse aus
     * @return 
     * Alle Befugnisse, die vorhanden sind.
     */
    public static Collection<Permission> getAllPermissions()
    {
        return UserFacade.getInstance().getAllPermissions();
    }

    /**
     * Gibt eine Befugnis gesucht nach Namen aus
     * @param name
     * Der Name der Befugnis
     * @return
     * Die gesuchte Befugnis
     */
    public static Permission getPermissionByName(String name) throws PermissionNotFoundException
    {
        return UserFacade.getInstance().getPermissionByName(name);
    }
}
