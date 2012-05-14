package hotelsoftware.util.pdf;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import hotelsoftware.controller.data.invoice.InvoiceItemData;
import hotelsoftware.controller.data.parties.CustomerData;
import hotelsoftware.model.domain.invoice.InvoiceItem;
import hotelsoftware.model.domain.parties.Customer;
import hotelsoftware.util.HelperFunctions;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;

/**
 * Dieses Objekt generiert PDFs. Diese Klasse ist nur als Thread ausführbar, das heißt, die Methode generatePDF() wird im run() des implementierten Interfaces Runnable aufgerufen.
 * Klasse die diese Objekt benutzt muss das Interface Observer implementieren, damit sobald das PDF im Thread fertig generiert wurde, dieses Objekt merkt, dass das generieren fertig ist.
 * Der Pfad in dem diese gespeichert werden liegt im Home-Verzeichnis des Users unter RoomanizerPDFs.
 * Alle generierten PDFs sollten die Schrift Times New Roman benutzen.
 *
 * @author Markus Mohanty <markus.mo at gmx.net>
 */
public class PdfGenerator
{
    /**
     * für intermediate Invoice
     */
    private static int intermediate = 0;
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
    private static final Font bigfont = new Font(Font.TIMES_ROMAN, 14, Font.BOLD);
    private Customer customer;
    private String invoiceNumber;
    private Collection<InvoiceItem> items;
    private Date created;
    private Date expiration;
    private String invoicePath;

    /**
     * Neue Instanz der Klasse PDFGenerator für Rechnungen
     *
     * @param customer der Kunde, der die Rechung zahlt
     * @param invoiceNumber die eindeutige Rechungsnummer
     * @param items die Rechungspositionen
     * @param created das Kreierungsdatum
     * @param expiration das Fälligkeitsdatum
     */
    public PdfGenerator(CustomerData customer, String invoiceNumber, Collection<InvoiceItemData> items, Date created, Date expiration)
    {
        this.customer = (Customer) customer;
        this.invoiceNumber = invoiceNumber;
        this.items = HelperFunctions.castCollectionDown(items, InvoiceItemData.class, InvoiceItem.class);
        this.created = created;
        this.expiration = expiration;
    }

    /**
     * Neue Instanz der Klasse PDFGenerator für Zwischenrechnungen
     * @param items die Rechnungspositionen
     * @param created das Kreierungsdatum
     */
    public PdfGenerator(Collection<InvoiceItemData> items, Date created)
    {
        this.items = HelperFunctions.castCollectionDown(items, InvoiceItemData.class, InvoiceItem.class);
        this.created = created;
    }
    

    /**
     * Konvertiert eine Rechung in ein PDF. Der Dateiname des PDFs wird mit der
     * Rechungsnummer erstellt.
     *
     * @param customer der Kunde, der die Rechung zahlt, für Anschrift nötig
     * @param invoiceNumber die einzigartige Nummer, der Rechnung
     * @param items die Rechnungspositionen, die aufgelistet werden
     * @param created das Kreierungsdatum
     * @param expiration das Fälligkeitsdatum
     * @throws FileNotFoundException Wenn das File nicht erstellt werden kann
     * @throws DocumentException Wenn ein Fehler bei der Erstellung des PDFs
     * auftritt
     * @throws BadElementException Wenn ein Element des PDFs einen Fehler
     * aufweist
     * @throws MalformedURLException Wenn der Pfad des Logos einen Fehler
     * aufweist
     * @throws IOException Wenn das laden des Logos einen Fehler aufweist
     */
    private void generateInvoicePDFwithTax() throws FileNotFoundException, DocumentException, BadElementException, MalformedURLException, IOException
    {
        Document doc = new Document(PageSize.A4);
        File temp = new File(path);
        if (!temp.exists())
        {
            temp.mkdir();
        }
        this.invoicePath = path + invoiceNumber + ".pdf";
        PdfWriter.getInstance(doc, new FileOutputStream(
                invoicePath));
        doc.open();
        addMetaData(doc);
        addLogo(doc);
        addCustomer(doc, customer);
        addHotelAddress(doc);
        addInvoiceBodyWithTax(doc, invoiceNumber, items,
                getTotalwithoutTax(items), created, expiration);
        addThankyouMessage(doc);
        
        doc.close();
    }

