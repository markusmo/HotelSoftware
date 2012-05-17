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
import hotelsoftware.gui.invoice.labels.SeperatorLabel;
import hotelsoftware.gui.invoice.payment.PaymentPanel;
import hotelsoftware.gui.invoice.splitCancel.splitNstornoPanel;
import hotelsoftware.model.domain.invoice.Invoice;
import hotelsoftware.util.HelperFunctions;
import hotelsoftware.util.pdf.PdfGenerator;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 *
 * diese Klasse übernimmt die Abhandlung des Use cases "Rechnung erstellen" auf GUI-Ebene
 */
public final class InvoiceGUIControler implements ActionListener
{
    /**
     * Use case Controller auf der Controller-Ebene
     */
    private CreateInvoiceController ctrl = CreateInvoiceController.getInstance();
    /**
     * Hauptpanel, in dem die Navigation, die Controls und der Content (Screens für einzelne Schritte) untergebracht sind
     */
    private InvoiceMain main;
    //Navigation labels
    private JLabel invoiceHomeLabel = new JLabel();
    private JLabel intermediatInvoiceLabel = new JLabel();
    private JLabel chooseCustomerLabel = new JLabel();
    private JLabel splitCancelLabel = new JLabel();
    private JLabel paymentLabel = new JLabel();
    // Bezeichnungen
    private static final String invoiceHome = "Invoice Home";
    private static final String intermediatInvoice = "Intermediat Invoice";
    private static final String chooseCustomer = "Customer Selection";
    private static final String splitCancel = "Split/Cancel";
    private static final String payment = "Payment";
    private static final String seperator = ">";
    private static final String abort = "Abort";
    private static final String back = "Back";
    private static final String payed = "Payed";

    private InvoiceGUIControler()
    {
        // init labels
        invoiceHomeLabel.setText(invoiceHome);
        intermediatInvoiceLabel.setText(intermediatInvoice);
        chooseCustomerLabel.setText(chooseCustomer);
        splitCancelLabel.setText(splitCancel);
        paymentLabel.setText(payment);
    }

    public static InvoiceGUIControler getInstance()
    {
        return invoiceGUIControlerHolder.INSTANCE;
    }

