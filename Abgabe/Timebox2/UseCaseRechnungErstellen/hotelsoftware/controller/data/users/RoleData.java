/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.controller.data.users;

import java.util.Collection;
import java.util.Set;

/**
 *Dieses Interface enthält alle Methoden die für die Klasse Role benötigt werden
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public interface RoleData
{
    String getName();

    Collection<PermissionData> getPermissionsData();
}
