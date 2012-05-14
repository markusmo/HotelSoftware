/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.invoice;

/**
 *
 * @author Kno
 */
public interface IInvoiceItemPK {

    Integer getIdInvoice();

    Integer getIdService();

    void setIdInvoice(int idInvoice);

    void setIdService(int idServices);
    
}
