/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.gui.checkin;

import hotelsoftware.gui.MainFrame;
import hotelsoftware.gui.checkin.subpanels.RoomPanel;
import hotelsoftware.gui.misc.ButtonIconTabComponent;
import hotelsoftware.gui.misc.ButtonTabComponent;
import hotelsoftware.gui.misc.ButtonTabComponentPlus;
import hotelsoftware.model.domain.reservation.ReservationData;
import hotelsoftware.model.domain.reservation.ReservationItemData;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Johannes
 */
public class CheckinTwo extends javax.swing.JPanel
{
    private ReservationData reservation;
    private CheckInGuiControler cigc = CheckInGuiControler.getInstance();
    private List<RoomPanel> rooms = new LinkedList<RoomPanel>();

    /**
     * Creates new form GUInr2
     */
    public CheckinTwo()
    {
        initComponents();
        init();
    }
    int i;

    private void init()
    {
        //################### Set TextBoxes
        reservation = cigc.getSelectedReservation();
        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        textAreaComment.setText(reservation.getComment());
        textBoxArrival.setText(df.format(reservation.getStart()));
        textBoxDeparture.setText(df.format(reservation.getEnd()));
        textBoxNumberOfGuests.setText(reservation.getGuestAmount() + "");
        textBoxReservationNumber.setText(reservation.getReservationNumber());

        //################### Create Panels
        for (ReservationItemData data : reservation.getReservationItemCollectionData())
        {
            int oldi = i;
            for (i = oldi; i < data.getAmount(); i++)
            {
                // cigc.addRoomSelection();
                ButtonIconTabComponent iconTab = new ButtonIconTabComponent(TabbedPaneRooms, new ImageIcon("src/resources/images/rotes_x.gif"));
                RoomPanel room = new RoomPanel(iconTab);
                rooms.add(room);
                // room.setRoomIndex(cigc.addRoomSelection());
                room.setRoomIndex(cigc.getCounter());
                TabbedPaneRooms.addTab("Room " + (i + 1), room);
                TabbedPaneRooms.setTabComponentAt(i, iconTab);
                //TabbedPaneRooms.setMnemonicAt(i, 48 + i);
            }
        }
        JPanel pPanel = new JPanel();
        TabbedPaneRooms.add("", pPanel);
        TabbedPaneRooms.setTabComponentAt(TabbedPaneRooms.getTabCount() - 1,
                new ButtonTabComponentPlus(TabbedPaneRooms, RoomPanel.class, "Room", new ImageIcon("src/resources/images/gh1.png")));
        TabbedPaneRooms.setEnabledAt(TabbedPaneRooms.getTabCount() - 1, false);

        TabbedPaneRooms.addChangeListener(new ChangeListener()
        {
            public void stateChanged(ChangeEvent e)
            {
                /*
                 * if (TabbedPaneRooms.getSelectedIndex() == TabbedPaneRooms.getTabCount() - 1)
                 * {
                 * ChangeListener cl = TabbedPaneRooms.getChangeListeners()[0];
                 * TabbedPaneRooms.removeChangeListener(cl);
                 * TabbedPaneRooms.setEnabled(false);
                 * Component c = TabbedPaneRooms.getComponent(TabbedPaneRooms.getComponentCount() - 1);
                 * TabbedPaneRooms.remove(c);
                 * TabbedPaneRooms.add("Room 4", new RoomPanel());
                 * TabbedPaneRooms.setTabComponentAt(TabbedPaneRooms.getTabCount() - 1,
                 * new ButtonIconTabComponent(TabbedPaneRooms, new ImageIcon("src/resources/images/gh1.png")));
                 * TabbedPaneRooms.add("+ ", c);
                 * TabbedPaneRooms.setSelectedIndex(TabbedPaneRooms.getTabCount() - 2);
                 * TabbedPaneRooms.setEnabled(true);
                 * TabbedPaneRooms.addChangeListener(cl);
                 * }
                 */
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        textBoxArrival = new javax.swing.JTextField();
        textBoxReservationNumber = new javax.swing.JTextField();
        textBoxNumberOfGuests = new javax.swing.JTextField();
        textBoxDeparture = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        textAreaComment = new javax.swing.JTextArea();
        TabbedPaneRooms = new javax.swing.JTabbedPane();
        jButton1 = new javax.swing.JButton();

        jButton3.setText("Check In");
        jButton3.setEnabled(false);

        jButton4.setText("Abort");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Reservation details"));

        jLabel1.setText("Arrival:");

        jLabel3.setText("Reservation no:");

        jLabel4.setText("Number of guests:");

        textBoxArrival.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textBoxArrivalActionPerformed(evt);
            }
        });

        textBoxReservationNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textBoxReservationNumberActionPerformed(evt);
            }
        });

        textBoxNumberOfGuests.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textBoxNumberOfGuestsActionPerformed(evt);
            }
        });

        textBoxDeparture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textBoxDepartureActionPerformed(evt);
            }
        });

        jLabel2.setText("Comment:");

        jLabel5.setText("Departure:");

        textAreaComment.setColumns(20);
        textAreaComment.setLineWrap(true);
        textAreaComment.setRows(5);
        textAreaComment.setWrapStyleWord(true);
        jScrollPane2.setViewportView(textAreaComment);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textBoxReservationNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textBoxArrival, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(79, 79, 79)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textBoxNumberOfGuests, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(256, 256, 256))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(textBoxDeparture, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(textBoxReservationNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(textBoxNumberOfGuests, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textBoxArrival, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textBoxDeparture, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        TabbedPaneRooms.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                TabbedPaneRoomsCaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });

        jButton1.setText("Back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TabbedPaneRooms, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 985, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton4)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(TabbedPaneRooms, javax.swing.GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton1)
                    .addComponent(jButton4))
                .addContainerGap())
        );

        jTabbedPane1.addTab("CkeckIn", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void textBoxReservationNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textBoxReservationNumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textBoxReservationNumberActionPerformed

    private void textBoxArrivalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textBoxArrivalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textBoxArrivalActionPerformed

    private void textBoxDepartureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textBoxDepartureActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textBoxDepartureActionPerformed

    private void textBoxNumberOfGuestsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textBoxNumberOfGuestsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textBoxNumberOfGuestsActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton1ActionPerformed
    {//GEN-HEADEREND:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void TabbedPaneRoomsCaretPositionChanged(java.awt.event.InputMethodEvent evt)//GEN-FIRST:event_TabbedPaneRoomsCaretPositionChanged
    {//GEN-HEADEREND:event_TabbedPaneRoomsCaretPositionChanged
    }//GEN-LAST:event_TabbedPaneRoomsCaretPositionChanged

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton4ActionPerformed
    {//GEN-HEADEREND:event_jButton4ActionPerformed
                rooms.get(1).setTabIcon(new ImageIcon("src/resources/images/gh1.png"));
    }//GEN-LAST:event_jButton4ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane TabbedPaneRooms;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea textAreaComment;
    private javax.swing.JTextField textBoxArrival;
    private javax.swing.JTextField textBoxDeparture;
    private javax.swing.JTextField textBoxNumberOfGuests;
    private javax.swing.JTextField textBoxReservationNumber;
    // End of variables declaration//GEN-END:variables

    public void persist(Object object)
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("HotelSoftwarePU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try
        {
            em.persist(object);
            em.getTransaction().commit();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
        finally
        {
            em.close();
        }
    }
}
