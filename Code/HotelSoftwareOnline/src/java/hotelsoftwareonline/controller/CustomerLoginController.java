/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftwareonline.controller;

import hotelsoftware.model.domain.parties.Customer;
import java.util.HashSet;

/**
 *
 * @author Markus Mohanty <markus.mo at gmx.net>
 */
public class CustomerLoginController
{
    private static HashSet<String> loggedInUsers = new HashSet<String>();
    
    /**
     * Logt einen Kunden ein
     * @param username der Username des Kunden
     * @param password das Passwort des Kunden
     */
    public void login(String username, String password)
    {
        //TODO implement, still missing function in HotelSoftware
        loggedInUsers.add(username);
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
        return null;
    }
}
