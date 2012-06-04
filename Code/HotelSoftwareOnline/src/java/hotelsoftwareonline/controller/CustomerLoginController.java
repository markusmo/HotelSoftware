/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftwareonline.controller;

import hotelsoftware.controller.login.LoginCustomerController;
import hotelsoftware.model.domain.parties.ICustomer;
import hotelsoftware.support.LoginFailureException;
import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author Markus Mohanty <markus.mo at gmx.net>
 */
public class CustomerLoginController implements Serializable
{
    private static HashMap<String,ICustomer> loggedInUsers = new HashMap<String,ICustomer>();
    
    /**
     * Logt einen Kunden ein
     * @param username der Username des Kunden
     * @param password das Passwort des Kunden
     */
    public void login(String username, String password) throws LoginFailureException
    {
        ICustomer customer = LoginCustomerController.getInstance().login(username, password);
        loggedInUsers.put(username,customer);
    }
    
    /**
     * Logt einen Kunden aus
     * @param username der Username des Kunden
     */
    public void logout(String username)
    {
        loggedInUsers.remove(username);
    }
    
    public ICustomer getCustomerByUsername(String username)
    {
        return loggedInUsers.get(username);
    }
}
