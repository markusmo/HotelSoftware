package hotelsoftware.model.domain.parties;

import hotelsoftware.controller.data.invoice.InvoiceData;
import hotelsoftware.controller.data.parties.AddressData;
import hotelsoftware.controller.data.parties.CompanyTypeData;
import hotelsoftware.controller.data.parties.PartyData;
import hotelsoftware.model.domain.invoice.IInvoice;
import hotelsoftware.support.CompanyNotFoundException;
import hotelsoftware.util.HelperFunctions;
import java.util.Collection;
import java.util.LinkedHashSet;

/**
 * Diese Klasse erbt von Customer, da sie für Zimmer/Dienstleistungen bezahlt. Hierbei handelt es sich um eine Firma mit einem Namen und einem Typ.
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class Company extends Customer implements ICompany
{
    private String companyname;
    private ICompanyType companyType;
    private Collection<IParty> contactPersons;

    public Company()
    {
    }

    /**
     * Instanziert eine neue Firma/Reisebuero, abgebildet als Kunde, fuer das System
     *
     * @param name
     * Der Name der Firma/Reisebuero
     * @param typ
     * Die Art der Firma (Reisebuero, Firma)
     * @param address
     * Die Adresse der Firma
     * @param invoiceAddress
     * Die Rechungsadresse, der Firma
     * @return
     * Eine neue Instanz der Klasse
     */
    public static Company create(String name, ICompanyType typ, IAddress address,
            IAddress invoiceAddress)
    {
        return new Company(name, typ, address, invoiceAddress,
                new LinkedHashSet<IParty>());
    }

    private Company(String name, ICompanyType type, IAddress address,
            IAddress invoiceAddress, Collection<IParty> partys)
    {
        super(address, invoiceAddress);
        this.companyname = name;
        this.companyType = type;
        contactPersons = partys;
    }

    @Override
    public String getName()
    {
        return companyname;
    }

    @Override
    public String getCompanyname()
    {
        return companyname;
    }

    @Override
    public void setName(String companyname)
    {
        this.companyname = companyname;
    }

    @Override
    public ICompanyType getCompanyType()
    {
        return companyType;
    }

    @Override
    public void setCompanyType(ICompanyType type)
    {
        this.companyType = type;
    }

    @Override
    public Collection<IParty> getContactPersons()
    {
        return contactPersons;
    }

    @Override
    public void setContactPersons(Collection<IParty> contactPersons)
    {
        this.contactPersons = contactPersons;
    }

    @Override
    public void removeContactPerson(IParty p)
    {
        contactPersons.remove(p);
    }

    @Override
    public void addContactPerson(IParty p)
    {
        contactPersons.add(p);
    }

    /**
     * sucht nach einer Firma anhand eines Namens
     *
     * @param name firmenname
     * @return Firmenobjekt
     * @throws CompanyNotFoundException Firma wurde nicht gefunden
     */
    public static ICompany getCompanyByName(String name)
            throws CompanyNotFoundException
    {
        return PartyFacade.getInstance().getCompanyByName(name);
    }
    
    /**
     * Gibt Firmen nach Namen aus
     * @param name der Name der Firma
     * @return eine Liste mit ähnlichen Namen
     */
    public static Collection<ICompany> getCompaniesByName(String name)
    {
        return PartyFacade.getInstance().getCompaniesByName(name);
    }

    @Override
    public Collection<PartyData> getContactPersonsData()
    {
        return new HelperFunctions<PartyData, IParty>().castCollectionUp(getContactPersons());
    }

    @Override
    public CompanyTypeData getTypeData()
    {
        return (CompanyTypeData) getCompanyType();
    }

    @Override
    public AddressData getAddressData()
    {
        return (AddressData) getAddress();
    }

    @Override
    public AddressData getInvoiceAddressData()
    {
        return (AddressData) getInvoiceAddress();
    }

    @Override
    public Collection<InvoiceData> getInvoicesData()
    {
        return new HelperFunctions<InvoiceData, IInvoice>().castCollectionUp(getInvoices());
    }
    
    @Override
    public String toString()
    {
        return companyname;
    }

}
