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
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

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
        customer = new CustomerBean()
        {
        };
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

    public CustomerBean getCustomer()
    {
        return customer;
    }

    public void setCustomer(CustomerBean customer)
    {
        this.customer = customer;
    }

    /**
     * Eventlistener zum ändern der Userdaten
     *
     * @param event das Event vom JSF
     */
    public void changeUserDataListener(ActionEvent event)
    {
        this.changeUserData = event.getComponent().getClientId();
    }

    /**
     * Eventlistener für Loginbutton
     *
     * @param event das Event vom JSF
     */
    public void loginButtonListener(ActionEvent event)
    {
        this.loginButton = event.getComponent().getClientId();
    }

    /**
     * Eventlistener für Logout-Link
     *
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

            /*
             * Bean in die Session stecken :-D
             * http://www.javabeat.net/qna/115-how-to-get-or-set-the-jsf-managed-bean-in-the/
             */
            if (temp instanceof PrivateCustomerBean)
            {
                PrivateCustomerBean pcb = (PrivateCustomerBean) temp;
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("privatecustomer", pcb);
            } else
            {
                CompanyBean cb = (CompanyBean) temp;
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("company", cb);
            }
            this.customer = temp;
            return "loggedin";
        } catch (LoginFailureException ex)
        {
            Logger.getLogger(LoginBean.class.getName()).log(Level.INFO, null, ex);
            return "loginfailed";
        }
    }

    /**
     * Überprüft ob ein Kunde eingeloggt ist oder nicht.
     *
     * @return
     * <code>true</code> wenn eingeloggt
     * <code>false</code> wenn nicht eingeloggt
     */
    public boolean isLoggedin()
    {
        if (customer instanceof PrivateCustomerBean || customer instanceof CompanyBean)
        {
            return true;
        }

        return false;
    }

    /**
     * Überprüft ob es ein PrivateCustomer ist oder nicht...
     *
     * @return True wenn PrivateCustomer
     */
    public boolean isPrivateCustomer()
    {
        return customer instanceof PrivateCustomerBean;
    }

    /**
     * Logt einen User aus
     *
     * @return "loggedout" bei erfolgreichem Logout
     */
    public String logout()
    {
        customer = new CustomerBean()
        {
        };
        HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        httpSession.invalidate();
        return "loggedout";
    }

    /**
     * Je nach dem, wenn eine Firma eingeloggt ist, wird "changeCompanyData"
     * ausgegeben, sonst wird "changePrivateCustomerData" ausgegeben
     *
     * @return
     */
    public String changeUserData()
    {
        if (customer instanceof PrivateCustomerBean)
        {
            return "changePrivateCustomerData";
        }
        return "changeCompanyData";
    }
}
