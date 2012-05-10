/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.util.pdf;

import javax.swing.JPanel;

/**
 *
 * @author Markus Mohanty <markus.mo at gmx.net>
 */
public interface PDFObserver
{
    public void getPDFasPanel(JPanel pdfPanel);
    public void gererationFinished(boolean done);
}
