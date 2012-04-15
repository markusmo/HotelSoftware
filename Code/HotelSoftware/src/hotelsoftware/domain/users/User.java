/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.domain.users;

import hotelsoftware.database.Exceptions.FailedToSaveToDatabaseException;
import hotelsoftware.database.model.Permissions;
import hotelsoftware.database.model.Roles;
import hotelsoftware.database.model.Users;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import org.hibernate.HibernateException;

/**
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class User
{
    private String username;
    private String password;
    private Collection<Permission> permissionCollection;
    private Users model;

    private User(String username, String password)
    {
        this.username = username;
        this.password = password;
        this.permissionCollection = new LinkedList();
    }

    private User(String username, String password, Users model)
    {
        this.username = username;
        this.password = password;
        this.model = model;
        this.permissionCollection = new LinkedList();
    }
    
    public User(Users users) {
        this(users.getUsername(), users.getPassword(), users);
        setPermissionCollection(mapRolesToPermission(users.getRolesCollection()));
    }

    public Users getModel()
    {
        return model;
    }


    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public Collection<Permission> getPermissionCollection()
    {
        return permissionCollection;
    }

    private void setPermissionCollection(Collection<Permission> permissionCollection)
    {
        this.permissionCollection = permissionCollection;
    }

    public String getUsername()
    {
        return username;
    }
    
   
          
    /**
     * creates a new User
     * @param username
     * @param password
     * @return 
     */
    public static User createNewUser(String username, String password)
    {
        return new User(username, password);
    }
    
    /**
     * adds a permission to the user
     * @param permission 
     */
    public void addPermission(Permission permission) {
        this.getPermissionCollection().add(permission);
    }
    
    /**
     * removes a Permission from the user
     * @param permission 
     */
    public void removePermission(Permission permission) {
        this.getPermissionCollection().remove(permission);
    }
    
    

     /** 
     * Calls the model and creates a new user in the database
     * @param username 
     * the name of the new user
     * @param password
     * the password of the new user
     */
    public static void saveUser(User user)
    {
        try
        {
            Collection<Roles> roles = mapPermissionToRoles(user.getPermissionCollection());
            //FIXME Roles are added
            Users usersToSafe = new Users(user.getUsername(), user.getPassword(), roles);
            Users.saveUsers(usersToSafe);
        } catch (HibernateException ex)
        {
            //connection faild
        } catch (FailedToSaveToDatabaseException ex)
        {
            //saving failed
        }
    }
    
        
    /**
     * Communicates with the model and creates a linked list of user
     * @return 
     * a linked list of users on domain-level
     */
    public static LinkedList<User> getUsers()
    {
        LinkedList<User> retList = new LinkedList<User>();
        try
        {
            List<Users> users = Users.getUsers();
            for (Users user : users)
            {
                User tempUser = new User(user.getUsername(), user.getPassword(), user);
                
                tempUser.setPermissionCollection(mapRolesToPermission(user.getRolesCollection()));
                retList.add(tempUser);
            }
        } catch (HibernateException e)
        {
            //connection failed ...
        }
        return retList;
    }
    
    /**
     * converts roles from model to permissions in the domainclass
     * @param permissions
     * @return Collection of permission
     */
    private static Collection<Permission> mapRolesToPermission(Collection<Roles> roles) {
        Collection<Permission> permissionCollection = new LinkedList<Permission>();
                        
        // adding permissions
        for (Roles role : roles) {
            Collection<Permissions> permissions = role.getPermissionsCollection();

            for (Permissions permission : permissions) {
                Permission p = new Permission(permission);
                if (!permissionCollection.contains(p)) {
                    permissionCollection.add(p);
                }                        
            }
        }
        return permissionCollection;
    } 
    
    /**
     * converts permission to roles
     * @param permissions
     * @return Collection of roles
     */
    private static Collection<Roles> mapPermissionToRoles (Collection<Permission> permissions) {
        Collection<Roles> rolesCollection = new LinkedList<Roles>();
                        
        // adding roles
        for (Permission permission : permissions) {            
            for (Roles role : permission.getModel().getRolesCollection()) {
                rolesCollection.add(role);
            }
        }
        return rolesCollection;
    } 
    
    

    /**
     * communicates with the model and retrieves a single user
     * @param username
     * @return user
     */
    public static User getUserByName(String username) {
        Users users = Users.getUserByUsername(username);        
        return new User(users.getUsername(), users.getPassword(), users);
    }
    
   
}
