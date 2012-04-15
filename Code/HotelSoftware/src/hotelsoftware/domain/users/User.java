/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.domain.users;

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
    private Collection<Permission> permissionCollection;
    private Users model;

    public User(String username, Collection<Permission> permissionCollection, Users model)
    {
        this.username = username;
        this.permissionCollection = permissionCollection;
        this.model = model;
    }

    public User(String username)
    {
        this.username = username;
    }

    public User(String username, Collection<Permission> permissionCollection)
    {
        this.username = username;
        this.permissionCollection = permissionCollection;
    }

    //FIXME not good
    private void addPermission(Permissions permissions) {
        
    }
    
    public Users getModel()
    {
        return model;
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
                User tempUser = new User(user.getUsername());
                                
//                for (Roles role : user.getRolesCollection()) {
//                    tempUser role.getPermissionsCollection()
//                }
//                retList.add(new User(user.getUsername(), ));
            }
        } catch (HibernateException e)
        {
            //connection failed ...
        }
        return retList;
    }

   
}
