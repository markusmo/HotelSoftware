/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.gui.invoice.home;

/**
 * 
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class InvoiceHome extends javax.swing.JPanel
{

    /**
     * Creates new form CheckInMain
     */
    public InvoiceHome()
    {
        initComponents();
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
        java.awt.GridBagConstraints gridBagConstraints;

        invoiceHomeTables = new javax.swing.JPanel();
        habitations1 = new org.netbeans.modules.form.InvalidComponent();
        dropSelectButtons = new javax.swing.JPanel();
        chooseAll = new javax.swing.JButton();
        chooseSelection = new javax.swing.JButton();
        dropSelected = new javax.swing.JButton();
        dropAll = new javax.swing.JButton();
        habitations2 = new org.netbeans.modules.form.InvalidComponent();
        searchPanel = new javax.swing.JPanel();
        label_lname = new javax.swing.JLabel();
        labelFname = new javax.swing.JLabel();
        label_roomRn = new javax.swing.JLabel();
        textBoxFname = new javax.swing.JTextField();
        textBoxRoomNumber = new javax.swing.JTextField();
        textBoxLname = new javax.swing.JTextField();
        buttonSearch = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();

        setPreferredSize(new java.awt.Dimension(1220, 500));
        setRequestFocusEnabled(false);
        setLayout(new java.awt.BorderLayout());

        invoiceHomeTables.setLayout(new java.awt.GridBagLayout());
        invoiceHomeTables.add(habitations1, new java.awt.GridBagConstraints());

        dropSelectButtons.setMaximumSize(new java.awt.Dimension(32767, 100));
        dropSelectButtons.setPreferredSize(new java.awt.Dimension(80, 240));

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
            .addGroup(dropSelectButtonsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dropSelectButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dropSelected, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dropAll, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(dropSelectButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(chooseAll, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(chooseSelection, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        dropSelectButtonsLayout.setVerticalGroup(
            dropSelectButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dropSelectButtonsLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(chooseAll, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chooseSelection, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(dropSelected, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(dropAll, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        invoiceHomeTables.add(dropSelectButtons, new java.awt.GridBagConstraints());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        invoiceHomeTables.add(habitations2, gridBagConstraints);

        add(invoiceHomeTables, java.awt.BorderLayout.CENTER);

        label_lname.setText("Last name:");

        labelFname.setText("First name:");

        label_roomRn.setText("Room Nr");

        textBoxFname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textBoxFnameActionPerformed(evt);
            }
        });

        textBoxRoomNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textBoxRoomNumberActionPerformed(evt);
            }
        });

        textBoxLname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textBoxLnameActionPerformed(evt);
            }
        });

        buttonSearch.setText("Search");
        buttonSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout searchPanelLayout = new javax.swing.GroupLayout(searchPanel);
        searchPanel.setLayout(searchPanelLayout);
        searchPanelLayout.setHorizontalGroup(
            searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, searchPanelLayout.createSequentialGroup()
                        .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(searchPanelLayout.createSequentialGroup()
                                .addComponent(label_lname)
                                .addGap(35, 35, 35)
                                .addComponent(textBoxLname, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(searchPanelLayout.createSequentialGroup()
                                .addComponent(labelFname)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(textBoxFname, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(68, 68, 68)
                        .addComponent(label_roomRn)
                        .addGap(35, 35, 35)
                        .addComponent(textBoxRoomNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 841, Short.MAX_VALUE)
                        .addComponent(buttonSearch))
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        searchPanelLayout.setVerticalGroup(
            searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, searchPanelLayout.createSequentialGroup()
                .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(searchPanelLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(buttonSearch)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(searchPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(textBoxLname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(label_roomRn)
                                .addComponent(textBoxRoomNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(label_lname, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(searchPanelLayout.createSequentialGroup()
                                .addComponent(labelFname, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(2, 2, 2))
                            .addComponent(textBoxFname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 1, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(searchPanel, java.awt.BorderLayout.NORTH);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonSearchActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_buttonSearchActionPerformed
    {//GEN-HEADEREND:event_buttonSearchActionPerformed
        // Search Button
        
    }//GEN-LAST:event_buttonSearchActionPerformed

    private void textBoxFnameActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_textBoxFnameActionPerformed
    {//GEN-HEADEREND:event_textBoxFnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textBoxFnameActionPerformed

    private void textBoxLnameActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_textBoxLnameActionPerformed
    {//GEN-HEADEREND:event_textBoxLnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textBoxLnameActionPerformed

    private void textBoxRoomNumberActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_textBoxRoomNumberActionPerformed
    {//GEN-HEADEREND:event_textBoxRoomNumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textBoxRoomNumberActionPerformed

    private void dropAllActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_dropAllActionPerformed
    {//GEN-HEADEREND:event_dropAllActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dropAllActionPerformed

    private void dropSelectedActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_dropSelectedActionPerformed
    {//GEN-HEADEREND:event_dropSelectedActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dropSelectedActionPerformed

    private void chooseSelectionActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_chooseSelectionActionPerformed
    {//GEN-HEADEREND:event_chooseSelectionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chooseSelectionActionPerformed

    private void chooseAllActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_chooseAllActionPerformed
    {//GEN-HEADEREND:event_chooseAllActionPerformed
        // TODO add your handling code here:

   }//GEN-LAST:event_chooseAllActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonSearch;
    private javax.swing.JButton chooseAll;
    private javax.swing.JButton chooseSelection;
    private javax.swing.JButton dropAll;
    private javax.swing.JPanel dropSelectButtons;
    private javax.swing.JButton dropSelected;
    private org.netbeans.modules.form.InvalidComponent habitations1;
    private org.netbeans.modules.form.InvalidComponent habitations2;
    private javax.swing.JPanel invoiceHomeTables;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel labelFname;
    private javax.swing.JLabel label_lname;
    private javax.swing.JLabel label_roomRn;
    private javax.swing.JPanel searchPanel;
    private javax.swing.JTextField textBoxFname;
    private javax.swing.JTextField textBoxLname;
    private javax.swing.JTextField textBoxRoomNumber;
    // End of variables declaration//GEN-END:variables
}
