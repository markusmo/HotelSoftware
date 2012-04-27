package hotelsoftware.gui.home;

import hotelsoftware.gui.checkin.CheckInGuiControler;
import hotelsoftware.util.test.CurrentWeather;
import hotelsoftware.util.test.CurrentWeatherPanel;
import hotelsoftware.util.test.Weather;
import hotelsoftware.util.test.ForeCastWeatherPanel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Johannes
 */
public class HomePanel extends javax.swing.JPanel
{
    /**
     * Creates new form HomePanel
     */
    public HomePanel()
    {
        initComponents();
        jPanel3.removeAll();
        GridBagLayout gbl = new GridBagLayout();
        jPanel3.setLayout(gbl);


        new DateOut();
        Weather w = new CurrentWeather();
        w = w.getCurrent();
        GridBagConstraints gbc = new GridBagConstraints();

        // Festlegen, dass die GUI-Elemente die Gitterfelder in 
        // waagerechter Richtung ausfüllen:
        gbc.fill = GridBagConstraints.BOTH;

        // Die Abstände der einzelnen GUI-Elemente zu den gedachten 
        // Gitterlinien festgelegen:
        gbc.insets = new Insets(2, 2, 2, 2);

        gbc.gridx = 0;  // x-Position im gedachten Gitter
        gbc.gridy = 0;  // y-Position im gedachten Gitter
        gbc.gridheight = 1;  // zwei Gitter-Felder hoch
        gbc.gridwidth = 3;
        CurrentWeatherPanel cwp = new CurrentWeatherPanel((CurrentWeather) w);
        gbl.setConstraints(cwp, gbc);
        jPanel3.add(cwp);
        List<Weather> list = w.getForeCasts();
        int i = 0;
        gbc.gridwidth = 1;
        for (Weather w2 : list)
        {
            gbc.gridx = i;  // x-Position im gedachten Gitter
            gbc.gridy = 1;
            ForeCastWeatherPanel wp = new ForeCastWeatherPanel(w2);
            gbl.setConstraints(wp, gbc);
            jPanel3.add(wp);
            i++;
        }
        gbc.gridx = 0;  // x-Position im gedachten Gitter
        gbc.gridy = 2;  // y-Position im gedachten Gitter
        gbc.gridheight = 1;  // zwei Gitter-Felder hoch
        gbc.gridwidth = 2;
        gbl.setConstraints(jLabel1, gbc);
        jPanel3.add(jLabel1);
        gbc.gridy = 3;  // y-Position im gedachten Gitter
        gbl.setConstraints(jLabel2, gbc);
        jPanel3.add(jLabel2);
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

        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        jLabel1.setText("jLabel1");

        jLabel2.setText("jLabel2");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(40, 40, 40)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(262, 262, 262))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(134, 134, 134))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables

    private class DateOut extends Thread
    {
        public DateOut()
        {
            setDaemon(true);
            start();
        }

        public void run()
        {
            while (true)
            {
                jLabel1.setText("<html><font color=#6655AA size=+3>" + timeNow() + "</font></html>");
                jLabel2.setText("<html><font color=#6688CC size=+2>" + dateToday() + "</font></html>");
                try
                {
                    Thread.sleep(1000);
                }
                catch (InterruptedException ex)
                {
                    Logger.getLogger(HomePanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

        private String dateToday()
        {
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
            return sdf.format(new Date());
        }

        private String timeNow()
        {
            Calendar now = Calendar.getInstance();
            int hrs = now.get(Calendar.HOUR_OF_DAY);
            int min = now.get(Calendar.MINUTE);
            int sec = now.get(Calendar.SECOND);

            String time = zero(hrs) + ":" + zero(min) + ":" + zero(sec);

            return time;
        }

        private String zero(int num)
        {
            String number = (num < 10) ? ("0" + num) : ("" + num);
            return number;                                    //Add leading zero if needed

        }
    }
}
