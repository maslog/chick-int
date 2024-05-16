/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chick_int_dtr_system.Components;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author Ronald
 */
public class DigitalClock {

    public String updateTime() {

        //Get the current time and format it
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat timeFormatter = new SimpleDateFormat("hh:mm:ss a");
        String time = timeFormatter.format(calendar.getTime());

        return time;

    }
    
    public String getTime() {

        //Get the current time and format it
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:MM:SS");
        String time = timeFormatter.format(calendar.getTime());

        return time;

    }

    public String updateDate() {
        //Get the current date and format it
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("EE, MMM dd, yyyy");
        String date = dateFormatter.format(calendar.getTime());

        return date;
    }
    public String getDate() {
        //Get the current date and format it
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("YYYY, MMM, DD");
        String date = dateFormatter.format(calendar.getTime());

        return date;
    }

}
