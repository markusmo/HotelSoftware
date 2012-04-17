/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.users;

import hotelsoftware.model.database.FaildToDeleteFromDatabaseException;
import hotelsoftware.model.database.FailedToSaveToDatabaseException;
import hotelsoftware.model.database.users.DBPermission;
import java.util.Collection;
import org.hibernate.HibernateException;

/**
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class Permission
{
    private Integer id;
    private String name;

    public String getName()
    {
        return name;
    }

    void setName(String permission)
    {
        this.name = permission;
    }
    
    public Integer getId()
    {
        return id;
    }

    void setId(Integer id)
    {
        if (id == null)
        {
            this.id = id;
        }
    }
    
    Permission()
    {
    }
    
    private Permission(String name)
    {
        this.name = name;
    }

    public static Permission create(String name)
    {
        return new Permission(name);
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
