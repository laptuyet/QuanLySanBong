/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

/**
 *
 * @author tranh
 */
public class FoodHelper {

    public double formatMoney(String price) {
        double value;
        try {
            value = Double.parseDouble(price);

        } catch (NumberFormatException ex) {
            System.out.println("Money input is invalid\n" + ex.getMessage());
            return -9999;
        }
        return value;
    }
}
