/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.users;

import java.util.Collection;

/**
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public interface UserData
{
    // location of interfaces in the domain subpackage like in the most java frameworks
    //FIXME change return-types of methods to interfaces (discuss)
    //--> sometimes casts on contoller-level neccessary (but most of the time you create new instances there)
    

    /**
     * converts roles from model to permissions in the domainclass
     *
     * @param permissions
     * @return Collection of permission
     */
    Collection<Permission> getAllPermissions();

    Collection<Role> getRoles();

    String getUsername();

    boolean hasPermission(Permission permission);
    
}
