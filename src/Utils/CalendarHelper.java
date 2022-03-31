/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tranh
 */
public class CalendarHelper {

    private Calendar cal = Calendar.getInstance();

    public CalendarHelper() {

    }

    public int getNumsDayOfMonth(int year, int month) {
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        int numsDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        return numsDay;
    }

    public boolean isEndDateGtStartDate(String sDate, String eDate) {
        SimpleDateFormat spf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        long start, end;
        try {
            start = spf.parse(sDate).getTime();
            end = spf.parse(eDate).getTime();
            return end > start;
        } catch (ParseException ex) {
            Logger.getLogger(CalendarHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean isEndTimeGtStartTime(String sTime, String eTime) {

        SimpleDateFormat spf = new SimpleDateFormat("HH:mm:ss");
        long start, end;
        try {
            start = spf.parse(sTime).getTime();
            end = spf.parse(eTime).getTime();
            return end > start;

        } catch (ParseException ex) {
            Logger.getLogger(CalendarHelper.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public String formatHourAndMinute(int hour, int minute) {
        String h = hour <= 9 ? "0" + String.valueOf(hour) : String.valueOf(hour);
        String m = minute <= 9 ? "0" + String.valueOf(minute) : String.valueOf(minute);

        return h + ":" + m + ":" + "00";
    }

    public String formatDate(int year, int month, int day) {
        String m = month <= 9 ? "0" + String.valueOf(month) : String.valueOf(month);
        String d = day <= 9 ? "0" + String.valueOf(day) : String.valueOf(day);

        return year + "/" + m + "/" + d;
    }

    public boolean isTimeBetween(String start, String current, String end) {
        SimpleDateFormat spf = new SimpleDateFormat("HH:mm:ss");

        long s;
        try {
            s = spf.parse(start).getTime();
            long c = spf.parse(current).getTime();
            long e = spf.parse(end).getTime();
            return s <= c && c <= e;
        } catch (ParseException ex) {
            Logger.getLogger(CalendarHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public float totalTime(String start, String end) {

        String[] s = start.split("\\s");
        String[] sTime = s[1].split("\\:");

        String[] e = end.split("\\s");
        String[] eTime = e[1].split("\\:");

        int sH = Integer.valueOf(sTime[0]); // lay gio
        int sM = Integer.valueOf(sTime[1]); // lay phut

        int eH = Integer.valueOf(eTime[0]); // lay gio
        int eM = Integer.valueOf(eTime[1]); // lay phut

        float total = (eH - sH) + ((float) (eM - sM) / 60);

        return total;
    }
    
    public String formatDateTime(String dateTime) {
        
        if(dateTime == null) return "";
        if(dateTime.equals("")) return "";
        
        String ans = "";
        SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat spf2 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        try {
            long ms = spf.parse(dateTime).getTime();
            
            ans = spf2.format(ms);
            
        } catch (ParseException ex) {
            Logger.getLogger(CalendarHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ans;
    }
    
    public String formatDateTime(long ms) {
        SimpleDateFormat spf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        
        Date date = new Date(ms);
        
        return spf.format(date);
    }
    
    public long getMiliSecondTime(String dateTime){
        SimpleDateFormat spf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        
        long ms = 0;
        try {
            ms = spf.parse(dateTime).getTime();
        } catch (ParseException ex) {
            Logger.getLogger(CalendarHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return ms;
    }
    
    public static void main(String[] args) {
        
        CalendarHelper c = new CalendarHelper();
        long ms = c.getMiliSecondTime("2022/03/30 20:30:00");
        System.out.println(ms);
        System.out.println(c.formatDateTime(ms));
    }
}
