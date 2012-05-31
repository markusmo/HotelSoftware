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
    private String username;
    private String password;
    
    public LoginBean()
    {
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }
    
    /**
     * Logt einen User ein
     * @return "loginfailed" wenn der Login fehlschlägt, anderfalls "loggedin"
     */
    public String login()
    {
        CustomerLoginController controller = new CustomerLoginController();
        controller.login(username, password);
        return "loggedin";
    }
    
    /**
     * Logt einen User aus
     * @return "notloggedin" bei erfolgreichem Logout
     */
    public String logout()
    {
        CustomerLoginController controller = new CustomerLoginController();
        controller.logout(username);
        return "notloggedin";
    }
}
