package hotelsoftware.model.domain.users;

import hotelsoftware.model.domain.users.data.RoleData;
import hotelsoftware.model.domain.users.data.PermissionData;
import hotelsoftware.util.HelperFunctions;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Diese Klasse bildet Rollen im System (Administrator, etc.) ab
 *
 * @author Dunst
 */
public class Role implements RoleData
{

    private Integer id;
    private String name;
    private Collection<Permission> permissions;

    public Collection<Permission> getPermissions()
    {
        return permissions;
    }

    public void setPermissions(Collection<Permission> permissions)
    {
        this.permissions = permissions;
    }

    @Override
    public String getName()
    {
        return name;
    }

    void setName(String name)
    {
        this.name = name;
    }

    public Integer getId()
    {
        return id;
    }

    void setId(Integer id)
    {
        if (this.id == null)
        {
            this.id = id;
        }
    }

    Role()
    {
    }

    private Role(String name)
    {
        this(name, new HashSet<Permission>());
    }

    private Role(String name, Collection<Permission> permissions)
    {
        this.name = name;
        this.permissions = permissions;
    }

    /**
     * Instanziert eine neue Rolle mit einem Namen
     * @param name
     * Der Name der Rolle
     * @param permissions
     * Die Befungnisse dieser Rolle
     * @return 
     * eine neue Instanz
     */
    public static Role create(String name, Collection<Permission> permissions)
    {
        return new Role(name, permissions);
    }

    public Collection<PermissionData> getPermissionsData()
    {
        return new HelperFunctions<PermissionData, Permission>().castCollectionUp(
                getPermissions());
    }
}
