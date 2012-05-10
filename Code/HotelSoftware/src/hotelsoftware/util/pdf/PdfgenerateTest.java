/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.util.pdf;

import hotelsoftware.model.domain.invoice.InvoiceItem;
import hotelsoftware.model.domain.parties.PartyFacade;
import hotelsoftware.model.domain.parties.PrivateCustomer;
import hotelsoftware.model.domain.service.Habitation;
import hotelsoftware.support.GuestNotFoundException;
import hotelsoftware.support.PrivateCustomerNotFoundException;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author mohi
 */
public class PdfgenerateTest extends JFrame implements PDFObserver
{    
    public PdfgenerateTest()
    {
        start();
    }
    
    private void start()
    {
        try
        {
            Collection<Habitation> habs = Habitation.searchHabitations(null, null, 201);
            Collection<InvoiceItem> items = habs.iterator().next().getInvoiceItems();
            PrivateCustomer customer = PartyFacade.getInstance().getPrivateCustomerByName("Otto", "von Schirach");
            PdfGenerator generator = new PdfGenerator(this ,customer, "i07051200000001", items, new Date(), new Date());
            Thread thread = new Thread(generator);
            thread.start();
        }
        catch (PrivateCustomerNotFoundException ex)
        {
            Logger.getLogger(PdfgenerateTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (GuestNotFoundException ex)
        {
            Logger.getLogger(PdfgenerateTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void getPDFasPanel(JPanel pdfPanel)
    {
        JScrollPane pane = new JScrollPane();
        pane.add(pdfPanel);
        this.getContentPane().add(pane);
    }

    @Override
    public void gererationFinished(boolean done)
    {
        this.pack();
    }
    
    public static void main(String args[])
    {
        PdfgenerateTest test = new PdfgenerateTest();
        test.setVisible(true);
    }
}
