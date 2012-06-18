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
@ManagedBean(name = "company")
@SessionScoped
public class CompanyBean extends CustomerBean implements Serializable
{
    private String name;
    private CompanyTypeBean type;

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

    public CompanyTypeBean getType()
    {
        return type;
    }

    public void setType(CompanyTypeBean type)
    {
        this.type = type;
    }

    @Override
    public AddressBean getAddress()
    {
        return super.getAddress();
    }

    @Override
    public void setAddress(AddressBean address)
    {
        super.setAddress(address);
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

    public String save()
    {
        Company company = new Company();

        company.setCompanyType(this.getType().getType());
        company.setName(this.getName());
        company.setIdParties(this.getId());

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

        company.setAddress(address);
        company.setInvoiceAddress(invoiceAddress);

        PartyManager.getInstance().saveParty(company);

        return "companySaved";
    }

    @Override
    public ICustomer getCustomer()
    {
        ICompany company = new Company();
        company.setAddress(getAddress().getAddress());
        company.setCompanyType(type.getType());
        company.setIdParties(getId());
        company.setInvoiceAddress(getInvoiceAddress().getAddress());
        company.setName(name);
        company.setPassword(getPassword());
        company.setUsername(getUsername());
        return company;
    }
}
