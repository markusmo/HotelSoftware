/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.util.pdf;

import com.lowagie.text.BadElementException;
import com.lowagie.text.DocumentException;
import hotelsoftware.controller.data.invoice.InvoiceItemData;
import hotelsoftware.model.domain.invoice.InvoiceItem;
import hotelsoftware.model.domain.parties.PartyFacade;
import hotelsoftware.model.domain.parties.PrivateCustomer;
import hotelsoftware.model.domain.service.Habitation;
import hotelsoftware.support.GuestNotFoundException;
import hotelsoftware.support.PrivateCustomerNotFoundException;
import hotelsoftware.util.HelperFunctions;
import hotelsoftware.util.RoomanizerProperties;
import java.awt.GraphicsEnvironment;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Collection;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author mohi
 */
public class PdfgenerateTest extends JFrame implements PDFObserver
{
    public PdfgenerateTest()
    {
    }

    public void start()
    {
        try
        {
            Collection<Habitation> habs = Habitation.searchHabitations(null, null, 201);
            Collection<InvoiceItem> items = habs.iterator().next().getInvoiceItems();
            PrivateCustomer customer = PartyFacade.getInstance().getPrivateCustomerByName("Otto", "von Schirach");
            
            PdfGenerator generator = new PdfGenerator(customer, "i07051200000001", HelperFunctions.castCollectionUp(items, InvoiceItemData.class, InvoiceItem.class), new Date(), new Date());
            JPanel generatePDFPanel = generator.generatePDFPanel();
            
            this.getContentPane().add(generatePDFPanel);
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
        this.getContentPane().add(pdfPanel);
    }

    @Override
    public void gererationFinished(boolean done)
    {
        System.out.println("got something");
    }

    public static void main(String args[])
    {
        try
        {
            RoomanizerProperties.setPropertiesFromConfig();
            PdfgenerateTest test = new PdfgenerateTest();
            test.start();
            
            GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
            test.setMaximizedBounds(env.getMaximumWindowBounds());
            test.setExtendedState(test.getExtendedState()
                    | JFrame.MAXIMIZED_BOTH);
            test.setMinimumSize(env.getMaximumWindowBounds().getSize());

            test.setVisible(true);
        }
        catch (IOException ex)
        {
            Logger.getLogger(PdfgenerateTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
