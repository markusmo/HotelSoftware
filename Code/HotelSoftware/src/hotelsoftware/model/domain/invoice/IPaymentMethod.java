/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.invoice;

import hotelsoftware.controller.data.invoice.PaymentMethodData;

/**
 *
 * @author Kno
 */
public interface IPaymentMethod extends PaymentMethodData{

    Integer getId();

    String getMethod();

    void setId(Integer id);

    void setMethod(String method);
    
}
