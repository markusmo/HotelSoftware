/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.gui.invoice.invoiceHome;

import hotelsoftware.controller.data.service.HabitationData;
import hotelsoftware.gui.invoice.InvoiceGUIControler;
import hotelsoftware.gui.invoice.buttons.AbortButton;
import hotelsoftware.gui.invoice.buttons.IntermediatInvoiceButton;
import hotelsoftware.gui.invoice.payment.ControlsSetter;
import java.awt.Component;
import java.awt.Container;
import java.awt.FocusTraversalPolicy;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.Collection;
import java.util.LinkedList;
import javax.swing.*;

/**
 * 
 * @author Lins Christian (christian.lins87@gmail.com)
 * 
 * Das ist der Start-Screen für den Use case "Rechnung erstellen"
 * Hier können die Aufentahlte ausgewählt werden
 */
public class InvoiceHome extends javax.swing.JPanel implements ControlsSetter
{
    private InvoiceGUIControler ctrl = InvoiceGUIControler.getInstance();
    private Collection<HabitationData> habitationsData;
    
    private IntermediatInvoiceButton iiB;
    private AbortButton aB;
        
    private FocusTraversalPolicy focusTraversal;
    private LinkedList<Component> order;

    /**
     * Creates new form CheckInMain
     */
    public InvoiceHome()
    {
        initComponents();
        init();
    }

    private void init()
    {        
       registerActionMap();
               
       // Fokus und Tab-Order
       order = new LinkedList();
       order.add(roomNrTextbox);
       order.add(lnameTextBox);
       order.add(fnameTextBox);  
       
       focusTraversal = new FocusTraversalPolicy() {
           
           private Component currentComponent = roomNrLabel;
           

            @Override
            public Component getComponentAfter(Container aContainer, Component aComponent)
            {
                int current = order.indexOf(currentComponent);
                
                if (order.size() < current + 1) {
                    return order.get(current + 1);
                } else {
                    return getFirstComponent(aContainer);
                }
                
            }

            @Override
            public Component getComponentBefore(Container aContainer, Component aComponent)
            {
                int current = order.indexOf(currentComponent);
                
                if (current - 1 < 0) {
                    return getLastComponent(aContainer);
                } else {
                    return order.get(current - 1);                    
                }
            }

            @Override
            public Component getFirstComponent(Container aContainer)
            {
                return order.getFirst();
            }

            @Override
            public Component getLastComponent(Container aContainer)
            {
                return order.getLast();
            }

            @Override
            public Component getDefaultComponent(Container aContainer)
            {
                return getFirstComponent(aContainer);
            }
        };
       
       
       this.setFocusTraversalPolicy(focusTraversal);
       this.setFocusCycleRoot(true);       
       roomNrTextbox.requestFocusInWindow();
    }

    /**
     * die verfügbaren Aufgenthalte werden in die ausgewählten Aufenthalte überführt
     */
    private void chooseAll()
    {
        selectedHabitations.setTable(availableHabitations.clearTable());
        checkSelected();
    }

    /**
     * die verfügbaren und markierten Aufgenthalte werden in die ausgewählten Aufenthalte überführt
     */
    private void chooseSelected()
    {
        selectedHabitations.setTable(availableHabitations.getSelectedRows());
        availableHabitations.removeSelectedRows();
        checkSelected();
    }

    /**
     * die ausgewählten Aufenthalte kommen zurück in die verfügbaren
     */
    private void dropAll()
    {
        Collection<HabitationData> removed = selectedHabitations.clearTable();
        availableHabitations.setTable(removed);
        checkSelected();
    }

    /**
     * die ausgewählten und markierten Aufenthalte kommen zurück in die verfügbaren
     */
    private void dropSelected()
    {
        Collection<HabitationData> removed = selectedHabitations.removeSelectedRows();
        availableHabitations.setTable(removed);
        checkSelected();
    }

