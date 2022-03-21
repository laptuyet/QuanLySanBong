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
public class MyClock extends Thread {

    private SimpleDateFormat spfTime = new SimpleDateFormat("HH:mm:ss");
    private SimpleDateFormat spfDate = new SimpleDateFormat("E, dd/MM/yyyy");
    private Date date;
    private String time;
    private String dat;

    private JLabel timeLabel;
    private JLabel dateLabel;

    public MyClock() {

    }

    public MyClock(JLabel timeLabel, JLabel dateLabel) {
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
                Logger.getLogger(MyClock.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public String getCurrentDate() {
        SimpleDateFormat spf = new SimpleDateFormat("yyyy/MM/dd");
        Calendar cal = Calendar.getInstance();
        Date d = cal.getTime();
        String date = spf.format(d);
        return date;

    }
    
    public String getCurrentTime() {
        SimpleDateFormat spf = new SimpleDateFormat("HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        String time = spf.format(date);
        return time;
    }

    public String getCurrentYMD_HM(String option) {

        String ans = "";
        SimpleDateFormat spf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        String dateTime = spf.format(date);

        String[] dateTimeArr = dateTime.split("\\s");
        String[] dateArr = dateTimeArr[0].split("\\/");
        String[] timeArr = dateTimeArr[1].split("\\:");

        switch (option) {
            case "year":
                ans = dateArr[0];
                break;
            case "month":
                ans = dateArr[1];
                break;
            case "day":
                ans = dateArr[2];
                break;
            case "hour":
                ans = timeArr[0];
                break;
            case "minute":
                ans = timeArr[1];
                break;
        }

        return ans;
    }
}
