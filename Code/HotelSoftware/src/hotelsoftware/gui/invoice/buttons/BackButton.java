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
public class BackButton extends JButton
{
    private InvoiceGUIControler ctrl = InvoiceGUIControler.getInstance();
    
    public BackButton() {
        setText(ctrl.getBackString());
        addActionListener(ctrl);
    }
}
