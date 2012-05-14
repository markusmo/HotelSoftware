package hotelsoftware.gui.invoice;

import hotelsoftware.controller.createinvoice.CreateInvoiceController;
import hotelsoftware.controller.data.invoice.InvoiceItemData;
import hotelsoftware.controller.data.parties.CountryData;
import hotelsoftware.controller.data.parties.GuestData;
import hotelsoftware.controller.data.parties.PartyData;
import hotelsoftware.controller.data.service.HabitationData;
import hotelsoftware.gui.invoice.customerSelection.addCustomer;
import hotelsoftware.gui.invoice.intermediatInvoice.IntermediatInvoicePanel;
import hotelsoftware.gui.invoice.invoiceHome.InvoiceHome;
import hotelsoftware.gui.invoice.payment.ControlsSetter;
import hotelsoftware.gui.invoice.payment.PaymentPanel;
import hotelsoftware.gui.invoice.splitCancel.splitNstornoPanel;
import hotelsoftware.model.domain.invoice.Invoice;
import hotelsoftware.util.HelperFunctions;
import hotelsoftware.util.pdf.PdfGenerator;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import javax.swing.*;

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
    // Bezeichnungen
    private final String invoiceHome = "Invoice Home";
    private final String intermediatInvoice = "Intermediat Invoice";
    private final String chooseCustomer = "Customer Selection";
    private final String splitCancel = "Split/Cancel";
    private final String payment = "Payment";
    private final String seperator = ">";
    private final String abort = "Abort";
    private final String back = "Back";
    private final String payed = "Payed";

    private InvoiceGUIControler()
    {
        // init labels
        invoiceHomeLabel.setText(invoiceHome);
        intermediatInvoiceLabel.setText(intermediatInvoice);
        chooseCustomerLabel.setText(chooseCustomer);
        splitCancelLabel.setText(splitCancel);
        paymentLabel.setText(payment);
        seperatorLabel.setText(seperator);

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
                        ctrl.next();
                        setContentPanel(getIntermediatInvoicePanel());
                    }
                    else
                    {
                        if (text.equals(splitCancel))
                        {
                            ctrl.splitInvoice();
                            setContentPanel(new splitNstornoPanel());
                        }
                        else
                        {
                            if (text.equals(chooseCustomer))
                            {
                                ctrl.next();
                                setContentPanel(new addCustomer());
                            }
                            else
                            {
//                                if (text.equals(payment))
//                                {
//                                    ctrl.next();
//                                    setContentPanel(getPaymentPanel());
//                                }
//                                else
//                                {
                                if (text.equals((payed)))
                                {
                                    ctrl.pay();
                                    //TODO ask for new State (Zwischenrechnung oder Ende)
                                }
//                                } 
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

    public String getIntermediatInvoiceString()
    {
        return intermediatInvoice;
    }

    public String getChooseCustomerString()
    {
        return chooseCustomer;
    }

    public String getSplitCancelString()
    {
        return splitCancel;
    }

    public String getPayedString()
    {
        return payed;
    }

    public String getBackString()
    {
        return back;
    }

    public String getAbortString()
    {
        return abort;
    }

    public void repaintControlPanel()
    {
        getControlPanel().repaint();
    }

    public void clearControlPanel()
    {
        getConstructiveControlPanel().removeAll();
        getDeconstructiveControlPanel().removeAll();
    }

    public void setSelectedHabitations(Collection<HabitationData> selectedRows)
    {
        ctrl.selectHabitations(selectedRows);
    }

    public boolean cancelItems(InvoiceItemData iid, int amount)
    {
        throw new UnsupportedOperationException("Not yet implemented");
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

    public JPanel getConstructiveControlPanel()
    {
        return main.getConstructiveControlPanel();
    }

    public JPanel getDeconstructiveControlPanel()
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

        // setze die Controls
        if (newcontent instanceof ControlsSetter)
        {
            ControlsSetter setter = (ControlsSetter) newcontent;
            setter.setControls();
        }
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

    private void abort(ActionEvent e)
    {
        ctrl.clear();
        // FIXME wenn items noch offen sind, meldung dementsprechend anpassen
        JPanel panel = getContentPanel();

        JOptionPane.showMessageDialog(panel, "Do you really want to abort?", "Abort", JOptionPane.WARNING_MESSAGE);
    }

    private void back(ActionEvent e)
    {
        ctrl.back();
        JPanel contentPanel = getContentPanel();
        if (contentPanel.getLayout() instanceof CardLayout)
        {
            CardLayout layout = (CardLayout) contentPanel.getLayout();
            layout.previous(getContentPanel());
            JPanel current = getCurrentPanel(contentPanel);
            setNavigation(current.getClass());
            // setze die Controls
            if (current instanceof ControlsSetter)
            {
                ControlsSetter setter = (ControlsSetter) current;
                setter.setControls();
            }
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
        ctrl.next();
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
        PdfGenerator generator = new PdfGenerator(ctrl.getCustomerData(), HelperFunctions.getNewContinousNumber(Invoice.class), ctrl.getChosenItems(), new Date(), new Date());
        PaymentPanel generatePDFPanel = new PaymentPanel();
        generatePDFPanel.add(generator.generatePaymentPanel());
        return generatePDFPanel;
    }

    private IntermediatInvoicePanel getIntermediatInvoicePanel()
    {
        PdfGenerator generator = new PdfGenerator(ctrl.getChosenItems(), new Date());
        IntermediatInvoicePanel generatePDFPanel = new IntermediatInvoicePanel();
        generatePDFPanel.add(generator.generateIntermediatPanel());
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

    public JPanel getInvoiceMainPanel()
    {
        return main;
    }

    public void selectItems(Map<InvoiceItemData, Integer> items)
    {
        CreateInvoiceController.getInstance().selectItems(items);
    }
}
