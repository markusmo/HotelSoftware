/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftwareonline.controller;

import hotelsoftware.model.domain.parties.Customer;
import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author Markus Mohanty <markus.mo at gmx.net>
 */
public class CustomerLoginController implements Serializable
{
    private static HashMap<String,Customer> loggedInUsers = new HashMap<String,Customer>();
    
    /**
     * Logt einen Kunden ein
     * @param username der Username des Kunden
     * @param password das Passwort des Kunden
     */
    public void login(String username, String password)
    {
        //TODO implement, still missing function in HotelSoftware, return the customer...
        loggedInUsers.put(username,null);
    }
    
    /**
     * Logt einen Kunden aus
     * @param username der Username des Kunden
     */
    public void logout(String username)
    {
        loggedInUsers.remove(username);
    }
    
    public Customer getCustomerByUsername(String username)
    {
        return loggedInUsers.get(username);
    }
}
