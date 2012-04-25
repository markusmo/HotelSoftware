package hotelsoftware.model.domain.invoice;
import hotelsoftware.model.domain.service.Habitation;
import java.util.Collection;
import java.util.Set;

/**
 * Fassade die das Package Invoice managed, alle relevanten Methoden sind auf dieser abgebildet.
 *
 * @author mohi
 */
public class InvoiceFacade
{
    private InvoiceFacade()
    {
    }

    public static InvoiceFacade getInstance()
    {
        return InvoiceFacadeHolder.INSTANCE;
    }

    private static class InvoiceFacadeHolder
    {
        private static final InvoiceFacade INSTANCE = new InvoiceFacade();
    }

    /**
     * Sucht eine Rechnung nach der Rechungsnummer und gibt diese aus, wenn vorhanden.
     *
     * @param invoicenumber
     * die Rechnungsnummer, nach der man sucht.
     * @return
     * die Rechung.
     */
    public Invoice getInvoiceByInvoiceNumber(String invoicenumber)
    {
        return Invoice.getInvoiceByInvoiceNumber(invoicenumber);
    }

    /**
     * Gibt eine neue Rechnung zu einer Belegung generiert aus einer bestehenden Rechnung
     * die bestehende Rechung verliert dabei die Rechungspositionen, die der neuen Rechung
     * hinzugefügt werden.
     *
     * @param invoice
     * Die Rechung, die alle Positionen enthält.
     * @param habitation
     * Die Belegung, auf die gefiltert wird.
     * @return
     * eine neue Rechung, in der alle Positionen auf eine Belegung stimmen.
     */
    public Invoice getInvoiceByHabitaion(Invoice invoice, Habitation habitation)
    {
        return invoice.getInvoiceByHabitation(habitation);
    }

    /**
     * Gibt alle Zahlungsmethoden aus.
     *
     * @return
     * Eine Collection aus allen Zahlungsmethoden
     */
    public Set<PaymentMethod> getAllPaymentMethods()
    {
        return PaymentMethod.getAllPaymentMethods();
    }

    /**
     * Gibt eine spezifische Zahlungsmethode nach Namen aus.
     *
     * @param name
     * Der Name der Zahlungsmethode.
     * @return
     * Die spezifische Zahlungsmethode.
     */
    public PaymentMethod getPaymentMethodByName(String name)
    {
        return PaymentMethod.getPaymentMethodByName(name);
    }
}
