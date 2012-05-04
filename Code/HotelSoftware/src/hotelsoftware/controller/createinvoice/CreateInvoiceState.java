/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.controller.createinvoice;

import hotelsoftware.controller.data.invoice.InvoiceItemData;
import hotelsoftware.controller.data.parties.CountryData;
import hotelsoftware.controller.data.parties.GuestData;
import hotelsoftware.controller.data.service.HabitationData;
import java.util.Collection;

/**
 *
 * @author Dunst
 */
abstract class CreateInvoiceState
{
    CreateInvoiceController context;
    
    CreateInvoiceState(CreateInvoiceController context)
    {
        this.context = context;
    }

    Collection<HabitationData> search(String firstName, String lastName, String roomNr)
    {
        throw new IllegalStateException();
    }

    void selectHabitations(Collection<HabitationData> habitations)
    {
        throw new IllegalStateException();
    }

    Collection<CountryData> getAllCountries()
    {
        throw new IllegalStateException();
    }

    void cancelItems(InvoiceItemData item, int amount)
    {
        throw new IllegalStateException();
    }

    void selectItems(Collection<InvoiceItemData> items)
    {
        throw new IllegalStateException();
    }

    Collection<HabitationData> getHabitations()
    {
        throw new IllegalStateException();
    }

    void createCompanyCustomer(String companyName, String street, String city, String zip, String email, String phone, String fax, CountryData country, 
            String invoiceStreet, String invoiceCity, String invoiceZip, String invoiceEmail, String invoicePhone, String invoiceFax, CountryData invoiceCountry)
    {
        throw new IllegalStateException();
    }

    void useGuestAsCustomer(GuestData guest)
    {
        throw new IllegalStateException();
    }

    Collection<InvoiceItemData> getSelectedItems()
    {
        throw new IllegalStateException();
    }

    void pay()
    {
        throw new IllegalStateException();
    }
}
