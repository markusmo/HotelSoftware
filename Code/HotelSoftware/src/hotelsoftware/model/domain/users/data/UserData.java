/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.users.data;

import hotelsoftware.model.domain.users.Permission;
import java.util.Collection;

/**
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public interface UserData
{    
    Collection<PermissionData> getAllPermissionsData();

    Collection<RoleData> getRolesData();

    String getUsername();

    boolean hasPermission(Permission permission);
    
}
