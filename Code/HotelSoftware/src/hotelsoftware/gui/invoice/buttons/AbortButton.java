/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.gui.invoice.buttons;

import hotelsoftware.gui.invoice.CreateInvoiceGUIController;
import javax.swing.JButton;

/**
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class AbortButton extends JButton
{
    private CreateInvoiceGUIController ctrl = CreateInvoiceGUIController.getInstance();
    
    public AbortButton() {
        setText(ctrl.getAbortString());
        addActionListener(ctrl);
    }
}
