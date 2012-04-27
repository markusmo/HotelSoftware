package hotelsoftware.gui;

import hotelsoftware.gui.checkin.CheckInGuiControler;
import hotelsoftware.gui.checkin.CheckInMain;
import hotelsoftware.gui.home.HomePanel;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.*;

/**
 * Diese Klasse ist die Main-Klasse dieses Projekts. Von hier wird das Programm
 * und die Use-Cases gestartet.
 *
 * @author Johannes
 */
public class MainFrame extends javax.swing.JFrame
{
    private CheckInGuiControler cigc = CheckInGuiControler.getInstance();

    /**
     * Creates new form TestFrame
     */
    public MainFrame()
    {
        this.setUndecorated(true);

        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        //GraphicsDevice myDevice = env.getDefaultScreenDevice();
        //myDevice.setFullScreenWindow(this);

        this.setMaximizedBounds(env.getMaximumWindowBounds());
        this.setExtendedState(this.getExtendedState()
                | JFrame.MAXIMIZED_BOTH);
        this.setMinimumSize(env.getMaximumWindowBounds().getSize());

        initComponents();
        registerActionMap();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        homeButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        checkInButton = new javax.swing.JButton();
        roomOverviewButton = new javax.swing.JButton();
        checkOutButton = new javax.swing.JButton();
        journalButton = new javax.swing.JButton();
        escButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        homeButton.setText("<html><b> Home</b> <br/> F1 </html>");
        homeButton.setActionCommand("");
        homeButton.setMaximumSize(new java.awt.Dimension(73, 73));
        homeButton.setMinimumSize(new java.awt.Dimension(73, 73));
        homeButton.setPreferredSize(new java.awt.Dimension(80, 80));
        homeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeButtonActionPerformed(evt);
            }
        });

        jPanel1.setLayout(new java.awt.CardLayout());

        checkInButton.setText("<html><b> Check In</b> <br/> F2 </html>");
        checkInButton.setActionCommand("");
        checkInButton.setMaximumSize(new java.awt.Dimension(73, 73));
        checkInButton.setMinimumSize(new java.awt.Dimension(73, 73));
        checkInButton.setPreferredSize(new java.awt.Dimension(80, 80));
        checkInButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkInButtonActionPerformed(evt);
            }
        });

        roomOverviewButton.setText("<html><b> Room Overview</b> <br/> F4 </html>");
        roomOverviewButton.setActionCommand("");
        roomOverviewButton.setMaximumSize(new java.awt.Dimension(73, 73));
        roomOverviewButton.setMinimumSize(new java.awt.Dimension(73, 73));
        roomOverviewButton.setPreferredSize(new java.awt.Dimension(80, 80));
        roomOverviewButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roomOverviewButtonActionPerformed(evt);
            }
        });

        checkOutButton.setText("<html><b> Check Out</b> <br/> F3 </html>");
        checkOutButton.setActionCommand("");
        checkOutButton.setMaximumSize(new java.awt.Dimension(73, 73));
        checkOutButton.setMinimumSize(new java.awt.Dimension(73, 73));
        checkOutButton.setPreferredSize(new java.awt.Dimension(80, 80));
        checkOutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkOutButtonActionPerformed(evt);
            }
        });

        journalButton.setText("<html><b> Journal</b> <br/> F5 </html>");
        journalButton.setActionCommand("");
        journalButton.setMaximumSize(new java.awt.Dimension(73, 73));
        journalButton.setMinimumSize(new java.awt.Dimension(73, 73));
        journalButton.setPreferredSize(new java.awt.Dimension(80, 80));
        journalButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                journalButtonActionPerformed(evt);
            }
        });

        escButton.setText("<html><b> Exit</b> <br/>Esc</html>");
        escButton.setActionCommand("");
        escButton.setMaximumSize(new java.awt.Dimension(73, 73));
        escButton.setMinimumSize(new java.awt.Dimension(73, 73));
        escButton.setPreferredSize(new java.awt.Dimension(80, 80));
        escButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                escButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(homeButton, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                .addGap(28, 28, 28)
                .addComponent(checkInButton, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                .addGap(28, 28, 28)
                .addComponent(checkOutButton, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                .addGap(45, 45, 45)
                .addComponent(roomOverviewButton, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                .addGap(40, 40, 40)
                .addComponent(journalButton, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                .addGap(43, 43, 43)
                .addComponent(escButton, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1025, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(homeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkOutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkInButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roomOverviewButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(journalButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(escButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 615, Short.MAX_VALUE)
                .addGap(16, 16, 16))
        );

        escButton.getAccessibleContext().setAccessibleName("<html> Exit <br/>Esc</html>");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void checkInButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton12ActionPerformed
    {//GEN-HEADEREND:event_jButton12ActionPerformed
        if (checkState())
        {
            cigc.getContentpane().removeAll();
            cigc.getContentpane().add(new CheckInMain(), BorderLayout.CENTER);
            ((CardLayout) cigc.getContentpane().getLayout()).next(cigc.getContentpane());
            cigc.getContentpane().repaint();
        }
    }//GEN-LAST:event_jButton12ActionPerformed

    private void roomOverviewButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_roomOverviewButtonActionPerformed
    {//GEN-HEADEREND:event_roomOverviewButtonActionPerformed
        // TODO add your handling code here:
        if (checkState())
        {
        }
    }//GEN-LAST:event_roomOverviewButtonActionPerformed

    private void checkOutButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_checkOutButtonActionPerformed
    {//GEN-HEADEREND:event_checkOutButtonActionPerformed
        // TODO add your handling code here:
        if (checkState())
        {
        }
    }//GEN-LAST:event_checkOutButtonActionPerformed

    private void journalButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_journalButtonActionPerformed
    {//GEN-HEADEREND:event_journalButtonActionPerformed
        // TODO add your handling code here:
        if (checkState())
        {
        }
    }//GEN-LAST:event_journalButtonActionPerformed

    private void homeButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton11ActionPerformed
    {//GEN-HEADEREND:event_jButton11ActionPerformed
        if (checkState())
        {
            cigc.getContentpane().removeAll();
            cigc.getContentpane().add(new HomePanel(), BorderLayout.CENTER);
            ((CardLayout) cigc.getContentpane().getLayout()).next(cigc.getContentpane());
            cigc.getContentpane().repaint();
        }
    }//GEN-LAST:event_jButton11ActionPerformed

    private void escButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_escButtonActionPerformed
    {//GEN-HEADEREND:event_escButtonActionPerformed
        //Esc Button

        if (JOptionPane.showConfirmDialog(this.jPanel1, "Are you sure?", "Closing?", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE) == 0)
        {
            System.exit(0);
        }
    }//GEN-LAST:event_escButtonActionPerformed
    
    /**
     * Initialisiert die Menübuttons mit den Icons und setzt ein neues Homepanel in das ContentPane;
     */
    private void Init()
    {
        homeButton.setIcon(new ImageIcon(MainFrame.class.getClassLoader().getResource(
                "resources/images/home-icon.png")));
        checkInButton.setIcon(new ImageIcon(MainFrame.class.getClassLoader().getResource(
                "resources/images/checkin-icon.png")));
        checkOutButton.setIcon(new ImageIcon(MainFrame.class.getClassLoader().getResource(
                "resources/images/logout-icon.png")));
        roomOverviewButton.setIcon(new ImageIcon(MainFrame.class.getClassLoader().getResource(
                "resources/images/Maps-icon.png")));
        journalButton.setIcon(new ImageIcon(
                MainFrame.class.getClassLoader().getResource(
                "resources/images/Address-Book-icon.png")));
        escButton.setIcon(new ImageIcon(
                MainFrame.class.getClassLoader().getResource(
                "resources/images/exit.png")));

        cigc.setContentpane(jPanel1);
        //jPanel1 = new CheckInMain();
        cigc.getContentpane().add(new HomePanel(), BorderLayout.CENTER);
        //jPanel1.add(new CheckinTwo(),BorderLayout.CENTER);
    }

    /**
     * Startet ein neues Gui
     */
    public static void CreateGui()
    {

        MainFrame frame = new MainFrame();
        frame.Init();
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Windows".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(
                    java.util.logging.Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(
                    java.util.logging.Level.SEVERE, null, ex);
        }
        catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(
                    java.util.logging.Level.SEVERE, null, ex);
        }
        catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(
                    java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                CreateGui();
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton checkInButton;
    private javax.swing.JButton checkOutButton;
    private javax.swing.JButton escButton;
    private javax.swing.JButton homeButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton journalButton;
    private javax.swing.JButton roomOverviewButton;
    // End of variables declaration//GEN-END:variables

    /**
     * Registriert die Shortcuts fuer die Buttons.
     */
    private void registerActionMap()
    {
        KeyStroke f1 = KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0);
        KeyStroke f2 = KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0);
        KeyStroke f3 = KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0);
        KeyStroke f4 = KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0);
        KeyStroke f5 = KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0);
        KeyStroke esc = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);

        InputMap map = this.getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        map.put(f1, "f1pressed");
        map.put(f2, "f2pressed");
        map.put(f3, "f3pressed");
        map.put(f4, "f4pressed");
        map.put(f5, "f5pressed");
        map.put(esc, "escpressed");

        Action f1pressed = new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                homeButtonActionPerformed(e);
            }
        };

        Action f2pressed = new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                checkInButtonActionPerformed(e);
            }
        };
        Action f3pressed = new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                checkOutButtonActionPerformed(e);
            }
        };
        Action f4pressed = new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                roomOverviewButtonActionPerformed(e);
            }
        };
        Action f5pressed = new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                journalButtonActionPerformed(e);
            }
        };
        Action escpressed = new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                escButtonActionPerformed(e);
            }
        };

        ActionMap amap = this.getRootPane().getActionMap();
        amap.put("f1pressed", f1pressed);
        amap.put("f2pressed", f2pressed);
        amap.put("f3pressed", f3pressed);
        amap.put("f4pressed", f4pressed);
        amap.put("f5pressed", f5pressed);
        amap.put("escpressed", escpressed);

    }

    /**
     * Überprüft ob der Klick auf einen Menüknopf zulässig ist
     * @return Returnt ob gewechselt werden darf.
     */
    private boolean checkState()
    {
      if(!GuiController.getInstance().checkStateForSwitching())
      {
          return JOptionPane.showConfirmDialog(this.jPanel1, "Are you sure?\nAll data will be lost!", "Closing?", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == 0;
      }
      return true;
    }
}
