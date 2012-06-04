/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.controller.login;

import hotelsoftware.model.domain.parties.Customer;
import hotelsoftware.model.domain.parties.ICustomer;
import hotelsoftware.support.LoginFailureException;

/**
 *
 * @author Markus Mohanty <markus.mo at gmx.net>
 */
public class LoginCustomerController
{
    private LoginCustomerController()
    {
    }
    
    public static LoginCustomerController getInstance()
    {
        return LoginCustomerControllerHolder.INSTANCE;
    }
    
    private static class LoginCustomerControllerHolder
    {
        private static final LoginCustomerController INSTANCE = new LoginCustomerController();
    }
    
    /**
     * Logt einen Kunden in das Onlinereservierunssystem ein
     * @param username Username des Kunden
     * @param password Passwort des Kunden
     * @throws LoginFailureException 
     */
    public ICustomer login(String username, String password) throws LoginFailureException
    {
        return Customer.login(username, password);
    }
}
