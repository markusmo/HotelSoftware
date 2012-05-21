/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.controller.createinvoice;

import hotelsoftware.controller.UseCaseController;
import hotelsoftware.controller.data.invoice.InvoiceItemData;
import hotelsoftware.controller.data.parties.CompanyTypeData;
import hotelsoftware.controller.data.parties.CountryData;
import hotelsoftware.controller.data.parties.CustomerData;
import hotelsoftware.controller.data.parties.GuestData;
import hotelsoftware.controller.data.parties.PartyData;
import hotelsoftware.controller.data.service.HabitationData;
import hotelsoftware.gui.GuiController;
import hotelsoftware.model.domain.invoice.IInvoiceItem;
import hotelsoftware.model.domain.invoice.InvoiceItem;
import hotelsoftware.model.domain.parties.Customer;
import hotelsoftware.model.domain.parties.Guest;
import hotelsoftware.model.domain.service.IHabitation;
import hotelsoftware.util.HelperFunctions;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;

/**
 *
 * @author Dunst
 */
public class CreateInvoiceController implements UseCaseController
{
    private CreateInvoiceState state;
    private Collection<IHabitation> habitations;
    private Collection<IInvoiceItem> selectedItems;
    private Collection<InvoiceItem> splittedItems;

    private Customer customer;

    private CreateInvoiceController()
    {
        state = new SearchState(this);
        GuiController.getInstance().addUseCaseController(this);
    }

    public static CreateInvoiceController getInstance()
    {
        return CreateInvoiceControllerHolder.INSTANCE;
    }
    
    /**
     * Schnittstelle für die Einbindung in einen Check-Out Vorgang
     * @param habitation Der bereits gesuchte Aufenthalt der ausgecheckt werden soll
     * @return Der Controller der die Suche bereits übersprungen hat
     */
    public static CreateInvoiceController getInstance(IHabitation habitation)
    {
        CreateInvoiceControllerHolder.INSTANCE.setState(new InterimBillState(CreateInvoiceControllerHolder.INSTANCE));
        Collection<IHabitation> habitations = new LinkedList<IHabitation>();
        habitations.add(habitation);
        CreateInvoiceControllerHolder.INSTANCE.setHabitations(habitations);
        
        return CreateInvoiceControllerHolder.INSTANCE;
    }

    @Override
    public boolean isInSwitchingState()
    {
        return state instanceof SearchState;
    }

    /**
     * Bricht den aktuellen Vorgang ab
     * Kann auch daran liegen, dass nicht alle Posten beglichen werden sollen
     */
    @Override
    public void clear()
    {
        state = new SearchState(this);
        habitations = null;
        selectedItems = null;
        customer = null;
    }

    void addInvoiceItemToHabitation(IHabitation habitation, IInvoiceItem item)
    {
        for (IHabitation h : habitations)
        {
            if (h.equals(habitation))
            {
                h.addInvoiceItems(item);
            }
        }
    }

    private static class CreateInvoiceControllerHolder
    {
        private static final CreateInvoiceController INSTANCE = new CreateInvoiceController();
    }

    /**
     * Sucht nach Aufenthalten anhand der angegebenen Suchparameter, leere Parameter werden ignoriert
     *
     * @param firstName Der Vorname eines Gastes der zum Aufenthalt gehört
     * @param lastName Der Nachname eines Gastes der zum Aufenthalt gehört
     * @param roomNr Die Raum Nummer des zum Aufenthalt gehörigen Raums
     * @return Eine Collection aus den dazu gefundenen Aufenthalten
     */
    public Collection<HabitationData> searchHabitations(String firstName, String lastName, String roomNr)
    {
        return state.search(firstName, lastName, roomNr);
    }

    /**
     * Wählt die Aufenthalte aus für die Rechnung(en) erstellt werden sollen
     *
     * @param habitations Die ausgewählten Aufenthalte
     */
    public void selectHabitations(Collection<HabitationData> habitations)
    {
        state.selectHabitations(habitations);
    }

    public Collection<HabitationData> getSelectedHabitations()
    {
        return state.getHabitations();
    }

    /**
     * Wählt die gewünschten Rechnungsposten für eine zu erstellende Rechnung aus
     *
     * @param items Die für die Rechnung relevanten Posten
     */
    public void selectItems(Map<InvoiceItemData, Integer> items)
    {
        state.selectItems(items);
    }

    /**
     * Storniert Rechnungsposten
     *
     * @param item Der zu stornierende Posten
     * @param amount Die Anzahl der zu stornierenden Posten
     * @return FALSE wenn der aktuelle User nicht über die benötigten Berechtigungen verfügt
     */
    public boolean cancelItems(InvoiceItemData item, int amount)
    {
        return state.cancelItems(item, amount);
    }

    /**
     * Gibt alle in der Datenbank vorhandenen Länder zurück
     *
     * @return Eine Collection mit den Ländern
     */
    public Collection<CountryData> getAllCountries()
    {
        return state.getAllCountries();
    }
    
