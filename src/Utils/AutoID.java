/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import java.util.ArrayList;
import quanlysanbong.model.PhieuDat;

/**
 *
 * @author tranh
 */
public class AutoID {

    public AutoID() {

    }

    public String getAutoPhieuDatID(ArrayList<PhieuDat> preOrderList) {

        preOrderList.sort((a, b) -> a.getMapd().compareTo(b.getMapd()));

        String lastId = preOrderList.get(preOrderList.size() - 1).getMapd();

        int stt = Integer.valueOf(lastId.substring(2)) + 1;

        String newId = "pd" + ((stt <= 9) ? "0" + stt : stt);
        return newId;
    }
}
