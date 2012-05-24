/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.invoice;

/**
 *
 * @author Kno
 */
public interface IPaymentMethod{

    Integer getId();

    String getMethod();

    void setId(Integer id);

    void setMethod(String method);
    
}
