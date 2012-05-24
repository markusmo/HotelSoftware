/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.gui.invoice.labels;

import hotelsoftware.gui.invoice.CreateInvoiceGUIController;
import javax.swing.JLabel;

/**
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class SeperatorLabel extends JLabel
{
    public SeperatorLabel() {
        setText(CreateInvoiceGUIController.getInstance().getSeperatorString());
    }
}
