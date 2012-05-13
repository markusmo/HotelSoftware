/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.gui.invoice.buttons;

import hotelsoftware.gui.invoice.InvoiceGUIControler;
import javax.swing.JButton;

/**
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class PayedButton extends JButton
{
    private InvoiceGUIControler ctrl = InvoiceGUIControler.getInstance();
    
    public PayedButton() {
        setText(ctrl.getPayedString());
        addActionListener(ctrl);
    }
}
