/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftwareonline.controller;

import hotelsoftware.controller.login.LoginCustomerController;
import hotelsoftware.model.domain.parties.IAddress;
import hotelsoftware.model.domain.parties.ICompany;
import hotelsoftware.model.domain.parties.ICustomer;
import hotelsoftware.model.domain.parties.IPrivateCustomer;
import hotelsoftware.support.LoginFailureException;
import hotelsoftwareonline.beans.*;
import java.io.Serializable;

/**
 *
 * @author Markus Mohanty <markus.mo at gmx.net>
 */
public class CustomerLoginController implements Serializable
{
    /**
     * Logt einen Kunden ein und wandelt alle Eigenschaften auf die Bean Objekte
     *
     * @param username der Username des Kunden
     * @param password das Passwort des Kunden
     */
    public CustomerBean login(String username, String password) throws LoginFailureException
    {
        ICustomer customer = LoginCustomerController.getInstance().login(username, password);

        IAddress invoiceAddress = customer.getInvoiceAddress();
        IAddress address = customer.getAddress();

        AddressBean invoiceAddressBean = new AddressBean();
        AddressBean addressBean = new AddressBean();

        invoiceAddressBean.setCity(invoiceAddress.getCity());
        invoiceAddressBean.setEmail(invoiceAddress.getEmail());
        invoiceAddressBean.setFax(invoiceAddress.getFax());
        invoiceAddressBean.setPhone(invoiceAddress.getPhone());
        invoiceAddressBean.setStreet(invoiceAddress.getStreet());
        invoiceAddressBean.setZip(invoiceAddress.getZip());

        addressBean.setCity(address.getCity());
        addressBean.setEmail(address.getEmail());
        addressBean.setFax(address.getFax());
        addressBean.setPhone(address.getPhone());
        addressBean.setStreet(address.getStreet());
        addressBean.setZip(address.getZip());

        if (customer instanceof IPrivateCustomer)
        {
            IPrivateCustomer privateCustomer = (IPrivateCustomer) customer;
            PrivateCustomerBean privateCustomerBean = new PrivateCustomerBean();

            privateCustomerBean.setFname(privateCustomer.getFname());
            privateCustomerBean.setLname(privateCustomer.getLname());
            privateCustomerBean.setGender(privateCustomer.getGender());
            privateCustomerBean.setUsername(privateCustomer.getUsername());
            privateCustomerBean.setAddress(addressBean);
            privateCustomerBean.setInvoiceAddress(invoiceAddressBean);
            
            return privateCustomerBean;
        }

        ICompany company = (ICompany) customer;
        CompanyBean companyBean = new CompanyBean();
        CompanyTypeBean type = new CompanyTypeBean();

        type.setName(company.getCompanyType().getName());

        companyBean.setName(company.getName());
        companyBean.setType(type);
        companyBean.setUsername(company.getUsername());
        companyBean.setInvoiceAddress(invoiceAddressBean);
        companyBean.setAddress(addressBean);

        return companyBean;
    }
}
