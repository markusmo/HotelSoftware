/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.gui.invoice;

import javax.swing.JPanel;

/**
 * 
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class InvoiceMain extends javax.swing.JPanel
{
    public JPanel getContentPanel() {
        return contentPanel;
    }
    
    public JPanel getControlPanel() {
        return controlPanel;
    }
    
    public JPanel getNavigationPanel() {
        return navigationPanel;
    }
        
    public JPanel getDeconstuctiveControlPanel() {
        return deconstructiveControls;
    }
    
     public JPanel getConstructiveControlPanel() {
        return constructiveControls;
    }

    /**
     * Creates new form CheckInMain
     */
    public InvoiceMain()
    {
        initComponents();
        InvoiceGUIControler.getInstance().setMain(this);
    }

    
    /**
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        navigationPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        contentPanel = new javax.swing.JPanel();
        controlPanel = new javax.swing.JPanel();
        deconstructiveControls = new javax.swing.JPanel();
        constructiveControls = new javax.swing.JPanel();

        setMaximumSize(new java.awt.Dimension(1200, 600));
        setLayout(new java.awt.BorderLayout());

        navigationPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel1.setText("Navigation");
        navigationPanel.add(jLabel1);

        add(navigationPanel, java.awt.BorderLayout.NORTH);

        contentPanel.setMaximumSize(new java.awt.Dimension(1200, 400));
        contentPanel.setMinimumSize(new java.awt.Dimension(600, 400));
        contentPanel.setLayout(new java.awt.CardLayout());
        add(contentPanel, java.awt.BorderLayout.CENTER);

        controlPanel.setLayout(new java.awt.BorderLayout());

        deconstructiveControls.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        controlPanel.add(deconstructiveControls, java.awt.BorderLayout.WEST);

        constructiveControls.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));
        controlPanel.add(constructiveControls, java.awt.BorderLayout.EAST);

        add(controlPanel, java.awt.BorderLayout.PAGE_END);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel constructiveControls;
    private javax.swing.JPanel contentPanel;
    private javax.swing.JPanel controlPanel;
    private javax.swing.JPanel deconstructiveControls;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel navigationPanel;
    // End of variables declaration//GEN-END:variables

    public void setFocus()
    {
        requestFocus();
    }
}