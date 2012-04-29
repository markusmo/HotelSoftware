package hotelsoftware.gui.checkin;

import hotelsoftware.checkin.CouldNotSaveException;
import hotelsoftware.gui.GuiController;
import hotelsoftware.gui.checkin.subpanels.GuestPanel;
import hotelsoftware.gui.checkin.subpanels.RoomPanel;
import hotelsoftware.gui.checkin.subpanels.SuccesPanel;
import hotelsoftware.gui.home.HomePanel;
import hotelsoftware.gui.misc.ButtonIconTabComponent;
import hotelsoftware.gui.misc.ButtonTabComponentPlus;
import hotelsoftware.model.domain.parties.data.GuestData;
import hotelsoftware.model.domain.reservation.data.ReservationData;
import hotelsoftware.model.domain.reservation.data.ReservationItemData;
import hotelsoftware.model.domain.room.NoPriceDefinedException;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
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
    private CheckInGuiControler cigc;
    private List<RoomPanel> rooms = new LinkedList<RoomPanel>();

    /**
     * Creates new form CheckInGUInr2
     */
    public CheckinTwo()
    {
        initComponents();
        cigc = CheckInGuiControler.getInstance();
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
            int oldi = i;
            for (i = oldi; i < data.getAmount() + oldi; i++)
            {
                addNewRoomPanel();
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
                if (isFinished())
                {
                    ButtonCheckIn.setEnabled(true);
                }
            }
        });
        inserGuestsFromReservation();
        StartUpdater();
    }

    private void StartUpdater()
    {
        new Updater().start();
    }

    private void updateRooms()
    {
        for (RoomPanel r : rooms)
        {
            r.refresh();
        }
    }

    public void initWalkIn()
    {
        TabbedPaneRooms.removeAll();
        cigc.setRoomTabPane(TabbedPaneRooms);
        //################### Set TextBoxes
        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        textAreaComment.setText("");
        DateChooserArrival.setDateFormat(df);
        DateChooserDeparture.setDateFormat(df);

        Date today = new Date();

        DateChooserArrival.setSelectedDate(dateToCalendar(today));
        DateChooserDeparture.setSelectedDate(dateToCalendar(today));

        cigc.changeInformation(today, today);

        textBoxNumberOfGuests.setText("");
        textBoxReservationNumber.setText("");

        //################### Create Panel

        addNewRoomPanel();
        cigc.addRoomSelection();

        JPanel pPanel = new JPanel();
        TabbedPaneRooms.add("", pPanel);
        TabbedPaneRooms.setTabComponentAt(TabbedPaneRooms.getTabCount() - 1,
                new ButtonTabComponentPlus(getRoomPannelAddListener()));
        TabbedPaneRooms.setEnabledAt(TabbedPaneRooms.getTabCount() - 1, false);

        TabbedPaneRooms.addChangeListener(new ChangeListener()
        {
            public void stateChanged(ChangeEvent e)
            {
                if (isFinished())
                {
                    ButtonCheckIn.setEnabled(true);
                }
            }
        });
        StartUpdater();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonAbort = new javax.swing.JButton();
        ButtonCheckIn = new javax.swing.JButton();
        TabbedPaneRooms = new javax.swing.JTabbedPane();
        buttonBack = new javax.swing.JButton();
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

        buttonAbort.setText("Abort");
        buttonAbort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAbortActionPerformed(evt);
            }
        });

        ButtonCheckIn.setText("Check In");
        ButtonCheckIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonCheckInActionPerformed(evt);
            }
        });

        buttonBack.setText("Back");
        buttonBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBackActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Reservation details"));

        jLabel1.setText("Arrival:");

        jLabel3.setText("Reservation no:");

        jLabel4.setText("Number of guests:");

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textBoxNumberOfGuests, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonBack)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonAbort))
                    .addComponent(TabbedPaneRooms)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                    .addComponent(buttonBack)
                    .addComponent(buttonAbort))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

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

    private void ButtonCheckInActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_ButtonCheckInActionPerformed
    {//GEN-HEADEREND:event_ButtonCheckInActionPerformed
        try
        {
            doTheCheckIn();
            cigc.getContentpane().add(new SuccesPanel(), BorderLayout.CENTER);
            ((CardLayout) cigc.getContentpane().getLayout()).next(cigc.getContentpane());
            cigc.getContentpane().repaint();
        }
        catch (NoPriceDefinedException ex)
        {
            // TODO message Box
            JOptionPane.showConfirmDialog(this, ex.getMessage(), "Error", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(CheckinTwo.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (CouldNotSaveException ex)
        {
            // TODO message Box
            JOptionPane.showConfirmDialog(this, ex.getMessage(), "Error", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(CheckinTwo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ButtonCheckInActionPerformed

    private void buttonBackActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_buttonBackActionPerformed
    {//GEN-HEADEREND:event_buttonBackActionPerformed
        cigc.back();        // TODO add your handling code here:
        ((CardLayout) cigc.getContentpane().getLayout()).previous(cigc.getContentpane());
    }//GEN-LAST:event_buttonBackActionPerformed

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
    private javax.swing.JButton buttonAbort;
    private javax.swing.JButton buttonBack;
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

        cigc.saveData();
    }

    private ActionListener getRoomPannelAddListener()
    {
        return new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
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
        ButtonIconTabComponent iconTab = new ButtonIconTabComponent(TabbedPaneRooms, rooms);
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
        boolean b = true;
        for (RoomPanel room : rooms)
        {
            if (!room.isFinished())
            {
                b = false;
            }
        }
        return b;
    }

    public void inserGuestsFromReservation()
    {
        Collection<GuestData> guestDatas = cigc.getGuests();
        if (!guestDatas.isEmpty())
        {
            Iterator<RoomPanel> rp = rooms.iterator();

            for (Iterator<GuestData> it = guestDatas.iterator(); it.hasNext() && rp.hasNext();)
            {

                RoomPanel rPanel = rp.next();
                Iterator<GuestPanel> ip = rPanel.getGuests().iterator();
                do
                {
                    GuestData g = it.next();
                    GuestPanel gPanel = ip.next();
                    gPanel.addGuest(g);
                } while (ip.hasNext() && it.hasNext());
            }
        }
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
            while (!GuiController.getInstance().checkStateForSwitching())
            {
                try
                {
                    Thread.sleep(500);
                    isFinished();
                }
                catch (InterruptedException ex)
                {
                    Logger.getLogger(HomePanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
    }
}
