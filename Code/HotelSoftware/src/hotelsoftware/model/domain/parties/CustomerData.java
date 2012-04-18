/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.parties;

import hotelsoftware.model.domain.invoice.InvoiceData;
import java.util.Collection;

/**
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public interface CustomerData
{

    AddressData getInvoiceAddressData();

    Collection<InvoiceData> getInvoicesData();

    String getName();
    
}
