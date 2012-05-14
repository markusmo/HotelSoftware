/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.gui.invoice.intermediatInvoice;

import hotelsoftware.gui.invoice.InvoiceGUIControler;
import hotelsoftware.gui.invoice.buttons.AbortButton;
import hotelsoftware.gui.invoice.buttons.BackButton;
import hotelsoftware.gui.invoice.buttons.ChooseCustomerButton;
import hotelsoftware.gui.invoice.buttons.SplitCancelButton;
import hotelsoftware.gui.invoice.ControlsSetter;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;

/**
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 * 
 * diese Panel hält die Zwischenrechnung mit der Menüleiste für die Speicherung, den Druck etc.
 */
public class IntermediatInvoicePanel extends JPanel implements ControlsSetter
{
    private InvoiceGUIControler ctrl = InvoiceGUIControler.getInstance();
    
    private SplitCancelButton sCB;
    private ChooseCustomerButton jCB;
    private AbortButton aB;
    private BackButton bB;
    
    public IntermediatInvoicePanel() {
        setLayout(new BorderLayout());
        setMaximumSize(new Dimension(1000, 400));
    }

    @Override
    public void setControls()
    {
        ctrl.clearControlPanel();
        
        aB = new AbortButton();
        ctrl.getDeconstructiveControlPanel().add(aB);
        
        bB = new BackButton();
        ctrl.getDeconstructiveControlPanel().add(bB);
        
        sCB = new SplitCancelButton();   
        ctrl.getConstructiveControlPanel().add(sCB);
        
        jCB = new ChooseCustomerButton();
        ctrl.getConstructiveControlPanel().add(jCB);
        
        ctrl.repaintControlPanel();
    }
    
}
