/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.parties;

import hotelsoftware.model.domain.invoice.Invoice;
import hotelsoftware.model.domain.invoice.InvoiceData;
import hotelsoftware.util.HelperFunctions;
import java.util.Collection;
import java.util.LinkedList;

/**
 * Eine Firma die ein oder mehere Zimmer gebucht hat
 * @author Lins Christian (christian.lins87@gmail.com)
 * @author Hubert
 */
public class Company extends Customer implements CompanyData
{
    private String companyname;
    private CompanyType type;
    private Collection<Party> contactPersons;

    public static Company create(String name, CompanyType typ, Address address,
            Address invoiceAddress)
    {
        return new Company(name, typ, address, invoiceAddress,
                new LinkedList<Party>());
    }

    private Company(String name, CompanyType type, Address address,
            Address invoiceAddress, LinkedList<Party> partys)
    {
        super(address, invoiceAddress);
        this.companyname = name;
        this.type = type;
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

    public void setCompanyname(String companyname)
    {
        this.companyname = companyname;
    }

    public CompanyType getType()
    {
        return type;
    }

    public void setType(CompanyType type)
    {
        this.type = type;
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
        return (CompanyTypeData) getType();
    }

    public AddressData getAddressData()
    {
        return (AddressData) getAddress();
    }

    public AddressData getInvoiceAddressData()
    {
        return (AddressData) getInvoiceAddress();
    }

    public Collection<InvoiceData> getInvoicesData()
    {
        return new HelperFunctions<InvoiceData, Invoice>().castCollectionUp(getInvoices());
    }
}
