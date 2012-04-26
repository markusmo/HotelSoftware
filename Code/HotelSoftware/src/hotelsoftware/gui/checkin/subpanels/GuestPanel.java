/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.gui.checkin.subpanels;

import hotelsoftware.gui.checkin.CheckInGuiControler;
import hotelsoftware.gui.misc.ButtonTabComponent;
import hotelsoftware.model.domain.parties.data.CountryData;
import hotelsoftware.model.domain.service.data.ExtraServiceData;
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

    private void init()
    {

        ESPane.add(esPane);

        ComboBoxCountry.removeAllItems();
        for (CountryData data : CheckInGuiControler.getInstance().getAllCountries())
        {
            ComboBoxCountry.addItem(data);
        }
        setTabCounts();
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(jLabelVorname))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(jLabel10))
                            .addComponent(jLabel8)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(58, 58, 58)
                                .addComponent(jLabel16)))
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TextFieldFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TextFieldLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TextFieldStreet, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(TextFieldCity, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jLabel12)
                                .addGap(4, 4, 4)
                                .addComponent(TextFieldZip, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addComponent(jLabel11))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addComponent(jLabel7))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(jLabel9)))
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TextFieldPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TextFieldFax, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BirthdayChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel14))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jLabel17)
                        .addGap(4, 4, 4)
                        .addComponent(ComboBoxCountry, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52)
                        .addComponent(jLabel15)
                        .addGap(4, 4, 4)
                        .addComponent(ComboBoxGender, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(357, 357, 357)
                        .addComponent(jLabel18)
                        .addGap(4, 4, 4)
                        .addComponent(TextFieldDeposit, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(TextFieldFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(TextFieldLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(TextFieldStreet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(TextFieldCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addComponent(jLabel12))
                                    .addComponent(TextFieldZip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(TextFieldPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(TextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(TextFieldFax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(BirthdayChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabelVorname)
                                        .addGap(12, 12, 12)
                                        .addComponent(jLabel10)
                                        .addGap(12, 12, 12)
                                        .addComponent(jLabel8)
                                        .addGap(12, 12, 12)
                                        .addComponent(jLabel16))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel13)
                                        .addGap(12, 12, 12)
                                        .addComponent(jLabel11)
                                        .addGap(12, 12, 12)
                                        .addComponent(jLabel7)
                                        .addGap(12, 12, 12)
                                        .addComponent(jLabel9))
                                    .addComponent(jLabel14))))
                        .addGap(1, 1, 1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ComboBoxCountry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ComboBoxGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel15))))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel18))
                            .addComponent(TextFieldDeposit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
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

    public boolean isFinished()
    {
        //TODO
        for (Component c : getComponents())
        {
            if (c instanceof JTextField)
            {
                if (!((JTextField) c).getText().isEmpty())
                {
                    System.out.println(((JTextField) c).getText());
                    //return false;
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

    private void setTabCounts()
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
}
