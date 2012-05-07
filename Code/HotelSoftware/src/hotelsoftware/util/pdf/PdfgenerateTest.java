/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.util.pdf;

import com.lowagie.text.BadElementException;
import com.lowagie.text.DocumentException;
import hotelsoftware.model.domain.invoice.Invoice;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mohi
 */
public class PdfgenerateTest
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        try
        {
            Invoice invoice = Invoice.getInvoiceByInvoiceNumber("05071200000001");
            PdfGenerator generator = new PdfGenerator();
            generator.generateInvoicePDF(invoice.getCustomer(), invoice.getInvoiceNumber(), invoice.getInvoiceItems(), invoice.getCreated(), invoice.getExpiration());
        }
        catch (FileNotFoundException ex)
        {
            Logger.getLogger(PdfgenerateTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (DocumentException ex)
        {
            Logger.getLogger(PdfgenerateTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (MalformedURLException ex)
        {
            Logger.getLogger(PdfgenerateTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IOException ex)
        {
            Logger.getLogger(PdfgenerateTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
