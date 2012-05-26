/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.users;

import java.util.Collection;

/**
 *
 * @author mohi
 */
public interface IRole 
{

    Integer getId();

    String getName();

    Collection<IPermission> getPermissions();

    void setPermissions(Collection<IPermission> permissions);
    
    void setId(Integer id);
    
    void setName(String Name);
    
}
