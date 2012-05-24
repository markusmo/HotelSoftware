package hotelsoftware.controller.login;

import hotelsoftware.model.domain.users.IUser;
import hotelsoftware.model.domain.users.User;

/**
 * Dieser Controller behandelt alle User-Relevanten operationen.
 * @author mohi
 */
public class LoginController
{
    
    private IUser user;
    
    private LoginController()
    {
    }
    /**
     * gibt eine Instanz der Klasse zurück
     * @return
     * eine Instanz der Klasse
     */
    public static LoginController getInstance()
    {
        return LoginControllerHolder.INSTANCE;
    }
    
    private static class LoginControllerHolder
    {
        private static final LoginController INSTANCE = new LoginController();
    }
    
    /**
     * Liefert den eingeloggten User zurueck
     * @return 
     * den Aktuellen User
     */
    public IUser getCurrentUser()
    {
        return user;
    }
    
    /**
     * Loggt einen User ein
     * @param username
     * der Username, des Users der sich einloggen will
     * @param password
     * das Passwort, des Uses der sich einloggen will
     * @throws LoginFailureException
     * Wenn der user nicht vorhanden ist oder das Passwort nicht validiert werden konnte
     * wird dieser Fehler geworfen.
     */
    public void login(String username, String password) throws LoginFailureException
    {
        this.user = User.login(username, password);
    }
}
