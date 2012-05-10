/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.gui.invoice;

import hotelsoftware.controller.createinvoice.CreateInvoiceController;
import hotelsoftware.gui.invoice.home.InvoiceHome;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public final class InvoiceGUIControler implements ActionListener
{
    private CreateInvoiceController ctrl = CreateInvoiceController.getInstance();
    
    
    private InvoiceMain main;
    
    //Navigation labels
    private JLabel invoiceHomeLabel = new JLabel();
    private JLabel intermediatInvoiceLabel = new JLabel();
    private JLabel chooseCustomerLabel = new JLabel();
    private JLabel splitCancelLabel = new JLabel();
    private JLabel paymentLabel = new JLabel();
    private JLabel seperatorLabel = new JLabel();
    
    // Controls
    private JButton abortButton = new JButton();
    private JButton backButton = new JButton();
    private JButton intermediatInvoiceButton = new JButton();
    private JButton chooseCustomerButton = new JButton();
    private JButton payed = new JButton();
    
    private String invoiceHome = "Invoice Home";
    private String intermediatInvoice = "Intermediat Invoice";
    private String chooseCustomer = "Customer Selection";
    private String splitCancel = "Split/Cancel";
    private String payment = "Payment";
    private String seperator = ">";
    private String abort = "Abort";
    private String back = "Back";
    
    private InvoiceGUIControler()
    {
        // init labels
        invoiceHomeLabel.setText(invoiceHome);
        intermediatInvoiceLabel.setText(intermediatInvoice);
        chooseCustomerLabel.setText(chooseCustomer);
        splitCancelLabel.setText(splitCancel);
        paymentLabel.setText(payment);
        seperatorLabel.setText(seperator);
        
        // init controls
        abortButton.setText(abort);
        abortButton.addActionListener(this);
        
        backButton.setText(back);
        backButton.addActionListener(this);
        
        intermediatInvoiceButton.setText(intermediatInvoice);
        intermediatInvoiceButton.addActionListener(this);
        
        chooseCustomerButton.setText(chooseCustomer);
        chooseCustomerButton.addActionListener(this);        
        // set start screen
        //setContentPanel(new InvoiceHome());
    }
    
    public static InvoiceGUIControler getInstance()
    {
        return invoiceGUIControlerHolder.INSTANCE;
    }

    void setMain(JPanel contentPanel)
    {
        main = (InvoiceMain) contentPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() instanceof JButton) {
            JButton button = (JButton) e.getSource();            
            String text = button .getText();
            
            if (text.equals(abort)) {
                abort(e);
            } else if (text.equals(back)) {
                back(e);
            } else if (text.equals(intermediatInvoice)) {
                setContentPanel(new IntermediatInvoice());
            } else if (text.equals(splitCancel)) {
                // TODO adapt setContentPanel(new IntermediatInvoice());
            } // more buttons!!!
        }
    }
    
    private static class invoiceGUIControlerHolder
    {
        private static final InvoiceGUIControler INSTANCE = new InvoiceGUIControler();
    }
    
//    public JPanel getContentPanel() {
//        return main.getContentPanel();
//    }
    
    private JPanel getContentPanel() {
        return main.getContentPanel();
    }
    
    private JPanel getNavigationPanel() {
        return main.getNavigationPanel();
    }
    
     private JPanel getConstructiveControlPanel() {
        return main.getConstructiveControlPanel();
    }
     
     private JPanel getDeconstructiveControlPanel() {
        return main.getDeconstuctiveControlPanel();
    }
     
     private JPanel getControlPanel() {
         return main.getControlPanel();
     }
    
    public void setContentPanel(JPanel newcontent) {        
        JPanel contentPanel = getContentPanel();
        contentPanel.removeAll();
        contentPanel.add(newcontent);
        ((CardLayout)contentPanel.getLayout()).next(contentPanel);
        
        setNavigation(newcontent.getClass());
        setControls(newcontent.getClass());
        
        contentPanel.repaint();
    }
    
    
    private void setNavigation(Class clazz) {
       JPanel navigation = getNavigationPanel();       
       navigation.removeAll();
       
//       if (class.equals(SplitCancel.class)) {
//               
//       } else {
       
            if (clazz.equals(InvoiceHome.class)) {             
                navigation.add(invoiceHomeLabel);
            } 
            if (clazz.equals(IntermediatInvoice.class)) {
                navigation.add(seperatorLabel);
                navigation.add(intermediatInvoiceLabel);
            } 
    //        if (class.equals(CostomerSelection.class)) {
    //            navigation.add(seperator);
    //            navigation.add(chooseCustomer);
    //        }
    //        if (class.equals(Payment.class)) {
    //            navigation.add(seperator);
    //            navigation.add(payment);
    //        }
//        }
        navigation.repaint();
    }
    
    private void setControls(Class clazz) {
        JPanel deconstructive = getDeconstructiveControlPanel();
        deconstructive.removeAll();
        deconstructive.add(abortButton);
        
                
        JPanel constructive = getConstructiveControlPanel();        
        constructive.removeAll();
        
        if (clazz.equals(InvoiceHome.class)) {             
            constructive.add(intermediatInvoiceButton);
            deconstructive.add(backButton);
        } 
        
        getControlPanel().repaint();
    }
    
    private void abort(ActionEvent e) {
        ctrl.abort();
        // TODO wenn items noch offen sind, meldung dementsprechend anpassen
        JOptionPane.showMessageDialog(getContentPanel(), "Do you really want to abort?");
    }
    
    private void back(ActionEvent e) {
        ctrl.back();   
        ((CardLayout) getContentPanel().getLayout()).previous(getContentPanel());
    }
    
}
