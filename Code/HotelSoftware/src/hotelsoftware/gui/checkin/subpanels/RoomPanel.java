/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.gui.checkin.subpanels;

import hotelsoftware.gui.checkin.CheckInGuiControler;
import hotelsoftware.gui.misc.ButtonIconTabComponent;
import hotelsoftware.gui.misc.ButtonTabComponent;
import hotelsoftware.gui.misc.ButtonTabComponentPlus;
import hotelsoftware.model.domain.reservation.data.ReservationData;
import hotelsoftware.model.domain.room.data.RoomCategoryData;
import hotelsoftware.model.domain.room.data.RoomData;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;

/**
 * Dieses Panel ist dazu da, um einen Gast zu einem Raum einer Kategorie zuzuweisen.
 *
 * @author Johannes
 */
public class RoomPanel extends javax.swing.JPanel
{
    private ReservationData reservation;
    private CheckInGuiControler cigc = CheckInGuiControler.getInstance();
    private int roomIndex;
    private ButtonIconTabComponent iconTab;
    private java.util.List<GuestPanel> guests = new LinkedList<GuestPanel>();

    /**
     * Creates new form RoomPanel
     */
    public RoomPanel()
    {
        this.reservation = cigc.getSelectedReservation();
        initComponents();
        //init();
    }
    int i;

    private void updateComboBoxRooms(String string)
    {
        System.out.println(string);
        ComboBoxFreeRooms.removeAllItems();
        RoomCategoryData cd = (RoomCategoryData) ComboBoxCategories.getSelectedItem();
        Collection<RoomData> roomdata = cigc.changeRoomCategory(roomIndex, cd);

        RoomData first = null;

        if (!roomdata.isEmpty())
        {
            for (RoomData data : roomdata)
            {
                if (first == null)
                {
                    first = data;
                }
                ComboBoxFreeRooms.addItem(data.getNumber());
            }
        }

        cigc.changeRoom(roomIndex, first);
    }

    public void refresh()
    {
        updateComboBoxRooms("");
    }

    public void init()
    {
        //############# DropDowns
        ComboBoxCategories.removeAllItems();
        RoomCategoryData first = null;
        for (RoomCategoryData data : cigc.getAllCategories())
        {
            if (first == null)
            {
                first = data;
            }

            ComboBoxCategories.addItem(data);
        }

        ComboBoxCategories.setSelectedItem(first);
        ComboBoxCategories.setSelectedItem(cigc.getRoomData(roomIndex).getCategoryData());
        updateComboBoxRooms(ComboBoxCategories.getSelectedItem().toString());

        ComboBoxCategories.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                updateComboBoxRooms(ComboBoxCategories.getSelectedItem().toString());
            }
        });
        ComboBoxFreeRooms.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if (ComboBoxFreeRooms.getItemCount() > 0)
                {
                    cigc.changeRoom(roomIndex, ComboBoxFreeRooms.getSelectedItem().toString());
                }
            }
        });

        //############ Guests
        for (i = 0; i < cigc.getRoomData(roomIndex).getCategoryData().getBedCount(); i++)
        {
            addNewGuestPanel();
            /*
             * ButtonTabComponent tabComponent = new ButtonTabComponent(TabbedPaneGuests, guests);
             * GuestPanel guest = new GuestPanel();
             * guest.setTabComponent(tabComponent);
             * guests.add(guest);
             * TabbedPaneGuests.addTab("Guest " + (i + 1), guest);
             * TabbedPaneGuests.setTabComponentAt(i, tabComponent);
             */
        }
        JPanel pPanel = new JPanel();
        TabbedPaneGuests.add("", pPanel);
        TabbedPaneGuests.setTabComponentAt(TabbedPaneGuests.getTabCount() - 1,
                new ButtonTabComponentPlus(getGuestPannelAddListener()));
        TabbedPaneGuests.setEnabledAt(TabbedPaneGuests.getTabCount() - 1, false);

    }

    public int getRoomIndex()
    {
        return roomIndex;
    }

    public void setTabIcon(ImageIcon icon)
    {
        this.iconTab.setImagePanel(icon);
    }

    public void setRoomIndex(int roomIndex)
    {
        this.roomIndex = roomIndex;
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

        ComboBoxFreeRooms = new javax.swing.JComboBox();
        TabbedPaneGuests = new javax.swing.JTabbedPane();
        ComboBoxCategories = new javax.swing.JComboBox();

        ComboBoxFreeRooms.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        TabbedPaneGuests.setTabPlacement(javax.swing.JTabbedPane.LEFT);

        ComboBoxCategories.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TabbedPaneGuests, javax.swing.GroupLayout.DEFAULT_SIZE, 485, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ComboBoxCategories, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(ComboBoxFreeRooms, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ComboBoxCategories, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ComboBoxFreeRooms, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(TabbedPaneGuests, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox ComboBoxCategories;
    private javax.swing.JComboBox ComboBoxFreeRooms;
    private javax.swing.JTabbedPane TabbedPaneGuests;
    // End of variables declaration//GEN-END:variables

    public void setTabComponent(ButtonIconTabComponent tabComponent)
    {
        this.iconTab = tabComponent;
    }

    /**
     * Überprüft ob in allen Guestpanels bereit sind.
     *
     * @return
     */
    public boolean isFinished()
    {
        for (GuestPanel guest : guests)
        {
            if (!guest.isFinished())
            {
                return false;
            }
        }
        return true;
    }

    /**
     * Dieser Actionlistener kann ein neues GuestPanel hinzufügen.
     *
     * @return
     */
    public ActionListener getGuestPannelAddListener()
    {
        return new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                /*
                 * ButtonTabComponent tabComponent = new ButtonTabComponent(TabbedPaneGuests, guests);
                 * GuestPanel guest = new GuestPanel();
                 * guest.setTabComponent(tabComponent);
                 * guests.add(guest);
                 *
                 * TabbedPaneGuests.add(guest, TabbedPaneGuests.getTabCount() - 1);
                 * TabbedPaneGuests.setTitleAt(TabbedPaneGuests.getTabCount() - 2, "Guest " + (TabbedPaneGuests.getTabCount() - 1));
                 *
                 *
                 * TabbedPaneGuests.setTabComponentAt(TabbedPaneGuests.getTabCount() - 2, tabComponent);
                 */
                addNewGuestPanel(true);
            }
        };
    }

    public java.util.List<GuestPanel> getGuests()
    {
        return guests;
    }

    private void addNewGuestPanel()
    {
        addNewGuestPanel(false);
    }

    private void addNewGuestPanel(boolean buttonClick)
    {
        int add = 0;
        if (buttonClick)
        {
            add = 1;
        }
        ButtonTabComponent tabComponent = new ButtonTabComponent(TabbedPaneGuests, guests);
        GuestPanel guest = new GuestPanel();
        guests.add(guest);
        guest.setTabComponent(tabComponent);


        TabbedPaneGuests.add(guest, TabbedPaneGuests.getTabCount() - add);

        TabbedPaneGuests.setTitleAt(TabbedPaneGuests.getTabCount() - add - 1, "Room " + (TabbedPaneGuests.getTabCount() - add));


        TabbedPaneGuests.setTabComponentAt(TabbedPaneGuests.getTabCount() - 1 - add, tabComponent);
    }
}
