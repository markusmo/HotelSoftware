/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.gui.invoice;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JPanel;

/**
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class InvoiceGUIControler
{
    private InvoiceMain main;
    
    
    
    private InvoiceGUIControler()
    {
    }
    
    public static InvoiceGUIControler getInstance()
    {
        return invoiceGUIControlerHolder.INSTANCE;
    }

    void setMain(JPanel contentPanel)
    {
        main = (InvoiceMain) contentPanel;
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
    
     private JPanel getStatePanel() {
        return main.getStatePanel();
    }
    
    public void setContentPanel(JPanel content) {
        JPanel contentPanel = getContentPanel();
        contentPanel.removeAll();
        contentPanel.add(content, BorderLayout.CENTER);
        ((CardLayout)contentPanel.getLayout()).next(contentPanel);
        contentPanel.repaint();
    }
    
    private void setNavigation() {
        
    }
    
    private void setControls() {
        JPanel state = getStatePanel();
        state.removeAll();
        //TODO buttons exportieren --> eigene klasse und hier adden usw.
    }
            
    
}
