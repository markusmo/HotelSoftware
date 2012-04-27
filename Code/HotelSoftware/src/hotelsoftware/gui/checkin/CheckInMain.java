/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.gui.checkin;

import hotelsoftware.gui.home.HomePanel;
import hotelsoftware.model.domain.parties.Company;
import hotelsoftware.model.domain.parties.Guest;
import hotelsoftware.model.domain.parties.PrivateCustomer;
import hotelsoftware.model.domain.parties.data.CompanyData;
import hotelsoftware.model.domain.parties.data.GuestData;
import hotelsoftware.model.domain.parties.data.PartyData;
import hotelsoftware.model.domain.reservation.data.ReservationData;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Johannes
 */
public class CheckInMain extends javax.swing.JPanel
{
    private Collection<ReservationData> reservations;
    private CheckInGuiControler cigc = CheckInGuiControler.getInstance();
    private CheckinTwo checkInTwo = null;

    /**
     * Creates new form CheckInMain
     */
    public CheckInMain()
    {
        reservations = cigc.getAllReservations();
        initComponents();
        setTable();
    }

    private void setTable()
    {
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                (reservations == null ? new Object[50][] : getTableModel()),
                new String[]
                {
                    "Reservation No.", "Company Name", "Last name", "First name", "Arrival", "Departure", "Number of Persons"
                })
        {
            boolean[] canEdit = new boolean[]
            {
                false, false, false, true, false, false, false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex)
            {
                return canEdit[columnIndex];
            }
        });
    }

    private Object[][] getTableModel()
    {
        int i = 0;
        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        Object[][] value = new Object[20 + reservations.size()][];
        for (ReservationData data : reservations)
        {
            String fName = null;
            String lName = null;
            String companyName = null;

            if (data.getPartyData() instanceof Company)
            {
                companyName = ((CompanyData) data.getPartyData()).getCompanyname();

            }
            if (data.getPartyData() instanceof Guest || data.getPartyData() instanceof PrivateCustomer)
            {
                lName = ((GuestData) data.getPartyData()).getLname();
                fName = ((GuestData) data.getPartyData()).getFname();
            }
            value[i++] = new Object[]
            {
                data.getReservationNumber() + "", companyName, fName, lName,
                df.format(data.getStartDate()), df.format(data.getEndDate()), data.getGuestAmount()
            };
        }
        return value;
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

        jLabel1 = new javax.swing.JLabel();
        buttonWalkIn = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        buttonSearch = new javax.swing.JButton();
        textBoxFname = new javax.swing.JTextField();
        textFieldCompany = new javax.swing.JTextField();
        buttonSelect = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        textBoxLname = new javax.swing.JTextField();
        buttonCheckIn = new javax.swing.JButton();
        buttonAbort = new javax.swing.JButton();
        textBoxReservationNumber = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();

        jLabel1.setText("Last name:");

        buttonWalkIn.setText("WalkIn");
        buttonWalkIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonWalkInActionPerformed(evt);
            }
        });

        buttonSearch.setText("Search");
        buttonSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSearchActionPerformed(evt);
            }
        });

        textBoxFname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textBoxFnameActionPerformed(evt);
            }
        });

        textFieldCompany.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldCompanyActionPerformed(evt);
            }
        });

        buttonSelect.setText("Select");
        buttonSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSelectActionPerformed(evt);
            }
        });

        jLabel3.setText("Reservation No.:");

        jLabel4.setText("First name:");

        textBoxLname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textBoxLnameActionPerformed(evt);
            }
        });

        buttonCheckIn.setText("Check In");
        buttonCheckIn.setEnabled(false);

        buttonAbort.setText("Abort");
        buttonAbort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAbortActionPerformed(evt);
            }
        });

        textBoxReservationNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textBoxReservationNumberActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Reservation No.", "Customer No.", "Last name", "First name", "Arrival", "Departure", "Number of Persons"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel2.setText("Company name:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(textBoxLname, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(textBoxReservationNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(46, 46, 46)
                                .addComponent(jLabel2)))
                        .addGap(54, 54, 54)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textBoxFname, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(textFieldCompany, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(buttonSearch)
                                .addGap(18, 18, 18)
                                .addComponent(buttonWalkIn)))
                        .addGap(0, 29, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(buttonCheckIn)
                        .addGap(18, 18, 18)
                        .addComponent(buttonSelect)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonAbort))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(textBoxLname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(textBoxFname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(textBoxReservationNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textFieldCompany, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonSearch)
                    .addComponent(buttonWalkIn))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonCheckIn)
                    .addComponent(buttonAbort)
                    .addComponent(buttonSelect))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void buttonSearchActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_buttonSearchActionPerformed
    {//GEN-HEADEREND:event_buttonSearchActionPerformed
        // Search Button
        reservations = cigc.search(textBoxFname.getText(), textBoxLname.getText(), textFieldCompany.getText(), textBoxReservationNumber.getText());
        setTable();
    }//GEN-LAST:event_buttonSearchActionPerformed

    private void textBoxFnameActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_textBoxFnameActionPerformed
    {//GEN-HEADEREND:event_textBoxFnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textBoxFnameActionPerformed

    private void textFieldCompanyActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_textFieldCompanyActionPerformed
    {//GEN-HEADEREND:event_textFieldCompanyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldCompanyActionPerformed

    private void buttonSelectActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_buttonSelectActionPerformed
    {//GEN-HEADEREND:event_buttonSelectActionPerformed
        //Select Button
        try
        {
            cigc.setSelectedReservation(reservations.toArray(new ReservationData[0])[jTable1.getSelectedRow()]);
            if (checkInTwo == null)
            {
                checkInTwo = new CheckinTwo();
                cigc.getContentpane().add(checkInTwo, BorderLayout.CENTER);
            }
            checkInTwo.init();
            ((CardLayout) cigc.getContentpane().getLayout()).next(cigc.getContentpane());
        }
        catch (ArrayIndexOutOfBoundsException ex)
        {
        }
    }//GEN-LAST:event_buttonSelectActionPerformed

    private void textBoxLnameActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_textBoxLnameActionPerformed
    {//GEN-HEADEREND:event_textBoxLnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textBoxLnameActionPerformed

    private void buttonAbortActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_buttonAbortActionPerformed
    {//GEN-HEADEREND:event_buttonAbortActionPerformed
        //Abort Button
        if (JOptionPane.showConfirmDialog(this, "Are you sure?", "Aborting?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0)
        {
            cigc.back();
            cigc.getContentpane().removeAll();
            cigc.getContentpane().add(new HomePanel(), BorderLayout.CENTER);
            ((CardLayout) cigc.getContentpane().getLayout()).next(cigc.getContentpane());
            cigc.getContentpane().repaint();
        }
    }//GEN-LAST:event_buttonAbortActionPerformed

    private void textBoxReservationNumberActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_textBoxReservationNumberActionPerformed
    {//GEN-HEADEREND:event_textBoxReservationNumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textBoxReservationNumberActionPerformed

    private void buttonWalkInActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_buttonWalkInActionPerformed
    {//GEN-HEADEREND:event_buttonWalkInActionPerformed
        //Walkin
        cigc.createNewWalking();
        if (checkInTwo == null)
        {
            checkInTwo = new CheckinTwo();
            cigc.getContentpane().add(checkInTwo, BorderLayout.CENTER);
        }
        checkInTwo.init();
        ((CardLayout) cigc.getContentpane().getLayout()).next(cigc.getContentpane());
    }//GEN-LAST:event_buttonWalkInActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAbort;
    private javax.swing.JButton buttonCheckIn;
    private javax.swing.JButton buttonSearch;
    private javax.swing.JButton buttonSelect;
    private javax.swing.JButton buttonWalkIn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField textBoxFname;
    private javax.swing.JTextField textBoxLname;
    private javax.swing.JTextField textBoxReservationNumber;
    private javax.swing.JTextField textFieldCompany;
    // End of variables declaration//GEN-END:variables
}
