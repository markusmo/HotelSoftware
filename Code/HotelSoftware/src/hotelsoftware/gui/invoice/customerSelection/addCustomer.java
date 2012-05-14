package hotelsoftware.gui.invoice.customerSelection;

import hotelsoftware.controller.data.parties.CountryData;
import hotelsoftware.controller.data.parties.CustomerData;
import hotelsoftware.controller.data.parties.GuestData;
import hotelsoftware.controller.data.parties.PartyData;
import hotelsoftware.gui.GuiController;
import hotelsoftware.gui.home.HomePanel;
import hotelsoftware.gui.invoice.InvoiceGUIControler;
import hotelsoftware.gui.invoice.buttons.AbortButton;
import hotelsoftware.gui.invoice.buttons.BackButton;
import hotelsoftware.gui.invoice.buttons.PaymentButton;
import hotelsoftware.gui.invoice.ControlsSetter;
import hotelsoftware.gui.misc.ButtonIconTabComponent;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Johannes
 */
public class addCustomer extends javax.swing.JPanel implements ControlsSetter
{
    private DefaultListModel listModel = new DefaultListModel();
    private final Collection<PartyData> URcustomers;
    private Collection<PartyData> customers = new ArrayList<PartyData>();
    private PersonPanel personPanel = new PersonPanel();
    private CompanyPanel companyPanel = new CompanyPanel();
    private CustomerData THEcustomer;
    private boolean isAlive = true;
    private InvoiceGUIControler ctrl = InvoiceGUIControler.getInstance();
    private PaymentButton pmB;
    private AbortButton aB;
    private BackButton bB;

    // private iCustomerPanel customerPanel = personPanel;
    /**
     * Creates new form addCustomer
     */
    public addCustomer()
    {
        Collection<PartyData> allCustomers = InvoiceGUIControler.getInstance().getWorkingHabitationsGuests();
        if (allCustomers != null)
        {
            URcustomers = allCustomers;
            this.customers.addAll(URcustomers);
        }
        else
        {
            URcustomers = null;
        }

        initComponents();
        jLabel3.setText("<html> <font size =+2>Choose customer</font></html>");
        jLabel2.setText("<html> <font size =+2>Create new customer</font></html>");
        init();

    }

