package hotelsoftware.util.pdf;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import hotelsoftware.model.domain.invoice.Invoice;
import hotelsoftware.model.domain.parties.Address;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

/**
 *
 * @author Markus Mohanty <markus.mo at gmx.net>
 */
public class PdfGenerator
{
    private static final String path = generatePath();
    private static final Font normalfont = new Font(Font.TIMES_ROMAN,12,Font.NORMAL);
    private static final Font bigfont = new Font(Font.TIMES_ROMAN,16,Font.BOLD);

    private static String generatePath()
    {
        BufferedWriter writer;
        String pathtofile = System.getProperty("user.home") + "/RoomanizerPDFs/";
        return pathtofile;
    }
    
    public void generateInvoicePDF(Invoice inv) throws FileNotFoundException, DocumentException, BadElementException, MalformedURLException, IOException
    {
        Document doc = new Document(PageSize.A4);
        String invoicePath = path + "/" +inv.getInvoiceNumber() + ".pdf";
        PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(invoicePath));
        doc.open();
        addMetaData(doc);
        addLogo(doc);
        
        doc.close();
    }
    
    private void addMetaData(Document doc)
    {
        doc.addTitle("Invoice");
        doc.addAuthor("Team E");
        doc.addSubject("Invoice");
    }
    
    private void addLogo(Document doc) throws BadElementException, MalformedURLException, IOException, DocumentException
    {
        Image image = Image.getInstance(PdfGenerator.class.getClassLoader().getResource("resources/images/logo_pdf.jpg"));
        doc.add(image);
    }
    
    private void addAdresses(Address company, Address roomanizer){}
    {
        
    }
}
