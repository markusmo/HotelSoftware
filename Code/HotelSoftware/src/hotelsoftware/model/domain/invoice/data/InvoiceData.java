package hotelsoftware.model.domain.invoice.data;

import hotelsoftware.model.domain.parties.data.CustomerData;
import hotelsoftware.model.domain.users.data.UserData;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

/**
 * Dieses Interface wird and die GUI weitergereicht, um eine Rechung abzubilden.
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public interface InvoiceData
{
    //FIXME change return-types to *Data!!!
    
    Date getCreated();

    Date getExpiration();

    CustomerData getCustomerData();

    UserData getUserData();

    PaymentMethodData getPaymentMethodData();

    String getInvoiceNumber();
   
    Collection<InvoiceItemData> getInvoiceItemsData();

    String getPaymentMethodName();

    Boolean isFulfilled();
    
}
