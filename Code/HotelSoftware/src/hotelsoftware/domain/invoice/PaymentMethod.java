package hotelsoftware.domain.invoice;

import hotelsoftware.database.Exceptions.FaildToDeleteFromDatabaseException;
import hotelsoftware.database.Exceptions.FailedToSaveToDatabaseException;
import hotelsoftware.database.model.Paymentmethods;
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
    private Paymentmethods model;

    private PaymentMethod(String method, Paymentmethods model)
    {
        this.method = method;
        this.model = model;
    }
    
    public PaymentMethod(Paymentmethods method)
    {
        this.method = method.getName();
        this.model = method;
    }
    
    public Paymentmethods getModel()
    {
        return model;
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
        LinkedList<PaymentMethod> retList = new LinkedList<PaymentMethod>();
        try
        {
            List<Paymentmethods> methods = Paymentmethods.getPaymentMethods();
            for (Paymentmethods paymentmethods : methods)
            {
                retList.add(new PaymentMethod(paymentmethods.getName(),paymentmethods));
            }
        } catch (HibernateException e)
        {
            //connection failed ...
        }
        return retList;
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
        //exception handling?
        Paymentmethods retMethod = Paymentmethods.getPaymentMethodByName(method);
        if(retMethod != null)
        {
            return new PaymentMethod(retMethod.getName(), retMethod);
        }
        return null;
    }

    /**
     * Calls the model and creates a new paymentmethod in the database
     * @param method 
     * the name of the new method
     */
    public static void savePaymentMethod(String method)
    {
        try
        {
            Paymentmethods.savePaymentMethod(method);
        } catch (HibernateException ex)
        {
            //connection faild
        } catch (FailedToSaveToDatabaseException ex)
        {
            //saving failed
        }
    }
    
    public static void deletePaymentMethod(String method)
    {
        try
        {
            Paymentmethods.deletePaymentMethod(method);
        } catch (FaildToDeleteFromDatabaseException ex)
        {
            //deleting failed
        }
    }
}
