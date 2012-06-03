package hotelsoftware.controller.data.parties;

import hotelsoftware.controller.data.invoice.InvoiceData;
import java.util.Collection;

/**
 *Dieses Interface enthält alle wichtigen Methoden für die Customerklasse. 
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public interface CustomerData extends PartyData
{

    AddressData getInvoiceAddressData();

    Collection<InvoiceData> getInvoicesData();

    String getName();
    
    String getUsername();

    String getPassword();
    
}
