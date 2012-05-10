package hotelsoftware.model.domain.invoice;

/**
 * Dieses Interface enthällt die Methoden der Klasse InvoiceItem, welche dort benötigt werden.
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
