package hotelsoftware.model.domain.users;

import hotelsoftware.support.LoginFailureException;
import hotelsoftware.controller.data.users.RoleData;
import hotelsoftware.controller.data.users.PermissionData;
import hotelsoftware.controller.data.users.UserData;
import hotelsoftware.util.HelperFunctions;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Bildet einen Benutzer im System ab.
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class User implements UserData, IUser
{

    private Integer id;
    private String username;
    private String password;
    private Boolean active;

    private Collection<Role> roles;

    public User()
    {
    }

    private User(String username, String password)
    {
        this(username, password, new LinkedHashSet<Role>());
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

    public void setPassword(String password)
    {
        this.password = password;
    }

    public Collection<Role> getRoles()
    {
        return roles;
    }

    public void setRoles(Collection<Role> roles)
    {
        this.roles = roles;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        if (this.id == null)
        {
            this.id = id;
        }
    }
    
    public Boolean getActive()
    {
        return active;
    }

    public void setActive(Boolean active)
    {
        this.active = active;
    }

    /**
     * Ueberprueft, ob ein Benutzer ein richtiges Passwort eingegeben
     * hat und ob der Benutzername stimmt und liefert den zugehoerigen User
     * zurueck
     *
     * @param username Benutzername, des Benutzers, der sich einloggen will
     * @param password Passworts, des Benutzers, der sich
     * einloggen will
     * @return Einen User, der validiert eingeloggt werden kann
     * @throws LoginFailureException Wirft einen Fehler, wenn der Login
     * fehlschlaegt (Password und/oder Benutzername stimmen nicht ueberein)
     */
    public static User login(String username, String password) throws LoginFailureException
    {
        System.out.println("Username: " + username);
        System.out.println("Passwort: " + password);
        User user = UserFacade.getInstance().login(username, password);

        if (user == null)
        {
            throw new LoginFailureException();
        }

        return user;
    }

    /**
     * Instanziert einen neuen Benutzer
     *
     * @param username Benutzername, des neuen Benutzers
     * @param password Passwort, unverschluesselt, des neuen Benutzers
     * @param roles Rolle, des neuen Benutzers
     * @return eine neue Instanz
     */
    public static User create(String username, String password,
            Collection<Role> roles)
    {
        return new User(username, password, roles);

    }

    /**
     * Gibt alle Befugnisse eines Benutzers aus
     *
     * @return Eine Liste aller Befugnisse des Benutzers
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

    @Override
    public boolean hasPermission(Permission permission)
    {
        return getAllPermissions().contains(permission);
    }

    /**
     * Aendern des Passwortes des Benutzers, altes Passwort muss mit Datenbank uebereinstimmen
     * @param oldPassword
     * Altes Benutzerpasswort
     * @param newPassword 
     * Das Passwort, in das es geaendert werden soll
     */
    @Override
    public void changePassword(String oldPassword, String newPassword)
    {

        if (password.equals(oldPassword))
        {
            password = newPassword;
        }
    }

    public Collection<PermissionData> getAllPermissionsData()
    {
        return new HelperFunctions<PermissionData, Permission>().castCollectionUp(
                getAllPermissions());
    }

    public Collection<RoleData> getRolesData()
    {
        return new HelperFunctions<RoleData, Role>().castCollectionUp(getRoles());
    }
}
