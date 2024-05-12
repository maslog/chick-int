/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.chick_int_dtr_system;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.UIManager;

/**
 *
 * @author Ronald
 */
public class Chick_Int_DTR_System {

    public static void main(String[] args) {
        FlatLightLaf.setup();
        
        new UserUI().setVisible(true);
        
    }
}
