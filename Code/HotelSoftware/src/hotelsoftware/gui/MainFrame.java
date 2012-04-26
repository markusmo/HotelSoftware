package hotelsoftware.gui;

import hotelsoftware.gui.checkin.CheckInGuiControler;
import hotelsoftware.gui.checkin.CheckinTwo;
import hotelsoftware.gui.checkin.CheckInMain;
import hotelsoftware.gui.checkin.CheckInMain;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.GraphicsEnvironment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Diese Klasse ist die Main-Klasse dieses Projekts. Von hier wird das Programm
 * gestartet und die Use-Cases gestartet.
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
        initComponents();
        /*
         * GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
         * this.setMaximizedBounds(env.getMaximumWindowBounds());
         * this.setExtendedState(this.getExtendedState()
         * | JFrame.MAXIMIZED_BOTH);
         * this.setMinimumSize(env.getMaximumWindowBounds().getSize());
         */
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton11 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton11.setText("<html> <b>Home</b> <br/> F1 </html>");
        jButton11.setActionCommand("");
        jButton11.setMaximumSize(new java.awt.Dimension(73, 73));
        jButton11.setMinimumSize(new java.awt.Dimension(73, 73));
        jButton11.setPreferredSize(new java.awt.Dimension(80, 80));
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jPanel1.setLayout(new java.awt.CardLayout());

        jButton12.setText("<html> Check In <br/> F2 </html>");
        jButton12.setActionCommand("");
        jButton12.setMaximumSize(new java.awt.Dimension(73, 73));
        jButton12.setMinimumSize(new java.awt.Dimension(73, 73));
        jButton12.setPreferredSize(new java.awt.Dimension(80, 80));
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton13.setText("<html> Room Overview <br/> F4 </html>");
        jButton13.setActionCommand("");
        jButton13.setMaximumSize(new java.awt.Dimension(73, 73));
        jButton13.setMinimumSize(new java.awt.Dimension(73, 73));
        jButton13.setPreferredSize(new java.awt.Dimension(80, 80));
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton14.setText("<html> Check Out <br/> F3 </html>");
        jButton14.setActionCommand("");
        jButton14.setMaximumSize(new java.awt.Dimension(73, 73));
        jButton14.setMinimumSize(new java.awt.Dimension(73, 73));
        jButton14.setPreferredSize(new java.awt.Dimension(80, 80));
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jButton15.setText("<html> Journal <br/> F5 </html>");
        jButton15.setActionCommand("");
        jButton15.setMaximumSize(new java.awt.Dimension(73, 73));
        jButton15.setMinimumSize(new java.awt.Dimension(73, 73));
        jButton15.setPreferredSize(new java.awt.Dimension(80, 80));
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 13, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 866, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 615, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton12ActionPerformed
    {//GEN-HEADEREND:event_jButton12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton13ActionPerformed
    {//GEN-HEADEREND:event_jButton13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton14ActionPerformed
    {//GEN-HEADEREND:event_jButton14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton15ActionPerformed
    {//GEN-HEADEREND:event_jButton15ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton11ActionPerformed
    {//GEN-HEADEREND:event_jButton11ActionPerformed
        CardLayout cl = (CardLayout) jPanel1.getLayout();
        cl.next(jPanel1);
    }//GEN-LAST:event_jButton11ActionPerformed

    private void Init()
    {
        jButton11.setIcon(new ImageIcon(MainFrame.class.getClassLoader().getResource(
                "resources/images/home-icon.png")));
        jButton12.setIcon(new ImageIcon(MainFrame.class.getClassLoader().getResource(
                "resources/images/checkin-icon.png")));
        jButton14.setIcon(new ImageIcon(MainFrame.class.getClassLoader().getResource(
                "resources/images/logout-icon.png")));
        jButton13.setIcon(new ImageIcon(MainFrame.class.getClassLoader().getResource(
                "resources/images/Maps-icon.png")));
        jButton15.setIcon(new ImageIcon(
                MainFrame.class.getClassLoader().getResource(
                "resources/images/Address-Book-icon.png")));

        cigc.setContentpane(jPanel1);
        //jPanel1 = new CheckInMain();
        cigc.getContentpane().add(new CheckInMain(), BorderLayout.CENTER);
        //jPanel1.add(new CheckinTwo(),BorderLayout.CENTER);


    }

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
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
