/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftwareonline.beans;

import hotelsoftware.support.LoginFailureException;
import hotelsoftwareonline.controller.CustomerLoginController;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Markus Mohanty <markus.mo at gmx.net>
 */
@ManagedBean(name = "login")
@SessionScoped
public class LoginBean implements Serializable
{
    private CustomerBean customer;
    private String password;
    private String loginButton;
    private String logoutButton;
    private String changeUserData;

    public LoginBean()
    {
        customer = new CustomerBean() {};
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
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
     * Eventlistener zum ändern der Userdaten
     * @param event das Event vom JSF
     */
    public void changeUserDataListener(ActionEvent event)
    {
        this.changeUserData = event.getComponent().getClientId();
    }
    
    /**
     * Eventlistener für Loginbutton
     * @param event das Event vom JSF
     */
    public void loginButtonListener(ActionEvent event)
    {
        this.loginButton = event.getComponent().getClientId();
    }
    
    /**
     * Eventlistener für Logout-Link
     * @param event das Event vom JSF
     */
    public void logoutButtonListener(ActionEvent event)
    {
        this.logoutButton = event.getComponent().getClientId();
    }

    /**
     * Logt einen Kunden ein
     *
     * @return "loginfailed" wenn der Login fehlschlägt, anderfalls "loggedin"
     */
    public String login()
    {
        try
        {
            CustomerLoginController controller = new CustomerLoginController();
            CustomerBean temp = controller.login(getUsername(), this.password);
            
            if(temp instanceof PrivateCustomerBean)
            {
                customer = (PrivateCustomerBean) temp;
            }
            else
            {
                customer = (CompanyBean) temp;
            }
            
            return "loggedin";
        }
        catch (LoginFailureException ex)
        {
            Logger.getLogger(LoginBean.class.getName()).log(Level.INFO, null, ex);
            return "loginfailed";
        }
    }

    /**
     * Überprüft ob ein Kunde eingeloggt ist oder nicht.
     * @return <code>true</code> wenn eingeloggt <code>false</code> wenn nicht eingeloggt
     */
    public boolean loggedin()
    {
       if(customer instanceof PrivateCustomerBean || customer instanceof CompanyBean)
       {
           return true;
       } 
       
       return false;
    }

    /**
     * Logt einen User aus
     *
     * @return "loggedout" bei erfolgreichem Logout
     */
    public String logout()
    {
        customer = new CustomerBean() {};
        return "loggedout";
    }
    
    /**
     * Je nach dem, wenn eine Firma eingeloggt ist, wird "changeCompanyData" ausgegeben,
     * sonst wird "changePrivateCustomerData" ausgegeben
     * @return 
     */
    public String changeUserData()
    {
        if(customer instanceof PrivateCustomerBean)
        {
            return "changePrivateCustomerData";
        }
        return "changeCompanyData";
    }
}
