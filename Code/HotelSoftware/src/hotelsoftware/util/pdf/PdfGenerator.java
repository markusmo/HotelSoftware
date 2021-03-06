package hotelsoftware.util.pdf;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import hotelsoftware.model.domain.invoice.Invoice;
import hotelsoftware.model.domain.invoice.InvoiceItem;
import hotelsoftware.model.domain.parties.Customer;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;

/**
 * Dieses Objekt generiert PDFs. Der Pfad in dem diese gespeichert werden liegt
 * im Home-Verzeichnis des Users unter RoomanizerPDFs. Alle generierten PDFs
 * sollten die Schrift Times New Roman benutzen.
 *
 * @author Markus Mohanty <markus.mo at gmx.net>
 */
public class PdfGenerator
{

    /**
     * Dynamische Pfadgenerierung, zu dem Ort, an dem die PDFs gespeichert
     * werden
     */
    private static final String path = System.getProperty("user.home") + "/RoomanizerPDFs/";
    /**
     * Die Schrift, mit der der normale Text dargestellt wird
     */
    private static final Font normalfont = new Font(Font.TIMES_ROMAN, 12,
            Font.NORMAL);
    /**
     * Die Schrift, mit der alle Überschriften dargestellt werden
     */
    private static final Font bigfont = new Font(Font.TIMES_ROMAN, 16, Font.BOLD);

    /**
     * Konvertiert eine Rechung in ein PDF. Der Dateiname des PDFs wird mit der
     * Rechungsnummer erstellt.
     *
     * @param inv die Rechung für die das PDF generiert werden soll
     * @throws FileNotFoundException Wenn das File nicht erstellt werden kann
     * @throws DocumentException Wenn ein Fehler bei der Erstellung des PDFs
     * auftritt
     * @throws BadElementException Wenn ein Element des PDFs einen Fehler
     * aufweist
     * @throws MalformedURLException Wenn der Pfad des Logos einen Fehler
     * aufweist
     * @throws IOException Wenn das laden des Logos einen Fehler aufweist
     */
    public void generateInvoicePDF(Invoice inv) throws FileNotFoundException, DocumentException, BadElementException, MalformedURLException, IOException
    {
        Document doc = new Document(PageSize.A4);
        File temp = new File(path);
        if (!temp.exists())
        {
            temp.mkdir();
        }
        String invoicePath = path + "/" + inv.getInvoiceNumber() + ".pdf";
        //for writing... adds a writer to the document...
        PdfWriter.getInstance(doc, new FileOutputStream(
                invoicePath));
        doc.open();
        addMetaData(doc);
        addLogo(doc);
        addCustomer(doc, inv.getCustomer());
        addHotelAddress(doc);
        addInvoiceBody(doc, inv.getInvoiceNumber(), inv.getInvoiceItems(),
                inv.getTotalwithTax(), inv.getCreated(), inv.getExpiration());
        addThankyouMessage(doc);

        doc.close();
    }

    /**
     * Metadaten zum PDF hinzufügen
     *
     * @param doc das Document-Object zum erstellen des PDFs
     */
    private void addMetaData(Document doc)
    {
        doc.addTitle("Invoice");
        doc.addAuthor("Team E");
        doc.addSubject("Invoice");
    }

    /**
     * Logo in das PDF einfügen
     *
     * @param doc das Document-Object zum erstellen des PDFs
     * @throws BadElementException Wenn ein falsches Element zum Document
     * hinzugefügt wird
     * @throws MalformedURLException Wenn der Pfad zum Logo nicht stimmt
     * @throws IOException wenn das Laden des Logos nicht funktioniert
     * @throws DocumentException Wenn ein genereller Fehler im Document entsteht
     */
    private void addLogo(Document doc) throws BadElementException, MalformedURLException, IOException, DocumentException
    {
        Image image = Image.getInstance(PdfGenerator.class.getClassLoader().getResource(
                "resources/images/logo_pdf.jpg"));
        doc.add(image);
        Paragraph empty = new Paragraph();
        addEmptyLine(empty, 5);
        doc.add(empty);
    }

    /**
     * Hinzufügen des Kunden in die Rechung
     *
     * @param doc das Document-Object zum erstellen des PDFs
     * @param customer der Kunde, der die Rechung zahlt
     * @throws DocumentException Wenn ein genereller Fehler im Document entsteht
     */
    private void addCustomer(Document doc, Customer customer) throws DocumentException
    {
        Paragraph customerParagraph = new Paragraph();
        //TODO privatecustomer lname fname ...
        customerParagraph.add(new Paragraph("To:", normalfont));
        customerParagraph.add(new Paragraph(customer.getName(), normalfont));
        customerParagraph.add(new Paragraph(customer.getAddress().getStreet(),
                normalfont));
        customerParagraph.add(new Paragraph(
                customer.getAddress().getZip() + " " + customer.getAddress().getCity(),
                normalfont));
        customerParagraph.add(new Paragraph(
                customer.getAddress().getIdCountry().getName(), normalfont));
        addEmptyLine(customerParagraph, 2);
        doc.add(customerParagraph);
    }

