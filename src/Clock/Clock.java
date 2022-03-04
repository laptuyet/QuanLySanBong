/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clock;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

/**
 *
 * @author tranh
 */
public class Clock extends Thread {

    private SimpleDateFormat spfTime = new SimpleDateFormat("HH:mm:ss");
    private SimpleDateFormat spfDate = new SimpleDateFormat("E, dd/MM/yyyy");
    private Date date;
    private String time;
    private String dat;
    
    private JLabel timeLabel;
    private JLabel dateLabel;

    public Clock(JLabel timeLabel, JLabel dateLabel) {
        start();
        this.timeLabel = timeLabel;
        this.dateLabel = dateLabel;
    }

    @Override
    public void run() {

        while (true) {
            Calendar cal = Calendar.getInstance();
            
            date = cal.getTime();
            time = spfTime.format(date);
            dat = spfDate.format(date);
            
            timeLabel.setText(time);
            dateLabel.setText(dat);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Clock.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public String getCurrentDateTime() {
        SimpleDateFormat spf = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        String dateTime = spf.format(date);
        return dateTime;

    }
}
