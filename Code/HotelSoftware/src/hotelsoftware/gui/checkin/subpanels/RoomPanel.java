/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.gui.checkin.subpanels;

import hotelsoftware.gui.checkin.CheckInGuiControler;
import hotelsoftware.gui.misc.ButtonIconTabComponent;
import hotelsoftware.gui.misc.ButtonTabComponent;
import hotelsoftware.gui.misc.ButtonTabComponentPlus;
import hotelsoftware.model.domain.reservation.ReservationData;
import hotelsoftware.model.domain.room.CategoryData;
import hotelsoftware.model.domain.room.RoomData;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;

/**
 *
 * @author Johannes
 */
public class RoomPanel extends javax.swing.JPanel
{
    private ReservationData reservation;
    private CheckInGuiControler cigc = CheckInGuiControler.getInstance();
    private int roomIndex;
    private ButtonIconTabComponent iconTab;
    /**
     * Creates new form RoomPanel
     */
    public RoomPanel(ButtonIconTabComponent iconTab)
    {
        this.iconTab = iconTab;
        reservation = cigc.getSelectedReservation();
        initComponents();
        init();
    }
    int i;

    private void init()
    {
        //############# DropDowns
        ComboBoxCategories.removeAllItems();
        for (CategoryData data : cigc.getCategories())
        {
            ComboBoxCategories.addItem(data.getName());
        }
        ComboBoxCategories.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                updateComboBoxRooms((String) ComboBoxCategories.getSelectedItem());
            }

            private void updateComboBoxRooms(String string)
            {
                System.out.println(string);
                ComboBoxFreeRooms.removeAll();
                CategoryData cd = cigc.getCategories().toArray(new CategoryData[0])[ComboBoxCategories.getSelectedIndex()];
                for (RoomData data : cigc.changeRoomCategory(roomIndex, cd))
                {
                    ComboBoxFreeRooms.addItem(data.getNumber());
                }
            }
        });
 
     /*  ComboBoxCategories.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                cigc.changeRoom(roomIndex, (String) ComboBoxFreeRooms.getSelectedItem());
            }
        });*/
        //############ Guests
        for (i = 0; i < cigc.getRoomData(roomIndex).getCategoryData().getBedCount(); i++)
        {
            TabbedPaneGuests.addTab("Guest " + (i + 1), new GuestPanel());
            TabbedPaneGuests.setTabComponentAt(i, new ButtonTabComponent(TabbedPaneGuests));
        }
        JPanel pPanel = new JPanel();
        TabbedPaneGuests.add("", pPanel);
        TabbedPaneGuests.setTabComponentAt(TabbedPaneGuests.getTabCount() - 1,
                new ButtonTabComponentPlus(TabbedPaneGuests, GuestPanel.class, "Guest"));

    }

    public int getRoomIndex()
    {
        return roomIndex;
    }
    
    public void setTabIcon(ImageIcon icon){
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
                    .addComponent(TabbedPaneGuests, javax.swing.GroupLayout.DEFAULT_SIZE, 791, Short.MAX_VALUE)
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
}
