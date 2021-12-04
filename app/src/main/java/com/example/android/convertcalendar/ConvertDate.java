package com.example.android.convertcalendar;


import java.time.LocalDate;
import java.util.Date;

public class ConvertDate {

    public static int[] persianMonth = {31, 31, 31, 31, 31, 31, 30, 30, 30, 30, 30, 29};
    public static int[] persianMonthLeap = {31, 31, 31, 31, 31, 31, 30, 30, 30, 30, 30, 30};
    public static int[] gregorianMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    public static int[] gregorianMonthLeap = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
/*

    public static LocalDate PersianToGregorianCalendar(LocalDate persianDate) {

        //Calculate the year of Gregorian and day of year
        int gregorianYear = persianDate.getYear() + 621;
        int dayOfYear = persianDate.getDayOfYear();
        //Create a gregorian variable
        LocalDate gregorianDate = persianDate.withYear(gregorianYear);
        //Calculate day of 20 March
        LocalDate march = new LocalDate(0, 3, 20);
        int dayOfMarch = march.getDayOfYear();
        //Calculate that year is leap(366 days) or not
        boolean leapYear = (gregorianYear % 400 == 0) || (gregorianYear % 100 != 0 && gregorianYear % 4 == 0);
        if (leapYear) {
            if (dayOfYear < 12) {
                gregorianDate = new LocalDate(gregorianYear, 3, dayOfYear + 19);
            } else {
                dayOfYear -= 12;
                gregorianDate = gregorianDate.withDayOfYear(dayOfYear);
            }
        } else {
            if (dayOfYear < 11) {
                gregorianDate = new LocalDate(gregorianYear, 3, 20 + 11);
            } else {
//                dayOfYear -= 11;
                dayOfYear += dayOfMarch;
                gregorianDate = gregorianDate.withDayOfYear(dayOfYear);
            }

        }


        return gregorianDate;
    }

*/
    //Covert Persian Date to GregorianCalender
    public static Date PersianToGregorianCalender(Date persianDate) {
        //Calculate day of year and year of gregorian calendar
        int dayOfYear = DayOfPersianYear(persianDate);
        int gregorianYear = 621 + persianDate.getYear();


        //Create gregorian Date variable
        Date gregorianDate = new Date();
        //Calculate that year is leap(366 days) or not
        boolean leapYear = (gregorianYear % 400 == 0 && gregorianYear%100 == 0) || (gregorianYear % 100 != 0 && gregorianYear % 4 == 0);

        //Calculate Day of gregorian year at First Day of persian year
        int dayOf20March = 79;
        //Calculate Day of gregorian
        int dayOfGregorianYear = dayOfYear + dayOf20March;

        if (leapYear) {
            if (dayOfGregorianYear > 366) {
                gregorianYear++;
                dayOfGregorianYear -= 366;
            }
            int month = 1;
            for (int dayOfMonth :
                    gregorianMonthLeap) {
                if (dayOfGregorianYear <= dayOfMonth) {
                    break;
                } else {
                    dayOfGregorianYear -= dayOfMonth;
                    month++;
                }
            }
            gregorianDate.setYear(gregorianYear);
            gregorianDate.setMonth(month);
            gregorianDate.setDate(dayOfGregorianYear);
        } else {
            if (dayOfGregorianYear > 365) {
                gregorianYear++;
                dayOfGregorianYear -= 365;
            }
            int month = 1;
            for (int dayOfMonth :
                    gregorianMonth) {
                if (dayOfGregorianYear <= dayOfMonth) {
                    break;
                } else {
                    dayOfGregorianYear -= dayOfMonth;
                    month++;
                }
            }
            gregorianDate.setYear(gregorianYear);
            gregorianDate.setMonth(month);
            gregorianDate.setDate(dayOfGregorianYear);

        }

        return gregorianDate;
    }

    //Calculate day of persian year
    public static int DayOfPersianYear(Date date) {
        int dayOfYear = 0;
        if(date.getMonth() == 1)
            return date.getDate();
        if(date.getMonth() == 0)
            return 336 + date.getDate();
        for (int i = 0; i < date.getMonth()-1; i++) {
            dayOfYear += persianMonth[i];
        }
        dayOfYear += date.getDate();
        return dayOfYear;
    }

    //Calculate day of gregorian year
    public static int DayOfGregorianYear(Date date) {
        int dayOfYear = 0;
        //Leap or Common Year
        int year = date.getYear();
        boolean leapYear = (year % 400 == 0) || (year % 100 != 0 && year % 4 == 0);
        for (int i = 0; i < date.getMonth(); i++) {
            if (leapYear)
                dayOfYear += gregorianMonthLeap[i];
            else
                dayOfYear += gregorianMonth[i];
        }
        dayOfYear += date.getDay();
        return dayOfYear;
    }
}
