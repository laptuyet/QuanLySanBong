/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clock;

import Utils.CalendarHelper;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import quanlysanbong.controller.CT_PhieuThueDAO;
import quanlysanbong.model.CT_PhieuThue;
import quanlysanbong.model.PhieuThue;

/**
 *
 * @author tranh
 */
public class ManageRentTime extends Thread {

    private ArrayList<PhieuThue> ptList;
    private ArrayList<CT_PhieuThue> ctptList;
    private CalendarHelper ch = new CalendarHelper();
    private CT_PhieuThueDAO ctptDao = new CT_PhieuThueDAO();

    public ManageRentTime() {

    }

    public ManageRentTime(ArrayList<PhieuThue> ptList) {
        this.ptList = ptList;
        start();
        
    }

    @Override
    public void run() {

        Calendar cal;
        Date date;
        long currTime, rentTime;

        while (true) {

            cal = Calendar.getInstance();
            date = cal.getTime();
            currTime = date.getTime();

            for (int j = 0; j < ptList.size(); j++) {
                PhieuThue pt = ptList.get(j);
                // Lay ra ctpt cua tung phieu
                ctptList = ctptDao.getOrderDetail(pt.getMapt());

                
                // Xu ly update rent time cua tung san trong ctpt
                for (int i = 0; i < ctptList.size(); i++) {
                    CT_PhieuThue item = ctptList.get(i);
                    rentTime = ch.getMiliSecondTime(item.getGio_dukientra());
                    if (currTime > rentTime && item.getGiotra().equals("")) { // gio tra chua co thi moi update
                        item.setGiotra(item.getGio_dukientra());
                        ctptList.set(i, item);
                        updateLeaveTime(item);
                    }
                }
                // END xu ly tung san trong ctpt
                ctptList.clear();

            }
            // END xu ly tung phieu thue

            try {
                Thread.sleep(30000); // 30s check 1 lan
            } catch (InterruptedException ex) {
                Logger.getLogger(MyClock.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void updateLeaveTime(CT_PhieuThue item) {
        try {
            ctptDao.updateLeaveTimeOfStadium(item);
        } catch (ParseException ex) {
            Logger.getLogger(ManageRentTime.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
