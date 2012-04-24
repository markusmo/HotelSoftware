/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.users.data;

import java.util.Collection;
import java.util.Set;

/**
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public interface RoleData
{
    String getName();

    Set<PermissionData> getPermissionsData();
}
