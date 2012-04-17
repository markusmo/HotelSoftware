/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.users;

import java.util.Collection;
import java.util.HashSet;

/**
 *
 * @author Dunst
 */
public class Role
{
    private Integer id;
    private String name;
    private Collection<Permission> permissions;

    public Collection<Permission> getPermissions()
    {
        return permissions;
    }
    
    void setPermissions(Collection<Permission> permissions)
    {
        this.permissions = permissions;
    }

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
    
    private Role(String name, Collection<Permission> permissions)
    {
        this.name = name;
        this.permissions = permissions;
    }

    public static Role create(String name, Collection<Permission> permissions)
    {
        return new Role(name, permissions);
    }
}
