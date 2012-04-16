/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.domain.users;

import java.util.Collection;
import java.util.LinkedList;

/**
 *
 * @author Dunst
 */
public class Role
{
    private String name;
    private Collection<Permission> permissions;

    public Collection<Permission> getPermissions()
    {
        return permissions;
    }

    public String getName()
    {
        return name;
    }

    private void setName(String name)
    {
        this.name = name;
    }
    
    public Role(String name)
    {
        this(name, new LinkedList<Permission>());
    }
    
    public Role(String name, Collection<Permission> permissions)
    {
        this.name = name;
        this.permissions = permissions;
    } 
}
