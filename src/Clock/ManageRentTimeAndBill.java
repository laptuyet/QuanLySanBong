/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clock;

import Utils.AutoID;
import Utils.CalendarHelper;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import quanlysanbong.controller.BillDAO;
import quanlysanbong.controller.CT_DichVuDAO;
import quanlysanbong.controller.CT_PhieuThueDAO;
import quanlysanbong.controller.KhachHangDAO;
import quanlysanbong.model.Bill;
import quanlysanbong.model.CT_DichVu;
import quanlysanbong.model.CT_PhieuThue;
import quanlysanbong.model.DichVu;
import quanlysanbong.model.HeSo;
import quanlysanbong.model.PhieuThue;
import quanylsanbong.view.AdminGUI;

/**
 *
 * @author tranh
 */
public class ManageRentTimeAndBill extends Thread {

    private ArrayList<PhieuThue> ptList;
    private ArrayList<CT_PhieuThue> ctptList;
    private ArrayList<Bill> billList;
    private ArrayList<DichVu> dvList;
    private ArrayList<HeSo> rateList;
    
    private CalendarHelper ch = new CalendarHelper();
    private CT_PhieuThueDAO ctptDao = new CT_PhieuThueDAO();
    private CT_DichVuDAO ctdvDao = new CT_DichVuDAO();
    private KhachHangDAO khDao = new KhachHangDAO();
    private BillDAO billDao = new BillDAO();
    
    MyClock clock = new MyClock();
    
    private AdminGUI adminGui;

    public ManageRentTimeAndBill() {

    }

    public ManageRentTimeAndBill(ArrayList<PhieuThue> ptList, ArrayList<Bill> billList, ArrayList<DichVu> dvList, ArrayList<HeSo> rateList) {
        this.ptList = ptList;
        this.billList = billList;
        this.dvList = dvList;
        this.rateList = rateList;
        start();

    }

    @Override
    public void run() {
        String msg = "";
        Calendar cal;
        Date date;
        long currTime, rentTime;
        PhieuThue pt;
        CT_PhieuThue item;
        String mahd;
        adminGui = new AdminGUI();

        while (true) {

            cal = Calendar.getInstance();
            date = cal.getTime();
            currTime = date.getTime();

            for (int j = 0; j < ptList.size(); j++) {
                pt = ptList.get(j);
                // Lay ra ctpt cua tung phieu
                ctptList = ctptDao.getOrderDetail(pt.getMapt());

                // Xu ly update rent time cua tung san trong ctpt
                for (int i = 0; i < ctptList.size(); i++) {
                    item = ctptList.get(i);
                    rentTime = ch.getMiliSecondTime(item.getGio_dukientra());
                    if (item.getGiotra().equals("") && currTime > rentTime) { // gio tra chua co thi moi update
                        item.setGiotra(item.getGio_dukientra());
                        ctptList.set(i, item);
                        updateLeaveTime(item);
                    }
                }
                // END xu ly tung san trong ctpt  

                // neu phieu thue nay chua co hoa don va tat ca san trong ctpt 
                // het gio thue thi tien hanh tao hoa don
                if ( !billDao.isOrderHasBill(pt.getMapt()) && adminGui.isAllOrderDetailExpired(ctptList)) {
                    mahd = new AutoID().getAutoHoaDonID(billList);
                    Bill bill = autoCreateBill(mahd, pt.getMapt(), pt.getManv(), pt.getMakh());
                    if(billDao.addBill(bill)) {
                        msg += ("+ New bill: " + mahd + "\nhas been created for Order: " + pt.getMapt() + "\n");
                        billList.add(bill);
                    } else {
                        System.out.println("Create bill for order : " + pt.getMapt() + " FAILED!");
                    }
                }
                // END tao hoa don
                
                ctptList.clear();
            }
            // END xu ly tung phieu thue
            
            if(!msg.equals(""))
                JOptionPane.showMessageDialog(adminGui, msg);
            
            try {
                Thread.sleep(10000); // 30s check 1 lan
            } catch (InterruptedException ex) {
                Logger.getLogger(MyClock.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void updateLeaveTime(CT_PhieuThue item) {
        try {
            ctptDao.updateLeaveTimeOfStadium(item);
        } catch (ParseException ex) {
            Logger.getLogger(ManageRentTimeAndBill.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public float getRate(String makhunggio) {
        for (HeSo hs : rateList) {
            if (hs.getMaKhungGio().equals(makhunggio)) {
                return hs.getHeSo();
            }
        }
        return -1;
    }

    public double getServicePrice(String madv) {
        for (DichVu item : dvList) {
            if (item.getMadv().equals(madv)) {
                return item.getDongia();
            }
        }
        return -1;
    }
    
    public String hasPreOrder(String mapt) {
        for(PhieuThue item : ptList)
            if(item.getMapt().equals(mapt)) // kiem tra phieu thue nao khop
                if(item.getMapd() != null) // kiem tra phieu thue do co phieu dat khong ?
                    return item.getMapd();
        return null;
    }

    public double getTotalBillWithoutMemDiscount(ArrayList<CT_PhieuThue> ctptList, ArrayList<CT_DichVu> ctdvList) {
        double totalBill = 0;
        float heso;

        for (CT_PhieuThue item : ctptList) {

            heso = getRate(item.getMakhunggio());
            totalBill += (item.getThanhtien() * heso);
        }

        for (CT_DichVu item : ctdvList) {
            totalBill += (getServicePrice(item.getMadv()) * item.getSoluong());
        }

        return totalBill;
    }

    public Bill autoCreateBill(String mahd, String mapt, String manv, String makh) {
        ArrayList<CT_PhieuThue> ctptList = ctptDao.getOrderDetail(mapt);
        ArrayList<CT_DichVu> ctdvList = ctdvDao.getOrderServices(mapt);

        double totalBill = getTotalBillWithoutMemDiscount(ctptList, ctdvList);

        float memberDiscount = khDao.getMemRateDiscount(makh);

        totalBill = totalBill * (1.0 - memberDiscount);

        Bill bill = new Bill(mahd, clock.getCurrentDateTime2(), totalBill, mapt, manv, makh);
        return bill;
    }
}
