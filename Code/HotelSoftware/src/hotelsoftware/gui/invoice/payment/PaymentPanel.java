/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.gui.invoice.payment;

import hotelsoftware.gui.invoice.InvoiceGUIControler;
import hotelsoftware.gui.invoice.buttons.AbortButton;
import hotelsoftware.gui.invoice.buttons.BackButton;
import hotelsoftware.gui.invoice.buttons.PayedButton;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;

/**
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class PaymentPanel extends JPanel implements ControlsSetter
{
    private InvoiceGUIControler ctrl = InvoiceGUIControler.getInstance();
    private PayedButton pB;
    private AbortButton aB;
    private BackButton bB;
    
    public PaymentPanel() {
        setLayout(new BorderLayout());
        setMaximumSize(new Dimension(1200, 400));
    }
    
    @Override
    public void setControls() {
        ctrl.clearControlPanel();
        
        aB = new AbortButton();
        ctrl.getDeconstructiveControlPanel().add(aB);
        
        bB = new BackButton();
        ctrl.getDeconstructiveControlPanel().add(bB);
        
        pB = new PayedButton();
        ctrl.getConstructiveControlPanel().add(pB);
        
        ctrl.repaintControlPanel();
    }
}
