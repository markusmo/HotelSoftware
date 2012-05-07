/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.util.pdf;

import hotelsoftware.model.domain.invoice.Invoice;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author mohi
 */
public class PdfgenerateTest implements Observer
{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {

        Invoice invoice = Invoice.getInvoiceByInvoiceNumber("05071200000001");
        PdfGenerator generator = new PdfGenerator(invoice.getCustomer(), invoice.getInvoiceNumber(), invoice.getInvoiceItems(), invoice.getCreated(), invoice.getExpiration());
        Thread thread = new Thread(generator);
        thread.start();
    }

    @Override
    public void update(Observable o, Object o1)
    {
        System.out.println("done");
    }
}
