/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.support;

import hotelsoftware.model.domain.users.Permission;

/**
 *
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
