/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.util.pdf;

import hotelsoftware.model.domain.invoice.Invoice;
import javax.swing.JPanel;

/**
 *
 * @author mohi
 */
public class PdfgenerateTest implements PDFObserver
{
    
    public void start()
    {
        Invoice invoice = Invoice.getInvoiceByInvoiceNumber("05071200000001");
        PdfGenerator generator = new PdfGenerator(this ,invoice.getCustomer(), invoice.getInvoiceNumber(), invoice.getInvoiceItems(), invoice.getCreated(), invoice.getExpiration());
        Thread thread = new Thread(generator);
        thread.start();
    }

    @Override
    public void getPDFasPanel(JPanel pdfPanel)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void gererationFinished(boolean done)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
