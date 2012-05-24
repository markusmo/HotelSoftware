/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.users;

import hotelsoftware.controller.data.users.PermissionData;

/**
 *
 * @author mohi
 */
public interface IPermission extends PermissionData
{
    Integer getId();

    void setId(Integer id);

    void setName(String permission);
    
}
