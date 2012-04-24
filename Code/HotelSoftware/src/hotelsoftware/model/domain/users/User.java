package hotelsoftware.model.domain.users;

import hotelsoftware.model.domain.users.data.RoleData;
import hotelsoftware.model.domain.users.data.PermissionData;
import hotelsoftware.model.domain.users.data.UserData;
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
public class User implements UserData
{

    private Integer id;
    private String username;
    private String password;
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

    /**
     * Ueberprueft, ob ein Benutzer ein richtiges Passwort (MD5-Hash) eingegeben
     * hat und ob der Benutzername stimmt und liefert den zugehoerigen User
     * zurueck
     *
     * @param username Benutzername, des Benutzers, der sich einloggen will
     * @param password Password (MD5-Hash davon), des Benutzers, der sich
     * einloggen will
     * @return Einen User, der validiert eingeloggt werden kann
     * @throws LoginFailureException Wirft einen Fehler, wenn der Login
     * fehlschlaegt (Password und/oder Benutzername stimmen nicht ueberein)
     */
    public static User login(String username, String password) throws LoginFailureException
    {
        User user = UserFacade.getInstance().login(username, password);

        if (user == null)
        {
            throw new LoginFailureException();
        }

        return user;
    }

    /**
     * Instanziert einen neuen Benutzer und fuehrt einen MD5-Hash auf sein
     * Passwort aus
     *
     * @param username Benutzername, des neuen Benutzers
     * @param password Passwort, unverschluesselt, des neuen Benutzers
     * @param roles Rolle, des neuen Benutzers
     * @return eine neue Instanz
     */
    public static User create(String username, String password,
            Collection<Role> roles)
    {
        MessageDigest coder;
        try
        {
            coder = MessageDigest.getInstance("MD5");
            return new User(username, new String(coder.digest(
                    password.getBytes())), roles);
        } catch (NoSuchAlgorithmException ex)
        {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
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
    public void changePassword(String oldPassword, String newPassword)
    {
        try
        {
            MessageDigest coder = MessageDigest.getInstance("MD5");
            String hashedPassword = new String(coder.digest(
                    oldPassword.getBytes()));

            if (password.equals(hashedPassword))
            {
                password = new String(coder.digest(newPassword.getBytes()));
            }
        } catch (NoSuchAlgorithmException ex)
        {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Doppelt?
     * @param permission
     * @return 
     */
    public boolean hasPermission(PermissionData permission)
    {
        throw new UnsupportedOperationException("Not supported yet.");
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
