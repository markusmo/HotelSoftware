/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.gui.checkin;

import hotelsoftware.checkin.CheckInController;
import hotelsoftware.checkin.CouldNotSaveException;
import hotelsoftware.gui.checkin.subpanels.GuestPanel;
import hotelsoftware.gui.checkin.subpanels.RoomPanel;
import hotelsoftware.gui.misc.ButtonIconTabComponent;
import hotelsoftware.gui.misc.ButtonTabComponent;
import hotelsoftware.gui.misc.ButtonTabComponentPlus;
import hotelsoftware.model.domain.parties.data.GuestData;
import hotelsoftware.model.domain.reservation.data.ReservationData;
import hotelsoftware.model.domain.reservation.data.ReservationItemData;
import hotelsoftware.model.domain.room.NoPriceDefinedException;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
        //init();
    }
    int i;

    private Calendar dateToCalendar(Date date)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    public void init()
    {
        TabbedPaneRooms.removeAll();
        cigc.setRoomTabPane(TabbedPaneRooms);
        //################### Set TextBoxes
        reservation = cigc.getSelectedReservation();
        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        textAreaComment.setText(reservation.getComment());
        DateChooserArrival.setDateFormat(df);
        DateChooserDeparture.setDateFormat(df);
        DateChooserArrival.setSelectedDate(dateToCalendar(reservation.getStartDate()));
        DateChooserDeparture.setSelectedDate(dateToCalendar(reservation.getEndDate()));

        textBoxNumberOfGuests.setText(reservation.getGuestAmount() + "");
        textBoxReservationNumber.setText(reservation.getReservationNumber());

        //################### Create Panels
        for (ReservationItemData data : reservation.getReservationItemCollectionData())
        {
            // int test = data.getAmount();
            int oldi = i;
            for (i = oldi; i < data.getAmount() + oldi; i++)
            {
                // cigc.addRoomSelection();
               /*
                 * ButtonIconTabComponent iconTab = new ButtonIconTabComponent(TabbedPaneRooms, new ImageIcon(CheckinTwo.class.getClassLoader().getResource("resources/images/rotes_x.gif")));
                 * RoomPanel room = new RoomPanel();
                 * room.setTabComponent(iconTab);
                 * rooms.add(room);
                 *
                 * room.setRoomIndex(cigc.addRoomSelection());
                 * room.init();
                 * TabbedPaneRooms.addTab("Room " + (i + 1), room);
                 * TabbedPaneRooms.setTabComponentAt(i, iconTab);
                 */
                addNewRoomPanel();
                //TabbedPaneRooms.setMnemonicAt(i, 48 + i);
            }
        }
        JPanel pPanel = new JPanel();
        TabbedPaneRooms.add("", pPanel);
        TabbedPaneRooms.setTabComponentAt(TabbedPaneRooms.getTabCount() - 1,
                new ButtonTabComponentPlus(getRoomPannelAddListener()));
        TabbedPaneRooms.setEnabledAt(TabbedPaneRooms.getTabCount() - 1, false);

        TabbedPaneRooms.addChangeListener(new ChangeListener()
        {
            public void stateChanged(ChangeEvent e)
            {
                for (RoomPanel room : rooms)
                {
                    if (room.isFinished())
                    {
                        room.setTabIcon(new ImageIcon(CheckinTwo.class.getClassLoader().getResource("resources/images/gh1.png")));
                    }
                }
                if (isFinished())
                {
                    ButtonCheckIn.setEnabled(true);
                }
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

    private void updateRooms()
    {
        for (RoomPanel r : rooms)
        {
            r.refresh();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton4 = new javax.swing.JButton();
        ButtonCheckIn = new javax.swing.JButton();
        TabbedPaneRooms = new javax.swing.JTabbedPane();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        textBoxReservationNumber = new javax.swing.JTextField();
        textBoxNumberOfGuests = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        textAreaComment = new javax.swing.JTextArea();
        DateChooserArrival = new datechooser.beans.DateChooserCombo();
        DateChooserDeparture = new datechooser.beans.DateChooserCombo();

        jButton4.setText("Abort");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        ButtonCheckIn.setText("Check In");
        ButtonCheckIn.setEnabled(false);
        ButtonCheckIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonCheckInActionPerformed(evt);
            }
        });

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

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Reservation details"));

        jLabel1.setText("Arrival:");

        jLabel3.setText("Reservation no:");

        jLabel4.setText("Number of guests:");

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

        jLabel2.setText("Comment:");

        jLabel5.setText("Departure:");

        textAreaComment.setColumns(20);
        textAreaComment.setLineWrap(true);
        textAreaComment.setRows(5);
        textAreaComment.setWrapStyleWord(true);
        jScrollPane2.setViewportView(textAreaComment);

        DateChooserArrival.addCommitListener(new datechooser.events.CommitListener() {
            public void onCommit(datechooser.events.CommitEvent evt) {
                DateChooserArrivalOnCommit(evt);
            }
        });

        DateChooserDeparture.addCommitListener(new datechooser.events.CommitListener() {
            public void onCommit(datechooser.events.CommitEvent evt) {
                DateChooserDepartureOnCommit(evt);
            }
        });

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
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(textBoxReservationNumber, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                    .addComponent(DateChooserArrival, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(DateChooserDeparture, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(79, 79, 79)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textBoxNumberOfGuests, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(256, 256, 256))
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
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(DateChooserArrival, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(DateChooserDeparture, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ButtonCheckIn)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton4))
                    .addComponent(TabbedPaneRooms)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 744, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(TabbedPaneRooms, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ButtonCheckIn)
                    .addComponent(jButton1)
                    .addComponent(jButton4))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton4ActionPerformed
    {//GEN-HEADEREND:event_jButton4ActionPerformed
        this.TabbedPaneRooms.setTitleAt(1, "New Title");
        rooms.get(1).setTabIcon(new ImageIcon(CheckinTwo.class.getClassLoader().getResource("resources/images/gh1.png")));
    }//GEN-LAST:event_jButton4ActionPerformed

    private void ButtonCheckInActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_ButtonCheckInActionPerformed
    {//GEN-HEADEREND:event_ButtonCheckInActionPerformed
        try
        {

            doTheCheckIn();
        }
        catch (NoPriceDefinedException ex)
        {
            // TODO message Box
            Logger.getLogger(CheckinTwo.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (CouldNotSaveException ex)
        {
            // TODO message Box
            Logger.getLogger(CheckinTwo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ButtonCheckInActionPerformed

    private void TabbedPaneRoomsCaretPositionChanged(java.awt.event.InputMethodEvent evt)//GEN-FIRST:event_TabbedPaneRoomsCaretPositionChanged
    {//GEN-HEADEREND:event_TabbedPaneRoomsCaretPositionChanged
   }//GEN-LAST:event_TabbedPaneRoomsCaretPositionChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton1ActionPerformed
    {//GEN-HEADEREND:event_jButton1ActionPerformed
        cigc.back();        // TODO add your handling code here:
        ((CardLayout)cigc.getContentpane().getLayout()).previous(cigc.getContentpane());
    }//GEN-LAST:event_jButton1ActionPerformed

    private void textBoxReservationNumberActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_textBoxReservationNumberActionPerformed
    {//GEN-HEADEREND:event_textBoxReservationNumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textBoxReservationNumberActionPerformed

    private void textBoxNumberOfGuestsActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_textBoxNumberOfGuestsActionPerformed
    {//GEN-HEADEREND:event_textBoxNumberOfGuestsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textBoxNumberOfGuestsActionPerformed

    private void DateChooserArrivalOnCommit(datechooser.events.CommitEvent evt)//GEN-FIRST:event_DateChooserArrivalOnCommit
    {//GEN-HEADEREND:event_DateChooserArrivalOnCommit
        cigc.changeInformation(DateChooserArrival.getSelectedDate().getTime(), DateChooserDeparture.getSelectedDate().getTime());
        updateRooms();
    }//GEN-LAST:event_DateChooserArrivalOnCommit

    private void DateChooserDepartureOnCommit(datechooser.events.CommitEvent evt)//GEN-FIRST:event_DateChooserDepartureOnCommit
    {//GEN-HEADEREND:event_DateChooserDepartureOnCommit
        cigc.changeInformation(DateChooserArrival.getSelectedDate().getTime(), DateChooserDeparture.getSelectedDate().getTime());
        updateRooms();
    }//GEN-LAST:event_DateChooserDepartureOnCommit
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonCheckIn;
    private datechooser.beans.DateChooserCombo DateChooserArrival;
    private datechooser.beans.DateChooserCombo DateChooserDeparture;
    private javax.swing.JTabbedPane TabbedPaneRooms;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea textAreaComment;
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

    private void doTheCheckIn() throws NoPriceDefinedException, CouldNotSaveException
    {
        for (RoomPanel room : rooms)
        {
            for (GuestPanel guest : room.getGuests())
            {
                GuestData guestData = cigc.addGuest(guest.getFirstName(), guest.getLastName(), guest.getGender(), guest.getBirthday(),
                        guest.getStreet(), guest.getCity(), guest.getZip(), guest.getEmail(), guest.getPhoneNumber(), guest.getFax(), guest.getCountry());
                cigc.assignRoom(room.getRoomIndex(), guestData);
            }
        }
        CheckInController cic = CheckInController.getInstance();
        cic.saveData();
    }

    private ActionListener getRoomPannelAddListener()
    {
        return new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                /*
                 * ButtonIconTabComponent iconTab = new ButtonIconTabComponent(TabbedPaneRooms, new ImageIcon(CheckinTwo.class.getClassLoader().getResource("resources/images/rotes_x.gif")));
                 * RoomPanel room = new RoomPanel();
                 * rooms.add(room);
                 * room.setTabComponent(iconTab);
                 * room.setRoomIndex(cigc.addRoomSelection());
                 * room.init();
                 *
                 * TabbedPaneRooms.add(room, TabbedPaneRooms.getTabCount() - 1);
                 *
                 * TabbedPaneRooms.setTitleAt(TabbedPaneRooms.getTabCount() - 2, "Room " + (TabbedPaneRooms.getTabCount() - 1));
                 *
                 *
                 * TabbedPaneRooms.setTabComponentAt(TabbedPaneRooms.getTabCount() - 2, iconTab);
                 */
                addNewRoomPanel(true);

            }
        };
    }

    private void addNewRoomPanel()
    {
        addNewRoomPanel(false);
    }

    private void addNewRoomPanel(boolean buttonClick)
    {
        int add = 0;
        if (buttonClick)
        {
            add = 1;
        }
        ButtonIconTabComponent iconTab = new ButtonIconTabComponent(TabbedPaneRooms,
                new ImageIcon(CheckinTwo.class.getClassLoader().getResource("resources/images/rotes_x.gif")), rooms);
        RoomPanel room = new RoomPanel();
        rooms.add(room);
        room.setTabComponent(iconTab);
        room.setRoomIndex(cigc.addRoomSelection());
        room.init();

        TabbedPaneRooms.add(room, TabbedPaneRooms.getTabCount() - add);

        TabbedPaneRooms.setTitleAt(TabbedPaneRooms.getTabCount() - add - 1, "Room " + (TabbedPaneRooms.getTabCount() - add));


        TabbedPaneRooms.setTabComponentAt(TabbedPaneRooms.getTabCount() - 1 - add, iconTab);
    }

    private boolean isFinished()
    {
        for (RoomPanel room : rooms)
        {
            if (!room.isFinished())
            {
                return false;
            }
        }
        return true;
    }
}
