/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftwareonline.beans;

import hotelsoftware.model.domain.parties.Company;
import hotelsoftware.model.domain.parties.ICompany;
import hotelsoftware.model.domain.parties.ICustomer;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Markus Mohanty <markus.mo at gmx.net>
 */
@ManagedBean(name="company")
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
    
    public CompanyTypeBean getType() {
        return type;
    }

    public void setType(CompanyTypeBean type) {
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
        return "companySaved";
    }
}
