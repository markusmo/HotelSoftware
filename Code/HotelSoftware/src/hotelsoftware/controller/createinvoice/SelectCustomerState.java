/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.controller.createinvoice;

import hotelsoftware.controller.data.parties.CountryData;
import hotelsoftware.controller.data.parties.GuestData;
import hotelsoftware.controller.data.parties.PartyData;
import hotelsoftware.model.domain.parties.*;
import hotelsoftware.model.domain.service.Habitation;
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
    public Collection<CountryData> getAllCountries()
    {
        return HelperFunctions.castCollectionUp(Country.getAllCountries(), CountryData.class, Country.class); 
    }
    
    @Override
    void useGuestAsCustomer(GuestData guest)
    {
        PrivateCustomer c = new PrivateCustomer();
        c.setFname(guest.getFname());
        c.setLname(guest.getLname());
        c.setAddress((Address)guest.getAddressData());
        
        context.setCustomer(c);
    }
    
    @Override
    Collection<PartyData> getWorkingHabitationsGuests()
    {
        Set<PartyData> set = new HashSet<PartyData>();
        
        for (Habitation h : context.getHabitations())
        {
            for (Guest g : h.getGuests())
            {
                set.add(g);
            }
        }
        
        return set;
    }
    
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
    void createCompanyCustomer(String companyName, String street, String city, String zip, String email, String phone, String fax, CountryData country, 
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
