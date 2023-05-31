package com.example.waiterapp.helpers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataAndTimeHelper {
    public static String getDate(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();

        String currentDate = dateFormat.format(date);

        return currentDate;
    }

    public static String getDay(){
        DateFormat dateFormat = new SimpleDateFormat("dd");
        Date date = new Date();

        String currentDay = dateFormat.format(date);

        return currentDay;
    }

    public static String getMonth(){
        DateFormat dateFormat = new SimpleDateFormat("MM");
        Date date = new Date();

        String currentMonth = dateFormat.format(date);

        return currentMonth;
    }

    public static String getYear(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy");
        Date date = new Date();

        String currentYear = dateFormat.format(date);

        return currentYear;
    }

    public static String getTime(){
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();

        String currentTime = dateFormat.format(date);

        return currentTime;
    }
}
