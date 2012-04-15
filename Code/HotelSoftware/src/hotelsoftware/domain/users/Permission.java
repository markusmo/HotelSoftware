/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.domain.users;

import hotelsoftware.database.Exceptions.FaildToDeleteFromDatabaseException;
import hotelsoftware.database.Exceptions.FailedToSaveToDatabaseException;
import hotelsoftware.database.model.Permissions;
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
    private Permissions model;

    public String getPermission()
    {
        return permission;
    }

    public void setPermission(String permission)
    {
        this.permission = permission;
    }
    
    private Permission(String permission, Permissions model)
    {
        this.permission = permission;
        this.model = model;
    }
    
    public Permission(Permissions permissions)
    {
        this.permission = permissions.getName();
        this.model = permissions;        
    }
    
    public Permissions getModel()
    {
        return model;
    }
    

    /**
     * Communicates with the model and creates a linked list of permission
     * @return 
     * a linked list of permissions on domain-level
     */
    public static LinkedList<Permission> getPermissions()
    {
        LinkedList<Permission> retList = new LinkedList<Permission>();
        try
        {
            List<Permissions> permissions = Permissions.getPermissions();
            for (Permissions permission : permissions)
            {
                retList.add(new Permission(permission.getName(),permission));
            }
        } catch (HibernateException e)
        {
            //connection failed ...
        }
        return retList;
    }

    /**
     * Communicates with the model and retrieves a single permission on domain
     * level by name
     * @param permission
     * the name of the permission
     * @return
     * a domain level permission
     */
    public static Permission getPermissionByName(String permission)
    {
        //exception handling?
        Permissions retMethod = Permissions.getPermissionByName(permission);
        if(retMethod != null)
        {
            return new Permission(retMethod.getName(), retMethod);
        }
        return null;
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
            Permissions.savePermission(permission);
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
            Permissions.deletePermission(permission);
        } catch (FaildToDeleteFromDatabaseException ex)
        {
            //deleting failed
        }
    }
    
    
}
