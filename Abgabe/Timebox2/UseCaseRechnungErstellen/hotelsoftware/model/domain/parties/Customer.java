package hotelsoftware.model.domain.parties;

import hotelsoftware.controller.data.parties.AddressData;
import hotelsoftware.controller.data.parties.CustomerData;
import hotelsoftware.model.domain.invoice.Invoice;
import hotelsoftware.controller.data.invoice.InvoiceData;
import hotelsoftware.model.domain.invoice.IInvoice;
import hotelsoftware.util.HelperFunctions;
import java.util.Collection;
import java.util.LinkedHashSet;

/**
 * Diese Klasse ist eine unterklasse der Klasse Party. Sie symbolisiert die Personen oder Gruppierungen, die für die Bezahlung derZimmer und/oder Extraleistungen zuständig sind.
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public abstract class Customer extends Party implements ICustomer
{
    protected IAddress invoiceAddress;
    protected Collection<IInvoice> invoices;

    public Customer()
    {
    }

    @Override
    public IAddress getInvoiceAddress()
    {
        return invoiceAddress;
    }

    @Override
    public void setInvoiceAddress(IAddress invoiceAddress)
    {
        this.invoiceAddress = invoiceAddress;
    }

    protected Customer(IAddress address, IAddress invoiceAddress)
    {
        this(address, invoiceAddress, new LinkedHashSet<IInvoice>());
    }

    protected Customer(IAddress address, IAddress invoiceAddress, Collection<IInvoice> invoices)
    {
        super(address);

        this.invoiceAddress = invoiceAddress;
        this.invoices = invoices;
    }

    @Override
    public Collection<IInvoice> getInvoices()
    {
        return invoices;
    }

    @Override
    public void setInvoices(Collection<IInvoice> invoices)
    {
        this.invoices = invoices;
    }

    @Override
    public void removeInvoice(IInvoice i)
    {
        invoices.remove(i);
    }

    @Override
    public void addInvoice(IInvoice i)
    {
        invoices.add(i);
    }

    @Override
    public AddressData getInvoiceAddressData()
    {
        return invoiceAddress;
    }

    @Override
    public Collection<InvoiceData> getInvoicesData()
    {
        return new HelperFunctions<InvoiceData, IInvoice>().castCollectionUp(invoices);
    }
}
