/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.login;

import hotelsoftware.model.domain.users.LoginFailureException;
import hotelsoftware.model.domain.users.User;

/**
 *
 * @author mohi
 */
public class LoginController
{
    
    private User user;
    
    private LoginController()
    {
    }
    
    public static LoginController getInstance()
    {
        return LoginControllerHolder.INSTANCE;
    }
    
    private static class LoginControllerHolder
    {
        private static final LoginController INSTANCE = new LoginController();
    }
    
    public User getCurrentUser()
    {
        return user;
    }
    
    public void login(String username, String password) throws LoginFailureException
    {
        this.user = User.login(username, password);
    }
}
