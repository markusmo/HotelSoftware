/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package onlinereservation.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Markus Mohanty <markus.mo at gmx.net>
 */
@ManagedBean(name="company")
@SessionScoped
public class CompanyBean
{

    private AddressBean address;
    private AddressBean invoiceAddress;
    private String name;
    private CompanyTypeBean type;

    public AddressBean getAddress()
    {
        return address;
    }

    public void setAddress(AddressBean address)
    {
        this.address = address;
    }

    public AddressBean getInvoiceAddress()
    {
        return invoiceAddress;
    }

    public void setInvoiceAddress(AddressBean invoiceAddress)
    {
        this.invoiceAddress = invoiceAddress;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
    
    public CompanyBean()
    {
    }
}
