/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.controller.createinvoice;

import hotelsoftware.controller.data.parties.CompanyTypeData;
import hotelsoftware.controller.data.parties.CountryData;
import hotelsoftware.controller.data.parties.GuestData;
import hotelsoftware.controller.data.parties.PartyData;
import hotelsoftware.model.domain.parties.*;
import hotelsoftware.model.domain.service.Habitation;
import hotelsoftware.model.domain.service.IHabitation;
import hotelsoftware.util.HelperFunctions;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Dunst
 */
public class SelectCustomerState extends CreateInvoiceState
{
    public SelectCustomerState(CreateInvoiceController context)
    {
        super(context);
    }
    
    @Override
    public Collection<PartyData> searchParties(String text)
    {
        return HelperFunctions.castCollectionUp(Party.searchParties(text), PartyData.class, IParty.class);
    }
    
    @Override
    public Collection<CountryData> getAllCountries()
    {
        return HelperFunctions.castCollectionUp(Country.getAllCountries(), CountryData.class, ICountry.class); 
    }
    
    @Override
    void useGuestAsCustomer(GuestData guest)
    {
        PrivateCustomer c = new PrivateCustomer();
        c.setFname(guest.getFname());
        c.setLname(guest.getLname());
        c.setAddress((Address)guest.getAddressData());
        c.setInvoiceAddress((Address)guest.getAddressData());
        
        context.setCustomer(c);
    }
    
    @Override
    void useCustomer(Customer customer)
    {
        context.setCustomer(customer);
    }
    
    @Override
    Collection<PartyData> getWorkingHabitationsGuests()
    {
        Set<PartyData> set = new HashSet<PartyData>();
        
        for (IHabitation h : context.getHabitations())
        {
            for (IGuest g : h.getGuests())
            {
                set.add(g);
            }
        }
        
        return set;
    }
    
    @Override
    void createPrivateCustomer(String firstName, String lastName, String street, String city, String zip, String email, String phone, String fax, CountryData country, 
            String invoiceStreet, String invoiceCity, String invoiceZip, String invoiceEmail, String invoicePhone, String invoiceFax, CountryData invoiceCountry)
    {
        Address postAdr = new Address();
        postAdr.setStreet(street);
        postAdr.setCity(city);
        postAdr.setZip(zip);
        postAdr.setPhone(phone);
        postAdr.setFax(fax);
        postAdr.setEmail(email);
        postAdr.setIdCountry((Country)country);
        
        Address invoiceAdr = new Address();
        invoiceAdr.setStreet(street);
        invoiceAdr.setCity(invoiceCity);
        invoiceAdr.setZip(invoiceZip);
        invoiceAdr.setPhone(invoicePhone);
        invoiceAdr.setFax(invoiceFax);
        invoiceAdr.setEmail(invoiceEmail);
        invoiceAdr.setIdCountry((Country)invoiceCountry);
        
        PrivateCustomer c = new PrivateCustomer();
        c.setFname(firstName);
        c.setLname(lastName);
        c.setAddress(postAdr);
        c.setInvoiceAddress(invoiceAdr);
        
        context.setCustomer(c);
    }
    
    @Override
    void createCompanyCustomer(String companyName, CompanyTypeData type, String street, String city, String zip, String email, String phone, String fax, CountryData country, 
            String invoiceStreet, String invoiceCity, String invoiceZip, String invoiceEmail, String invoicePhone, String invoiceFax, CountryData invoiceCountry)
    {
        Address postAdr = new Address();
        postAdr.setStreet(street);
        postAdr.setCity(city);
        postAdr.setZip(zip);
        postAdr.setPhone(phone);
        postAdr.setFax(fax);
        postAdr.setEmail(email);
        postAdr.setIdCountry((Country)country);
        
        Address invoiceAdr = new Address();
        invoiceAdr.setStreet(street);
        invoiceAdr.setCity(invoiceCity);
        invoiceAdr.setZip(invoiceZip);
        invoiceAdr.setPhone(invoicePhone);
        invoiceAdr.setFax(invoiceFax);
        invoiceAdr.setEmail(invoiceEmail);
        invoiceAdr.setIdCountry((Country)invoiceCountry);
        
        Company c = new Company();
        c.setCompanyType((ICompanyType)type);
        c.setName(companyName);
        c.setAddress(postAdr);
        c.setInvoiceAddress(invoiceAdr);
        
        context.setCustomer(c);
    }
    
    @Override
    void next()
    {
        context.setState(new PaymentState(context));
    }
    
    @Override
    void back()
    {
        context.setState(new InterimBillState(context));
    }
}