    /**
     * sucht Aufenthalte und gibt diese im Anschluss in den verfügbaren Aufenthalten aus
     */
    private void searchHabitations()
    {
        // lösche aktuellen Table
        availableHabitations.setTable(null);
        
        String lname = lnameTextBox.getText();
        String fname = fnameTextBox.getText();
        String roomNr = roomNrTextbox.getText();
        
        Collection<HabitationData> habitations = ctrl.search(fname, lname, roomNr);
       
        if (habitations != null) {
             availableHabitations.setTable(habitations);
             habitationsData = habitations;
        }
       //TODO availableHabitations.requestFocusInWindow();
    }
    
    
    /**
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        invoiceHomeTables = new javax.swing.JPanel();
        availableHabitations = new hotelsoftware.gui.invoice.invoiceHome.habitations();
        dropSelectButtons = new javax.swing.JPanel();
        chooseAll = new javax.swing.JButton();
        chooseSelection = new javax.swing.JButton();
        dropSelected = new javax.swing.JButton();
        dropAll = new javax.swing.JButton();
        selectedHabitations = new hotelsoftware.gui.invoice.invoiceHome.habitations();
        searchPanel = new javax.swing.JPanel();
        lnameLabel = new javax.swing.JLabel();
        fnameLabel = new javax.swing.JLabel();
        roomNrLabel = new javax.swing.JLabel();
        fnameTextBox = new javax.swing.JTextField();
        roomNrTextbox = new javax.swing.JTextField();
        lnameTextBox = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        seperator = new javax.swing.JSeparator();

        setPreferredSize(new java.awt.Dimension(1220, 500));
        setRequestFocusEnabled(false);
        setLayout(new java.awt.BorderLayout());

        invoiceHomeTables.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        invoiceHomeTables.add(availableHabitations, gridBagConstraints);

        dropSelectButtons.setMaximumSize(new java.awt.Dimension(32767, 100));
        dropSelectButtons.setPreferredSize(new java.awt.Dimension(80, 320));

        chooseAll.setText("<html><b  style=\"font: 16px\">>></b></html>");
        chooseAll.setToolTipText("Shortcut: Strg+Shift+R");
        chooseAll.setActionCommand("");
        chooseAll.setMaximumSize(new java.awt.Dimension(73, 73));
        chooseAll.setMinimumSize(new java.awt.Dimension(73, 73));
        chooseAll.setPreferredSize(new java.awt.Dimension(80, 80));
        chooseAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseAllActionPerformed(evt);
            }
        });

        chooseSelection.setText("<html><b  style=\"font: 16px\">></b></html>");
        chooseSelection.setToolTipText("Shortcut: Strg+R");
        chooseSelection.setActionCommand("");
        chooseSelection.setMaximumSize(new java.awt.Dimension(73, 73));
        chooseSelection.setMinimumSize(new java.awt.Dimension(73, 73));
        chooseSelection.setPreferredSize(new java.awt.Dimension(80, 80));
        chooseSelection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseSelectionActionPerformed(evt);
            }
        });

        dropSelected.setText("<html><b  style=\"font: 16px\">&lt;</b></html>");
        dropSelected.setToolTipText("Shortcut: Strg+L");
        dropSelected.setActionCommand("");
        dropSelected.setMaximumSize(new java.awt.Dimension(73, 73));
        dropSelected.setMinimumSize(new java.awt.Dimension(73, 73));
        dropSelected.setPreferredSize(new java.awt.Dimension(80, 80));
        dropSelected.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dropSelectedActionPerformed(evt);
            }
        });

        dropAll.setText("<html><b style=\"font: 16px\">&lt;&lt;</b></html>");
        dropAll.setToolTipText("Shortcut: Strg+Shift+L");
        dropAll.setActionCommand("");
        dropAll.setMaximumSize(new java.awt.Dimension(73, 73));
        dropAll.setMinimumSize(new java.awt.Dimension(73, 73));
        dropAll.setPreferredSize(new java.awt.Dimension(80, 80));
        dropAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dropAllActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout dropSelectButtonsLayout = new javax.swing.GroupLayout(dropSelectButtons);
        dropSelectButtons.setLayout(dropSelectButtonsLayout);
        dropSelectButtonsLayout.setHorizontalGroup(
            dropSelectButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dropSelectButtonsLayout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(dropSelectButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dropSelected, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dropAll, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(dropSelectButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(chooseAll, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(chooseSelection, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        dropSelectButtonsLayout.setVerticalGroup(
            dropSelectButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dropSelectButtonsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chooseAll, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chooseSelection, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(dropSelected, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(dropAll, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        invoiceHomeTables.add(dropSelectButtons, new java.awt.GridBagConstraints());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        invoiceHomeTables.add(selectedHabitations, gridBagConstraints);

        add(invoiceHomeTables, java.awt.BorderLayout.CENTER);

        lnameLabel.setText("Last name:");

        fnameLabel.setText("First name:");

        roomNrLabel.setText("Room Nr");

        fnameTextBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fnameTextBoxActionPerformed(evt);
            }
        });

        roomNrTextbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roomNrTextboxActionPerformed(evt);
            }
        });

        lnameTextBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lnameTextBoxActionPerformed(evt);
            }
        });

        searchButton.setText("Search");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout searchPanelLayout = new javax.swing.GroupLayout(searchPanel);
        searchPanel.setLayout(searchPanelLayout);
        searchPanelLayout.setHorizontalGroup(
            searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, searchPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(seperator)
                    .addGroup(searchPanelLayout.createSequentialGroup()
                        .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(searchPanelLayout.createSequentialGroup()
                                .addComponent(lnameLabel)
                                .addGap(35, 35, 35)
                                .addComponent(lnameTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(searchPanelLayout.createSequentialGroup()
                                .addComponent(fnameLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(fnameTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(searchPanelLayout.createSequentialGroup()
                                .addGap(68, 68, 68)
                                .addComponent(roomNrLabel)
                                .addGap(35, 35, 35)
                                .addComponent(roomNrTextbox, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 906, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, searchPanelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(searchButton)))))
                .addContainerGap())
        );
        searchPanelLayout.setVerticalGroup(
            searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, searchPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lnameTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(roomNrLabel)
                        .addComponent(roomNrTextbox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lnameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(searchPanelLayout.createSequentialGroup()
                        .addComponent(fnameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(2, 2, 2))
                    .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(fnameTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(searchButton)))
                .addGap(9, 9, 9)
                .addComponent(seperator, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(searchPanel, java.awt.BorderLayout.NORTH);
    }// </editor-fold>//GEN-END:initComponents

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_searchButtonActionPerformed
    {//GEN-HEADEREND:event_searchButtonActionPerformed
        searchHabitations();
    }//GEN-LAST:event_searchButtonActionPerformed

    private void fnameTextBoxActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_fnameTextBoxActionPerformed
    {//GEN-HEADEREND:event_fnameTextBoxActionPerformed
        searchHabitations();
    }//GEN-LAST:event_fnameTextBoxActionPerformed

    private void lnameTextBoxActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_lnameTextBoxActionPerformed
    {//GEN-HEADEREND:event_lnameTextBoxActionPerformed
        searchHabitations();
    }//GEN-LAST:event_lnameTextBoxActionPerformed

    private void roomNrTextboxActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_roomNrTextboxActionPerformed
    {//GEN-HEADEREND:event_roomNrTextboxActionPerformed
        searchHabitations();
    }//GEN-LAST:event_roomNrTextboxActionPerformed

    private void dropAllActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_dropAllActionPerformed
    {//GEN-HEADEREND:event_dropAllActionPerformed
        dropAll();
    }//GEN-LAST:event_dropAllActionPerformed

    private void dropSelectedActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_dropSelectedActionPerformed
    {//GEN-HEADEREND:event_dropSelectedActionPerformed
        dropSelected();
    }//GEN-LAST:event_dropSelectedActionPerformed

    private void chooseSelectionActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_chooseSelectionActionPerformed
    {//GEN-HEADEREND:event_chooseSelectionActionPerformed
        chooseSelected();
    }//GEN-LAST:event_chooseSelectionActionPerformed

    private void chooseAllActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_chooseAllActionPerformed
    {//GEN-HEADEREND:event_chooseAllActionPerformed
        chooseAll();
   }//GEN-LAST:event_chooseAllActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private hotelsoftware.gui.invoice.invoiceHome.habitations availableHabitations;
    private javax.swing.JButton chooseAll;
    private javax.swing.JButton chooseSelection;
    private javax.swing.JButton dropAll;
    private javax.swing.JPanel dropSelectButtons;
    private javax.swing.JButton dropSelected;
    private javax.swing.JLabel fnameLabel;
    private javax.swing.JTextField fnameTextBox;
    private javax.swing.JPanel invoiceHomeTables;
    private javax.swing.JLabel lnameLabel;
    private javax.swing.JTextField lnameTextBox;
    private javax.swing.JLabel roomNrLabel;
    private javax.swing.JTextField roomNrTextbox;
    private javax.swing.JButton searchButton;
    private javax.swing.JPanel searchPanel;
    private hotelsoftware.gui.invoice.invoiceHome.habitations selectedHabitations;
    private javax.swing.JSeparator seperator;
    // End of variables declaration//GEN-END:variables

    /**
     * Methode überprüft, ob bereits Aufenthalte ausgewählt wurden
     * wenn ja, dann wird der Button "IntermediatInvoice" enabled - es kann weitergeganen werden
     */
    private void checkSelected()
    {
        if (selectedHabitations.getRowCount() > 0) {
            iiB.setEnabled(true);
        } else {
            iiB.setEnabled(false);
        }
    }