    /**
     * Fügt die Adresse des Hotels zur hinzu. Diese werden aus den Properties
     * geladen. roomanizer.hotelname -> Hotelname roomanizer.street -> Straße
     * und Hausnummer des Hotels roomanizer.city -> Ort in dem das Hotel liegt
     * roomanizer.zip -> Postleitzahl des Ortes roomanizer.country -> Land in
     * dem der Ort liegt Siehe Klasse
     * <code>util.RoomanizerProperties</code>
     *
     * @param doc das Document-Object zum erstellen des PDFs
     * @throws DocumentException Wenn ein genereller Fehler im Document entsteht
     */
    private void addHotelAddress(Document doc) throws DocumentException
    {
        String name = System.getProperty("roomanizer.hotelname");
        String street = System.getProperty("roomanizer.street");
        String city = System.getProperty("roomanizer.city");
        String zip = System.getProperty("roomanizer.zip");
        String country = System.getProperty("roomanizer.country");

        Paragraph hotelParagraph = new Paragraph();
        hotelParagraph.add(new Paragraph("From:", normalfont));
        hotelParagraph.add(new Paragraph(name, normalfont));
        hotelParagraph.add(new Paragraph(street, normalfont));
        hotelParagraph.add(new Paragraph(street, normalfont));
        hotelParagraph.add(new Paragraph(zip + " " + city, normalfont));
        hotelParagraph.add(new Paragraph(country, normalfont));
        addEmptyLine(hotelParagraph, 4);
        doc.add(hotelParagraph);
    }

    /**
     * Fügt die Rechungsdetails zu der Rechung hinzu. Austellungsdatum,
     * Rechungsnummer, Fälligkeitsdatum Alle Positionen werden aufgelistet in 4
     * Spalten: Anzahl | Einzelpreis | Servicename | Preis Gesamtpreis der
     * Rechnung mit Taxen
     *
     * @param doc das Document-Object zum erstellen des PDFs
     * @param invoicenumber Die Rechungsnummer
     * @param items Rechungspositionen
     * @param totalamount der Gesamtbetrag der Rechnung
     * @param created das Ausstellungsdatum der Rechnung
     * @param expiration das Fälligkeitsdatum der Rechnung
     * @throws DocumentException Wenn ein genereller Fehler im Document entsteht
     */
    private void addInvoiceBody(Document doc, String invoicenumber,
            Collection<InvoiceItem> items, double totalamount,
            Date created, Date expiration) throws DocumentException
    {
        Paragraph invoiceParagraph = new Paragraph();
        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");

        //invoice creation date + invoice number
        invoiceParagraph.add(new Paragraph(
                "Creation date " + dateFormat.format(created), bigfont));
        invoiceParagraph.add(new Paragraph("Invoice due to " + dateFormat.format(
                expiration), bigfont));
        invoiceParagraph.add(new Paragraph("Invoice number: " + invoicenumber,
                bigfont));
        addEmptyLine(invoiceParagraph, 1);

        // Table has 4 colums:
        // amount | price per unit | servicename | price
        PdfPTable table = new PdfPTable(4);
        // add headers for the tablecolums
        PdfPCell cell = new PdfPCell(new Phrase("Amount"));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Price per unit"));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Service"));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Price"));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        //one header row
        table.setHeaderRows(1);

        DecimalFormat currencyFormat = (DecimalFormat) NumberFormat.getInstance(new Locale(
                "en_US"));
        DecimalFormatSymbols symbols = currencyFormat.getDecimalFormatSymbols();
        symbols.setGroupingSeparator(' ');
        symbols.setCurrency(Currency.getInstance("EUR"));

        for (InvoiceItem item : items)
        {
            table.addCell(item.getAmount() + "x");
            table.addCell(currencyFormat.format(item.getPriceWithTax()));
            //TODO service name still missing
            table.addCell("Service");
            table.addCell(currencyFormat.format(item.getTotalPriceWithTax()));
        }
        //TODO taxes 10% and 20% of bill, separation?
        table.addCell("");
        table.addCell("");
        table.addCell("Total");
        table.addCell(currencyFormat.format(totalamount));

        //add table to paragraph
        invoiceParagraph.add(table);
        //add paragraph to document
        doc.add(invoiceParagraph);
    }

    /**
     * Fügt die Dankes-Nachricht zum PDF hinzu
     *
     * @param doc das Document-Object zum erstellen des PDFs
     * @throws DocumentException Wenn ein genereller Fehler im Document entsteht
     */
    private void addThankyouMessage(Document doc) throws DocumentException
    {
        Paragraph thankyou = new Paragraph();
        addEmptyLine(thankyou, 6);
        String message = System.getProperty("roomanizer.thankyou");
        thankyou.add(new Paragraph(message, normalfont));
        doc.add(thankyou);
    }

    /**
     * Fügt leere Zeilen zum PDF hinzu
     *
     * @param paragraph Der Absatz in dem die leeren Zeilen eingefügt werden
     * @param number die Anzahl der leeren Zeilen
     */
    private void addEmptyLine(Paragraph paragraph, int number)
    {
        for (int i = 0; i < number; i++)
        {
            paragraph.add(new Paragraph(" "));
        }
    }
}
