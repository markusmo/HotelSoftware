package hotelsoftware.model.domain.users;

import hotelsoftware.controller.data.users.PermissionData;
import hotelsoftware.util.HelperFunctions;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Diese Klasse bildet Rollen im System (Administrator, etc.) ab
 *
 * @author Dunst
 */
public class Role implements IRole
{

    private Integer id;
    private String name;
    private Collection<IPermission> permissions;

    @Override
    public Collection<IPermission> getPermissions()
    {
        return permissions;
    }

    @Override
    public void setPermissions(Collection<IPermission> permissions)
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

    @Override
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

    public Role()
    {
    }

    private Role(String name)
    {
        this(name, new HashSet<IPermission>());
    }

    private Role(String name, Collection<IPermission> permissions)
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
    public static Role create(String name, Collection<IPermission> permissions)
    {
        return new Role(name, permissions);
    }

    @Override
    public Collection<PermissionData> getPermissionsData()
    {
        return new HelperFunctions<PermissionData, IPermission>().castCollectionUp(
                getPermissions());
    }
}
