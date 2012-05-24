package hotelsoftware.model.domain.invoice;

import hotelsoftware.model.database.manager.InvoiceManager;
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
    public static Set<IPaymentMethod> getAllPaymentMethods()
    {
        return InvoiceManager.getInstance().getAllPaymentMethods();
    }

    /**
     * Gibt eine spezifische Zahlungsmethode aus
     * @param method
     * die gesuchte Methode
     * @return
     * das Zahlungsmethoden Objekt
     */
    public static IPaymentMethod getPaymentMethodByName(String method)
    {
        return InvoiceManager.getInstance().getPaymentMethodByName(method);
    }
}
