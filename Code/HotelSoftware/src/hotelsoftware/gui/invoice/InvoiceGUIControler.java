package hotelsoftware.gui.invoice;

import hotelsoftware.controller.createinvoice.CreateInvoiceController;
import hotelsoftware.controller.data.parties.CountryData;
import hotelsoftware.controller.data.parties.GuestData;
import hotelsoftware.controller.data.parties.PartyData;
import hotelsoftware.controller.data.room.RoomData;
import hotelsoftware.controller.data.service.HabitationData;
import hotelsoftware.gui.invoice.home.InvoiceHome;
import hotelsoftware.gui.invoice.subpanels.addCustomer;
import hotelsoftware.model.domain.invoice.Invoice;
import hotelsoftware.util.HelperFunctions;
import hotelsoftware.util.pdf.PdfGenerator;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public final class InvoiceGUIControler implements ActionListener
{
    private CreateInvoiceController ctrl = CreateInvoiceController.getInstance();
    private InvoiceMain main;
    //Navigation labels
    private JLabel invoiceHomeLabel = new JLabel();
    private JLabel intermediatInvoiceLabel = new JLabel();
    private JLabel chooseCustomerLabel = new JLabel();
    private JLabel splitCancelLabel = new JLabel();
    private JLabel paymentLabel = new JLabel();
    private JLabel seperatorLabel = new JLabel();
    // Controls
    private JButton abortButton = new JButton();
    private JButton backButton = new JButton();
    private JButton intermediatInvoiceButton = new JButton();
    private JButton chooseCustomerButton = new JButton();
    private JButton splitCancelButton = new JButton();
    private JButton payedButton = new JButton();
    private String invoiceHome = "Invoice Home";
    private String intermediatInvoice = "Intermediat Invoice";
    private String chooseCustomer = "Customer Selection";
    private String splitCancel = "Split/Cancel";
    private String payment = "Payment";
    private String seperator = ">";
    private String abort = "Abort";
    private String back = "Back";
    private String payed = "Payed";

    private InvoiceGUIControler()
    {
        // init labels
        invoiceHomeLabel.setText(invoiceHome);
        intermediatInvoiceLabel.setText(intermediatInvoice);
        chooseCustomerLabel.setText(chooseCustomer);
        splitCancelLabel.setText(splitCancel);
        paymentLabel.setText(payment);
        seperatorLabel.setText(seperator);

        // init controls
        abortButton.setText(abort);
        abortButton.addActionListener(this);

        backButton.setText(back);
        backButton.addActionListener(this);

        intermediatInvoiceButton.setText(intermediatInvoice);
        intermediatInvoiceButton.addActionListener(this);

        chooseCustomerButton.setText(chooseCustomer);
        chooseCustomerButton.addActionListener(this);

        splitCancelButton.setText(splitCancel);
        splitCancelButton.addActionListener(this);

        payedButton.setText(payed);
        payedButton.addActionListener(this);
    }

    public static InvoiceGUIControler getInstance()
    {
        return invoiceGUIControlerHolder.INSTANCE;
    }

    void setMain(JPanel contentPanel)
    {
        main = (InvoiceMain) contentPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() instanceof JButton)
        {
            JButton button = (JButton) e.getSource();
            String text = button.getText();

            if (text.equals(abort))
            {
                abort(e);
            }
            else
            {
                if (text.equals(back))
                {
                    back(e);
                }
                else
                {
                    if (text.equals(intermediatInvoice))
                    {
                        setContentPanel(getIntermediatInvoicePanel());
                    }
                    else
                    {
                        if (text.equals(splitCancel))
                        {
                            setContentPanel(new splitNstornoPanel());
                        }
                        else
                        {
                            if (text.equals(chooseCustomer))
                            {
                                setContentPanel(new addCustomer());
                            }
                            else
                            {
                                if (text.equals(payment))
                                {
                                    setContentPanel(getPaymentPanel());
                                }
                                else
                                {
                                    if (text.equals((payed)))
                                    {
                                        ctrl.pay();
                                        //TODO ask for new State (Zwischenrechnung oder Ende)
                                    }
                                } 
                            }
                        }
                    }
                }
            }
        }
    }

    public Collection<CountryData> getAllCountries()
    {
        return CreateInvoiceController.getInstance().getAllCountries();
    }

    private static class invoiceGUIControlerHolder
    {
        private static final InvoiceGUIControler INSTANCE = new InvoiceGUIControler();
    }

    private JPanel getContentPanel()
    {
        return main.getContentPanel();
    }

    private JPanel getNavigationPanel()
    {
        return main.getNavigationPanel();
    }

    private JPanel getConstructiveControlPanel()
    {
        return main.getConstructiveControlPanel();
    }

    private JPanel getDeconstructiveControlPanel()
    {
        return main.getDeconstuctiveControlPanel();
    }

    private JPanel getControlPanel()
    {
        return main.getControlPanel();
    }

    public void setContentPanel(JPanel newcontent)
    {
        JPanel contentPanel = getContentPanel();
        contentPanel.add(newcontent);

        if (contentPanel.getLayout() instanceof CardLayout)
        {
            CardLayout layout = (CardLayout) contentPanel.getLayout();
            layout.next(contentPanel);
        }

        setNavigation(newcontent.getClass());
        setControls(newcontent.getClass());

        contentPanel.repaint();

    }

    private void setNavigation(Class clazz)
    {
        JPanel navigation = getNavigationPanel();
        navigation.removeAll();

        if (clazz.equals(splitNstornoPanel.class))
        {
            navigation.add(invoiceHomeLabel);
            navigation.add(seperatorLabel);
            navigation.add(intermediatInvoiceLabel);
            navigation.add(seperatorLabel);
            navigation.add(splitCancelLabel);
        }
        else
        {
            if (clazz.equals(InvoiceHome.class))
            {
                navigation.add(invoiceHomeLabel);
            }
            else
            {
                if (clazz.equals(IntermediatInvoicePanel.class))
                {
                    navigation.add(invoiceHomeLabel);
                    navigation.add(seperatorLabel);
                    navigation.add(intermediatInvoiceLabel);
                }
                else
                {
                    if (clazz.equals(addCustomer.class))
                    {
                        navigation.add(invoiceHomeLabel);
                        navigation.add(seperatorLabel);
                        navigation.add(intermediatInvoiceLabel);
                        navigation.add(seperatorLabel);
                        navigation.add(chooseCustomerLabel);
                    }
                    else
                    {
                        if (clazz.equals(PaymentPanel.class))
                        {
                            navigation.add(invoiceHomeLabel);
                            navigation.add(seperatorLabel);
                            navigation.add(intermediatInvoiceLabel);
                            navigation.add(seperatorLabel);
                            navigation.add(chooseCustomerLabel);
                            navigation.add(seperatorLabel);
                            navigation.add(paymentLabel);
                        }
                    }
                }
            }
        }

        navigation.repaint();
    }

    private void setControls(Class clazz)
    {
        JPanel deconstructive = getDeconstructiveControlPanel();
        deconstructive.removeAll();
        deconstructive.add(abortButton);


        JPanel constructive = getConstructiveControlPanel();
        constructive.removeAll();

        if (clazz.equals(InvoiceHome.class))
        {
            constructive.add(intermediatInvoiceButton);
        }
        else
        {
            if (clazz.equals(IntermediatInvoicePanel.class))
            {
                constructive.add(splitCancelButton);
                constructive.add(chooseCustomerButton);
                deconstructive.add(backButton);
            }
            else
            {
                if (clazz.equals(addCustomer.class))
                {
                    deconstructive.add(backButton);
                }
                else
                {
                    if (clazz.equals(splitNstornoPanel.class))
                    {
                        deconstructive.add(backButton);
                        constructive.add(intermediatInvoiceButton);
                    }
                    else
                    {
                        if (clazz.equals(PaymentPanel.class))
                        {
                            deconstructive.add(backButton);
                            constructive.add(payedButton);
                        }
                    }
                }
            }
        }
        getControlPanel().repaint();
    }

    private void abort(ActionEvent e)
    {
        //ctrl.abort();
        // FIXME wenn items noch offen sind, meldung dementsprechend anpassen
        JPanel panel = getContentPanel();

        JOptionPane.showMessageDialog(panel, "Do you really want to abort?", "Abort", JOptionPane.WARNING_MESSAGE);
    }

    private void back(ActionEvent e)
    {
        // FIXME decomment 
        //ctrl.back();   
        JPanel contentPanel = getContentPanel();
        if (contentPanel.getLayout() instanceof CardLayout)
        {
            CardLayout layout = (CardLayout) contentPanel.getLayout();
            layout.previous(getContentPanel());
            JPanel current = getCurrentPanel(contentPanel);
            setNavigation(current.getClass());
            setControls(current.getClass());
        }


        contentPanel.repaint();
    }

    public Collection<HabitationData> search(String firstName, String lastName, String roomNr)
    {
        return ctrl.search(firstName, lastName, roomNr);
    }

    public JPanel getCurrentPanel(JPanel currentPanel)
    {

        for (Component component : currentPanel.getComponents())
        {
            if (component.isVisible())
            {
                if (component instanceof JPanel)
                {
                    currentPanel = (JPanel) component;
                }
//                else if (component instanceof JScrollPane)
//                    currentPanel = (JPanel) ((JScrollPane) component).getViewport().getComponent(0);
            }
        }

        return currentPanel;
    }

    public void setPaymentPanel()
    {
        setContentPanel(getPaymentPanel());
    }

    public void setIntermediatInvoicePanel()
    {
        setContentPanel(getIntermediatInvoicePanel());
    }

    /**
     *
     * @return
     */
    private PaymentPanel getPaymentPanel()
    {
        // FIXME set expireData
        PdfGenerator generator = new PdfGenerator(ctrl.getCustomerData(), HelperFunctions.getNewContinousNumber(Invoice.class), ctrl.getChosenItems(), new Date(), new Date());
        PaymentPanel generatePDFPanel = (PaymentPanel) generator.generatePaymentPanel();
        return generatePDFPanel;
    }

    private IntermediatInvoicePanel getIntermediatInvoicePanel()
    {
        // FIXME set expireData
        PdfGenerator generator = new PdfGenerator(ctrl.getChosenItems(), new Date());
        IntermediatInvoicePanel generatePDFPanel = (IntermediatInvoicePanel) generator.generateIntermediatPanel();
        return generatePDFPanel;
    }

    public void useGuestAsCustomer(GuestData guest)
    {
        CreateInvoiceController.getInstance().useGuestAsCustomer(guest);
    }

    /**
     * Erstellt einen neuen Kunden in Form eines Unternehmens mit gleicher Rechnungs- und Postanschrift
     *
     * @param companyName Der Name der Firma
     * @param street Postanschrift: die Straße
     * @param city Postanschrift: der Ort/die Stadt
     * @param zip Postanschrift: die Postleitzahl
     * @param email Postanschrift: die E-Mail Adresse
     * @param phone Postanschrift: die Telefonnummer
     * @param fax Postanschrift: die Fax-Nummer
     * @param country Postanschrift: das Land
     */
    public void createCompanyCustomer(String companyName, String street, String city, String zip, String email, String phone, String fax, CountryData country)
    {
        createCompanyCustomer(companyName, street, city, zip, email, phone, fax, country, street, city, zip, email, phone, fax, country);
    }

    /**
     * Erstellt einen neuen Kunden in Form eines Unternehmens
     *
     * @param companyName Der Name der Firma
     * @param street Postanschrift: die Straße
     * @param city Postanschrift: der Ort/die Stadt
     * @param zip Postanschrift: die Postleitzahl
     * @param email Postanschrift: die E-Mail Adresse
     * @param phone Postanschrift: die Telefonnummer
     * @param fax Postanschrift: die Fax-Nummer
     * @param country Postanschrift: das Land
     * @param invoiceStreet Rechnungsanschrift: die Straße
     * @param invoiceCity Rechnungsanschrift: der Ort/die Stadt
     * @param invoiceZip Rechnungsanschrift: die Postleitzahl
     * @param invoiceEmail Rechnungsanschrift: die E-Mail Adresse
     * @param invoicePhone Rechnungsanschrift: die Telefonnummer
     * @param invoiceFax Rechnungsanschrift: die Fax-Nummer
     * @param invoiceCountry Rechnungsanschrift: das Land
     */
    public void createCompanyCustomer(String companyName, String street, String city, String zip, String email, String phone, String fax, CountryData country,
            String invoiceStreet, String invoiceCity, String invoiceZip, String invoiceEmail, String invoicePhone, String invoiceFax, CountryData invoiceCountry)
    {
        CreateInvoiceController.getInstance().createCompanyCustomer(companyName, street, city, zip, email, phone, fax, country, invoiceStreet,
                invoiceCity, invoiceZip, invoiceEmail, invoicePhone, invoiceFax, invoiceCountry);
    }

    /**
     * Erstellt einen neuen Kunden in Form einer realen Person mit gleicher Rechnungs- und Postanschrift
     *
     * @param firstName Der Vorname des Kunden
     * @param lastName Der Nachname des Kunden
     * @param street Postanschrift: die Straße
     * @param city Postanschrift: der Ort/die Stadt
     * @param zip Postanschrift: die Postleitzahl
     * @param email Postanschrift: die E-Mail Adresse
     * @param phone Postanschrift: die Telefonnummer
     * @param fax Postanschrift: die Fax-Nummer
     * @param country Postanschrift: das Land
     */
    public void createPrivateCustomer(String firstName, String lastName, String street, String city, String zip, String email, String phone, String fax, CountryData country)
    {
        createPrivateCustomer(firstName, lastName, street, city, zip, email, phone, fax, country, street, city, zip, email, phone, fax, country);
    }

    /**
     * Erstellt einen neuen Kunden in Form einer realen Person
     *
     * @param firstName Der Vorname des Kunden
     * @param lastName Der Nachname des Kunden
     * @param street Postanschrift: die Straße
     * @param city Postanschrift: der Ort/die Stadt
     * @param zip Postanschrift: die Postleitzahl
     * @param email Postanschrift: die E-Mail Adresse
     * @param phone Postanschrift: die Telefonnummer
     * @param fax Postanschrift: die Fax-Nummer
     * @param country Postanschrift: das Land
     * @param invoiceStreet Rechnungsanschrift: die Straße
     * @param invoiceCity Rechnungsanschrift: der Ort/die Stadt
     * @param invoiceZip Rechnungsanschrift: die Postleitzahl
     * @param invoiceEmail Rechnungsanschrift: die E-Mail Adresse
     * @param invoicePhone Rechnungsanschrift: die Telefonnummer
     * @param invoiceFax Rechnungsanschrift: die Fax-Nummer
     * @param invoiceCountry Rechnungsanschrift: das Land
     */
    public void createPrivateCustomer(String firstName, String lastName, String street, String city, String zip, String email, String phone, String fax, CountryData country,
            String invoiceStreet, String invoiceCity, String invoiceZip, String invoiceEmail, String invoicePhone, String invoiceFax, CountryData invoiceCountry)
    {
        CreateInvoiceController.getInstance().createPrivateCustomer(firstName, lastName, street, city, zip, email, phone, fax, country, invoiceStreet,
                invoiceCity, invoiceZip, invoiceEmail, invoicePhone, invoiceFax, invoiceCountry);
    }

    /**
     * Gibt die Parteien zurück, welche mit den gewählten Aufenthalten in Verbindung stehen
     *
     * @return Eine Collection von Parteien
     */
    public Collection<PartyData> getWorkingHabitationsGuests()
    {
        return CreateInvoiceController.getInstance().getWorkingHabitationsGuests();
    }

     public Collection<HabitationData> getSelectedHabitations()
    {
        return CreateInvoiceController.getInstance().getSelectedHabitations();
    }
}
