/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.gui.checkin.subpanels;

import hotelsoftware.gui.checkin.CheckInGuiControler;
import hotelsoftware.model.domain.service.ExtraService;
import hotelsoftware.model.domain.service.data.ExtraServiceData;
import java.awt.GridLayout;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.swing.JCheckBox;

/**
 * Dieses Panel ist dazu da, um die Extraservices graphisch abzubilden.
 * @author Johannes
 */
public class ExtraServicesPanel extends javax.swing.JPanel
{
    /**
     * Creates new form ExtraServicesPanel
     */
    public ExtraServicesPanel()
    {
        initComponents();
        init();
    }
    private List<JCheckBox> checkboxes = new LinkedList<JCheckBox>();

    private void init()
    {        
        Collection<ExtraService> services = ExtraService.getAllHabitationServices();
        this.removeAll();
        this.setLayout(new GridLayout(services.size(), 1));
        for (ExtraServiceData data : services)
        {
            JCheckBox checki = new JCheckBox(data.getName());
            checkboxes.add(checki);
            add(checki);
        }
        this.repaint();
    
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 78, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 15, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    public List<JCheckBox> getCheckboxes()
    {
        return checkboxes;
    }

    public void setCheckboxes(List<JCheckBox> checkboxes)
    {
        this.checkboxes = checkboxes;
    }
}
