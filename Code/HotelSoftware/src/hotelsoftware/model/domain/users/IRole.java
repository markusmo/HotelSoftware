/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.users;

import hotelsoftware.controller.data.users.PermissionData;
import hotelsoftware.controller.data.users.RoleData;
import java.util.Collection;

/**
 *
 * @author mohi
 */
public interface IRole extends RoleData
{
    Integer getId();

    Collection<IPermission> getPermissions();

    void setPermissions(Collection<IPermission> permissions);
    
    void setId(Integer id);
    
    void setName(String Name);
    
}
