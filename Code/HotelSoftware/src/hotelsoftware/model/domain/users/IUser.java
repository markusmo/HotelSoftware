/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.users;

import hotelsoftware.controller.data.users.UserData;
import java.util.Collection;

/**
 *
 * @author Markus Mohanty <markus.mo at gmx.net>
 */
public interface IUser extends UserData
{
    /**
     * Aendern des Passwortes des Benutzers, altes Passwort muss mit Datenbank uebereinstimmen
     *
     * @param oldPassword
     * Altes Benutzerpasswort
     * @param newPassword
     * Das Passwort, in das es geaendert werden soll
     */
    void changePassword(String oldPassword, String newPassword);

    Boolean getActive();

    /**
     * Gibt alle Befugnisse eines Benutzers aus
     *
     * @return Eine Liste aller Befugnisse des Benutzers
     */
    Collection<IPermission> getAllPermissions();

    Integer getId();

    String getPassword();

    Collection<IRole> getRoles();

    void setActive(Boolean active);

    void setId(Integer id);

    void setPassword(String password);

    void setRoles(Collection<IRole> roles);

    void setUsername(String username);
}
