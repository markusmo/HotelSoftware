package hotelsoftware.model.domain.parties;

import hotelsoftware.model.domain.parties.data.PartyData;
import hotelsoftware.model.domain.parties.data.AddressData;
import hotelsoftware.model.domain.parties.data.CompanyData;
import hotelsoftware.model.domain.parties.data.CompanyTypeData;
import hotelsoftware.model.domain.invoice.Invoice;
import hotelsoftware.model.domain.invoice.data.InvoiceData;
import hotelsoftware.util.HelperFunctions;
import java.util.Collection;
import java.util.LinkedHashSet;

/**
 * Diese Klasse erbt von Customer, da sie für Zimmer/Dienstleistungen bezahlt. Hierbei handelt es sich um eine Firma mit einem Namen und einem Typ.
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class Company extends Customer implements CompanyData
{
    private String companyname;
    private CompanyType companyType;
    private Collection<Party> contactPersons;

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
    public static Company create(String name, CompanyType typ, Address address,
            Address invoiceAddress)
    {
        return new Company(name, typ, address, invoiceAddress,
                new LinkedHashSet<Party>());
    }

    private Company(String name, CompanyType type, Address address,
            Address invoiceAddress, Collection<Party> partys)
    {
        super(address, invoiceAddress);
        this.companyname = name;
        this.companyType = type;
        contactPersons = partys;
    }

    @Override
    public String getName()
    {
        // TODO Auto-generated method stub
        return companyname;
    }

    public String getCompanyname()
    {
        return companyname;
    }

    public void setName(String companyname)
    {
        this.companyname = companyname;
    }

    public CompanyType getCompanyType()
    {
        return companyType;
    }

    public void setCompanyType(CompanyType type)
    {
        this.companyType = type;
    }

    public Collection<Party> getContactPersons()
    {
        return contactPersons;
    }

    public void setContactPersons(Collection<Party> contactPersons)
    {
        this.contactPersons = contactPersons;
    }

    public void removeContactPerson(Party p)
    {
        contactPersons.remove(p);
    }

    public void addContactPerson(Party p)
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
    public static Company getCompanyByName(String name)
            throws CompanyNotFoundException
    {
        return PartyFacade.getInstance().getCompanyByName(name);
    }

    public Collection<PartyData> getContactPersonsData()
    {
        return new HelperFunctions<PartyData, Party>().castCollectionUp(getContactPersons());
    }

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
        return new HelperFunctions<InvoiceData, Invoice>().castCollectionUp(getInvoices());
    }
}
