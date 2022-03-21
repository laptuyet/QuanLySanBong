/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import java.util.ArrayList;
import quanlysanbong.model.PhieuDat;
import quanlysanbong.model.PhieuThue;

/**
 *
 * @author tranh
 */
public class AutoID {

    public AutoID() {

    }

    public String getAutoPhieuDatID(ArrayList<PhieuDat> preOrderList) {
        String newId = "";
        if (!preOrderList.isEmpty()) {
            preOrderList.sort((a, b) -> a.getMapd().compareTo(b.getMapd()));

            String lastId = preOrderList.get(preOrderList.size() - 1).getMapd();

            int stt = Integer.valueOf(lastId.substring(2)) + 1;

            newId = "pd" + ((stt <= 9) ? "0" + stt : stt);
        } else {
            newId = "pd01";
        }

        return newId;
    }

    public String getAutoPhieuThueID(ArrayList<PhieuThue> orderList) {
        String newId = "";
        if (!orderList.isEmpty()) {
            orderList.sort((a, b) -> a.getMapt().compareTo(b.getMapt()));

            String lastId = orderList.get(orderList.size() - 1).getMapt();

            int stt = Integer.valueOf(lastId.substring(2)) + 1;

            newId = "pt" + ((stt <= 9) ? "0" + stt : stt);
        } else {
            newId = "pt01";
        }

        return newId;
    }
}
