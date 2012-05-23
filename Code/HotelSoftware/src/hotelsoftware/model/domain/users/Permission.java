package hotelsoftware.model.domain.users;

import hotelsoftware.controller.data.users.PermissionData;
import hotelsoftware.support.PermissionNotFoundException;
import java.util.Collection;

/**
 * Stellt eine Befungnis (veraendern von Daten, erstellen von Reservierugen, etc.)
 * dar, mit der das System arbeitet
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class Permission implements IPermission
{
    private Integer id;
    private String name;

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public void setName(String permission)
    {
        this.name = permission;
    }
    
    @Override
    public Integer getId()
    {
        return id;
    }

    @Override
    public void setId(Integer id)
    {
        if (this.id == null)
        {
            this.id = id;
        }
    }
    
    public Permission()
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
    public static IPermission create(String name)
    {
        return new Permission(name);
    }

    /**
     * Gibt alle Befugnisse aus
     * @return 
     * Alle Befugnisse, die vorhanden sind.
     */
    public static Collection<IPermission> getAllPermissions()
    {
        return UserManager.getInstance().getAllPermissions();
    }

    /**
     * Gibt eine Befugnis gesucht nach Namen aus
     * @param name
     * Der Name der Befugnis
     * @return
     * Die gesuchte Befugnis
     */
    public static IPermission getPermissionByName(String name) throws PermissionNotFoundException
    {
        return UserManager.getInstance().getPermissionByName(name);
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final Permission other = (Permission) obj;
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name))
        {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 59 * hash + (this.name != null ? this.name.hashCode() : 0);
        return hash;
    }
    
    
}
