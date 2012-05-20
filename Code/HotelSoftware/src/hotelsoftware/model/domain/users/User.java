package hotelsoftware.model.domain.users;

import hotelsoftware.controller.data.users.PermissionData;
import hotelsoftware.controller.data.users.RoleData;
import hotelsoftware.support.LoginFailureException;
import hotelsoftware.util.HelperFunctions;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;

/**
 * Bildet einen Benutzer im System ab.
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class User implements IUser
{

    private Integer id;
    private String username;
    private String password;
    private Boolean active;

    private Collection<IRole> roles;

    public User()
    {
    }

    private User(String username, String password)
    {
        this(username, password, new LinkedHashSet<IRole>());
    }

    private User(String username, String password, Collection<IRole> roles)
    {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    @Override
    public String getPassword()
    {
        return password;
    }

    @Override
    public void setPassword(String password)
    {
        this.password = password;
    }

    @Override
    public Collection<IRole> getRoles()
    {
        return roles;
    }

    @Override
    public void setRoles(Collection<IRole> roles)
    {
        this.roles = roles;
    }

    @Override
    public String getUsername()
    {
        return username;
    }

    @Override
    public void setUsername(String username)
    {
        this.username = username;
    }

    @Override
    public Integer getId()
    {
        return id;
    }

    @Override
    public void setId(Integer id)
    {
        if (this.id == null)
        {
            this.id = id;
        }
    }
    
    @Override
    public Boolean getActive()
    {
        return active;
    }

    @Override
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
    public static IUser login(String username, String password) throws LoginFailureException
    {
        System.out.println("Username: " + username);
        System.out.println("Passwort: " + password);
        IUser user = UserFacade.getInstance().login(username, password);

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
    public static IUser create(String username, String password,
            Collection<IRole> roles)
    {
        return new User(username, password, roles);

    }

    /**
     * Gibt alle Befugnisse eines Benutzers aus
     *
     * @return Eine Liste aller Befugnisse des Benutzers
     */
    @Override
    public Collection<IPermission> getAllPermissions()
    {
        Collection<IPermission> permissions = new HashSet<IPermission>();

        // adding permissions
        for (IRole role : this.getRoles())
        {
            Collection<IPermission> rolePermissions = role.getPermissions();

            for (IPermission permission : rolePermissions)
            {
                permissions.add(permission);
            }
        }
        return permissions;
    }

    @Override
    public boolean hasPermission(IPermission permission)
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

    @Override
    public Collection<PermissionData> getAllPermissionsData()
    {
        return new HelperFunctions<PermissionData, IPermission>().castCollectionUp(
                getAllPermissions());
    }

    @Override
    public Collection<RoleData> getRolesData()
    {
        return new HelperFunctions<RoleData, IRole>().castCollectionUp(getRoles());
    }
}