    private void generateInvoicePDFwithoutTax() throws FileNotFoundException, DocumentException, MalformedURLException, BadElementException, IOException
    {
        Document doc = new Document(PageSize.A4);
        File temp = new File(path);
        if (!temp.exists())
        {
            temp.mkdir();
        }
        Random rand = new Random();
        this.invoicePath = path + intermediate+rand.nextInt() + "_intermediate" + ".pdf";
        intermediate++;
        PdfWriter.getInstance(doc, new FileOutputStream(
                invoicePath));
        doc.open();
        addMetaData(doc);
        addInvoiceBodyWithoutTax(doc, invoiceNumber, items,
                getTotalwithTax(items), created, expiration);

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
        URL url = PdfGenerator.class.getClassLoader().getResource(
                "resources/images/logo_pdf.jpg");
        Image image = Image.getInstance(url);
        image.scalePercent(15);
        doc.add(image);
        Paragraph empty = new Paragraph();
        addEmptyLine(empty, 2);
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
        hotelParagraph.add(new Paragraph(zip + " " + city, normalfont));
        hotelParagraph.add(new Paragraph(country, normalfont));
        addEmptyLine(hotelParagraph, 2);
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
    private void addInvoiceBodyWithTax(Document doc, String invoicenumber,
            Collection<InvoiceItem> items, double totalamount,
            Date created, Date expiration) throws DocumentException
    {
        double percent10Total = get10PercentTotal(items);
        double percent20Total = get20PercentTotal(items);
        Paragraph invoiceParagraph = new Paragraph();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

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

        DecimalFormat currencyFormat = (DecimalFormat) NumberFormat.getCurrencyInstance(Locale.GERMANY);
        DecimalFormatSymbols symbols = currencyFormat.getDecimalFormatSymbols();
        symbols.setGroupingSeparator(' ');
        symbols.setDecimalSeparator(',');
        currencyFormat.setMinimumFractionDigits(2);
        currencyFormat.setDecimalFormatSymbols(symbols);

        for (InvoiceItem item : items)
        {
            cell = new PdfPCell(new Phrase(item.getAmount() + "x"));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(currencyFormat.format(item.getPriceWithTax())));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(item.getService().getServiceName()));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(currencyFormat.format(item.getTotalPriceWithTax())));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);
        }

        //total amount
        table.addCell("");
        table.addCell("");
        table.addCell("Total");
        cell = new PdfPCell(new Phrase(currencyFormat.format(totalamount)));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        table.addCell(cell);

        //10 percent sales tax
        table.addCell("");
        table.addCell("");
        table.addCell("sales tax 10%");
        cell = new PdfPCell(new Phrase(currencyFormat.format(percent10Total)));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        table.addCell(cell);

        //20 percent sales tax
        table.addCell("");
        table.addCell("");
        table.addCell("sales tax 20%");
        cell = new PdfPCell(new Phrase(currencyFormat.format(percent20Total)));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        table.addCell(cell);
        //add table to paragraph
        invoiceParagraph.add(table);
        //add paragraph to document
        doc.add(invoiceParagraph);
    }

    /**
     * Fügt die Rechungsdetails zu der Rechung hinzu. Austellungsdatum,
     * Rechungsnummer, Fälligkeitsdatum Alle Positionen werden aufgelistet in 4
     * Spalten: Anzahl | Einzelpreis | Servicename | Preis Gesamtpreis der
     * Rechnung ohne Taxen
     *
     * @param doc das Document-Object zum erstellen des PDFs
     * @param invoiceNumber Die Rechungsnummer
     * @param items Rechungspositionen
     * @param totalamount der Gesamtbetrag der Rechnung
     * @param created das Ausstellungsdatum der Rechnung
     * @param expiration das Fälligkeitsdatum der Rechnung
     * @throws DocumentException Wenn ein genereller Fehler im Document entsteht
     */
    private void addInvoiceBodyWithoutTax(Document doc, String invoiceNumber, Collection<InvoiceItem> items, double totalamount, Date created, Date expiration) throws DocumentException
    {
        Paragraph invoiceParagraph = new Paragraph();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        //invoice creation date + invoice number
        invoiceParagraph.add(new Paragraph(
                "Creation date " + dateFormat.format(created), bigfont));
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

        DecimalFormat currencyFormat = (DecimalFormat) NumberFormat.getCurrencyInstance(Locale.GERMANY);
        DecimalFormatSymbols symbols = currencyFormat.getDecimalFormatSymbols();
        symbols.setGroupingSeparator(' ');
        symbols.setDecimalSeparator(',');
        currencyFormat.setMinimumFractionDigits(2);
        currencyFormat.setDecimalFormatSymbols(symbols);

        for (InvoiceItem item : items)
        {
            cell = new PdfPCell(new Phrase(item.getAmount() + "x"));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(currencyFormat.format(item.getPriceWithoutTax())));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(item.getService().getServiceName()));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(currencyFormat.format(item.getTotalPriceWithoutTax())));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);
        }

        //total amount
        table.addCell("");
        table.addCell("");
        table.addCell("Total");
        cell = new PdfPCell(new Phrase(currencyFormat.format(totalamount)));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        table.addCell(cell);

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

    /**
     * Rechnet die Gesammtsumme einer Rechnung aus
     *
     * @param items die Rechnungspositionen, mit der eine Rechnung generiert werden
     * @return
     * die Gesammtsumme einer Rechnung
     */
    private double getTotalwithTax(Collection<InvoiceItem> items)
    {
        double total = 0;
        for (InvoiceItem item : items)
        {
            total = total + item.getTotalPriceWithTax();
        }
        return total;
    }

    /**
     * Rechnet die Gesammtsumme einer Rechung aus, ohne Steuern
     *
     * @param items die Rechnungspositionen, mit der eine Rechung generiert werden
     * @return die Gesammtsumme einer Rechung ohne Steuern
     */
    private double getTotalwithoutTax(Collection<InvoiceItem> items)
    {
        double total = 0;
        for (InvoiceItem item : items)
        {
            total = total + item.getTotalPriceWithoutTax();
        }
        return total;
    }

    private double get20PercentTax(double total)
    {
        return total / 0.2;
    }

    private double get10PercentTax(double total)
    {
        return total / 0.1;
    }

    /**
     * Rechnet den Totalbetrag für alle Services mit 20% Mehrwertssteuer
     *
     * @param items alle Rechnungspositionen
     * @return der Totalbetrag aller Services mit 20% Mehrwertsteuer
     */
    private double get20PercentTotal(Collection<InvoiceItem> items)
    {
        double total = 0;

        for (InvoiceItem item : items)
        {
            if (item.getService().getServiceType().getTaxRate().doubleValue() == 20.0)
            {
                total = total + item.getTotalPriceWithTax();
            }
        }

        return total;
    }

    /**
     * Rechnet den Totalbetrag für alle Services mit 10% Mehrwertssteuer
     *
     * @param items alle Rechnungspositionen
     * @return der Totalbetrag aller Services mit 10% Mehrwertsteuer
     */
    private double get10PercentTotal(Collection<InvoiceItem> items)
    {
        double total = 0;

        for (InvoiceItem item : items)
        {
            if (item.getService().getServiceType().getTaxRate().doubleValue() == 10.0)
            {
                total = total + item.getTotalPriceWithTax();
            }
        }

        return total;
    }

    /**
     * Generiert ein JPanel mit einem PDFViewer für eine Rechung. Wenn das generieren fehlschlägt, gibt es ein JPanel aus
     * welches eine Fehlermeldung enthält
     * 
     * @return ein JPanel mit einem PDFViewer
     */
    public JPanel generatePaymentPanel()
    {
        JPanel viewerComponentPanel = new JPanel();
        try
        {
            generateInvoicePDFwithTax();
            SwingController controller = new SwingController();
            SwingViewBuilder factory = new SwingViewBuilder(controller);
            viewerComponentPanel = factory.buildViewerPanel();
            controller.openDocument(this.invoicePath);
            return viewerComponentPanel;
        }
        catch (Exception ex)
        {
            JLabel label = new JLabel("Error occurred generating PDF file");
            viewerComponentPanel.add(label);
            Logger.getLogger(PdfGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            return viewerComponentPanel;
        }
    }

    /**
     * Generiert ein JPanel mit einem PDFViewer für eine Zwischenrechnung. Wenn das generieren fehlschlägt, gibt es ein JPanel aus
     * welches eine Fehlermeldung enthält.
     * 
     * @return ein JPanel mit einem PDFViewer
     */
    public JPanel generateIntermediatPanel()
    {
        JPanel viewerComponentPanel = new JPanel();
        try
        {
            generateInvoicePDFwithoutTax();
            SwingController controller = new SwingController();
            SwingViewBuilder factory = new SwingViewBuilder(controller);
            viewerComponentPanel = factory.buildViewerPanel();
            controller.openDocument(this.invoicePath);
            return viewerComponentPanel;
        }
        catch (Exception ex)
        {
            JLabel label = new JLabel("Error occurred generating PDF file");
            viewerComponentPanel.add(label);
            Logger.getLogger(PdfGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            return viewerComponentPanel;
        }
    }
}
