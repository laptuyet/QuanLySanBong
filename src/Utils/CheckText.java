/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

/**
 *
 * @author tranh
 */
public class CheckText {
    
    private static final String cmndPattern = "^\\d{12}$";
    private static final String phonePattern = "^0{1}\\d{9,10}$";
    
    public boolean isPhoneNumber(String phone){
        if(phone.matches(phonePattern))
            return true;
        return false;
    }
    
    public boolean isCmnd(String cmnd){
        if(cmnd.matches(cmndPattern))
            return true;
        return false;
    }
}
