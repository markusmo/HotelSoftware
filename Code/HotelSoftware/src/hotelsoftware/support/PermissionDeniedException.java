/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.support;

import hotelsoftware.model.domain.users.IPermission;

/**
 *Diese Exception ist dafür da, falls die Rechte nicht gewährt werden
 * @author Dunst
 */
public class PermissionDeniedException extends Exception
{
    private IPermission missingPermission;
    
    public PermissionDeniedException(IPermission missing)
    {
        this.missingPermission = missing;
    }
    
    public IPermission getMissingPermission()
    {
        return missingPermission;
    }
}
