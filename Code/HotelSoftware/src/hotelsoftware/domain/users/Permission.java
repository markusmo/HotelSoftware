/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.domain.users;

import hotelsoftware.database.Exceptions.FaildToDeleteFromDatabaseException;
import hotelsoftware.database.Exceptions.FailedToSaveToDatabaseException;
import hotelsoftware.database.model.DBPermission;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import org.hibernate.HibernateException;

/**
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class Permission
{
    private String permission;

    public String getPermission()
    {
        return permission;
    }

    private void setPermission(String permission)
    {
        this.permission = permission;
    }
    
    public Permission(String permission)
    {
        this.permission = permission;
    }    

    /**
     * Communicates with the model and creates a linked list of permission
     * @return 
     * a linked list of permissions on domain-level
     */
    public static Collection<Permission> getAllPermissions()
    {
        return UserFacade.getInstance().getAllPermissions();
    }

    /**
     * Communicates with the model and retrieves a single permission on domain
     * level by name
     * @param name
     * the name of the permission
     * @return
     * a domain level permission
     */
    public static Permission getPermissionByName(String name) throws PermissionNotFoundException
    {
        return UserFacade.getInstance().getPermissionByName(name);
    }

    /**
     * Calls the model and creates a new permission in the database
     * @param permission 
     * the name of the new permission
     */
    public static void savePermission(String permission)
    {
        try
        {
            DBPermission.savePermission(permission);
        } catch (HibernateException ex)
        {
            //connection faild
        } catch (FailedToSaveToDatabaseException ex)
        {
            //saving failed
        }
    }
    
    /**
     * Calls the model and deletes the permission in the database
     * @param permission 
     * the name of the permission
     */
    public static void deletePermission(String permission)
    {
        try
        {
            DBPermission.deletePermission(permission);
        } catch (FaildToDeleteFromDatabaseException ex)
        {
            //deleting failed
        }
    }
    
    
}