    /**
     * Gibt alle in der Datenbank vorhandenen CompanyTypes zurück
     *
     * @return Eine Collection mit den Typen
     */
    public Collection<CompanyTypeData> getAllCompanyTypes()
    {
        return state.getAllCompanyTypes();
    }

    /**
     * Erstellt einen neuen Kunden in Form eines Unternehmens mit gleicher Rechnungs- und Postanschrift
     *
     * @param companyName Der Name der Firma
     * @param street Postanschrift: die Straße
     * @param city Postanschrift: der Ort/die Stadt
     * @param zip Postanschrift: die Postleitzahl
     * @param email Postanschrift: die E-Mail Adresse
     * @param phone Postanschrift: die Telefonnummer
     * @param fax Postanschrift: die Fax-Nummer
     * @param country Postanschrift: das Land
     */
    public void createCompanyCustomer(String companyName, String street, String city, String zip, String email, String phone, String fax, CountryData country)
    {
        createCompanyCustomer(companyName, street, city, zip, email, phone, fax, country, street, city, zip, email, phone, fax, country);
    }

    /**
     * Erstellt einen neuen Kunden in Form eines Unternehmens
     *
     * @param companyName Der Name der Firma
     * @param street Postanschrift: die Straße
     * @param city Postanschrift: der Ort/die Stadt
     * @param zip Postanschrift: die Postleitzahl
     * @param email Postanschrift: die E-Mail Adresse
     * @param phone Postanschrift: die Telefonnummer
     * @param fax Postanschrift: die Fax-Nummer
     * @param country Postanschrift: das Land
     * @param invoiceStreet Rechnungsanschrift: die Straße
     * @param invoiceCity Rechnungsanschrift: der Ort/die Stadt
     * @param invoiceZip Rechnungsanschrift: die Postleitzahl
     * @param invoiceEmail Rechnungsanschrift: die E-Mail Adresse
     * @param invoicePhone Rechnungsanschrift: die Telefonnummer
     * @param invoiceFax Rechnungsanschrift: die Fax-Nummer
     * @param invoiceCountry Rechnungsanschrift: das Land
     */
    public void createCompanyCustomer(String companyName, String street, String city, String zip, String email, String phone, String fax, CountryData country,
            String invoiceStreet, String invoiceCity, String invoiceZip, String invoiceEmail, String invoicePhone, String invoiceFax, CountryData invoiceCountry)
    {
        state.createCompanyCustomer(companyName, street, city, zip, email, phone, fax, country, invoiceStreet,
                invoiceCity, invoiceZip, invoiceEmail, invoicePhone, invoiceFax, invoiceCountry);
    }

    /**
     * Erstellt einen neuen Kunden in Form einer realen Person mit gleicher Rechnungs- und Postanschrift
     *
     * @param firstName Der Vorname des Kunden
     * @param lastName Der Nachname des Kunden
     * @param street Postanschrift: die Straße
     * @param city Postanschrift: der Ort/die Stadt
     * @param zip Postanschrift: die Postleitzahl
     * @param email Postanschrift: die E-Mail Adresse
     * @param phone Postanschrift: die Telefonnummer
     * @param fax Postanschrift: die Fax-Nummer
     * @param country Postanschrift: das Land
     */
    public void createPrivateCustomer(String firstName, String lastName, String street, String city, String zip, String email, String phone, String fax, CountryData country)
    {
        createPrivateCustomer(firstName, lastName, street, city, zip, email, phone, fax, country, street, city, zip, email, phone, fax, country);
    }

    /**
     * Erstellt einen neuen Kunden in Form einer realen Person
     *
     * @param firstName Der Vorname des Kunden
     * @param lastName Der Nachname des Kunden
     * @param street Postanschrift: die Straße
     * @param city Postanschrift: der Ort/die Stadt
     * @param zip Postanschrift: die Postleitzahl
     * @param email Postanschrift: die E-Mail Adresse
     * @param phone Postanschrift: die Telefonnummer
     * @param fax Postanschrift: die Fax-Nummer
     * @param country Postanschrift: das Land
     * @param invoiceStreet Rechnungsanschrift: die Straße
     * @param invoiceCity Rechnungsanschrift: der Ort/die Stadt
     * @param invoiceZip Rechnungsanschrift: die Postleitzahl
     * @param invoiceEmail Rechnungsanschrift: die E-Mail Adresse
     * @param invoicePhone Rechnungsanschrift: die Telefonnummer
     * @param invoiceFax Rechnungsanschrift: die Fax-Nummer
     * @param invoiceCountry Rechnungsanschrift: das Land
     */
    public void createPrivateCustomer(String firstName, String lastName, String street, String city, String zip, String email, String phone, String fax, CountryData country,
            String invoiceStreet, String invoiceCity, String invoiceZip, String invoiceEmail, String invoicePhone, String invoiceFax, CountryData invoiceCountry)
    {
        state.createPrivateCustomer(firstName, lastName, street, city, zip, email, phone, fax, country, invoiceStreet,
                invoiceCity, invoiceZip, invoiceEmail, invoicePhone, invoiceFax, invoiceCountry);
    }

