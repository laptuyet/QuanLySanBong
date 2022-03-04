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
        SimpleDateFormat spf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
        long start, end;
        try {
            start = spf.parse(sDate).getTime();
            end = spf.parse(eDate).getTime();
            return end >= start;
        } catch (ParseException ex) {
            Logger.getLogger(CalendarHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean isEndTimeGtStartTime(String sTime, String eTime) {

        SimpleDateFormat spf = new SimpleDateFormat("hh:mm:ss");
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
}