    /**
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        radioButtonPerson = new javax.swing.JRadioButton();
        RadioButtonCompany = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        TextFieldCity = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        ComboBoxCountry = new javax.swing.JComboBox();
        jLabel16 = new javax.swing.JLabel();
        TextFieldStreet = new javax.swing.JTextField();
        TextFieldZip = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        TextFieldFax = new javax.swing.JTextField();
        TextFieldEmail = new javax.swing.JTextField();
        TextFieldPhoneNumber = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        list = new javax.swing.JList();
        textfieldSearch = new javax.swing.JTextField();
        buttonSearch = new javax.swing.JButton();
        buttonSelectCustomer = new javax.swing.JButton();
        buttonCreateCustomer = new javax.swing.JButton();

        jPanel2.setLayout(new java.awt.BorderLayout());

        radioButtonPerson.setSelected(true);
        radioButtonPerson.setText("Private customer");
        radioButtonPerson.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButtonPersonActionPerformed(evt);
            }
        });

        RadioButtonCompany.setText("Company");
        RadioButtonCompany.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RadioButtonCompanyActionPerformed(evt);
            }
        });

        jLabel8.setText("Street / Number:");

        jLabel12.setText("ZIP:");

        jLabel17.setText("Country:");

        ComboBoxCountry.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel16.setText("City:");

        jLabel11.setText("Email:");

        jLabel13.setText("Phone number:");

        jLabel7.setText("Fax:");

        jLabel2.setText("jLabel2");

        jLabel3.setText("jLabel3");

        jCheckBox1.setText("create new invoice address");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        list.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        list.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(list);

        textfieldSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textfieldSearchActionPerformed(evt);
            }
        });

        buttonSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/search.png"))); // NOI18N
        buttonSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSearchActionPerformed(evt);
            }
        });

        buttonSelectCustomer.setText("Select customer");
        buttonSelectCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSelectCustomerActionPerformed(evt);
            }
        });

        buttonCreateCustomer.setText("Create new customer");
        buttonCreateCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCreateCustomerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(buttonCreateCustomer))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(radioButtonPerson)
                        .addGap(18, 18, 18)
                        .addComponent(RadioButtonCompany)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonSelectCustomer))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(textfieldSearch)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(buttonSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel2))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(85, 85, 85)
                                        .addComponent(jCheckBox1))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING))
                                                .addGap(3, 3, 3))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING))
                                                .addGap(4, 4, 4)))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(ComboBoxCountry, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(TextFieldFax, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(TextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(TextFieldPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                    .addComponent(TextFieldCity, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jLabel12)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(TextFieldZip))
                                                .addComponent(TextFieldStreet, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                        .addGap(0, 201, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(buttonSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textfieldSearch))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addGap(45, 45, 45)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(radioButtonPerson)
                            .addComponent(RadioButtonCompany)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jCheckBox1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(TextFieldStreet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(TextFieldZip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16)
                            .addComponent(TextFieldCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(ComboBoxCountry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(TextFieldPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(TextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel7)
                            .addComponent(TextFieldFax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(49, 49, 49)
                        .addComponent(buttonSelectCustomer)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonCreateCustomer)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void buttonSearchActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_buttonSearchActionPerformed
    {//GEN-HEADEREND:event_buttonSearchActionPerformed
        Collection<PartyData> searchedCustomer = ctrl.searchParties(textfieldSearch.getText());
        addElements2List(searchedCustomer);
        textfieldSearch.setText("");
    }//GEN-LAST:event_buttonSearchActionPerformed

    private void radioButtonPersonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_radioButtonPersonActionPerformed
    {//GEN-HEADEREND:event_radioButtonPersonActionPerformed
        // customerPanel = personPanel;
        jPanel2.removeAll();
        jPanel2.add(personPanel, BorderLayout.CENTER);
        jPanel2.revalidate();
        jPanel2.repaint();
    }//GEN-LAST:event_radioButtonPersonActionPerformed

    private void RadioButtonCompanyActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_RadioButtonCompanyActionPerformed
    {//GEN-HEADEREND:event_RadioButtonCompanyActionPerformed
        //  customerPanel = companyPanel;
        jPanel2.removeAll();
        jPanel2.add(companyPanel, BorderLayout.CENTER);
        jPanel2.revalidate();
        jPanel2.repaint();

    }//GEN-LAST:event_RadioButtonCompanyActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jCheckBox1ActionPerformed
    {//GEN-HEADEREND:event_jCheckBox1ActionPerformed
        if (jCheckBox1.isSelected())
        {
            EnableAddressInputs(true);
            EmptyAddressInputs();
        }
        else
        {
            EnableAddressInputs(false);            
            FillAddressInputs((PartyData) list.getSelectedValue());
        }
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void buttonCreateCustomerActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_buttonCreateCustomerActionPerformed
    {//GEN-HEADEREND:event_buttonCreateCustomerActionPerformed
        if (radioButtonPerson.isSelected())
        {
            if (personPanel.isFinished())
            {
                InvoiceGUIControler.getInstance().createPrivateCustomer(personPanel.getFName(), personPanel.getLName(), personPanel.getStreet(), personPanel.getCity(), personPanel.getZip(), personPanel.getEmail(), personPanel.getPhone(), personPanel.getFax(), personPanel.getIdCountry());
            }
            else
            {
                JOptionPane.showMessageDialog(list, "You have to fill out the required fields!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        else
        {
            if (companyPanel.isFinished())
            {
                InvoiceGUIControler.getInstance().createCompanyCustomer(companyPanel.getCompanyName(), companyPanel.getStreet(), companyPanel.getCity(), companyPanel.getZip(), companyPanel.getEmail(), companyPanel.getPhone(), companyPanel.getFax(), companyPanel.getIdCountry());
            }
            else
            {
                JOptionPane.showMessageDialog(list, "You have to fill out the required fields!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        isAlive = false;
        InvoiceGUIControler.getInstance().setPaymentPanel();
    }//GEN-LAST:event_buttonCreateCustomerActionPerformed

    private void buttonSelectCustomerActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_buttonSelectCustomerActionPerformed
    {//GEN-HEADEREND:event_buttonSelectCustomerActionPerformed
        if (!(list.getSelectedIndex() != -1))
        {
            JOptionPane.showMessageDialog(list, "You have to select a person first!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }


        if (jCheckBox1.isSelected())
        {
            GuestData gd = (GuestData) list.getSelectedValue();
            InvoiceGUIControler.getInstance().createPrivateCustomer(gd.getFname(), gd.getLname(), gd.getAddressData().getStreet(), gd.getAddressData().getCity(), gd.getAddressData().getZip(), gd.getAddressData().getEmail(), gd.getAddressData().getPhone(), gd.getAddressData().getFax(), gd.getAddressData().getIdCountry(), TextFieldStreet.getText(), TextFieldCity.getText(), TextFieldZip.getText(), TextFieldEmail.getText(), TextFieldPhoneNumber.getText(), TextFieldFax.getText(), (CountryData) ComboBoxCountry.getSelectedItem());
        }
        else
        {
            InvoiceGUIControler.getInstance().useGuestAsCustomer((GuestData) list.getSelectedValue());
        }

        isAlive = false;
        InvoiceGUIControler.getInstance().setPaymentPanel();

    }//GEN-LAST:event_buttonSelectCustomerActionPerformed

    private void listPropertyChange(java.beans.PropertyChangeEvent evt)//GEN-FIRST:event_listPropertyChange
    {//GEN-HEADEREND:event_listPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_listPropertyChange

    private void textfieldSearchActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_textfieldSearchActionPerformed
    {//GEN-HEADEREND:event_textfieldSearchActionPerformed
        buttonSearchActionPerformed(evt);
    }//GEN-LAST:event_textfieldSearchActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox ComboBoxCountry;
    private javax.swing.JRadioButton RadioButtonCompany;
    private javax.swing.JTextField TextFieldCity;
    private javax.swing.JTextField TextFieldEmail;
    private javax.swing.JTextField TextFieldFax;
    private javax.swing.JTextField TextFieldPhoneNumber;
    private javax.swing.JTextField TextFieldStreet;
    private javax.swing.JTextField TextFieldZip;
    private javax.swing.JButton buttonCreateCustomer;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton buttonSearch;
    private javax.swing.JButton buttonSelectCustomer;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList list;
    private javax.swing.JRadioButton radioButtonPerson;
    private javax.swing.JTextField textfieldSearch;
    // End of variables declaration//GEN-END:variables

    private void init()
    {
        list.addListSelectionListener(new ListSelectionListener()
        {
            @Override
            public void valueChanged(ListSelectionEvent e)
            {
                if (jCheckBox1.isSelected())
                {
                    EnableAddressInputs(true);
                    EmptyAddressInputs();
                }
                else
                {
                    EnableAddressInputs(false);
                    FillAddressInputs((PartyData) list.getSelectedValue());
                }
            }
        });
        addElements2List(customers);
        list.setModel(listModel);

        buttonSearch.setIcon(new ImageIcon(ButtonIconTabComponent.class.getClassLoader().getResource("resources/images/search.png")));
        buttonSearch.setText("");
        buttonGroup1.add(radioButtonPerson);

        buttonGroup1.add(RadioButtonCompany);

        buttonCreateCustomer.setEnabled(false);
        buttonSelectCustomer.setEnabled(false);
        initNewCustomer();

        ComboBoxCountry.removeAllItems();
        for (CountryData data : InvoiceGUIControler.getInstance().getAllCountries())
        {
            ComboBoxCountry.addItem(data);
        }

        EnableAddressInputs(false);
        StartUpdater();
    }

    private Collection<PartyData> search(String text)
    {
        // TODO implement!!!    
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Diese Methode startet den Updater
     */
    private void StartUpdater()
    {
        new Updater().start();
    }

