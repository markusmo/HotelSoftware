/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftwareonline.beans;

import hotelsoftware.model.database.manager.PartyManager;
import hotelsoftware.model.domain.parties.*;
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

   
    @Override
    public ICustomer getCustomer()
    {
        IPrivateCustomer customer = new PrivateCustomer();
        customer.setFname(fname);
        customer.setGender(gender);
        customer.setLname(lname);
        customer.setAddress(getAddress().getAddress());
        customer.setIdParties(getId());
        customer.setInvoiceAddress(getInvoiceAddress().getAddress());
        customer.setPassword(getPassword());
        customer.setUsername(getUsername());
        return customer;
    }

    public String save()
    {
        PrivateCustomer customer = new PrivateCustomer();

        customer.setIdParties(this.getId());
        customer.setFname(this.getFname());
        customer.setLname(this.getLname());
        customer.setUsername(this.getUsername());
        customer.setPassword(this.getPassword());

        IAddress address = new Address();
        IAddress invoiceAddress = new Address();
        AddressBean addressBean = this.getAddress();
        AddressBean invoiceAddressBean = this.getInvoiceAddress();

        address.setId(addressBean.getId());
        address.setCity(addressBean.getCity());
        address.setEmail(addressBean.getEmail());
        address.setFax(addressBean.getFax());
        address.setIdCountry(addressBean.getDomainCountry());
        address.setPhone(addressBean.getPhone());
        address.setStreet(addressBean.getStreet());
        address.setZip(addressBean.getZip());

        invoiceAddress.setId(invoiceAddressBean.getId());
        invoiceAddress.setCity(invoiceAddressBean.getCity());
        invoiceAddress.setEmail(invoiceAddressBean.getEmail());
        invoiceAddress.setFax(invoiceAddressBean.getFax());
        invoiceAddress.setIdCountry(invoiceAddressBean.getDomainCountry());
        invoiceAddress.setPhone(invoiceAddressBean.getPhone());
        invoiceAddress.setStreet(invoiceAddressBean.getStreet());
        invoiceAddress.setZip(invoiceAddressBean.getZip());

        PartyManager.getInstance().saveParty(customer);

        return "customerSaved";
    }
}
