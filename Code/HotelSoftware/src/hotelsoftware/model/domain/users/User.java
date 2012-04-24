/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.users;

import hotelsoftware.util.HelperFunctions;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class User implements UserData
{
    private Integer id;
    private String username;
    private String password;
    private Set<Role> roles;

    public User()
    {        
    }
    
    private User(String username, String password)
    {
        this(username, password, new LinkedHashSet<Role>());
    }

    private User(String username, String password, Set<Role> roles)
    {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public String getPassword()
    {
        return password;
    }
    
    void setPassword(String password)
    {
        this.password = password;
    }
    
    
    public Set<Role> getRoles()
    {
        return roles;
    }
    
    void setRoles(Set<Role> roles)
    {
        this.roles = roles;
    }
    
    public String getUsername()
    {
        return username;
    }

    void setUsername(String username)
    {
        this.username = username;
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

    public static User login(String username, String password) throws LoginFailureException
    {
        User user = UserFacade.getInstance().login(username, password);

        if (user == null)
        {
            throw new LoginFailureException();
        }

        return user;
    }
    
    public static User create(String username, String password, Set<Role> roles)
    {
        MessageDigest coder;
        try
        {
            coder = MessageDigest.getInstance("MD5");
            return new User(username, new String(coder.digest(password.getBytes())), roles);
        }
        catch (NoSuchAlgorithmException ex)
        {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

    /**
     * converts roles from model to permissions in the domainclass
     *
     * @param permissions
     * @return Collection of permission
     */   
    public Set<Permission> getAllPermissions()
    {
        Set<Permission> permissions = new HashSet<Permission>();

        // adding permissions
        for (Role role : this.getRoles())
        {
            Set<Permission> rolePermissions = role.getPermissions();

            for (Permission permission : rolePermissions)
            {
                permissions.add(permission);
            }
        }
        return permissions;
    }
    
   
    public boolean hasPermission(Permission permission)
    {
        return getAllPermissions().contains(permission);
    }
    
    public void changePassword(String oldPassword, String newPassword)
    {
        try
        {
            MessageDigest coder = MessageDigest.getInstance("MD5");
            String hashedPassword = new String(coder.digest(oldPassword.getBytes()));
            
            if (password.equals(hashedPassword))
            {
                password = new String(coder.digest(newPassword.getBytes()));
            }
        }
        catch (NoSuchAlgorithmException ex)
        {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    
    public boolean hasPermission(PermissionData permission)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Set<PermissionData> getAllPermissionsData()
    {
        return new HelperFunctions<PermissionData, Permission>().castCollectionUp(getAllPermissions());
    }

    public Set<RoleData> getRolesData()
    {
        return new HelperFunctions<RoleData, Role>().castCollectionUp(getRoles());
    }

}
