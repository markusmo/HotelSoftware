/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.gui.invoice;

import com.lowagie.text.BadElementException;
import com.lowagie.text.DocumentException;
import hotelsoftware.controller.createinvoice.CreateInvoiceController;
import hotelsoftware.controller.data.service.HabitationData;
import hotelsoftware.gui.invoice.home.InvoiceHome;
import hotelsoftware.gui.invoice.subpanels.addCustomer;
import hotelsoftware.model.domain.invoice.Invoice;
import hotelsoftware.model.domain.parties.Customer;
import hotelsoftware.util.HelperFunctions;
import hotelsoftware.util.pdf.PdfGenerator;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Collection;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private JButton splitCancelButton = new JButton();
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
        
        splitCancelButton.setText(splitCancel);
        splitCancelButton.addActionListener(this);  
        // set start screen
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
               setContentPanel(new splitNstornoPanel());
            } else if (text.equals(chooseCustomer)) {
               setContentPanel(new addCustomer(ctrl.getCustomerData()));
            } else if (text.equals(payment)) {
               setContentPanel(getPaymentPanel());
            } 
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
        contentPanel.add(newcontent);
        
        if (contentPanel.getLayout() instanceof CardLayout) {
            CardLayout layout = (CardLayout) contentPanel.getLayout();
            layout.next(contentPanel);
        }
        
        setNavigation(newcontent.getClass());
        setControls(newcontent.getClass());
        
        contentPanel.repaint();
        
   }
    
    
    private void setNavigation(Class clazz) {
       JPanel navigation = getNavigationPanel();       
       navigation.removeAll();
       
//       if (class.equals(SplitCancel.class)) {
//               
//       } 
       
        if (clazz.equals(InvoiceHome.class)) {             
            navigation.add(invoiceHomeLabel);
        } 
        if (clazz.equals(IntermediatInvoice.class)) {
            navigation.add(invoiceHomeLabel);
            navigation.add(seperatorLabel);
            navigation.add(intermediatInvoiceLabel);
        } 
        if (clazz.equals(addCustomer.class)) {
            navigation.add(invoiceHomeLabel);
            navigation.add(seperatorLabel);
            navigation.add(intermediatInvoiceLabel);
            navigation.add(seperatorLabel);
            navigation.add(chooseCustomerLabel);
        }
//            if (clazz.equals(Payment.class)) {
//                navigation.add(seperator);
//                navigation.add(payment);
//            }

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
        }
        if (clazz.equals(IntermediatInvoice.class)) {
            constructive.add(splitCancelButton);
            constructive.add(chooseCustomerButton);
            deconstructive.add(backButton);
        }
         if (clazz.equals(addCustomer.class)) {
            deconstructive.add(backButton);
        }        
        
        getControlPanel().repaint();
    }
    
    private void abort(ActionEvent e) {
        //ctrl.abort();
        // FIXME wenn items noch offen sind, meldung dementsprechend anpassen
        JPanel panel = getContentPanel();
        
        JOptionPane.showMessageDialog(panel, "Do you really want to abort?", "Abort", JOptionPane.ABORT);
    }
    
    private void back(ActionEvent e) {
        // FIXME decomment 
        //ctrl.back();   
        JPanel contentPanel = getContentPanel();
        if (contentPanel.getLayout() instanceof CardLayout) {
            CardLayout layout = (CardLayout) contentPanel.getLayout();
            layout.previous(getContentPanel());
            JPanel current = getCurrentPanel(contentPanel);  
            setNavigation(current.getClass());
            setControls(current.getClass());
        }
        
        
        contentPanel.repaint();
    }
    
     public Collection<HabitationData> search(String firstName, String lastName, String roomNr)
    {
        return ctrl.search(firstName, lastName, roomNr);
    }
    
    public JPanel getCurrentPanel(JPanel currentPanel) {

        for (Component component : currentPanel.getComponents()) {
            if (component.isVisible()) {
                if (component instanceof JPanel) 
                    currentPanel = (JPanel) component;
//                else if (component instanceof JScrollPane)
//                    currentPanel = (JPanel) ((JScrollPane) component).getViewport().getComponent(0);
            }
        }

        return currentPanel;
    }

    /**
     * 
     * @return 
     */
    private JPanel getPaymentPanel() {
        // FIXME set expireData
        PdfGenerator generator = new PdfGenerator(ctrl.getCustomer(), HelperFunctions.getNewContinousNumber(Invoice.class), ctrl.getChosenItems(), new Date(), new Date());
        JPanel generatePDFPanel = generator.generatePaymentPanel();
        return generatePDFPanel;
    }
    
    
    private JPanel getIntermediatInvoicePanel() {
        // FIXME set expireData
        PdfGenerator generator = new PdfGenerator(ctrl.getCustomer(), HelperFunctions.getNewContinousNumber(Invoice.class), ctrl.getChosenItems(), new Date(), new Date());
        JPanel generatePDFPanel = generator.generateIntermediatPanel();
        return generatePDFPanel;
    }
   
}
