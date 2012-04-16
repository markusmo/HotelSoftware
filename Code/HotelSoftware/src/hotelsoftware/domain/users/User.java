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
    private Integer id;
    private String username;
    private String password;
    private Collection<Role> roles;

    User()
    {        
    }
    
    private User(String username, String password)
    {
        this(username, password, new LinkedList<Role>());
    }

    private User(String username, String password, Collection<Role> roles)
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
    
    public Collection<Role> getRoles()
    {
        return roles;
    }
    
    void setRoles(Collection<Role> roles)
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
        for (Role role : this.getRoles())
        {
            Collection<Permission> rolePermissions = role.getPermissions();

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
}
