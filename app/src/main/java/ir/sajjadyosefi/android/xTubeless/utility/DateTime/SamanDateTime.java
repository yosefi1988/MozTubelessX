package ir.sajjadyosefi.android.xTubeless.utility.DateTime;



import androidx.annotation.NonNull;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import ir.sajjadyosefi.android.xTubeless.Global;
import ir.sajjadyosefi.android.xTubeless.classes.model.user.User;
import ir.sajjadyosefi.android.xTubeless.utility.SamanString;
import ir.sajjadyosefi.android.xTubeless.widget.samanPersianDatePicker.util.PersianCalendar2;

/**
 * Created by s.yousefi on 07/02/2018.
 */

public class SamanDateTime {

    public static Date getDateTime() {
        return Calendar.getInstance().getTime();
    }

    public static long getDateTimeAsLong() {
        return Calendar.getInstance().getTimeInMillis();
    }


    public static String getDateTime(int day ) {
        String dt = SamanDateTime.getYear() + "-" + SamanDateTime.getMonth() +"-" + SamanDateTime.getDay();

//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat time = new SimpleDateFormat("yyyy-MM-dd");


        Calendar c = Calendar.getInstance();
        try {
            c.setTime(time.parse(dt));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE, day);  // number of days to add

//        dt = sdf.format(c.getTime());  // dt is now the new date
        dt = time.format(c.getTime());  // dt is now the new date
        return SamanString.ArabicNumberToEnglishNumbers(dt);
    }


    public static int getYear() {
        Date date; // your date
        Calendar cal = Calendar.getInstance();
        cal.setTime(getDateTime());
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return year;
    }


    public static int getMonth() {
//        Date date = getDateTime(); // your date
//        date.getMonth();
        Calendar cal = Calendar.getInstance();
        cal.setTime(getDateTime());
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        cal.getTimeInMillis();

//
//        long tmp = cal.getTimeInMillis();
//        Date d = new Date(tmp);
//        PersianCalendar1.getPersianDate(d);

        return month + 1;
    }


    public static int getDay() {
        Date date; // your date
        Calendar cal = Calendar.getInstance();
        cal.setTime(getDateTime());
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return day;
    }



    public static Long getDateTimeInMillis() {
        return Calendar.getInstance().getTimeInMillis();
    }

    @NonNull
    public static PersianCalendar2 getPersianCalendar(User customer) {
        //                    DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss");
//        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss");
//
//        org.joda.time.SamanDateTime dateTime;
//        if (!customer.getBirthDay().equals(""))
//            dateTime = dateTimeFormatter.parseDateTime(customer.getBirthDay());
//        else
//            dateTime = dateTimeFormatter.parseDateTime("1989-02-08T00:00:00");


        PersianCalendar1 persianCalendar = new PersianCalendar1();

//                    JalaliCalendar jalaliDate = new JalaliCalendar(new GregorianCalendar(dateTime.getYear(),dateTime.getMonthOfYear(),dateTime.getDayOfMonth()));
//                    persianCalendar.setPersianDate(jalaliDate.getYear(),jalaliDate.getMonth(),jalaliDate.getDay());
//
//                    com.sb24.diamond.classes.Utility.PersianCalendar1 jalali2 = new com.sb24.diamond.classes.Utility.PersianCalendar1(new GregorianCalendar(dateTime.getYear(),dateTime.getMonthOfYear(),dateTime.getDayOfMonth()));

//        long tmp = 509414400000l;
//        Date d = new Date(tmp);
//        PersianCalendar1.getPersianDate(d);

//        long tmp = Long.parseLong(customer.getBirthDayUnix());
//        Date d = new Date(tmp);
//        String persianDate = com.sb24.diamond.classes.utility.DateTime.PersianCalendar1.getPersianDate(d);
//        int year = com.sb24.diamond.classes.utility.DateTime.PersianCalendar1.getPersianYear(d);
//        int month = com.sb24.diamond.classes.utility.DateTime.PersianCalendar1.getPersianMonth(d);
//        int day = com.sb24.diamond.classes.utility.DateTime.PersianCalendar1.getPersianDayOfMonth(d);
//

//
//        CalendarTool ct = new CalendarTool(dateTime.getYear(),dateTime.getMonthOfYear(),dateTime.getDayOfMonth());
//        System.out.println(ct.getPersianDate());
//
//        persianCalendar.setPersianDate(ct.getPersianYear(),ct.getPersianMonth(),ct.getPersianDay());
//        return persianCalendar;
        return null;
    }

