package hotelsoftware.model.domain.invoice;

import hotelsoftware.model.domain.parties.CustomerData;
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

    CustomerData getIdCustomersData();

    UserData getIdUsersData();

    PaymentMethodData getIdpaymentMethodsData();

    // not needed
    //InvoiceData getInvoiceByHabitationData(HabitationData habitation);

    String getInvoiceNumber();

   
    Collection<InvoiceItemData> getInvoiceitemsData();

    String getPaymentMethodName();

    boolean isFulfilled();
    
}
