package hotelsoftware.controller.data.invoice;

import hotelsoftware.controller.data.parties.CustomerData;
import hotelsoftware.controller.data.users.UserData;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

/**
 * Dieses Interface wird and die GUI weitergereicht, um eine Rechung abzubilden.
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public interface InvoiceData
{
    
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
