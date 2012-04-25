package hotelsoftware.model.domain.parties;

import hotelsoftware.model.domain.invoice.Invoice;
import hotelsoftware.model.domain.invoice.InvoiceData;
import hotelsoftware.util.HelperFunctions;
import java.util.Collection;
import java.util.LinkedHashSet;

/**
 * Diese Klasse ist eine unterklasse der Klasse Party. Sie symbolisiert die Personen oder Gruppierungen, die für die Bezahlung derZimmer und/oder Extraleistungen zuständig sind.
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class Customer extends Party implements CustomerData
{
    protected Address invoiceAddress;
    protected Collection<Invoice> invoices;

    public Customer()
    {
    }

    public Address getInvoiceAddress()
    {
        return invoiceAddress;
    }

    public void setInvoiceAddress(Address invoiceAddress)
    {
        this.invoiceAddress = invoiceAddress;
    }

    protected Customer(Address address, Address invoiceAddress)
    {
        this(address, invoiceAddress, new LinkedHashSet<Invoice>());
    }

    protected Customer(Address address, Address invoiceAddress, Collection<Invoice> invoices)
    {
        super(address);

        this.invoiceAddress = invoiceAddress;
        this.invoices = invoices;
    }

    public Collection<Invoice> getInvoices()
    {
        return invoices;
    }

    public void setInvoices(Collection<Invoice> invoices)
    {
        this.invoices = invoices;
    }

    public void removeInvoice(Invoice i)
    {
        invoices.remove(i);
    }

    public void addInvoice(Invoice i)
    {
        invoices.add(i);
    }

    public AddressData getInvoiceAddressData()
    {
        return invoiceAddress;
    }

    public Collection<InvoiceData> getInvoicesData()
    {
        return new HelperFunctions<InvoiceData, Invoice>().castCollectionUp(invoices);
    }

    public String getName()
    {
        //TODO Vererbung
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
