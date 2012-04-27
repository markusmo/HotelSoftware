/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.gui.checkin.subpanels;

import hotelsoftware.gui.checkin.CheckInGuiControler;
import hotelsoftware.gui.misc.ButtonTabComponent;
import hotelsoftware.model.domain.invoice.data.InvoiceItemData;
import hotelsoftware.model.domain.parties.Guest;
import hotelsoftware.model.domain.parties.data.CountryData;
import hotelsoftware.model.domain.parties.data.GuestData;
import hotelsoftware.model.domain.service.Habitation;
import hotelsoftware.model.domain.service.data.ExtraServiceData;
import hotelsoftware.model.domain.service.data.HabitationData;
import java.awt.*;
import java.util.*;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JTextField;

/**
 * Dieses Panel ist dazu da, um die Informationen eines Gastes einzutragen.
 *
 * @author Johannes
 */
public class GuestPanel extends javax.swing.JPanel
{
    private ButtonTabComponent tabComponent;
    private ExtraServicesPanel esPane = new ExtraServicesPanel();

    /**
     * Creates new form GuestPanel
     */
    public GuestPanel()
    {
        initComponents();
        init();

    }

    /**
     * Initialisiert ein neues GuestPanel
     */
    private void init()
    {

        ESPane.add(esPane);

        ComboBoxCountry.removeAllItems();
        for (CountryData data : CheckInGuiControler.getInstance().getAllCountries())
        {
            ComboBoxCountry.addItem(data);
        }
        setTabStops();
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

        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        TextFieldLastName = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        TextFieldCity = new javax.swing.JTextField();
        TextFieldFirstName = new javax.swing.JTextField();
        TextFieldFax = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        TextFieldEmail = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        TextFieldStreet = new javax.swing.JTextField();
        jLabelVorname = new javax.swing.JLabel();
        TextFieldPhoneNumber = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        TextFieldZip = new javax.swing.JTextField();
        ESPane = new javax.swing.JPanel();
        BirthdayChooser = new datechooser.beans.DateChooserCombo();
        ComboBoxGender = new javax.swing.JComboBox();
        jLabel15 = new javax.swing.JLabel();
        ComboBoxCountry = new javax.swing.JComboBox();
        jLabel18 = new javax.swing.JLabel();
        TextFieldDeposit = new javax.swing.JTextField();

        jLabel10.setText("Last name:");

        jLabel9.setText("Birthday:");

        jLabel8.setText("Street / Number:");

        jLabel13.setText("Phone number:");

        jLabel17.setText("Country:");

        jLabel11.setText("Email:");

        jLabel12.setText("ZIP:");

        jLabel16.setText("City:");

        jLabel14.setText("Extra services:");

        jLabelVorname.setText("First name:");

        jLabel7.setText("Fax:");

        ESPane.setLayout(new java.awt.BorderLayout());

        ComboBoxGender.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "m", "w" }));

        jLabel15.setText("Gender:");

        ComboBoxCountry.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel18.setText("Deposit:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelVorname, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ComboBoxCountry, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextFieldStreet, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextFieldFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(TextFieldCity, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TextFieldZip))
                    .addComponent(TextFieldLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TextFieldDeposit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ComboBoxGender, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BirthdayChooser, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextFieldPhoneNumber, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextFieldEmail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextFieldFax, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ESPane, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ESPane, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(TextFieldPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelVorname)
                            .addComponent(jLabel14)
                            .addComponent(TextFieldFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(TextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)
                            .addComponent(TextFieldLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel7)
                            .addComponent(TextFieldStreet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TextFieldFax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(BirthdayChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TextFieldZip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16)
                            .addComponent(TextFieldCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)
                            .addComponent(jLabel9))
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(ComboBoxCountry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ComboBoxGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15)
                            .addComponent(jLabel17))
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(TextFieldDeposit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18))))
                .addContainerGap(45, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private datechooser.beans.DateChooserCombo BirthdayChooser;
    private javax.swing.JComboBox ComboBoxCountry;
    private javax.swing.JComboBox ComboBoxGender;
    private javax.swing.JPanel ESPane;
    private javax.swing.JTextField TextFieldCity;
    private javax.swing.JTextField TextFieldDeposit;
    private javax.swing.JTextField TextFieldEmail;
    private javax.swing.JTextField TextFieldFax;
    private javax.swing.JTextField TextFieldFirstName;
    private javax.swing.JTextField TextFieldLastName;
    private javax.swing.JTextField TextFieldPhoneNumber;
    private javax.swing.JTextField TextFieldStreet;
    private javax.swing.JTextField TextFieldZip;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelVorname;
    // End of variables declaration//GEN-END:variables

    public String getFirstName()
    {
        return TextFieldFirstName.getText();
    }

    public String getLastName()
    {
        return TextFieldLastName.getText();
    }

    public String getStreet()
    {
        return TextFieldStreet.getText();
    }

    public String getZip()
    {
        return TextFieldZip.getText();
    }

    public String getCity()
    {
        return TextFieldCity.getText();
    }

    public CountryData getCountry()
    {
        return (CountryData) ComboBoxCountry.getSelectedItem();
    }

    public String getPhoneNumber()
    {
        return TextFieldPhoneNumber.getText();
    }

    public String getEmail()
    {
        return TextFieldEmail.getText();
    }

    public String getFax()
    {
        return TextFieldFax.getText();
    }

    public Date getBirthday()
    {
        Calendar c = BirthdayChooser.getSelectedDate();
        return new Date(c.getTimeInMillis());
    }

    /*
     * Kontrolliert ob ein alle Textfelder einen text enthalten
     */
    public boolean isFinished()
    {
        for (Component c : getComponents())
        {
            if (c instanceof JTextField)
            {
                if (((JTextField) c).getText().isEmpty())
                {
                    //System.out.println(((JTextField) c).getText());
                    return false;
                }
            }
        }
        return true;
    }

    public ButtonTabComponent getTabComponent()
    {
        return tabComponent;
    }

    public void setTabComponent(ButtonTabComponent tabComponent)
    {
        this.tabComponent = tabComponent;
    }

    public char getGender()
    {
        return ComboBoxGender.getSelectedItem().toString().charAt(0);
    }

    public Collection<ExtraServiceData> getExtraservices()
    {
        return esPane.getExtraservices();
    }

    /**
     * Setzt die Tabstops in der richtigen Reihenfolge
     */
    private void setTabStops()
    {

        final ArrayList<JComponent> order = new ArrayList<JComponent>();
        order.addAll(Arrays.asList(new JComponent[]
                {
                    this.TextFieldFirstName, this.TextFieldLastName,
                    this.TextFieldStreet, this.TextFieldCity, this.TextFieldZip, this.ComboBoxCountry, this.TextFieldPhoneNumber,
                    this.TextFieldEmail, this.TextFieldFax, this.BirthdayChooser, this.ComboBoxGender, this.TextFieldDeposit
                }));
        order.addAll(esPane.checkboxes);


        FocusTraversalPolicy policy = new FocusTraversalPolicy()
        {
            List<JComponent> list = order;

            public Component getFirstComponent(Container focusCycleRoot)
            {
                return order.get(0);
            }

            public Component getLastComponent(Container focusCycleRoot)
            {
                return order.get(order.size() - 1);
            }

            public Component getComponentAfter(Container focusCycleRoot, Component aComponent)
            {
                int index = list.indexOf(aComponent);
                return order.get((index + 1) % order.size());
            }

            public Component getComponentBefore(Container focusCycleRoot, Component aComponent)
            {
                int index = list.indexOf(aComponent);
                return order.get((index - 1 + order.size()) % order.size());
            }

            public Component getDefaultComponent(
                    java.awt.Container focusCycleRoot)
            {
                return order.get(0);
            }

            public Component getInitialComponent(Window window)
            {
                return order.get(0);
            }
        };
        this.setFocusTraversalPolicy(policy);
        this.setFocusCycleRoot(true);
    }

    public void addGuest(GuestData g)
    {
        this.TextFieldFirstName.setText(g.getFname());
        this.TextFieldLastName.setText(g.getLname());
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(g.getBirthday().getTime());
        this.BirthdayChooser.setSelectedDate(c);
        CountryData cd = (CountryData) g.getAddressData().getIdCountry();
        this.ComboBoxCountry.setSelectedItem(cd);
        this.TextFieldCity.setText(g.getAddressData().getCity());
        this.TextFieldZip.setText(g.getAddressData().getZip());
        //this.TextFieldDeposit.setText(g.).
        this.TextFieldEmail.setText(g.getAddressData().getEmail());
        this.TextFieldFax.setText(g.getAddressData().getFax());
        this.TextFieldStreet.setText(g.getAddressData().getStreet());
        this.TextFieldPhoneNumber.setText(g.getAddressData().getPhone());
        /*
         * Collection<Habitation> hd1 = ((Guest) g).getCurrentHabitations();
         * Collection<HabitationData> hd2 = ((Guest) g).getHabitationsData();
         * Collection<Habitation> hd3 = ((Guest) g).getHabitations();
         * Collection<HabitationData> hd4 = ((Guest) g).getCurrentHabitationsData();
         * Collection<InvoiceItemData> ivid = hd1.toArray(new HabitationData[0])[0].getInvoiceItemsData();
         * for (InvoiceItemData i : ivid)
         * {
         * if (i.getServiceData() instanceof ExtraServiceData)
         * {
         * if (i.getServiceData().getServiceTypeData().getName().equals("Habitation"))
         * {
         * esPane.setExtraservice(i.getServiceData());
         * }
         * }
         * }
         */
    }
}
