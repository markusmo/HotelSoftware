/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.gui.home;

import hotelsoftware.gui.checkin.CheckInGuiControler;
import hotelsoftware.util.test.CurrentWeather;
import hotelsoftware.util.test.Weather;
import hotelsoftware.util.test.WeatherPanel;
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
        new DateOut();
        Weather w = new CurrentWeather();
        List<Weather> list = w.getCurrent();
        list.addAll(w.getForeCasts());
        for (Weather w2 : list)
        {
            WeatherPanel wp = new WeatherPanel(w2);
            jPanel1.add(wp);
        }
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        jLabel1.setText("jLabel1");

        jLabel2.setText("jLabel2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(188, 188, 188)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(0, 1021, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(343, 343, 343))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
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
