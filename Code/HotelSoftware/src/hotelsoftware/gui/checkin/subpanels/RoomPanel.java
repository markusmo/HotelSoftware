/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.gui.checkin.subpanels;

import hotelsoftware.controller.checkin.NoRoomsAvailableException;
import hotelsoftware.controller.checkin.NoRoomsInCategoryAvailableException;
import hotelsoftware.controller.checkin.CheckInGuiControler;
import hotelsoftware.gui.checkin.CheckInMain;
import hotelsoftware.gui.misc.ButtonIconTabComponent;
import hotelsoftware.gui.misc.ButtonTabComponentPlus;
import hotelsoftware.controller.data.parties.GuestData;
import hotelsoftware.controller.data.room.RoomCategoryData;
import hotelsoftware.controller.data.room.RoomData;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Dieses Panel ist dazu da, um einen Gast zu einem Raum einer Kategorie zuzuweisen.
 *
 * @author Johannes
 */
public class RoomPanel extends javax.swing.JPanel
{
    private CheckInGuiControler cigc = CheckInGuiControler.getInstance();
    private int roomIndex;
    private ButtonIconTabComponent iconTab;
    private java.util.List<GuestPanel> guests = new LinkedList<GuestPanel>();

    /**
     * Creates new form RoomPanel
     */
    public RoomPanel()
    {
        initComponents();
        //init();
    }
    int i;

    /**
     * Diese Methode aktualisiert die Checkbox
     * @param string
     */
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
        for (RoomCategoryData data : cigc.getAllCategories())
        {
            ComboBoxCategories.addItem(data);
        }

        try
        {
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
            }

            JPanel pPanel = new JPanel();
            TabbedPaneGuests.add("", pPanel);
            TabbedPaneGuests.setTabComponentAt(TabbedPaneGuests.getTabCount() - 1,
                    new ButtonTabComponentPlus(getGuestPannelAddListener()));
            TabbedPaneGuests.setEnabledAt(TabbedPaneGuests.getTabCount() - 1, false);
        }
        catch (NoRoomsInCategoryAvailableException ex)
        {
            //Alternativen Raum verwenden
            JOptionPane.showMessageDialog(this, "No rooms in reserved category available. Another category was chosen.");
            
            ComboBoxCategories.setSelectedItem(ex.getCategory());
            updateComboBoxRooms(ex.getRoom().toString());
            
            for (i = 0; i < ex.getCategory().getBedCount(); i++)
            {
                addNewGuestPanel();
            }
        }
        catch (NoRoomsAvailableException ex)
        {
            JOptionPane.showMessageDialog(this, "No rooms are available", "No rooms available", JOptionPane.WARNING_MESSAGE);

            //Wieder zurück zum Check In
            cigc.back();
            cigc.getContentpane().removeAll();
            cigc.getContentpane().add(new CheckInMain(), BorderLayout.CENTER);
            ((CardLayout) cigc.getContentpane().getLayout()).next(cigc.getContentpane());
            cigc.getContentpane().repaint();
        }
    }

    public int getRoomIndex()
    {
        return roomIndex;
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
     * Überprüft, ob alle Gäste-Tabs fertig ausgefüllt sind
     *
     * @return
     * True, wenn fertig, False, wenn nicht fertig
     */
    public boolean isFinished()
    {
        boolean b = true;
        for (GuestPanel guest : guests)
        {
            if (!guest.isFinished())
            {
                b = false;
            }
        }
        if (b)
        {
            iconTab.setFinished();
        }
        else
        {
            iconTab.setUnFinished();
        }
        return b;
    }

    /**
     * Dieser Actionlistener kann ein neues GuestPanel hinzufügen.
     *
     * @return
     * den Actionlistener für das GuestPanel
     */
    public ActionListener getGuestPannelAddListener()
    {
        return new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
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

    /**
     *diese Methode ermöglicht es ein neuen Gast für ein Zimmer hinzuzufügen
     * @param buttonClick Falls ein neues GuestPanel über den Plusbutton hinzugefügt wird darf dieses nicht am schluss pasieren.
     */
    private void addNewGuestPanel(boolean buttonClick)
    {
        int add = 0;
        if (buttonClick)
        {
            add = 1;
        }
        ButtonIconTabComponent tabComponent = new ButtonIconTabComponent(TabbedPaneGuests, guests);
        GuestPanel guest = new GuestPanel();
        guests.add(guest);
        guest.setTabComponent(tabComponent);


        TabbedPaneGuests.add(guest, TabbedPaneGuests.getTabCount() - add);

        TabbedPaneGuests.setTitleAt(TabbedPaneGuests.getTabCount() - add - 1, "Guest " + (TabbedPaneGuests.getTabCount() - add));


        TabbedPaneGuests.setTabComponentAt(TabbedPaneGuests.getTabCount() - 1 - add, tabComponent);
    }
/**
 * Diese Methode fügt einen Gast aus einer Reservierung hinzu
 */
    public void inserGuestsFromReservation()
    {
        Collection<GuestData> guestDatas = cigc.getGuests();
        if (!guestDatas.isEmpty())
        {
            Iterator<GuestPanel> gp = guests.iterator();
            for (Iterator<GuestData> it = guestDatas.iterator(); it.hasNext() && gp.hasNext();)
            {
                GuestData g = it.next();
                GuestPanel gPanel = gp.next();
                gPanel.addGuest(g);
            }
        }
    }
}
