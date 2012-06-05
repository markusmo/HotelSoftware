/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftwareonline.beans;

import java.io.Serializable;

/**
 *
 * @author mohi
 */
public abstract class CustomerBean implements Serializable
{
    private String username;
    private AddressBean address;
    private AddressBean invoiceAddress;
    
    public String getUsername()
    {
        return username;
    }
    
    public void setUsername(String username)
    {
        this.username = username;
    }

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
}
