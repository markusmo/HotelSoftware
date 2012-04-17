package hotelsoftware.model.domain.invoice;

import hotelsoftware.database.FaildToDeleteFromDatabaseException;
import hotelsoftware.database.FailedToSaveToDatabaseException;
import hotelsoftware.model.database.invoice.DBPaymentmethods;
import java.util.LinkedList;
import java.util.List;
import org.hibernate.HibernateException;

/**
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 * @author mohi
 */
public class PaymentMethod
{

    private String method;

    public PaymentMethod(String method)
    {
        this.method = method;
    }

    public String getMethod()
    {
        return method;
    }

    /**
     * Communicates with the model and creates a linked list of paymentmethods
     * @return 
     * a linked list of paymentmethods on domain-level
     */
    public static LinkedList<PaymentMethod> getPaymentMethods()
    {
        return null;
    }

    /**
     * Communicates with the model and retrieves a single paymentmethod on domain
     * level by name
     * @param method
     * the name of the paymentmethod
     * @return
     * a domain level paymentmethod
     */
    public static PaymentMethod getPaymentMethodByName(String method)
    {
        return null;
    }
}
