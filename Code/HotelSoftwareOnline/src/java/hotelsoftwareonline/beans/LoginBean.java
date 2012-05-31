/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftwareonline.beans;

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
    
    private String login()
    {
        return "loggedin";
    }
}
