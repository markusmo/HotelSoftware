/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.controller.createinvoice;

import hotelsoftware.controller.data.invoice.InvoiceItemData;
import hotelsoftware.controller.data.parties.CountryData;
import hotelsoftware.controller.data.parties.GuestData;
import hotelsoftware.controller.data.service.HabitationData;
import hotelsoftware.model.domain.invoice.InvoiceItem;
import hotelsoftware.model.domain.service.Habitation;
import java.util.Collection;
import java.util.LinkedList;

/**
 *
 * @author Dunst
 */
public class CreateInvoiceController
{
    private CreateInvoiceState state;
    
    private Collection<Habitation> habitations;
    private Collection<InvoiceItem> selectedItems;
    
    private CreateInvoiceController()
    {
    }
    
    public static CreateInvoiceController getInstance()
    {
        return CreateInvoiceControllerHolder.INSTANCE;
    }

    public void abort()
    {
        //TODO Methode implementieren
        // wenn wir zum beispiel im state Zwischenrechnung sind, weil noch offene items der habitation da sind, der User aber abschließen möchte
        // das kann passieren, wenn eine Person aus-checkt, aber sein zimmerkollege noch bleibt...
        throw new UnsupportedOperationException("Not yet implemented");
    }
    
    private static class CreateInvoiceControllerHolder
    {
        private static final CreateInvoiceController INSTANCE = new CreateInvoiceController();
    }
    
    public Collection<HabitationData> search(String firstName, String lastName, String roomNr)
    {
        return state.search(firstName, lastName, roomNr);
    }
    
    public void selectHabitations(Collection<HabitationData> habitations)
    {
        state.selectHabitations(habitations);
    }
    
    public Collection<HabitationData> getSelectedHabitations()
    {
        return state.getHabitations();
    }
    
    public void selectItems(Collection<InvoiceItemData> items)
    {
        state.selectItems(items);
    }
    
    public void cancelItems(InvoiceItemData item, int amount)
    {
        state.cancelItems(item, amount);
    }
    
    public Collection<CountryData> getAllCountries()
    {
        return state.getAllCountries();
    }
    
    public void createCompanyCustomer(String companyName, String street, String city, String zip, String email, String phone, String fax, CountryData country)
    {
        createCompanyCustomer(companyName, street, city, zip, email, phone, fax, country, street, city, zip, email, phone, fax, country);
    }
    
    public void createCompanyCustomer(String companyName, String street, String city, String zip, String email, String phone, String fax, CountryData country,
            String invoiceStreet, String invoiceCity, String invoiceZip, String invoiceEmail, String invoicePhone, String invoiceFax, CountryData invoiceCountry)
    {
        state.createCompanyCustomer(companyName, street, city, zip, email, phone, fax, country, invoiceStreet, 
                invoiceCity, invoiceZip, invoiceEmail, invoicePhone, invoiceFax, invoiceCountry);
    }
    
    public void createPrivateCustomer(String firstName, String lastName, String street, String city, String zip, String email, String phone, String fax, CountryData country)
    {
        createPrivateCustomer(firstName, lastName, street, city, zip, email, phone, fax, country, street, city, zip, email, phone, fax, country);
    }
    
    public void createPrivateCustomer(String firstName, String lastName, String street, String city, String zip, String email, String phone, String fax, CountryData country,
            String invoiceStreet, String invoiceCity, String invoiceZip, String invoiceEmail, String invoicePhone, String invoiceFax, CountryData invoiceCountry)
    {
        state.createPrivateCustomer(firstName, lastName, street, city, zip, email, phone, fax, country, invoiceStreet, 
                invoiceCity, invoiceZip, invoiceEmail, invoicePhone, invoiceFax, invoiceCountry);
    }
    
    public Collection<GuestData> getWorkingHabitationsGuests()
    {
        return state.getWorkingHabitationsGuests();
    }
    
    public void useGuestAsCustomer(GuestData guest)
    {
        state.useGuestAsCustomer(guest);
    }
    
    public Collection<InvoiceItemData> getChosenItems()
    {
        return state.getSelectedItems();
    }
    
    public void pay()
    {
        state.pay();
    }
    
    public void next()
    {
        state.next();
    }
    
    public void back()
    {
        state.back();
    }
    
    Collection<InvoiceItem> getOpenItems()
    {
        Collection<InvoiceItem> openItems = new LinkedList<InvoiceItem>();
        
        for (Habitation h : habitations)
        {
            for (InvoiceItem i : h.getInvoiceItems())
            {
                if (i.getInvoice() != null && !i.getInvoice().isFulfilled())
                {
                    openItems.add(i);
                }
            }
        }
        
        return openItems;
    }
    
    /******************************************************************/
    
    Collection<Habitation> getHabitations()
    {
        return habitations;
    }

    void setHabitations(Collection<Habitation> habitations)
    {
        this.habitations = habitations;
    }

    Collection<InvoiceItem> getSelectedItems()
    {
        return selectedItems;
    }

    void setSelectedItems(Collection<InvoiceItem> selectedItems)
    {
        this.selectedItems = selectedItems;
    }

    CreateInvoiceState getState()
    {
        return state;
    }

    void setState(CreateInvoiceState state)
    {
        this.state = state;
    }
}