    /**
     * Gibt die Parteien zurück, welche mit den gewählten Aufenthalten in Verbindung stehen
     *
     * @return Eine Collection von Parteien
     */
    public Collection<PartyData> getWorkingHabitationsGuests()
    {
        return state.getWorkingHabitationsGuests();
    }

    /**
     * Verwendet einen Gast als Kunden, der die Rechnugn bezahlt
     *
     * @param guest Der zu verwendende Gast
     */
    public void useGuestAsCustomer(GuestData guest)
    {
        state.useGuestAsCustomer(guest);
    }
    
    /**
     * Verwendet eine vorhandene Partei als Kunden
     *
     * @param party Die Partei die verwendet werden soll
     */
    public void useExistingParty(PartyData party)
    {
        if (party instanceof Guest)
        {
            state.useGuestAsCustomer((Guest) party);
        }
        else if (party instanceof Customer)
        {
            state.useCustomer((Customer) party);
        }
        else
        {
            //Unerreichbarer Code
            assert(true) : "Party muss instanceof Guest oder Customer sein, da darüberliegende Klassen abstrakt sind";
        }
    }

    /**
     * Gibt die ausgewählten Rechnungsposten zurück
     *
     * @return Die gewählten Posten
     */
    public Collection<InvoiceItemData> getChosenItems()
    {
        return HelperFunctions.castCollectionUp(selectedItems, InvoiceItemData.class, IInvoiceItem.class);
    }

    /**
     * Gibt an, dass die Rechnung bezahlt wurde
     */
    public void pay()
    {
        state.pay();
    }

    /**
     * Geht im Vorgang einen Schritt weiter
     */
    public void next()
    {
        state.next();
    }

    /**
     * Geht im Vorgang einen Schritt zurück
     */
    public void back()
    {
        state.back();
    }

    /**
     * Geht in SplitInvoice State über
     */
    public void splitInvoice()
    {
        state.splitInvoice();
    }

    public CustomerData getCustomerData()
    {
        return customer;
    }

    /**
     * Gibt die noch offenen Posten der aktuellen Rechnung an
     *
     * @return Eine COllection mit noch offenen Posten
     */
    Collection<IInvoiceItem> getOpenItems()
    {
        Collection<IInvoiceItem> openItems = new LinkedList<IInvoiceItem>();

        for (IHabitation h : habitations)
        {
            for (IInvoiceItem i : h.getInvoiceItems())
            {
                if (i.getInvoice() == null)
                {
                    openItems.add(i);
                }
                else
                {
                    if (!i.getInvoice().isFulfilled())
                    {
                        openItems.add(i);
                    }
                }
            }
        }

        return openItems;
    }
    
    /**
     * Gibt die noch offenen Posten der aktuellen Rechnung an
     *
     * @return Eine Collection mit noch offenen Posten
     */
    public Collection<InvoiceItemData> getOpenItems(HabitationData habitation)
    {
        Collection<InvoiceItemData> openItems = new LinkedList<InvoiceItemData>();
        IHabitation h = (IHabitation)habitation;
        
        for (IInvoiceItem i : h.getInvoiceItems())
        {
            if (i.getInvoice() == null)
            {
                openItems.add(i);
            }
            else
            {
                if (!i.getInvoice().isFulfilled())
                {
                    openItems.add(i);
                }
            }
        }

        return openItems;
    }
    
    /**
     * ***************************************************************
     */
    Collection<IHabitation> getHabitations()
    {
        return habitations;
    }

    void setHabitations(Collection<IHabitation> habitations)
    {
        this.habitations = habitations;
    }

    Collection<IInvoiceItem> getSelectedItems()
    {
        return selectedItems;
    }

    void setSelectedItems(Collection<IInvoiceItem> selectedItems)
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

    void setCustomer(Customer customer)
    {
        this.customer = customer;
    }

    public Collection<PartyData> searchParties(String text)
    {
        return state.searchParties(text);
    }
    
    void addSplittedItems(InvoiceItem item)
    {
        if (splittedItems == null)
        {
            splittedItems = new LinkedList<InvoiceItem>();
        }
        
        splittedItems.add(item);
    }
    
    void setSplittedItems(Collection<InvoiceItem> items)
    {
        splittedItems = items;
    }

    Collection<InvoiceItem> getSplittedItems()
    {
        return splittedItems;
    }
    
    Collection<IInvoiceItem> getAllInvoiceItems()
    {
        Collection<IInvoiceItem> col = new HashSet<IInvoiceItem>();
        
        for (IHabitation h : getHabitations())
        {
            for (IInvoiceItem ii : h.getInvoiceItems())
            {
                col.add(ii);
            }
        }
        
        return col;
    }
}