    /**
     * setzten des Hauptpanels
     *
     * @param contentPanel
     */
    void setMain(InvoiceMain contentPanel)
    {
        main = contentPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() instanceof JButton)
        {
            JButton button = (JButton) e.getSource();
            String text = button.getText();

            if (text.equals(abort)) // abort Button
            {
                abort(e);
            }
            else
            {
                if (text.equals(back)) // back Button
                {
                    back(e);
                }
                else
                {
                    if (text.equals(intermediatInvoice)) //intermediatInvoice Button
                    {
                        ctrl.next();
                        setContentPanel(getIntermediatInvoicePanel());
                    }
                    else
                    {
                        if (text.equals(splitCancel)) // splitCancel Button 
                        {
                            ctrl.splitInvoice();
                            setContentPanel(new splitNstornoPanel());
                        }
                        else
                        {
                            if (text.equals(chooseCustomer)) // chooseCustomer Button
                            {
                                ctrl.next();
                                setContentPanel(new addCustomer());
                            }
                            else
                            {
                                if (text.equals((payed)))
                                {
                                    ctrl.pay();
                                    if (ctrl.isInSwitchingState()) // frage nach neuem Zustand (Zwischenrechnung oder Ende)
                                    {
                                        setContentPanel(new InvoiceHome());
                                    }
                                    else
                                    {
                                        setIntermediatInvoicePanel(); //FIXME eventuell nur layer-Wechsel im Layout
                                    }
                                }
                                else
                                {
                                    if (text.equals(payment))
                                    {
                                        ctrl.next();
                                        setContentPanel(getPaymentPanel());
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
        return ctrl.getAllCountries();
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

    /**
     * löscht die Buttons auf dem ControlPanel (Achtung: subPanels - nicht direkt aufrufen)
     */
    public void clearControlPanel()
    {
        getConstructiveControlPanel().removeAll();
        getDeconstructiveControlPanel().removeAll();
    }

    /**
     * setzt die Aufenthalte (Workingset) im Controller
     *
     * @param selectedRows
     */
    public void setSelectedHabitations(Collection<HabitationData> selectedRows)
    {
        ctrl.selectHabitations(selectedRows);
    }

    public boolean cancelItems(InvoiceItemData iid, int amount)
    {
        return CreateInvoiceController.getInstance().cancelItems(iid, amount);
    }

    public String getPaymentString()
    {
        return payment;
    }

    public String getSeperatorString()
    {
        return seperator;
    }

    public void useExistingParty(PartyData data)
    {
        CreateInvoiceController.getInstance().useExistingParty(data);
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

    public Collection<InvoiceItemData> getOpenItems(HabitationData habitation)
    {
        return CreateInvoiceController.getInstance().getOpenItems(habitation);
    }

    /**
     * setzt den Content, die Navigation und die Controls
     * das Setzen der Controls wird an die Content-Panels weitergeleitet
     *
     * @param newcontent
     */
    public void setContentPanel(JPanel newcontent)
    {
        JPanel contentPanel = getContentPanel();
        contentPanel.add(newcontent, BorderLayout.CENTER);

        if (contentPanel.getLayout() instanceof CardLayout)
        {
            CardLayout layout = (CardLayout) contentPanel.getLayout();
            layout.next(contentPanel);
        }

        // setze die Controls
        if (newcontent instanceof ControlsSetter)
        {
            ControlsSetter setter = (ControlsSetter) newcontent;
            setter.setControls();
        }

        setNavigation(newcontent.getClass());

        contentPanel.repaint();

    }

    /**
     * setzt die Navigation bzw. Fortschrittsbalken
     *
     * @param clazz Klasse, welche im Content ist (entsprechend dieser wird die Navigation zusammengebaut
     */
    private void setNavigation(Class clazz)
    {
        JPanel navigation = getNavigationPanel();
        navigation.removeAll();

        if (clazz.equals(splitNstornoPanel.class))
        {
            navigation.add(invoiceHomeLabel);
            navigation.add(new SeperatorLabel());
            navigation.add(intermediatInvoiceLabel);
            navigation.add(new SeperatorLabel());
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
                    navigation.add(new SeperatorLabel());
                    navigation.add(intermediatInvoiceLabel);
                }
                else
                {
                    if (clazz.equals(addCustomer.class))
                    {
                        navigation.add(invoiceHomeLabel);
                        navigation.add(new SeperatorLabel());
                        navigation.add(intermediatInvoiceLabel);
                        navigation.add(new SeperatorLabel());
                        navigation.add(chooseCustomerLabel);
                    }
                    else
                    {
                        if (clazz.equals(PaymentPanel.class))
                        {
                            navigation.add(invoiceHomeLabel);
                            navigation.add(new SeperatorLabel());
                            navigation.add(intermediatInvoiceLabel);
                            navigation.add(new SeperatorLabel());
                            navigation.add(chooseCustomerLabel);
                            navigation.add(new SeperatorLabel());
                            navigation.add(paymentLabel);
                        }
                    }
                }
            }
        }

        navigation.repaint();
    }

    /**
     * ruft den CofirmDialog auf und bei Abbruch kommt man auf den StartScreen zurück
     *
     * @param e
     */
    private void abort(ActionEvent e)
    {
        if (JOptionPane.showConfirmDialog(main, "Do you really want to abort?", "Abort", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0)
        {
            ctrl.clear(); // abort the Use case
            getContentPanel().removeAll();
            setContentPanel(new InvoiceHome());
        }
    }

    /**
     * er wird bei den sogenannten Screens (Content) ein Schritt zurück gegangen
     *
     * @param e
     */
    private void back(ActionEvent e)
    {
        ctrl.back();
        JPanel contentPanel = getContentPanel();
        if (contentPanel.getLayout() instanceof CardLayout)
        {
            CardLayout layout = (CardLayout) contentPanel.getLayout();

            JPanel last = getCurrentPanel(contentPanel);
            layout.previous(getContentPanel());
            JPanel current = getCurrentPanel(contentPanel);


            // if (last instanceof PaymentPanel || last instanceof IntermediatInvoicePanel)
            //  {
            //layout.removeLayoutComponent(last);
            contentPanel.remove(last);
            //}


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

    /**
     * Suche nach Aufentalten (Delegation an Controller)
     *
     * @param firstName
     * @param lastName
     * @param roomNr
     * @return
     */
    public Collection<HabitationData> search(String firstName, String lastName, String roomNr)
    {
        return ctrl.searchHabitations(firstName, lastName, roomNr);
    }

    public Collection<PartyData> searchParties(String text)
    {
        return ctrl.searchParties(text);
    }

    /**
     * sucht die sichtbare Karte aus dem CardLayoutPanel heraus
     *
     * @param cardLayoutPanel
     * @return sichtbares Panel
     */
    public JPanel getCurrentPanel(JPanel cardLayoutPanel)
    {
        JPanel current = new JPanel();

        for (Component component : cardLayoutPanel.getComponents())
        {
            if (component.isVisible())
            {
                if (component instanceof JPanel)
                {
                    current = (JPanel) component;
                }
                // um JScrollPanes ebenfalls zu berücksichtigen
//                else if (component instanceof JScrollPane)
//                    currentPanel = (JPanel) ((JScrollPane) component).getViewport().getComponent(0);
            }
        }

        return current;
    }

    /**
     * ruft setContentPanel auf
     * da dieses Panel zuerst generiert werden muss wird es über diese Methode aufgerufen
     */
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
     * generiert ein Panel für die Rechnung
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

    /**
     * generiert ein Panel für die Zwischenrechnung
     *
     * @return
     */
    private IntermediatInvoicePanel getIntermediatInvoicePanel()
    {
        PdfGenerator generator = new PdfGenerator(ctrl.getChosenItems(), new Date());
        IntermediatInvoicePanel generatePDFPanel = new IntermediatInvoicePanel();
        generatePDFPanel.add(generator.generateIntermediatPanel());
        return generatePDFPanel;
    }

    public void useGuestAsCustomer(GuestData guest)
    {
        ctrl.useGuestAsCustomer(guest);
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

    /**
     * gibt das Workingset das Erstellen der Rechnung zurück (Controller)
     *
     * @return workingset
     */
    public Collection<HabitationData> getSelectedHabitations()
    {
        return ctrl.getSelectedHabitations();
    }

    /**
     * gibt das Hauptpanel zurück
     *
     * @return Hauptpanel Use case Rechnung erstellen
     */
    public JPanel getInvoiceMainPanel()
    {
        return main;
    }

    public void selectItems(Map<InvoiceItemData, Integer> items)
    {
        CreateInvoiceController.getInstance().selectItems(items);
    }
}
