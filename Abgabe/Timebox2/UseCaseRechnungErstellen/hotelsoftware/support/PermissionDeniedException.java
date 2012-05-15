/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.support;

import hotelsoftware.model.domain.users.Permission;

/**
 *Diese Exception ist dafür da, falls die Rechte nicht gewährt werden
 * @author Dunst
 */
public class PermissionDeniedException extends Exception
{
    private Permission missingPermission;
    
    public PermissionDeniedException(Permission missing)
    {
        this.missingPermission = missing;
    }
    
    public Permission getMissingPermission()
    {
        return missingPermission;
    }
}
