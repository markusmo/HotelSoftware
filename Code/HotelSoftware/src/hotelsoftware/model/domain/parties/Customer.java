package hotelsoftware.model.domain.parties;

import hotelsoftware.controller.data.invoice.InvoiceData;
import hotelsoftware.controller.data.parties.AddressData;
import hotelsoftware.model.database.manager.PartyManager;
import hotelsoftware.model.domain.invoice.IInvoice;
import hotelsoftware.support.LoginFailureException;
import hotelsoftware.util.HelperFunctions;
import java.util.Collection;

/**
 * Diese Klasse ist eine unterklasse der Klasse Party. Sie symbolisiert die Personen oder Gruppierungen, die für die Bezahlung derZimmer und/oder Extraleistungen zuständig sind.
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public abstract class Customer extends Party implements ICustomer
{

    protected IAddress invoiceAddress;
    protected Collection<IInvoice> invoices;
    protected String username;
    protected String password;

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
    
    @Override
    public String getUsername()
    {
        return username;
    }

    @Override
    public void setUsername(String username)
    {
        this.username = username;
    }
    
    @Override
    public String getPassword()
    {
        return password;
    }

    @Override
    public void setPassword(String password)
    {
        this.password = password;
    }
    
    public static ICustomer login(String username, String password) throws LoginFailureException
    {
        return PartyManager.getInstance().login(username, password);
    }
}
