/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.invoice;

/**
 *
 * @author Kno
 */
public interface IInvoiceItem {

    void fullfill();

    /**
     * Diese Methode reduziert den RechnungsBetrag um den eingegebenen Betrag
     * @param amount
     */
    void remove(Integer amount);
    
}
