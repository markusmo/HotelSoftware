/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.domain.users;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class User
{
    private String username;
    private String password;
    private Collection<Role> roles;

    User(String username, String password)
    {
        this(username, password, new LinkedList<Role>());
    }

    User(String username, String password, Collection<Role> roles)
    {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public String getPassword()
    {
        return password;
    }

    public String getUsername()
    {
        return username;
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
    
    public static User create(String username, String password, Collection<Role> roles)
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
    public Collection<Permission> getAllPermissions()
    {
        Collection<Permission> permissions = new HashSet<Permission>();

        // adding permissions
        for (Role role : this.getAllRoles())
        {
            Collection<Permission> rolePermissions = role.getPermissions();

            for (Permission permission : rolePermissions)
            {
                permissions.add(permission);
            }
        }
        return permissions;
    }

    /**
     * converts permission to roles
     *
     * @param permissions
     * @return Collection of roles
     */
    public Collection<Role> getAllRoles()
    {
        return roles;
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
}
