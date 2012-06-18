/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftwareonline.beans;

import hotelsoftware.model.domain.parties.ICustomer;
import java.io.Serializable;

/**
 *
 * @author mohi
 */
public abstract class CustomerBean implements Serializable
{
    private Integer id;
    private String username;
    private String password;
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

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public abstract ICustomer getCustomer();
}
