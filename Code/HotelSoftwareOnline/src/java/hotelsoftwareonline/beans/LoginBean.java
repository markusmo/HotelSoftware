/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftwareonline.beans;

import hotelsoftwareonline.controller.CustomerLoginController;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Markus Mohanty <markus.mo at gmx.net>
 */
@ManagedBean(name = "login")
@SessionScoped
public class LoginBean
{
    private CustomerBean customer;
    
    public LoginBean()
    {
    }

    public void setUsername(String username)
    {
        customer.setUsername(username);
    }

    public String getUsername()
    {
        return customer.getUsername();
    }

    /**
     * Logt einen User ein
     * @return "loginfailed" wenn der Login fehlschlägt, anderfalls "loggedin"
     */
    public String login(String password)
    {
        CustomerLoginController controller = new CustomerLoginController();
        controller.login(getUsername(), password);
        return "loggedin";
    }
    
    /**
     * Logt einen User aus
     * @return "notloggedin" bei erfolgreichem Logout
     */
    public String logout()
    {
        CustomerLoginController controller = new CustomerLoginController();
        controller.logout(getUsername());
        return "notloggedin";
    }
}
