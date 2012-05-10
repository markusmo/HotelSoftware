/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.controller.createinvoice;

import hotelsoftware.controller.data.invoice.InvoiceItemData;
import hotelsoftware.controller.data.parties.CountryData;
import hotelsoftware.controller.data.parties.CustomerData;
import hotelsoftware.controller.data.parties.GuestData;
import hotelsoftware.controller.data.parties.PartyData;
import hotelsoftware.controller.data.service.HabitationData;
import hotelsoftware.model.domain.invoice.InvoiceItem;
import hotelsoftware.model.domain.parties.Customer;
import hotelsoftware.model.domain.parties.Country;
import hotelsoftware.model.domain.service.Habitation;
import hotelsoftware.util.HelperFunctions;
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
    private Customer customer;

    private CreateInvoiceController()
    {
    }

    public static CreateInvoiceController getInstance()
    {
        return CreateInvoiceControllerHolder.INSTANCE;
    }

    private static class CreateInvoiceControllerHolder
    {
        private static final CreateInvoiceController INSTANCE = new CreateInvoiceController();
    }

    /**
     * Sucht nach Aufenthalten anhand der angegebenen Suchparameter, leere Parameter werden ignoriert
     * @param firstName Der Vorname eines Gastes der zum Aufenthalt gehört
     * @param lastName Der Nachname eines Gastes der zum Aufenthalt gehört
     * @param roomNr Die Raum Nummer des zum Aufenthalt gehörigen Raums
     * @return Eine Collection aus den dazu gefundenen Aufenthalten
     */
    public Collection<HabitationData> search(String firstName, String lastName, String roomNr)
    {
        return state.search(firstName, lastName, roomNr);
    }

    /**
     * Wählt die Aufenthalte aus für die Rechnung(en) erstellt werden sollen
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
     * @param items Die für die Rechnung relevanten Posten
     */
    public void selectItems(Collection<InvoiceItemData> items)
    {
        state.selectItems(items);
    }

    /**
     * Storniert Rechnungsposten
     * @param item Der zu stornierende Posten
     * @param amount Die Anzahl der zu stornierenden Posten
     */
    public void cancelItems(InvoiceItemData item, int amount)
    {
        state.cancelItems(item, amount);
    }

    /**
     * Gibt alle in der Datenbank vorhandenen Länder zurück
     * @return Eine Collection mit den Ländern
     */
    public Collection<CountryData> getAllCountries()
    {
        return state.getAllCountries();
    }

    /**
     * Erstellt einen neuen Kunden in Form eines Unternehmens mit gleicher Rechnungs- und Postanschrift
     * @param companyName Der Name der Firma
     * @param street Postanschrift: die Straße
     * @param city Postanschrift: der Ort/die Stadt
     * @param zip Postanschrift: die Postleitzahl
     * @param email Postanschrift: die E-Mail Adresse
     * @param phone Postanschrift: die Telefonnummer
     * @param fax Postanschrift: die Fax-Nummer
     * @param country  Postanschrift: das Land
     */
    public void createCompanyCustomer(String companyName, String street, String city, String zip, String email, String phone, String fax, CountryData country)
    {
        createCompanyCustomer(companyName, street, city, zip, email, phone, fax, country, street, city, zip, email, phone, fax, country);
    }

    /**
     * Erstellt einen neuen Kunden in Form eines Unternehmens
     * @param companyName Der Name der Firma
     * @param street Postanschrift: die Straße
     * @param city Postanschrift: der Ort/die Stadt
     * @param zip Postanschrift: die Postleitzahl
     * @param email Postanschrift: die E-Mail Adresse
     * @param phone Postanschrift: die Telefonnummer
     * @param fax Postanschrift: die Fax-Nummer
     * @param country  Postanschrift: das Land
     * @param invoiceStreet Rechnungsanschrift: die Straße
     * @param invoiceCity Rechnungsanschrift: der Ort/die Stadt
     * @param invoiceZip Rechnungsanschrift: die Postleitzahl
     * @param invoiceEmail Rechnungsanschrift: die E-Mail Adresse
     * @param invoicePhone Rechnungsanschrift: die Telefonnummer
     * @param invoiceFax Rechnungsanschrift: die Fax-Nummer
     * @param invoiceCountry  Rechnungsanschrift: das Land
     */
    public void createCompanyCustomer(String companyName, String street, String city, String zip, String email, String phone, String fax, CountryData country,
            String invoiceStreet, String invoiceCity, String invoiceZip, String invoiceEmail, String invoicePhone, String invoiceFax, CountryData invoiceCountry)
    {
        state.createCompanyCustomer(companyName, street, city, zip, email, phone, fax, country, invoiceStreet,
                invoiceCity, invoiceZip, invoiceEmail, invoicePhone, invoiceFax, invoiceCountry);
    }

    /**
     * Erstellt einen neuen Kunden in Form einer realen Person mit gleicher Rechnungs- und Postanschrift
     * @param firstName Der Vorname des Kunden
     * @param lastName Der Nachname des Kunden
     * @param street Postanschrift: die Straße
     * @param city Postanschrift: der Ort/die Stadt
     * @param zip Postanschrift: die Postleitzahl
     * @param email Postanschrift: die E-Mail Adresse
     * @param phone Postanschrift: die Telefonnummer
     * @param fax Postanschrift: die Fax-Nummer
     * @param country  Postanschrift: das Land
     */
    public void createPrivateCustomer(String firstName, String lastName, String street, String city, String zip, String email, String phone, String fax, CountryData country)
    {
        createPrivateCustomer(firstName, lastName, street, city, zip, email, phone, fax, country, street, city, zip, email, phone, fax, country);
    }

    /**
     * Erstellt einen neuen Kunden in Form einer realen Person
     * @param firstName Der Vorname des Kunden
     * @param lastName Der Nachname des Kunden
     * @param street Postanschrift: die Straße
     * @param city Postanschrift: der Ort/die Stadt
     * @param zip Postanschrift: die Postleitzahl
     * @param email Postanschrift: die E-Mail Adresse
     * @param phone Postanschrift: die Telefonnummer
     * @param fax Postanschrift: die Fax-Nummer
     * @param country  Postanschrift: das Land
     * @param invoiceStreet Rechnungsanschrift: die Straße
     * @param invoiceCity Rechnungsanschrift: der Ort/die Stadt
     * @param invoiceZip Rechnungsanschrift: die Postleitzahl
     * @param invoiceEmail Rechnungsanschrift: die E-Mail Adresse
     * @param invoicePhone Rechnungsanschrift: die Telefonnummer
     * @param invoiceFax Rechnungsanschrift: die Fax-Nummer
     * @param invoiceCountry  Rechnungsanschrift: das Land
     */
    public void createPrivateCustomer(String firstName, String lastName, String street, String city, String zip, String email, String phone, String fax, CountryData country,
            String invoiceStreet, String invoiceCity, String invoiceZip, String invoiceEmail, String invoicePhone, String invoiceFax, CountryData invoiceCountry)
    {
        state.createPrivateCustomer(firstName, lastName, street, city, zip, email, phone, fax, country, invoiceStreet,
                invoiceCity, invoiceZip, invoiceEmail, invoicePhone, invoiceFax, invoiceCountry);
    }

    /**
     * Gibt die Parteien zurück, welche mit den gewählten Aufenthalten in Verbindung stehen
     * @return Eine Collection von Parteien
     */
    public Collection<PartyData> getWorkingHabitationsGuests()
    {
        return state.getWorkingHabitationsGuests();
    }

    /**
     * Verwendet einen Gast als Kunden, der die Rechnugn bezahlt
     * @param guest Der zu verwendende Gast
     */
    public void useGuestAsCustomer(GuestData guest)
    {
        state.useGuestAsCustomer(guest);
    }

    /**
     * Gibt die ausgewählten Rechnungsposten zurück
     * @return Die gewählten Posten
     */
    public Collection<InvoiceItemData> getChosenItems()
    {
        return HelperFunctions.castCollectionUp(selectedItems, InvoiceItemData.class, InvoiceItem.class);
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
    
    /**
     * Bricht den aktuellen Vorgang ab
     * Kann auch daran liegen, dass nicht alle Posten beglichen werden sollen
     */
    public void abort()
    {
        state = new SearchState(this);
        habitations = null;
        selectedItems = null;
    }

    public CustomerData getCustomerData()
    {
        return customer;
    }

    /**
     * Gibt die noch offenen Posten der aktuellen Rechnung an
     * @return Eine COllection mit noch offenen Posten
     */
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

    /**
     * ***************************************************************
     */
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
    
    void setCustomer(Customer customer)
    {
        this.customer = customer;
    }
}