    @NonNull
    public static String getPersianCalendar(Date dateDate) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(dateDate);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        CalendarTool ct = new CalendarTool(year,month,day);
        return ct.getPersianDate();
    }

    @NonNull
    public static PersianCalendar2 getGeorgianCalendar(User customer) {
//        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss");
//        org.joda.time.SamanDateTime dateTime = dateTimeFormatter.parseDateTime(customer.getBirthDay());
//        com.sb24.diamond.view.widget.samanPersianDatePicker.util.PersianCalendar1 persianCalendar = new com.sb24.diamond.view.widget.samanPersianDatePicker.util.PersianCalendar1();
//        persianCalendar.setPersianDate(dateTime.getYear(),dateTime.getMonthOfYear(),dateTime.getDayOfMonth());
//        return persianCalendar;
        return null;
    }

    @NonNull
    public static String getGeorgianCalendar(Date date) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        CalendarTool ct = new CalendarTool(year,month,day);
        return ct.getGregorianDate();
    }

    @NonNull
    public static Calendar getGeorgianCalendar() {
        Date d = SamanDateTime.getDateTime();
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return cal;
    }

    @NonNull
    public static Calendar getGeorgianCalendar(String date) {
        try {
            Date d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS").parse(date + ".000000");
            Calendar cal = Calendar.getInstance();
            cal.setTime(d);
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH);
            return cal;
        }catch (Exception ex){
            return null;
        }
    }


    public static Date dateBeforeXDays() {

        Calendar cal = Calendar.getInstance();
        cal.setTime(cal.getTime());
//        cal.add(Calendar.DATE, - Global.application.setting.getNotification().getClearDataAfterXDay());
        Date dateBeforeXDays = cal.getTime();

        return dateBeforeXDays;
    }
//    public static boolean isLimitValid() {
//        AppStatus appStatus = null;
//        InviteList<AppStatus> appStatusList = AppStatus.listAll(AppStatus.class);
//        if (appStatusList.size() == 1) {
//            appStatus = appStatusList.get(0);
//        }
//
//
//        if (appStatus != null && appStatus.InstallDate != null) {
//            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
//
//            Date d1 = null;
//            Date d2 = null;
//            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
//            String currentDateandTime = sdf.format(new Date());
//
//            try {
//                d1 = format.parse(appStatus.InstallDate);
//                d2 = format.parse(currentDateandTime);
//
//                //in milliseconds
//                long diff = d2.getTime() - d1.getTime();
//
//                long diffSeconds = diff / 1000 % 60;
//                long diffMinutes = diff / (60 * 1000) % 60;
//                long diffHours = diff / (60 * 60 * 1000) % 24;
//                long diffDays = diff / (24 * 60 * 60 * 1000);
//
//                System.out.print(diffDays + " days, ");
//                System.out.print(diffHours + " hours, ");
//                System.out.print(diffMinutes + " minutes, ");
//                System.out.print(diffSeconds + " seconds.");
//
//                if(diffDays <= 2) {
//                    return true;
//                }
//                else {
//                    //Save
//                    appStatus.buyStatus = "Null";
//                    //appStatus.buyStatus = "Limit";
//                    appStatus.save();
//                    //End Save
//                    return false;
//
//                }
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return  true;
//    }
}
