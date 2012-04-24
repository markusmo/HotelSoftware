/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.users;

import hotelsoftware.model.domain.users.data.RoleData;
import hotelsoftware.model.domain.users.data.PermissionData;
import hotelsoftware.util.HelperFunctions;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Dunst
 */
public class Role implements RoleData
{
    private Integer id;
    private String name;
    private Set<Permission> permissions;

    public Set<Permission> getPermissions()
    {
        return permissions;
    }
    
    public
    
    void setPermissions(Set<Permission> permissions)
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
        if (id == null)
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
    
    private Role(String name, Set<Permission> permissions)
    {
        this.name = name;
        this.permissions = permissions;
    }

    public static Role create(String name, Set<Permission> permissions)
    {
        return new Role(name, permissions);
    }
       

    public Set<PermissionData> getPermissionsData()
    {
        return new HelperFunctions<PermissionData, Permission>().castCollectionUp(getPermissions());
    }
}
