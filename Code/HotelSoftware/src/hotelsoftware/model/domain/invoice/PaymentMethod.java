package hotelsoftware.model.domain.invoice;

import hotelsoftware.model.DynamicMapper;
import hotelsoftware.model.database.invoice.DBPaymentmethod;
import java.util.Collection;

/**
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 * @author mohi
 */
public class PaymentMethod implements PaymentMethodData
{
    private Integer id;
    private String method;

    public PaymentMethod()
    {
    }

    public PaymentMethod(String method)
    {
        this.method = method;
    }

    @Override
    public String getMethod()
    {
        return method;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        if (this.id == null)
        {
            this.id = id;
        }
    }

    public void setMethod(String method)
    {
        this.method = method;
    }

    /**
     * Gibt eine Liste von allen Zahlungsmethoden aus
     * @return 
     * eine Collection aus allen Zahlungsmethoden
     */
    public static Collection<PaymentMethod> getAllPaymentMethods()
    {
        Collection<DBPaymentmethod> dbpm = DBPaymentmethod.getPaymentMethods();
        return (Collection<PaymentMethod>)DynamicMapper.map(dbpm);
    }

    /**
     * Gibt eine spezifische Zahlungsmethode aus
     * @param method
     * die gesuchte Methode
     * @return
     * das Zahlungsmethoden Objekt
     */
    public static PaymentMethod getPaymentMethodByName(String method)
    {
        DBPaymentmethod dbpm = DBPaymentmethod.getPaymentMethodByName(method);
        return (PaymentMethod)DynamicMapper.map(dbpm);
    }
}
