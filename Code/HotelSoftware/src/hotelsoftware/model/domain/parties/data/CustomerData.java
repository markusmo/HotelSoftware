package hotelsoftware.model.domain.parties.data;

import hotelsoftware.model.domain.invoice.data.InvoiceData;
import java.util.Collection;

/**
 *Dieses Interface enthällt alle wichtigen Methoden für die Customerklasse. 
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public interface CustomerData
{

    AddressData getInvoiceAddressData();

    Collection<InvoiceData> getInvoicesData();

    String getName();
    
}
