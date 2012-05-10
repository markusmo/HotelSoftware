/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.users;

/**
 *
 * @author Kno
 */
public interface IUser {

    /**
     * Aendern des Passwortes des Benutzers, altes Passwort muss mit Datenbank uebereinstimmen
     * @param oldPassword
     * Altes Benutzerpasswort
     * @param newPassword
     * Das Passwort, in das es geaendert werden soll
     */
    void changePassword(String oldPassword, String newPassword);

    boolean hasPermission(Permission permission);
    
}
