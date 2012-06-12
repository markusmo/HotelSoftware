/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftwareonline.beans;

import hotelsoftware.model.domain.parties.Customer;
import hotelsoftware.model.domain.parties.ICustomer;
import hotelsoftware.model.domain.parties.IPrivateCustomer;
import hotelsoftware.model.domain.parties.PrivateCustomer;
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
    private String lname;
    private String fname;
    private Character gender;

    @Override
    public AddressBean getAddress()
    {
        return super.getAddress();
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
    
    @Override
    public void setAddress(AddressBean address)
    {
        super.setAddress(address);
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

    @Override
    public AddressBean getInvoiceAddress()
    {
        return super.getInvoiceAddress();
    }

    @Override
    public void setInvoiceAddress(AddressBean invoiceAddress)
    {
        super.setInvoiceAddress(invoiceAddress);
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
