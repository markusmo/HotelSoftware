package hotelsoftware.model.database.invoice;
/**
 * Diese Klasse Dient zum Testen der Invoice Klasse
 * @author Kno
 */
public class InvoiceTest {

/**
 * Mainklasse zum testen
 * @param args 
 */
    public static void main(String[] args)
    {
        DBInvoice testInvoice = DBInvoice.getInvoiceByInvoiceNumber("20120419");
        System.out.println(testInvoice.getInvoiceNumber());
    }
}
