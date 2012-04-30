/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.controller.data.users;

import hotelsoftware.model.domain.users.Permission;
import java.util.Collection;

/**
 *Dieses Interface enthält alle Methoden die für die Klasse User benötigt werden
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public interface UserData
{    
    Collection<PermissionData> getAllPermissionsData();

    Collection<RoleData> getRolesData();

    String getUsername();

    boolean hasPermission(Permission permission);
    
}