    private void addElements2List(Collection<PartyData> elements)
    {
        for (PartyData data : elements)
        {
            if (!listModel.contains(data))
            {
                listModel.addElement(data);
            }
        }
        listModel.trimToSize();
    }

    private void initNewCustomer()
    {
        jPanel2.add(personPanel, BorderLayout.CENTER);
    }

    private void EnableAddressInputs(boolean b)
    {
        TextFieldCity.setEnabled(b);
        TextFieldEmail.setEnabled(b);
        TextFieldFax.setEnabled(b);
        TextFieldPhoneNumber.setEnabled(b);
        TextFieldStreet.setEnabled(b);
        TextFieldZip.setEnabled(b);
        ComboBoxCountry.setEnabled(b);
    }

    private void EmptyAddressInputs()
    {
        TextFieldCity.setText("");
        TextFieldEmail.setText("");
        TextFieldFax.setText("");
        TextFieldPhoneNumber.setText("");
        TextFieldStreet.setText("");
        TextFieldZip.setText("");
    }

    private void FillAddressInputs(PartyData d)
    {
        TextFieldCity.setText(d.getAddressData().getCity());
        TextFieldEmail.setText(d.getAddressData().getEmail());
        TextFieldFax.setText(d.getAddressData().getFax());
        TextFieldPhoneNumber.setText(d.getAddressData().getPhone());
        TextFieldStreet.setText(d.getAddressData().getStreet());
        TextFieldZip.setText(d.getAddressData().getZip());
        ComboBoxCountry.setSelectedItem(d.getAddressData().getIdCountry());
    }

    private boolean isAlive()
    {
        return isAlive;
    }

    private void isFinished()
    {
        if (list.getSelectedIndex() != -1)
        {
            buttonSelectCustomer.setEnabled(true);
        }
        else
        {
            buttonSelectCustomer.setEnabled(false);
        }
        if (radioButtonPerson.isSelected())
        {
            if (personPanel.isFinished())
            {
                buttonCreateCustomer.setEnabled(true);
            }
            else
            {
                buttonCreateCustomer.setEnabled(false);
            }
        }
        else
        {
            if (companyPanel.isFinished())
            {
                buttonCreateCustomer.setEnabled(true);
            }
            else
            {
                buttonCreateCustomer.setEnabled(false);
            }
        }
    }

    @Override
    public void setControls()
    {
        isAlive = true;
        StartUpdater();
        ctrl.clearControlPanel();

        aB = new AbortButton();
        ctrl.getDeconstructiveControlPanel().add(aB);

        bB = new BackButton();
        ctrl.getDeconstructiveControlPanel().add(bB);

        pmB = new PaymentButton();
        //ctrl.getConstructiveControlPanel().add(pmB);

        ctrl.repaintControlPanel();
    }

    private class Updater extends Thread
    {
        public Updater()
        {
            setDaemon(true);
        }

        @Override
        public void run()
        {
            while (addCustomer.this.isAlive())
            {
                try
                {
                    Thread.sleep(500);
                    addCustomer.this.isFinished();
                }
                catch (InterruptedException ex)
                {
                    Logger.getLogger(HomePanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
    }
}
