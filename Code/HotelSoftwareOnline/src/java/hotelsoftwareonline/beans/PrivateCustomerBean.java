/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftwareonline.beans;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Markus Mohanty <markus.mo at gmx.net>
 */
@ManagedBean(name = "privatecustomer")
@SessionScoped
public class PrivateCustomerBean extends CustomerBean implements Serializable
{
    private AddressBean address;
    private AddressBean invoiceAddress;
    private String lname;
    private String fname;
    private Character gender;

    public AddressBean getAddress()
    {
        return address;
    }

    @Override
    public String getUsername()
    {
        return super.getUsername();
    }

    @Override
    public void setUsername(String username)
    {
        super.setUsername(username);
    }
    
    public void setAddress(AddressBean address)
    {
        this.address = address;
    }

    public String getFname()
    {
        return fname;
    }

    public void setFname(String fname)
    {
        this.fname = fname;
    }

    public Character getGender()
    {
        return gender;
    }

    public void setGender(Character gender)
    {
        this.gender = gender;
    }

    public AddressBean getInvoiceAddress()
    {
        return invoiceAddress;
    }

    public void setInvoiceAddress(AddressBean invoiceAddress)
    {
        this.invoiceAddress = invoiceAddress;
    }

    public String getLname()
    {
        return lname;
    }

    public void setLname(String lname)
    {
        this.lname = lname;
    }
    
    public PrivateCustomerBean()
    {
    }
    
    public String save()
    {
        return "customerSaved";
    }
}