    @Override
    public void setControls()
    {
        ctrl.clearControlPanel();
        
        aB = new AbortButton();
        ctrl.getDeconstructiveControlPanel().add(aB);
        
        iiB = new IntermediatInvoiceButton();
        ctrl.getConstructiveControlPanel().add(iiB);
        iiB.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                ctrl.setSelectedHabitations(selectedHabitations.getRows());
            }
        });
        iiB.setEnabled(false);
        checkSelected();
        ctrl.repaintControlPanel();
    }

    
    
     /**
     * Registriert die Shortcuts fuer die Buttons.
     */
    private void registerActionMap()
    {
        KeyStroke strgR = KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK);
        KeyStroke strgShiftR = KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK);
        KeyStroke strgL = KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_MASK);
        KeyStroke strgShiftL = KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK);
        KeyStroke enter = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
        
        InputMap map = ctrl.getInvoiceMainPanel().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        map.put(strgR, "strgR");
        map.put(strgShiftR, "strgShiftR");
        map.put(strgL, "strgL");
        map.put(strgShiftL, "strgShiftL");
        map.put(enter, "enter");
        
        Action strgRPressed = new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                chooseSelected();
            }
        };
        
        Action strgShiftRPressed = new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                chooseAll();
            }
        };

        Action strgLPressed = new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                dropSelected();                
            }
        };
        
        Action strgShiftLPressed = new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                dropAll();
            }
        };
        
        Action enterPressed = new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                searchHabitations();
            }
        };
        

        ActionMap amap = ctrl.getInvoiceMainPanel().getActionMap();
        amap.put("strgR", strgRPressed);
        amap.put("strgShiftR", strgShiftRPressed);
        amap.put("strgL", strgLPressed);
        amap.put("strgShiftL", strgShiftLPressed);
        amap.put("enter", enterPressed);

    }
}
