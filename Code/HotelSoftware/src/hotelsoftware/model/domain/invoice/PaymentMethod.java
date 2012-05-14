package hotelsoftware.model.domain.invoice;

import hotelsoftware.controller.data.invoice.PaymentMethodData;
import hotelsoftware.model.DynamicMapper;
import hotelsoftware.model.database.invoice.DBPaymentMethod;
import java.util.Collection;
import java.util.Set;

/**
 * Diese Klasse repraesentiert eine Zahlungsmethode, mit der das System arbeitet.
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class PaymentMethod implements IPaymentMethod
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

    @Override
    public Integer getId()
    {
        return id;
    }

    @Override
    public void setId(Integer id)
    {
        if (this.id == null)
        {
            this.id = id;
        }
    }

    @Override
    public void setMethod(String method)
    {
        this.method = method;
    }

    /**
     * Gibt eine Liste von allen Zahlungsmethoden aus
     * @return 
     * eine Collection aus allen Zahlungsmethoden
     */
    public static Set<PaymentMethod> getAllPaymentMethods()
    {
        Set<DBPaymentMethod> dbpm = DBPaymentMethod.getPaymentMethods();
        return (Set<PaymentMethod>)DynamicMapper.map(dbpm);
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
        DBPaymentMethod dbpm = DBPaymentMethod.getPaymentMethodByName(method);
        return (PaymentMethod)DynamicMapper.map(dbpm);
    }
}
