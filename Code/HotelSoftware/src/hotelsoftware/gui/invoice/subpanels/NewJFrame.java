/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.gui.invoice.subpanels;

import hotelsoftware.controller.data.parties.CustomerData;
import hotelsoftware.model.domain.invoice.Invoice;
import hotelsoftware.model.domain.invoice.InvoiceItem;
import hotelsoftware.model.domain.parties.Address;
import hotelsoftware.model.domain.parties.Customer;
import hotelsoftware.model.domain.parties.PrivateCustomer;
import hotelsoftware.support.PrivateCustomerNotFoundException;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Random;

/**
 *
 * @author Johannes
 */
public class NewJFrame extends javax.swing.JFrame
{
    /**
     * Creates new form NewJFrame
     */
    public NewJFrame()
    {
        initComponents();
        Invoice i = new Invoice();
        Random r = new Random();
        LinkedList<InvoiceItem> iil = new LinkedList<InvoiceItem>();
        for (int o = 0; o < 13; o++)
        {
            InvoiceItem ii = new InvoiceItem();
            ii.setAmount(r.nextInt(25));
            iil.add(ii);
        }
        i.setInvoiceItems(iil);
        //jPanel1.add(new splitNstornoRoom(i), BorderLayout.CENTER);
        Collection<CustomerData> cs = new ArrayList<CustomerData>();
        PrivateCustomer c = new PrivateCustomer();
        c.setFname("Egon");
        c.setLname("Hans");
        cs.add(c);
        PrivateCustomer c3 = new PrivateCustomer();
        c3.setFname("Egon3");
        c3.setLname("Hans3");
        cs.add(c3);
        PrivateCustomer c2 = new PrivateCustomer();
        c2.setFname("Egon2");
        c2.setLname("Hans2");
        cs.add(c2);
        //jPanel1.add(new addCustomer(cs), BorderLayout.CENTER);
        //jPanel1.add(new PersonPanel(), BorderLayout.CENTER);
        //jPanel1.add(new CompanyPanel(), BorderLayout.CENTER);
        pack();
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

        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 997, Short.MAX_VALUE)
                .addGap(71, 71, 71))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                .addGap(86, 86, 86))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Metal".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new NewJFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
